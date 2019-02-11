package niorgai.qiu.workmanagerdemo.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import niorgai.qiu.workmanagerdemo.R
import niorgai.qiu.workmanagerdemo.worker.TestWorker

class OneTImeWorkerActivity : AppCompatActivity() {

    private lateinit var networkGroup: RadioGroup
    private lateinit var charging: Switch
    private lateinit var storage: Switch
    private lateinit var battery: Switch
    private lateinit var idle: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_time_worker)

        networkGroup = findViewById(R.id.network)
        charging = findViewById(R.id.charging)
        storage = findViewById(R.id.storage)
        battery = findViewById(R.id.battery)
        idle = findViewById(R.id.idle)

        findViewById<View>(R.id.start).setOnClickListener {
            startWorker()
        }
    }

    private fun startWorker() {
        val oneTimeWorker = OneTimeWorkRequest.Builder(TestWorker::class.java)
                .setConstraints(getWorkerConstraints())
                .build()
        WorkManager.getInstance().enqueue(oneTimeWorker)
    }

    private fun getWorkerConstraints(): Constraints {
        val constraints = Constraints.Builder()
        when (networkGroup.checkedRadioButtonId) {
            R.id.connected -> constraints.setRequiredNetworkType(NetworkType.CONNECTED)
            R.id.metered -> constraints.setRequiredNetworkType(NetworkType.METERED)
            R.id.not_required -> constraints.setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            R.id.not_roaming -> constraints.setRequiredNetworkType(NetworkType.NOT_ROAMING)
            R.id.unmetered -> constraints.setRequiredNetworkType(NetworkType.UNMETERED)
        }
        constraints.setRequiresCharging(charging.isChecked)
        constraints.setRequiresStorageNotLow(storage.isChecked)
        constraints.setRequiresBatteryNotLow(battery.isChecked)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraints.setRequiresDeviceIdle(idle.isChecked)
        }
        return constraints.build()
    }
}
