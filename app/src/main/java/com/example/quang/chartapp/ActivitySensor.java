package com.example.quang.chartapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Context;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySensor extends AppCompatActivity implements SensorEventListener, TextToSpeech.OnInitListener {

    private static final int N_SAMPLES = 200;
    private static List<Float> x;
    private static List<Float> y;
    private static List<Float> z;
    private TextView downstairsTextView;
    private TextView joggingTextView;
    private TextView sittingTextView;
    private TextView standingTextView;
    private TextView upstairsTextView;
    private TextView walkingTextView;
    private TextView timewalkTextView;
    private TextView Activity;
    private TextToSpeech textToSpeech;
    private ImageButton btnClick;
    private ImageButton userClick;
    private ImageButton bluetoothClick;
    private double height;
    private double weight;
    private double heartrate;
    private double spo2;
    private double temp;
    private double calories;
    private int age=23;
    private float[] results;
    private float timewalk=0;
    private float timesit=0;
    private float timejog=0;
    private float timestan=0;
    private float timeupstair=0;
    private float timedownstair=0;
    private float walkingtime = 0;
    final Context context = this;
    private TensorFlowClassifier classifier;

    private String[] labels = {"Downstairs", "Jogging", "Sitting", "Standing", "Upstairs", "Walking"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);
        x = new ArrayList<>();
        y = new ArrayList<>();
        z = new ArrayList<>();
        Intent intent = getIntent();
        age= intent.getIntExtra("Age",24);
        height = intent.getDoubleExtra("Height",0.00);
        weight = intent.getDoubleExtra("Weight",0.00);
        heartrate = intent.getDoubleExtra("HeartRate",0.00);
        spo2 = intent.getDoubleExtra("Spo2",0.00);
        temp = intent.getDoubleExtra("Temp",0.00);
        timewalk = intent.getFloatExtra("Time Walk", 0);
        calories = intent.getDoubleExtra("Calories",0.00);
//        Log.e("Time Walk:", String.valueOf(timewalk));
//        Log.e("Calories:", String.valueOf(calories));

        downstairsTextView = (TextView) findViewById(R.id.downstairs_prob);
        joggingTextView = (TextView) findViewById(R.id.jogging_prob);
//        joggingTextView.setText(Float.toString(round(results[1], 2)));
        sittingTextView = (TextView) findViewById(R.id.sitting_prob);
        standingTextView = (TextView) findViewById(R.id.standing_prob);
        upstairsTextView = (TextView) findViewById(R.id.upstairs_prob);
        walkingTextView = (TextView) findViewById(R.id.walking_prob);
        timewalkTextView = (TextView) findViewById(R.id.textView);
        btnClick = (ImageButton) findViewById(R.id.get_data);
        userClick = (ImageButton) findViewById(R.id.user_infor);
        bluetoothClick = (ImageButton) findViewById(R.id.connect_bluetooth);

        downstairsTextView.setText(Float.toString(round(timedownstair, 2)));
        joggingTextView.setText(Float.toString(round(timejog, 2)));
        sittingTextView.setText(Float.toString(round(timesit, 2)));
        standingTextView.setText(Float.toString(round(timestan, 2)));
        upstairsTextView.setText(Float.toString(round(timeupstair, 2)));
        Activity = (TextView) findViewById((R.id.title));
        Activity.setText(Float.toString(round((float) calories,2)));
        timewalkTextView.setText(Float.toString(timewalk));
        classifier = new TensorFlowClassifier(getApplicationContext());

        textToSpeech = new TextToSpeech(this, this);
        textToSpeech.setLanguage(Locale.US);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySensor.this, MainActivity.class);
                intent.putExtra("Calories",calories);
                intent.putExtra("Time Walk",timewalk);
                intent.putExtra("Height",height);
                intent.putExtra("Weight",weight);
                intent.putExtra("Spo2",spo2);
                intent.putExtra("Temp",temp);
                intent.putExtra("Heart Rate",heartrate);
                startActivity(intent);
            }
        });

        bluetoothClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothClick.setBackgroundColor(0xF5F5F5);
                Intent intent2 = new Intent(ActivitySensor.this, Bluetooth_test.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onInit(int status) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                if (results == null || results.length == 0) {
                    return;
                }
                float max = -1;
                int idx = -1;
                for (int i = 0; i < results.length; i++) {
                    if (results[i] > max) {
                        idx = i;
                        max = results[i];
                    }
                }
                switch (idx) {
                    case 0: timedownstair = timedownstair + 5;
                            timewalk = timewalk + 5;
                        break;
                    case 1: timejog = timejog + 5;
                            timewalk = timewalk + 5;
                        break;
                    case 2: timesit = timesit + 5;
                        break;
                    case 3: timestan = timestan + 5;
                        break;
                    case 4: timeupstair = timeupstair + 5;
                            timewalk = timewalk + 5;
                        break;
                    case 5: walkingtime =walkingtime + 5;
                        timewalk = timewalk + 5;
                        break;
                }
//                textToSpeech.speak(labels[idx], TextToSpeech.QUEUE_ADD, null, Integer.toString(new Random().nextInt()));
            }
        }, 2000, 5000);
    }

    protected void onPause() {
        getSensorManager().unregisterListener(this);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        getSensorManager().registerListener(this, getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        activityPrediction();
        x.add(event.values[0]);
        y.add(event.values[1]);
        z.add(event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void activityPrediction() {
        if (x.size() == N_SAMPLES && y.size() == N_SAMPLES && z.size() == N_SAMPLES) {
            List<Float> data = new ArrayList<>();
            data.addAll(x);
            data.addAll(y);
            data.addAll(z);

            results = classifier.predictProbabilities(toFloatArray(data));

//            downstairsTextView.setText(Float.toString(round(results[0], 2)));
//            joggingTextView.setText(Float.toString(round(results[1], 2)));
//            sittingTextView.setText(Float.toString(round(results[2], 2)));
//            standingTextView.setText(Float.toString(round(results[3], 2)));
//            upstairsTextView.setText(Float.toString(round(results[4], 2)));
//            walkingTextView.setText(Float.toString(round(results[5], 2)));
            downstairsTextView.setText(Float.toString(round(timedownstair, 2)));
            joggingTextView.setText(Float.toString(round(timejog, 2)));
            sittingTextView.setText(Float.toString(round(timesit, 2)));
            standingTextView.setText(Float.toString(round(timestan, 2)));
            upstairsTextView.setText(Float.toString(round(timeupstair, 2)));
            walkingTextView.setText(Float.toString(round(walkingtime, 2)));
            timewalkTextView.setText(Float.toString(round(timewalk,2)));
            double timeactivity = timewalk / 3600;
            double bmr = (9.99 * weight) + (6.25 * height) - (4.92*age) +5;
            calories = bmr * 3.5/24 * timeactivity;
            Activity.setText(Float.toString(round((float) calories,2)));
            if (timesit >30) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Need to walk around!!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
            x.clear();
            y.clear();
            z.clear();
        }
    }

    private float[] toFloatArray(List<Float> list) {
        int i = 0;
        float[] array = new float[list.size()];

        for (Float f : list) {
            array[i++] = (f != null ? f : Float.NaN);
        }
        return array;
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private SensorManager getSensorManager() {
        return (SensorManager) getSystemService(SENSOR_SERVICE);
    }

}

