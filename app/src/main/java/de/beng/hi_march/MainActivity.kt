package de.beng.hi_march

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.main.*
import java.time.Instant
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var tv_x: TextView
    private lateinit var tv_y: TextView
    private lateinit var tv_z: TextView
    private var x_acc = 0f
    private var y_acc = 0f
    private var z_acc = 0f

//    private lateinit var waves: List<>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        tv_x = layout_x_acc
        tv_y = layout_y_acc
        tv_z = layout_z_acc
    }

    override fun onResume() {
        super.onResume()
//        tv_x.setText("5")
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            x_acc = event.values[0]
            y_acc = event.values[1]
            z_acc = event.values[2]
            tv_x.setText(x_acc.toString())
            tv_y.setText(y_acc.toString())
            tv_z.setText(z_acc.toString())

            val t = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            val wave = listOf(t, x_acc, y_acc, z_acc)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
}
