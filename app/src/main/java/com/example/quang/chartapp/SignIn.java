package com.example.quang.chartapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by quang on 11/22/2017.
 */

public class SignIn extends Activity {
    private String save;
    private String save2;
    private Button btnLogin;
    private TextView Email_view;
    private TextView PassWord_view;
    private TextView Wrong_view;
    private TextView Sign_up;
    private String tempUserName;
    private String tempEmail;
    private String Email;
    private String PassWord;
    private int age=24;
    private String GET_LOGIN_URL= "http://18.218.77.52:3000/";
    private int check =0;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_in);
        btnLogin = (Button) findViewById(R.id.btn_login);
        Email_view = (TextView) findViewById(R.id.input_email);
        PassWord_view = (TextView) findViewById(R.id.input_password);
        Wrong_view = (TextView) findViewById(R.id.wrong_message);
        Sign_up = (TextView) findViewById(R.id.link_signup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = Email_view.getText().toString();
                PassWord= PassWord_view.getText().toString();
//                Log.v("Email Input:",Email);
//                Log.v("PassWord Input:",PassWord);
//                if (check == 1) {
//                    Intent intent = new Intent(SignIn.this, MainActivity.class);
//                    startActivity(intent);
//                }
                new GetLoginData().execute(GET_LOGIN_URL);
//                Log.e("After CLick: ",save2);

            }
        });
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SignIn.this, SignUp.class);
                startActivity(intent1);
            }
        });

    }
    public class GetLoginData extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
//                    Log.v("CatalogClient", server_response);
                    save = "";
                    save = save + server_response;
//                    Log.v("CatalogClient",save);
                    save2 = "";
                    save2 = save2 + save;
                    JSONArray logindata= new JSONArray(save);
                    for (int i=0; i<logindata.length();i++){
                        JSONObject obj = logindata.getJSONObject(i);
                        tempUserName = obj.getString("UserName");
                        tempEmail =obj.getString("Email");
                        int tempAge = obj.getInt("Age");
                        String tempPassWord = obj.getString("PassWord");
                        Log.e("UserName: ",tempUserName);
                        Log.e("Email: ",tempEmail);
                        if (tempUserName.equals(Email)  && tempPassWord.equals(PassWord)) {
                            Log.e("UserName: ",tempUserName);
                            age=tempAge;
                            Log.e("Age: ",String.valueOf(age));
                            check = 1;
                            break;
                        }
                        if (tempEmail.equals(Email)  && tempPassWord.equals(PassWord)) {
                            Log.e("UserName: ",tempUserName);
                            age=tempAge;
                            Log.e("Age: ",String.valueOf(age));
                            check = 1;
                            break;
                        }
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (check==0) {
                Wrong_view.setText("Wrong email or password");
            } else{
                Wrong_view.setText("");
                check = 0;
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                intent.putExtra("UserName",tempUserName);
                intent.putExtra("Age",age);
                startActivity(intent);
            }
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


