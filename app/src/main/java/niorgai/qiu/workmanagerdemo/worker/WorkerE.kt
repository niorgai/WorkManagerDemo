package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by jianqiu on 2/10/19.
 */
class WorkerE(context: Context, params: WorkerParameters): Worker(context, params) {

    companion object {
        val TAG = WorkerE::class.java.simpleName.toString()
    }

    override fun doWork(): Result {
        Log.e(TAG, "WorkerE doWork")
        return Result.success()
    }
}