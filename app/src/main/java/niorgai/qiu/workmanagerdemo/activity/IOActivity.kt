package niorgai.qiu.workmanagerdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import niorgai.qiu.workmanagerdemo.R
import niorgai.qiu.workmanagerdemo.worker.PlusWorker

class IOActivity : AppCompatActivity() {

    private lateinit var firstInput: EditText
    private lateinit var secondInput: EditText
    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_io)

        firstInput = findViewById(R.id.first_input)
        secondInput = findViewById(R.id.second_input)
        output = findViewById(R.id.output)

        findViewById<View>(R.id.start).setOnClickListener {
            WorkManager.getInstance().cancelAllWork()

            var firstNumber = 0
            var secondNumber = 0

            try {
                firstNumber = firstInput.text.toString().toInt()
                secondNumber = secondInput.text.toString().toInt()
            } catch (e: Exception) {

            }

            val inputData = Data.Builder()
                    .putInt("KEY_FIRST", firstNumber)
                    .putInt("KEY_SECOND", secondNumber)
                    .build()

            val worker = OneTimeWorkRequest.Builder(PlusWorker::class.java)
                    .setInputData(inputData)
                    .build()

            WorkManager.getInstance().enqueue(worker)
            WorkManager.getInstance().getWorkInfoByIdLiveData(worker.id)
                    .observe(this@IOActivity, Observer { info ->
                        if (info != null && info.state.isFinished) {
                            val result = info.outputData.getInt("KEY_RESULT", 0)
                            output.text = result.toString()
                        }
                    })
        }
    }
}
