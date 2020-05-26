package com.ramonsl.CoronaExemplo


import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class StatesAdapter (private val states: List<States>): RecyclerView.Adapter<StatesAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val intent = Intent(v.context, StateInformationsActivity::class.java)
            var arrayStates = states[vh.adapterPosition]
            intent.putExtra("State", arrayStates)
            v.context.startActivity(intent)
        }
        return vh
    }

    override fun getItemCount(): Int {
        return states.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var state = states[position]
        holder.stateName.text = state.state
        holder.stateCases.text = state.cases.toString()
        holder.stateDay.text = state.date.toString().substring(0,2)
        holder.stateMonth.text = alterMonth(state.date.toString()).toString()
        holder.stateYear.text = state.date.toString().substring(6,10)
        holder.stateCaution.setBackgroundColor(setColor(state.cases))
    }


    class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        var stateName:TextView = itemView.name
        var stateCases:TextView = itemView.nCases
        var stateDay:TextView = itemView.dia
        var stateMonth:TextView = itemView.mes
        var stateYear:TextView = itemView.ano
        var stateCaution:View = itemView.caution
    }

    fun alterMonth(month: String): String? {
        var newString: String? = ""
        when {
            month.substring(3,5) == "01" -> {
                newString = "JAN"
            }
            month.substring(3,5) == "02" -> {
                newString = "FEV"
            }
            month.substring(3,5) == "03" -> {
                newString = "MAR"
            }
            month.substring(3,5) == "04" -> {
                newString = "ABR"
            }
            month.substring(3,5) == "05" -> {
                newString = "MAI"
            }
            month.substring(3,5) == "06" -> {
                newString = "JUN"
            }
            month.substring(3,5) == "07" -> {
                newString = "JUL"
            }
            month.substring(3,5) == "08" -> {
                newString = "AGO"
            }
            month.substring(3,5) == "09" -> {
                newString = "SET"
            }
            month.substring(3,5) == "10" -> {
                newString = "OUT"
            }
            month.substring(3,5) == "11" -> {
                newString = "NOV"
            }
            month.substring(3,5) == "12" -> {
                newString = "DEZ"
            }
        }

        return newString
    }

    fun setColor(n: Int): Int {

        if(n < 2000){
            return Color.GREEN
        }else if (n < 6000){
             return Color.YELLOW
        }else{
            return Color.RED
        }
    }

}