import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Surface;

import androidx.appcompat.app.AppCompatActivity;

// collect sensor data in real time

// Use Sensor mock data to emu-late a Bluetoothsensor or use an Android sensor of your choice
//(https://developer.android.com/guide/topics/sensors/sensors_overview)
//▶Make sure to choose an appropriate data format
//▶See follow up slides for more detailed instructions


public class Sensor1 extends AppCompatActivity {

    private Sensor accel;
    private SensorManager sm;
    private SensorEventListener listener =
            new SensorEventListener() {
                @Override
                public void onAccuracyChanged(
                        Sensor sensor, int accuracy) {

                }
                @Override
                public void onSensorChanged(SensorEvent event){

                }


            };

    public void onCreate (Bundle savedInstanceState) {
        sm = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        if (sm == null) ; //...
        accel =
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accel == null) ; //...
    }
    @Override public void onResume() {
        super.onResume();
        sm.registerListener(listener, accel,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override public void onPause() {
        super.onPause();
        sm.unregisterListener(listener);

    }

    // Example ??

    public void onSensorChanged(SensorEvent event) {

        final float alpha = 0.8;

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];
    }


    private float[] remapToDisplayRotation(float[] inR){
        int h,v;
        float[] outR = new float[9];
        switch (display.getRotation()) {
            case Surface.ROTATION_90:
                h = SensorManager.AXIS_MINUS_X;
                v = SensorManager.AXIS_MINUS_Y;
                break;
            case Surface.ROTATION_270:
                h = SensorManager.AXIS_MINUS_Y;
                v = SensorManager.AXIS_X;
                break;
            case Surface.ROTATION_0:
                default:
            return inR;
        }
        SensorManager.remapCoordinateSystem(inR, h, v, outR);
        return outR;
    }


}
