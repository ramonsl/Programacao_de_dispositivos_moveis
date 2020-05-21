package com.example.ciclodevida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("Ciclo", "O método onCreate é usado para configurar a interface de usuário, usando setContentView, e para iniciar outras partes estáticas da Activity.")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Ciclo", "O método onStart é executado depois de a Activity ter sido enviada para o segundo plano. Isso faz do método onStart um bom lugar para se certificar de que todos os recursos requeridos continuam disponíveis. Por exemplo, se estiver usando o GPS, o onStart é um bom lugar para se certificar de que o GPS estará disponível.")

    }

    override fun onResume() {
        super.onResume()
        Log.v("Ciclo", "O método onResume é acionado quando a Activity se inicia e quando é reiniciada. Ele é acionado sempre que a Activity voltar para o primeiro plano, um bom lugar para fazer coisas como obter Intents e dados extras.")
    }


    override fun onPause() {
        super.onPause()
        Log.v("Ciclo", "O método onPause é acionado, quando a Activity deixa o primeiro plano. Isso pode significar que uma janela de diálogo está sendo mostrada na tela, ou pode significar que este é o primeiro passo para que a Activity seja parada. Isso faz do onPause o lugar ideal para tarefas como parar animações, salvar dados e liberar recursos do sistema. Tudo que for liberado no método aqui deverá ser reconfigurado no método onResume.")

    }

    override fun onStop() {
        super.onStop()
        Log.v("Ciclo","O método onStop é chamado quando a Activity não está mais visível para o usuário. Isso pode acontecer porque ela está sendo destruída ou porque outra Activity foi reiniciada e está em sua frente. Aqui é o lugar para liberar todos os recursos que não são mais utilizados pelo usuário.")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Ciclo", "O método onDestroy é chamado quando a Activity vai ser destruida.  É a última chamada que a Activity receberá antes de ser finalizada.\n" +
                "\n" +
                "Você pode combinar os métodos de criação e limpeza. Se algo é criado no onResume, limpe-o no onPause. Se algo é configurado no onStart, limpe-o no onStop.")
    }
}
