package com.example.quang.chartapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private String save;
    private double height_value;
    private double weight_value;
    private double heart_value;
    private double spo2_value;
    private double temp_value;

    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 16.89f, 23.9f};
    private String[] xData = {"Mike", "Jessica", "Mohammad", "Kesley", "Sam", "Robert", "Ashley"};
//    PieChart pieChart;
    private Button btnClick;
    private Button sensorClick;
    private Button bluetoothClick;
    private TextView Height;
    private TextView Weight;
    private TextView Spo2;
    private TextView Temp;
    private TextView Bloodpressure;
    private TextView HeartRate;
    private String GET_RESULT_URL = "http://18.218.77.52:3000/getresult";
    public MainActivity() {
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Height = (TextView) findViewById(R.id.height_view);
        Weight = (TextView) findViewById(R.id.weight_view);
        HeartRate = (TextView) findViewById(R.id.heart_view);
        Spo2 = (TextView) findViewById(R.id.spo2_view);
        Temp = (TextView) findViewById(R.id.temp_view);
        Bloodpressure = (TextView) findViewById(R.id.blood_view);
        btnClick = (Button) findViewById(R.id.get_data);
        sensorClick = (Button) findViewById(R.id.sensor_change);
        bluetoothClick = (Button) findViewById(R.id.connect_bluetooth);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetMethodDemo().execute(GET_RESULT_URL);

            }
        });
        sensorClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySensor.class);
//                Intent intent = new Intent("com.example.quang.chartapp.ActivitySensor");
                intent.putExtra("Height",height_value);
                intent.putExtra("Weight",weight_value);
                intent.putExtra("HearRate",heart_value);
                startActivity(intent);
                //startActivity(new Intent(MainActivity.this, ActivitySensor.class));;
            }
        });

        bluetoothClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Bluetooth_test.class);
                startActivity(intent2);
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

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                    save = "";
                    save = save + server_response;
                    JSONArray healthdata = new JSONArray(save);
                    Log.v("CatalogClient", healthdata.toString());
                    for (int i=healthdata.length()-1; i>0; i--){
                        JSONObject obj = healthdata.getJSONObject(i);
                        if (i == healthdata.length()-1) {
                            height_value = obj.getDouble("Height");
                            weight_value = obj.getDouble("Weight");
                            spo2_value = obj.getDouble("Spo2");
                            heart_value = obj.getDouble("HeartRate");
                            temp_value = obj.getDouble("Temp");
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

            Log.e("Response", "" + server_response);
            Height.setText(Double.toString(height_value));
            Weight.setText(Double.toString(weight_value));
            Temp.setText(Double.toString(temp_value));
            Spo2.setText(Double.toString(spo2_value));
            HeartRate.setText(Double.toString(heart_value));

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
