package com.example.quang.chartapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
//import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private String save;
    private double height_value=0;
    private double weight_value=0;
    private double heart_value=0;
    private double spo2_value=0;
    private double temp_value=0;
    private double max_heart_value = 0;
    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 16.89f, 23.9f};
    private String[] xData = {"Mike", "Jessica", "Mohammad", "Kesley", "Sam", "Robert", "Ashley"};
//    PieChart pieChart;
    private ImageButton btnClick;
    private ImageButton sensorClick;
    private ImageButton bluetoothClick;
    private ImageButton userinforClick;
    private ImageButton heartpredictClick;
    private TextView Height;
    private TextView Weight;
    private TextView Spo2;
    private TextView Temp;
    private TextView Bloodpressure;
    private TextView HeartRate;
    private TextView Calories_view;
    private TextView MaxHeart_view;
    private Double Calories;
    private float timewalk;
    private String username;
    private int age;
    private int sex;
    final Context context = this;
    private String GET_RESULT_URL = "http://18.218.77.52:3000/getresult";
    private String GET_RESULT_WITH_NAME_URL = "http://18.218.77.52:3000/list";
    private String GET_HEART_PREDICTION_URL= "http://18.218.77.52:3000/heartprediction";

    public MainActivity() {
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calories = (double) 0;
        timewalk = (float) 0;
        Intent getfromsensor = getIntent();
        Calories= getfromsensor.getDoubleExtra("Calories",0.00);
        timewalk= getfromsensor.getFloatExtra("Time Walk",0);
        height_value= getfromsensor.getDoubleExtra("Height",0.00);
        weight_value= getfromsensor.getDoubleExtra("Weight",0.00);
        heart_value= getfromsensor.getDoubleExtra("Heart Rate",0.00);
        temp_value= getfromsensor.getDoubleExtra("Temp",0.00);
        spo2_value= getfromsensor.getDoubleExtra("Spo2",0.00);
        username = getfromsensor.getStringExtra("UserName");
        age=getfromsensor.getIntExtra("Age",24);
        sex=getfromsensor.getIntExtra("Sex",1);
        Height = (TextView) findViewById(R.id.height_view);
        Weight = (TextView) findViewById(R.id.weight_view);
        HeartRate = (TextView) findViewById(R.id.heart_view);
        Spo2 = (TextView) findViewById(R.id.spo2_view);
        Temp = (TextView) findViewById(R.id.temp_view);
        Bloodpressure = (TextView) findViewById(R.id.blood_view);
        Calories_view = (TextView) findViewById(R.id.calories_view);
        MaxHeart_view = (TextView) findViewById(R.id.max_heart_view);

        Calories_view.setText(Double.toString((double) (Math.round(Calories*100))/100));
        Height.setText(Double.toString(height_value));
        Weight.setText(Double.toString(weight_value));
        Temp.setText(Double.toString(temp_value));
        Spo2.setText(Double.toString(spo2_value));
        HeartRate.setText(Double.toString(heart_value));
        MaxHeart_view.setText(Double.toString(max_heart_value));

        btnClick = (ImageButton) findViewById(R.id.get_data);
        sensorClick = (ImageButton) findViewById(R.id.sensor_change);
        bluetoothClick = (ImageButton) findViewById(R.id.connect_bluetooth);
        userinforClick = (ImageButton) findViewById(R.id.user_infor);
        heartpredictClick = (ImageButton) findViewById(R.id.heart_predict);
        heartpredictClick.setClickable(false);
        heartpredictClick.setEnabled(false);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetMethodDemo().execute(GET_RESULT_WITH_NAME_URL);

            }
        });
        sensorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorClick.setBackgroundColor(0xF5F5F5);
                Intent intent = new Intent(MainActivity.this, ActivitySensor.class);
//                Intent intent = new Intent("com.example.quang.chartapp.ActivitySensor");
                intent.putExtra("Height",height_value);
                intent.putExtra("Weight",weight_value);
                intent.putExtra("HearRate",heart_value);
                intent.putExtra("Spo2",spo2_value);
                intent.putExtra("Temp",temp_value);
//                Log.e("Calories:",String.valueOf(Calories));
                intent.putExtra("Calories",Calories);
                intent.putExtra("Time Walk",timewalk);
                intent.putExtra("Age",age);
                intent.putExtra("Sex",sex);
                intent.putExtra("UserName",username);
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, ActivitySensor.class));;
            }
        });

        bluetoothClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothClick.setBackgroundColor(0xF5F5F5);
                Intent intent2 = new Intent(MainActivity.this, Bluetooth_test.class);
                intent2.putExtra("UserName",username);
                startActivity(intent2);
            }
        });

        userinforClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent senduserinfor = new Intent(MainActivity.this,UserInfor.class);
                senduserinfor.putExtra("UserName",username);
                startActivity(senduserinfor);
            }
        });

        heartpredictClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new HeartPredictMethodTest().execute(GET_HEART_PREDICTION_URL);
            }
        });

//        Log.d(TAG, "onCreate: starting to creat chart");
//        pieChart = (PieChart) findViewById(R.id.idPieChart);
//
//        pieChart.setRotationEnabled(true);
//        pieChart.setHoleRadius(16);
//        pieChart.setTransparentCircleAlpha(0);
//        pieChart.setCenterText("Test");
//        pieChart.setCenterTextSize(10);
//        pieChart.setDrawEntryLabels(true);


    }
    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", username);
                String query = builder.build().getEncodedQuery();
                byte[] postDataBytes = query.toString().getBytes("UTF-8");
                urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(postDataBytes);
                StringBuilder sb = new StringBuilder();
                Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
                String server_response = sb.toString();
//                urlConnection = (HttpURLConnection) url.openConnection();
//
//                int responseCode = urlConnection.getResponseCode();

                    Log.v("CatalogClient", server_response);
                    save = "";
                    save = save + server_response;
                    JSONArray healthdata = new JSONArray(save);
//                    Log.v("CatalogClient", healthdata.toString());
                    int i;
                    for(i=0; i<healthdata.length(); i++){
                        JSONObject obj = healthdata.getJSONObject(i);
                        Double temp_heart = obj.getDouble("HeartRate");
                        if (max_heart_value < temp_heart){
                            max_heart_value = temp_heart;
                        }
                        if (i == healthdata.length()-1) {
                            height_value = obj.getDouble("Height");
                            weight_value = obj.getDouble("Weight");
                            spo2_value = obj.getDouble("Spo2");
                            heart_value = obj.getDouble("HeartRate");
                            temp_value = obj.getDouble("Temp");
                            break;
                        }
                    }
                } catch (ProtocolException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Log.e("Response", "" + server_response);
            Height.setText(Double.toString(height_value));
            Weight.setText(Double.toString(weight_value));
            Temp.setText(Double.toString(temp_value));
            Spo2.setText(Double.toString(spo2_value));
            HeartRate.setText(Double.toString(heart_value));
            MaxHeart_view.setText(Double.toString(max_heart_value));
            heartpredictClick.setClickable(true);
            heartpredictClick.setEnabled(true);
        }
    }

    public class HeartPredictMethodTest extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
//                Log.e("URL:",url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", username)
                        .appendQueryParameter("heartrate", String.valueOf(heart_value));
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
                server_response = response;
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
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set title
            alertDialogBuilder.setTitle("");

            // set dialog message
            alertDialogBuilder
                    .setMessage(server_response)
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
