package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by jianqiu on 2/10/19.
 */
class WorkerA(context: Context, params: WorkerParameters): Worker(context, params) {

    companion object {
        val TAG = WorkerA::class.java.simpleName.toString()
    }

    override fun doWork(): Result {
        Log.e(TAG, "WorkerA doWork")
        return Result.success()
    }
}