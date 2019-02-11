package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.util.Log
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
        Log.e(TAG, "ContentUriTriggerWorker do work")
        return Result.success()
    }

}