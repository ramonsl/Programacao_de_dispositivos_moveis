package com.lopes.covidworld.activitys

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.lopes.covidworld.R
import com.lopes.covidworld.all_states
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.appBar))

        uf.text = null

        btnAllStates.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, all_states::class.java)
            this.startActivity(intent)
        })



        btnSearch.setOnClickListener(View.OnClickListener {
            if(uf.text.toString().isNotEmpty()) {
                val intent = Intent(this, searchState::class.java)
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
