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
class TestWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    companion object {
        val TAG = TestWorker::class.java.simpleName.toString()
    }

    override fun doWork(): Result {
        Log.e(TAG, "TestWorker doWork")
        Looper.prepare()
        Toast.makeText(applicationContext, "TestWorker doWork", Toast.LENGTH_SHORT).show()
        Looper.loop()
        return Result.success()
    }
}