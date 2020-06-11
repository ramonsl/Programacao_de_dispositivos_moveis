package com.ramonsl.CoronaExemplo

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object HttpAllStates {
    val urlStates = "https://covid19-brazil-api.now.sh/api/report/v1"
    fun hasConnection(ctx: Context): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info = cm.activeNetworkInfo
        return info != null && info.isConnected
    }

   // @RequiresApi(Build.VERSION_CODES.O)
    fun loadStates(): List<States>? {
        val state = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(urlStates)
            .build()
        val response = state.newCall(request).execute()
        val jsonString = response.body?.string()
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")
        return readState(jsonArray)


    }

    //@RequiresApi(Build.VERSION_CODES.O)
    fun readState(json: JSONArray): List<States>? {
        val estados = arrayListOf<States>()
        try {
            for (i in 0..json.length() - 1) {
                var js = json.getJSONObject(i)

                val dia = formatarData(js.getString("datetime").substring(0, 10))
                val hora = js.getString("datetime").substring(11, 16)

                var states = States(
                    js.getString("state"),
                    js.getInt("cases"),
                    js.getInt("deaths"),
                    js.getInt("suspects"),
                    js.getInt("refuses"),
                    dia,
                    hora
                )
                estados.add(states)
            }
        } catch (e: IOException) {
            Log.e("Erro", "Impossivel ler JSON")
        }

        return estados
    }


  /*  @RequiresApi(Build.VERSION_CODES.O)
    fun formatarData(data: String): String {
        val diaString = data
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(diaString)
        var formattedDate = date.format(formatter)
        return formattedDate
    }*/

    fun formatarData(data: String):String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val output: String = formatter.format(parser.parse("2018-12-14T09:55:00"))
        return output
    }
}