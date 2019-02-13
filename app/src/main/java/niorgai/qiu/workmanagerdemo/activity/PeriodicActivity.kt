package niorgai.qiu.workmanagerdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import niorgai.qiu.workmanagerdemo.R
import niorgai.qiu.workmanagerdemo.worker.PeriodicWorker
import java.util.concurrent.TimeUnit

class PeriodicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodic)

        val repeat = findViewById<EditText>(R.id.repeat_interval)
        val flex = findViewById<EditText>(R.id.flex_interval)

        findViewById<View>(R.id.start).setOnClickListener {

            var repeatInterval = 0L
            var flexInterval = 0L

            try {
                repeatInterval = repeat.text.toString().toLong()
                flexInterval = flex.text.toString().toLong()
            } catch (e: Exception) {

            }

            WorkManager.getInstance().cancelAllWork()
            val worker = PeriodicWorkRequest.Builder(PeriodicWorker::class.java, repeatInterval, TimeUnit.MINUTES, flexInterval, TimeUnit.MINUTES)
                    .build()
            WorkManager.getInstance().enqueue(worker)
        }
    }
}
