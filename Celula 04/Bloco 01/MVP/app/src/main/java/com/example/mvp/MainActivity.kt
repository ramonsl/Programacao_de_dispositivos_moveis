package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private  val presenter  = MainPresenter(this, MainInteractor())
    var viewOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnShow.setOnClickListener{presenter.onValida(viewOn)}
    }

    override fun showMensagem() {
        txtText.visibility =  View.VISIBLE
        viewOn = true
    }
    override fun hiddenMensagem() {
        txtText.visibility =  View.GONE
        viewOn = false
    }

}
