package com.example.quang.chartapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 1/11/2018.
 */

public class UserInfor extends AppCompatActivity {
    private Button btnSave;
    private TextView username_view;
    private TextView firstname_view;
    private TextView lastname_view;
    private TextView age_view;
    private TextView calorie_view;
    private TextView email_view;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String save;
    private String GET_USER_INFOR_URL = "http://18.218.77.52:3000/getuser";
    private String UPDATE_USER_INFOR_URL = "http://18.218.77.52:3000/updateuser";
    private RadioButton man;
    private RadioButton woman;
    private int age;
    private int sex;
    final Context context = this;
    private float calorie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);
        Intent getusername = getIntent();
        username = getusername.getStringExtra("UserName");
        Log.e("UserName:",username);
        btnSave = (Button) findViewById(R.id.btn_save);
        username_view = (TextView) findViewById(R.id.input_username);
        username_view.setEnabled(false);
        firstname_view = (TextView) findViewById(R.id.first_name);
        lastname_view = (TextView) findViewById(R.id.last_name);
        age_view = (TextView) findViewById(R.id.input_age);
        email_view = (TextView) findViewById(R.id.input_email);
        calorie_view = (TextView) findViewById(R.id.input_calorie);
        man = (RadioButton) findViewById(R.id.man_button);
        woman = (RadioButton) findViewById(R.id.woman_button);
//        GET_USER_INFOR_URL = GET_USER_INFOR_URL + "/" + username;
        new GetUserInforMethod().execute(GET_USER_INFOR_URL);
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                woman.setChecked(false);
                man.setChecked(true);
                sex = 1;
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                man.setChecked(false);
                woman.setChecked(true);
                sex = 0;
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_view.getText().toString();
                firstname = firstname_view.getText().toString();
                lastname = lastname_view.getText().toString();
                age = Integer.parseInt(age_view.getText().toString());
                calorie = Float.parseFloat(calorie_view.getText().toString());
                email = email_view.getText().toString();
                Log.e("Age:", String.valueOf(age));
                new UpdateUserInforMethod().execute(UPDATE_USER_INFOR_URL);
            }
        });
    }

    public class GetUserInforMethod extends AsyncTask<String , Void ,String> {
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
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("username", username);;
                String query = builder.build().getEncodedQuery();
                Log.e("Query:",query);
                byte[] postDataBytes = query.toString().getBytes("UTF-8");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(postDataBytes);
                StringBuilder sb = new StringBuilder();
                Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
                String response = sb.toString();
//                int responseCode = urlConnection.getResponseCode();
//                String response = urlConnection.getResponseMessage();
                server_response = response;
                Log.v("CatalogClient", server_response);
                save = "";
                save = save + server_response;
                JSONArray userinfor = new JSONArray(save);
                for (int i=userinfor.length()-1; i>=0; i--){
                    JSONObject obj = userinfor.getJSONObject(i);
                    if (i == userinfor.length()-1) {
                        firstname = obj.getString("FirstName");
                        lastname= obj.getString("LastName");
                        email = obj.getString("Email");
                        calorie= (float) obj.getDouble("CalorieNeed");
                        age= obj.getInt("Age");
                        sex=obj.getInt("Sex");
                        break;
                    }
                }

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
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            username_view.setText(username);
            firstname_view.setText(firstname);
            lastname_view.setText(lastname);
            age_view.setText(String.valueOf(age));
            email_view.setText(email);
            calorie_view.setText(String.valueOf(calorie));
            if (sex==1){
                man.setChecked(true);
            }
            if (sex==0){
                woman.setChecked(true);
            }
            Log.e("Response", "" + server_response);


        }
    }

    public class UpdateUserInforMethod extends AsyncTask<String , Void ,String> {
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
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("username", username)
                        .appendQueryParameter("firstname", firstname)
                        .appendQueryParameter("lastname", lastname)
                        .appendQueryParameter("email",email)
                        .appendQueryParameter("Age", String.valueOf(age))
                        .appendQueryParameter("calorieNeed", String.valueOf(calorie))
                        .appendQueryParameter("sex", String.valueOf(sex));;
                String query = builder.build().getEncodedQuery();
                Log.e("Query:",query);
                byte[] postDataBytes = query.toString().getBytes("UTF-8");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(postDataBytes);
                StringBuilder sb = new StringBuilder();
                Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
                String response = sb.toString();

                server_response = response;
//                Log.v("CatalogClient", server_response);


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

            Log.e("Response", "" + server_response);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("");

            // set dialog message
            alertDialogBuilder
                    .setMessage("User Information was saved")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

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
