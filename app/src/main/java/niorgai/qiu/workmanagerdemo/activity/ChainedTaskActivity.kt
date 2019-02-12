package niorgai.qiu.workmanagerdemo.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import niorgai.qiu.workmanagerdemo.R
import niorgai.qiu.workmanagerdemo.worker.*
import java.util.*

class ChainedTaskActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chained_task)

        findViewById<View>(R.id.first).setOnClickListener(this)
        findViewById<View>(R.id.second).setOnClickListener(this)
        findViewById<View>(R.id.third).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.first -> firstChain()
            R.id.second -> secondChain()
            R.id.third -> thirdChain()
        }
    }

    private fun firstChain() {
        WorkManager.getInstance().cancelAllWork()
        val workA = OneTimeWorkRequest.Builder(WorkerA::class.java).build()
        val workB = OneTimeWorkRequest.Builder(WorkerB::class.java).build()
        val workC = OneTimeWorkRequest.Builder(WorkerC::class.java).build()
        WorkManager.getInstance()
                .beginWith(workA)
                .then(workB)
                .then(workC)
                .enqueue()
    }

    private fun secondChain() {
        WorkManager.getInstance().cancelAllWork()
        val workA = OneTimeWorkRequest.Builder(WorkerA::class.java).build()
        val workB = OneTimeWorkRequest.Builder(WorkerB::class.java).build()
        val workC = OneTimeWorkRequest.Builder(WorkerC::class.java).build()
        val workD = OneTimeWorkRequest.Builder(WorkerD::class.java).build()
        val workE = OneTimeWorkRequest.Builder(WorkerE::class.java).build()
        WorkManager.getInstance()
                .beginWith(Arrays.asList(workA, workB))
                .then(workC)
                .then(Arrays.asList(workD, workE))
                .enqueue()
    }

    @SuppressLint("EnqueueWork")
    private fun thirdChain() {
        WorkManager.getInstance().cancelAllWork()
        val workA = OneTimeWorkRequest.Builder(WorkerA::class.java).build()
        val workB = OneTimeWorkRequest.Builder(WorkerB::class.java).build()
        val workC = OneTimeWorkRequest.Builder(WorkerC::class.java).build()
        val workD = OneTimeWorkRequest.Builder(WorkerD::class.java).build()
        val workE = OneTimeWorkRequest.Builder(WorkerE::class.java).build()
        val chain1 = WorkManager.getInstance()
                .beginWith(workA)
                .then(workB)
        val chain2 = WorkManager.getInstance()
                .beginWith(workC)
                .then(workD)
        WorkContinuation.combine(Arrays.asList(chain1, chain2))
                .then(workE)
                .enqueue()
    }
}
