package com.ramonsl.CoronaExemplo.ws

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ramonsl.CoronaExemplo.States
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object HttpUf {

    val url = "https://covid19-brazil-api.now.sh/api/report/v1/brazil/uf/"


    fun hasConnection(ctx: Context): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }

//    @RequiresApi(Build.VERSION_CODES.O)
    fun loadState(uf: String): States? {

        val state = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(url + uf)
            .build()

        val response = state.newCall(request).execute()
        val jsonString = response.body?.string()

        val json = JSONObject(jsonString)

        return readState(json)


    }

   // @RequiresApi(Build.VERSION_CODES.O)
    fun readState(json: JSONObject): States? {
        try {

            val dia = formatarData(json.getString("datetime").substring(0, 10))
            val hora = json.getString("datetime").substring(11, 16)

            var states = States(
                json.getString("state"),
                json.getInt("cases"),
                json.getInt("deaths"),
                json.getInt("suspects"),
                json.getInt("refuses"),
                dia,
                hora
            )

            return states
        } catch (e: IOException) {
            Log.e("Erro", "Impossivel ler JSON")
        }
        return null
    }


    fun formatarData(data: String):String{
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val output: String = formatter.format(parser.parse("2018-12-14T09:55:00"))
        return output
    }
/* APIS > 23
    fun formatarData(data: String): String {
        val diaString = data
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(diaString)
        var formattedDate = date.format(formatter)
        return formattedDate
    }*/
}
