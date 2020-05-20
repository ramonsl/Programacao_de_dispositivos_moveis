package com.example.clientesapp

import android.os.Parcel
import android.os.Parcelable

 data class Cliente(var id: Int?, val nome:String?, val fone: String?, val idade:Int):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )


     override fun writeToParcel(parcel: Parcel, flags: Int) {
         this!!.id?.let { parcel.writeInt(it) }
        parcel.writeString(nome)
        parcel.writeString(fone)
        parcel.writeInt(idade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }
}