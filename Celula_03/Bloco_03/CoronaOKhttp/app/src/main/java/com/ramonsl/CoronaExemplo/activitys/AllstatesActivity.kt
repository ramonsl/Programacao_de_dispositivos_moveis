package com.ramonsl.CoronaExemplo

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.ramonsl.CoronaExemplo.activitys.HomeActivity
import kotlinx.android.synthetic.main.activity_all_states.*

class AllstatesActivity : AppCompatActivity() {

    private var listStates = arrayListOf<States>()
    private var adapter = StatesAdapter(listStates)
    private var asyncTask : StatesTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_states)
        setSupportActionBar(findViewById(R.id.appBar))
        CarregaDados()

        rvDados.layoutManager = GridLayoutManager(this,2)
        rvDados.itemAnimator = DefaultItemAnimator()
        rvDados.adapter = adapter

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Clique sobre algum estado para mais informações!")
        builder.setPositiveButton("OK") { _: DialogInterface, i: Int -> }
        builder.show()


        appBar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@AllstatesActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })

    }

    fun showProgress(show: Boolean){
        if(show){
            txtMsg.text = "Carregando..."
        }else{
            txtMsg.visibility = if(show) View.VISIBLE else View.GONE
            progressBar.visibility = if(show) View.VISIBLE else View.GONE
        }
    }

    fun CarregaDados(){
        listStates.clear()
        if(listStates.isNotEmpty()){
            showProgress(false)
        }else{
            if(asyncTask==null){
                if(HttpAllStates.hasConnection(this)){
                    if(asyncTask?.status != AsyncTask.Status.RUNNING){
                        asyncTask = StatesTask()
                        asyncTask?.execute()
                    }
                }else{
                    progressBar.visibility = View.GONE
                }
            }else if(asyncTask?.status==AsyncTask.Status.RUNNING){
                showProgress(true)
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class StatesTask: AsyncTask<Void, Void, List<States?>>(){

        override fun onPreExecute() {
            super.onPreExecute()
            showProgress(true)
        }


        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): List<States>? {
            return HttpAllStates.loadStates()
        }



        override fun onPostExecute(result: List<States?>?) {
            super.onPostExecute(result)
            showProgress(false)
            update(result as List<States>?)
        }

        private fun update(result: List<States>?){
            if(result != null){
                listStates.clear()
                listStates.addAll(result.reversed())
            }else{
                txtMsg.text = "Erro ao Carregar"
            }
            adapter.notifyDataSetChanged()
            asyncTask = null
        }
    }

}
