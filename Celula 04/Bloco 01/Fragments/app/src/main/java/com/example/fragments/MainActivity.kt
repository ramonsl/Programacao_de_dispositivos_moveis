package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bnm_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState ==null){
            val fragmet = MoneyFragment.newInstance(null,null)
            openFragment(fragmet)
        }

    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when(it.itemId){
            R.id.nav_money ->{
                val moneyFragment  = MoneyFragment.newInstance(null, null)
                openFragment(moneyFragment)

            }
            R.id.nav_savings->{
                val  saveFragment= SaveFragment.newInstance(null,null)
                openFragment(saveFragment)
            }
            else ->{
                val moneyFragment  = MoneyFragment.newInstance(null, null)
                openFragment(moneyFragment)
            }
        }

    }

    fun openFragment(frag : Fragment): Boolean{
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, frag)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }



}
