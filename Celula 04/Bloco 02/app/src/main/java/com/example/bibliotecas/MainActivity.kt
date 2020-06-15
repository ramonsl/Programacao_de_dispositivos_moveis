package com.example.bibliotecas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.github.razir.progressbutton.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bind your button to activity lifecycle
        bindProgressButton(myButton)

        // (Optional) Enable fade In / Fade out animations
        myButton.attachTextChangeAnimator()

        // Show progress with "Loading" text
        myButton.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }

        // Hide progress and show "Submit" text instead
        myButton.hideProgress(R.string.submit)


        val animatedDrawable = ContextCompat.getDrawable(this, R.drawable.money)
        //Defined bounds are required for your drawable
        animatedDrawable!!.setBounds(0, 0, 20, 20)

        myButton.showDrawable(animatedDrawable) {
            buttonTextRes = R.string.saved

        }

        myButton.setOnClickListener {

            MaterialDialog(this).show {
                input(hint = "Seu nome")
                positiveButton(R.string.submit)
            }
        }


    }
}