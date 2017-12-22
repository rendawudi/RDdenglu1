package com.example.rd.baomingxitong.entity.Task

import android.databinding.ObservableField
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by rd on 2017/9/25.
 */
data class TaskmainGet (
    var xiangmu: String ="",
    var laoshi: String="",
    var jianjie: String="",
    var gonghao: String="",
    var xiangmuId: Int=0,
    var piciId: Int =0,
    var renshu: Int=0,
    var dqrenshu: Int=0
): Parcelable
{
    constructor(source: Parcel): this(source.readString(),source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(this.xiangmu)
        p0.writeString(this.laoshi)
        p0.writeString(this.jianjie)
        p0.writeInt(xiangmuId)
        p0.writeInt(piciId)
        p0.writeInt(renshu)
        p0.writeInt(dqrenshu)
    }

    companion object {
        @JvmField var CREATOR: Parcelable.Creator<TaskmainGet> = object : Parcelable.Creator<TaskmainGet>
        {
            override fun createFromParcel(p0: Parcel): TaskmainGet {
                return TaskmainGet(p0)
            }

            override fun newArray(p0: Int): Array<TaskmainGet?> {
                return arrayOfNulls(p0)
            }
        }
    }
}