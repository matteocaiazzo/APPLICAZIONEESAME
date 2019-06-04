package com.androstock.myweatherapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ContapassiActivity extends Activity {

    private static final String TAG = "ContapassiActivity";

    private SensorManager sensorManager;
    private Sensor sensor;

    // numero di passi oggi
    private int todayStepsCount = 0;
    // numero di passi fino a mezzanotte
    private int todayOffset = -1;

    private long previousStepTimeStamp = 0;

    private TextView numberOfStepsView;
    private TextView numberOfTotalStepsView;

    SharedPreferences settings;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contapassi);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (sensor != null) {
            sensorManager.registerListener(mySensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
            Log.i("ContapassiActivity", "Registerered for stepcounter Sensor");

        } else {
            Log.e("ContapassiActivity", "Registerered for Stepcounter Sensor");
            Toast.makeText(this, "stepcounter Sensor not found",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        // recuperiamo i dati dalle shared preferences
        settings = getSharedPreferences("myapp", MODE_PRIVATE);
        todayOffset = settings.getInt("todayOffset", -1);
        todayStepsCount = settings.getInt("TodayStepsCount", -1);
        previousStepTimeStamp = settings.getLong("previousStepTimeStamp", -1);

        numberOfStepsView = (TextView) findViewById(R.id.numberOfSteps);
        numberOfTotalStepsView = (TextView) findViewById(R.id.numberTotalSteps);
        numberOfStepsView.setText(" " + todayStepsCount);

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences("myapp", 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("todayOffset", todayOffset);
        editor.putInt("todayStepsCount", todayStepsCount);
        editor.putLong("previousStepTimeStamp", previousStepTimeStamp);

        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensor != null) {
            sensorManager.unregisterListener(mySensorEventListener);
        }
    }

    private SensorEventListener mySensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            SimpleDateFormat format1 = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Calendar actualTimeStamp = Calendar.getInstance();
            Log.d(TAG, "trigger evento");
            if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

                Calendar currentTimeStamp = Calendar.getInstance();
                currentTimeStamp.setTimeInMillis(event.timestamp / 1000000);

                int currentStepCount = (int) event.values[0];

                if (currentStepCount == 0) {
                    /*
                     * abbiamo appena avuto un reboot oppure si parte da 0
                     */
                    todayOffset = 0;
                    previousStepTimeStamp = currentTimeStamp.getTimeInMillis();
                }

                Log.d(TAG, "new stepcount: " + currentStepCount);
                /* controlliamo se è cambiato giorno */
                Calendar current = Calendar.getInstance();
                current.setTime(actualTimeStamp.getTime());
                current.set(Calendar.HOUR_OF_DAY, 0);
                current.set(Calendar.MINUTE, 0);
                current.set(Calendar.SECOND, 0);
                current.set(Calendar.MILLISECOND, 0);

                Calendar previous = Calendar.getInstance();
                previous.setTimeInMillis(previousStepTimeStamp);
                previous.set(Calendar.HOUR_OF_DAY, 0);
                previous.set(Calendar.MINUTE, 0);
                previous.set(Calendar.SECOND, 0);
                previous.set(Calendar.MILLISECOND, 0);

                if (current.after(previous)) {
                    Log.d(TAG, "new day: " + format1.format(current.getTime())
                            + "\n" + format1.format(previous.getTime()));

                    todayOffset = (int) event.values[0];
                    todayStepsCount = 0;

                }
                todayStepsCount = currentStepCount - todayOffset;

                previousStepTimeStamp = actualTimeStamp.getTimeInMillis();

                numberOfStepsView.setText(" " + todayStepsCount);
                numberOfTotalStepsView.setText(" " + currentStepCount);

                /* fine controlliamo se è cambiato giorno */

            }

        }
    };

}