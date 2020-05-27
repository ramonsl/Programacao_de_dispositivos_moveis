package com.ramonsl.CoronaExemplo.activitys

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.ramonsl.CoronaExemplo.R
import com.ramonsl.CoronaExemplo.AllstatesActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.appBar))

        uf.text = null

        btnAllStates.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AllstatesActivity::class.java)
            this.startActivity(intent)
        })



        btnSearch.setOnClickListener(View.OnClickListener {
            if(uf.text.toString().isNotEmpty()) {
                val intent = Intent(this, SearchStateActivity::class.java)
                intent.putExtra("uf", uf.text.toString().toLowerCase())
                this.startActivity(intent)
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Você deve informar um uf")
                builder.setPositiveButton("OK") { _: DialogInterface, i: Int -> }
                builder.show()
            }

        })
    }


}
