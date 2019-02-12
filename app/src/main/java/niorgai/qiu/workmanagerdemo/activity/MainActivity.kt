package niorgai.qiu.workmanagerdemo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import niorgai.qiu.workmanagerdemo.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.one_time).setOnClickListener(this)
        findViewById<View>(R.id.content_uri_trigger).setOnClickListener(this)
        findViewById<View>(R.id.chain).setOnClickListener(this)
        findViewById<View>(R.id.io).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.one_time -> startActivity(Intent(this, OneTImeWorkerActivity::class.java))
            R.id.content_uri_trigger -> startActivity(Intent(this, ContentUriTriggerActivity::class.java))
            R.id.chain -> startActivity(Intent(this, ChainedTaskActivity::class.java))
            R.id.io -> startActivity(Intent(this, IOActivity::class.java))
        }
    }
}
