package com.example.quang.chartapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 1/9/2018.
 */

public class SignUp extends AppCompatActivity {
    private String GET_LOGIN_URL = "http://18.218.77.52:3000/adduser";
    private Button btnSignUp;
    private TextView UserName_view;
    private TextView Email_view;
    private TextView PassWord_view;
    private TextView Confirm_view;
    private TextView Wrong_view;
    private String UserName;
    private String Email;
    private String PassWord;
    private String Confirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        btnSignUp= (Button)  findViewById(R.id.btn_signup);
        UserName_view = (TextView) findViewById(R.id.input_username);
        Email_view = (TextView) findViewById(R.id.input_email);
        PassWord_view = (TextView) findViewById(R.id.input_password);
        Confirm_view = (TextView) findViewById(R.id.input_confirm);
        Wrong_view = (TextView) findViewById(R.id.wrong_message);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserName = UserName_view.getText().toString();
                Email =  Email_view.getText().toString();
                PassWord = PassWord_view.getText().toString();
                Confirm = Confirm_view.getText().toString();
                if (Confirm.equals(PassWord)){
                    Wrong_view.setText("Please Confirm Your Password Again");
                } else {
                    Wrong_view.setText("");
                    new SignUpMethod().execute(GET_LOGIN_URL);
                }

            }
        });
    }
    public class SignUpMethod extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                Log.e("URL:",url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", UserName)
                        .appendQueryParameter("email", Email)
                        .appendQueryParameter("password", PassWord);
                String query = builder.build().getEncodedQuery();
                byte[] postDataBytes = query.toString().getBytes("UTF-8");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(postDataBytes);
                StringBuilder sb = new StringBuilder();
                Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
                String response = sb.toString();
//                Log.e("Response:",response);
//                OutputStream os = urlConnection.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                Log.e("Query:",query);
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();

//                urlConnection.connect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Log.e("Response", "" + server_response);


        }
    }

// Converting InputStream to String

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
