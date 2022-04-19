package com.example.tema8acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor acelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cogemos el sensor acelerómetro por defecto
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        TextView nombreSensor = findViewById(R.id.textviewNombre);
        nombreSensor.setText(acelerometro.getName());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView valores = findViewById(R.id.textViewValores);
        float x,y,z;

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        String valoresString = "X: " + x + " | Y: " + y + " | Z: " + z;
        valores.setText(valoresString);
    }

    /**
     * El método onAccuracyChanged() es invocado cuando un sensor cambia su precisión
     * Parámetros:
     * - Sensor sensor: referencia al objeto de tipo Sensor que ha cambiado de precisión
     * - int accuracy: la nueva precisión del sensor
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,acelerometro, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}