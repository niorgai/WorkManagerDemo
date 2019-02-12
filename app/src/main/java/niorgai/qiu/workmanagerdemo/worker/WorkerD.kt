package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by jianqiu on 2/10/19.
 */
class WorkerD(context: Context, params: WorkerParameters): Worker(context, params) {

    companion object {
        val TAG = WorkerD::class.java.simpleName.toString()
    }

    override fun doWork(): Result {
        Log.e(TAG, "WorkerD doWork")
        return Result.success()
    }
}