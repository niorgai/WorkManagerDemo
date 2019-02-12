package niorgai.qiu.workmanagerdemo.worker

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by jianqiu on 2/12/19.
 */
class PlusWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val first = inputData.getInt("KEY_FIRST", 0)
        val second = inputData.getInt("KEY_SECOND", 0)
        val result = first + second
        val output = Data.Builder()
                .putInt("KEY_RESULT", result)
                .build()
        return Result.success(output)
    }
}
