package com.ramonsl.CoronaExemplo

import android.os.Parcel
import android.os.Parcelable

class States(
    var state: String?,
    var cases: Int=0,
    var deaths: Int=0,
    var suspects: Int=0,
    var refuses: Int=0,
    var date: String?,
    var hour: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(state)
        parcel.writeInt(cases)
        parcel.writeInt(deaths)
        parcel.writeInt(suspects)
        parcel.writeInt(refuses)
        parcel.writeString(date)
        parcel.writeString(hour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<States> {
        override fun createFromParcel(parcel: Parcel): States {
            return States(parcel)
        }

        override fun newArray(size: Int): Array<States?> {
            return arrayOfNulls(size)
        }
    }
}