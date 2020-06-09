package com.example.stonks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = StonksFragment()
            supportFragmentManager.beginTransaction().replace(R.id.Frame, fragment,null)
                .commit()
        }

        BNV_MENU.setOnNavigationItemSelectedListener  (mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when(it.itemId){
            R.id.navigation_money->{
                Log.v("Log", "money")

                val moneyFragment = moneyFragment.newInstance(null,null);
                openFragment(moneyFragment)
            }
            R.id.navigation_savings->{
                val moneyFragment = StonksFragment.newInstance(null,null);
                openFragment(moneyFragment)

            }
            R.id.navigation_growth->{
                val moneyFragment = StonksFragment.newInstance(null,null);
                openFragment(moneyFragment)


            }
            else -> {
                val moneyFragment = StonksFragment.newInstance(null, null);
                openFragment(moneyFragment)

            }
            }
        }

    fun openFragment(frag: Fragment):Boolean{


        Log.v("Log", "OpenFragment")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame, frag)
        transaction.addToBackStack(null)
        transaction.commit()
        return true

    }


}
