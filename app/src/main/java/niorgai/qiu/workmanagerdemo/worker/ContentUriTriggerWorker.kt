package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.os.Build
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by jianqiu on 2/11/19.
 */
class ContentUriTriggerWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    companion object {
        val TAG = ContentUriTriggerWorker::class.java.simpleName
    }

    override fun doWork(): Result {
        Log.e(TAG, "ContentUriTriggerWorker do work: ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            triggeredContentAuthorities.forEach {
                Log.e(TAG, "authorities: $it")
            }
            triggeredContentUris.forEach {
                Log.e(TAG, "uris: $it")
            }
        }
        Looper.prepare()
        Toast.makeText(applicationContext, "ContentUriTriggerWorker do work", Toast.LENGTH_SHORT).show()
        Looper.loop()
        return Result.success()
    }

}