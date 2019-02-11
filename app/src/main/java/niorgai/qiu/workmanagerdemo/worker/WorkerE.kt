package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
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
        Looper.prepare()
        Toast.makeText(applicationContext, "WorkerE doWork", Toast.LENGTH_SHORT).show()
        Looper.loop()
        return Result.success()
    }
}