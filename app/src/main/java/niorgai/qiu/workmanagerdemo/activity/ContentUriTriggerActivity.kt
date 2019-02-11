package niorgai.qiu.workmanagerdemo.activity

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import niorgai.qiu.workmanagerdemo.R
import niorgai.qiu.workmanagerdemo.worker.ContentUriTriggerWorker

class ContentUriTriggerActivity : AppCompatActivity() {

    companion object {
        val CONTENT = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_uri_trigger)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            findViewById<View>(R.id.start).setOnClickListener {
                val constraint = Constraints.Builder()
                        .addContentUriTrigger(CONTENT, true)
                        .build()
                val oneTimeWorker = OneTimeWorkRequest.Builder(ContentUriTriggerWorker::class.java)
                        .setConstraints(constraint)
                        .build()
                WorkManager.getInstance().enqueue(oneTimeWorker)
            }
        }
    }
}
