package com.ramonsl.CoronaExemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_all_states.appBar
import kotlinx.android.synthetic.main.activity_state_informations.*
import kotlinx.android.synthetic.main.item.dia
import kotlinx.android.synthetic.main.item.mes

class StateInformationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_informations)

        appBar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@StateInformationsActivity, AllstatesActivity::class.java)
                startActivity(intent)
            }
        })


        val arrayState = this.intent.getParcelableExtra<States>("State")

        dia.text = arrayState.date.toString().substring(0,2)
        mes.text = alterMonth(arrayState.date.toString())
        hora.text = arrayState.hour
        cidade.text = arrayState.state
        casos.text = arrayState.cases.toString()
        mortes.text = arrayState.deaths.toString()
        suspeitos.text = arrayState.suspects.toString()
        descartados.text = arrayState.refuses.toString()


    }

    fun alterMonth(month: String): String? {
        var newString: String? = ""
        when {
            month.substring(3,5) == "01" -> {
                newString = "JANEIRO"
            }
            month.substring(3,5) == "02" -> {
                newString = "FEVEREIRO"
            }
            month.substring(3,5) == "03" -> {
                newString = "MARÇO"
            }
            month.substring(3,5) == "04" -> {
                newString = "ABRIL"
            }
            month.substring(3,5) == "05" -> {
                newString = "MAIO"
            }
            month.substring(3,5) == "06" -> {
                newString = "JUNHO"
            }
            month.substring(3,5) == "07" -> {
                newString = "JULHO"
            }
            month.substring(3,5) == "08" -> {
                newString = "AGOSTO"
            }
            month.substring(3,5) == "09" -> {
                newString = "SETEMBRO"
            }
            month.substring(3,5) == "10" -> {
                newString = "OUTUBRO"
            }
            month.substring(3,5) == "11" -> {
                newString = "NOVEMBRO"
            }
            month.substring(3,5) == "12" -> {
                newString = "DEZEMBRO"
            }
        }

        return newString
    }
}
