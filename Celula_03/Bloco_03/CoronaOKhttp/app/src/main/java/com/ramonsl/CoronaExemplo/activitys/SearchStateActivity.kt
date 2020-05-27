package com.ramonsl.CoronaExemplo.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.ramonsl.CoronaExemplo.R
import com.ramonsl.CoronaExemplo.States
import com.ramonsl.CoronaExemplo.ws.HttpUf
import kotlinx.android.synthetic.main.activity_search_state.*

class SearchStateActivity : AppCompatActivity() {

    var uf: String = "rs"
    private var asyncTask : StatesTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_state)
        uf = intent.getStringExtra("uf")
        CarregaDados()


        appBarSearch.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@SearchStateActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

    fun CarregaDados(){
        if(asyncTask==null){
            if(HttpUf.hasConnection(this)){
                if(asyncTask?.status != AsyncTask.Status.RUNNING){
                    asyncTask = StatesTask()
                    asyncTask?.execute()
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, States?>() {


      @SuppressLint("WrongThread")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): States? {
            return HttpUf.loadState(uf)
        }

        private fun update(result: States?){

            if (result != null) {
                dia.text = result.date.toString().substring(0,2)
                mes.text = alterMonth(result.date.toString())
                hora.text = result.hour
                cidade.text = result.state
                casos.text = result.cases.toString()
                mortes.text = result.deaths.toString()
                suspeitos.text = result.suspects.toString()
                descartados.text = result.refuses.toString()
            }

            asyncTask = null
        }

        override fun onPostExecute(result: States?) {
            super.onPostExecute(result)
            update(result)
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
}
