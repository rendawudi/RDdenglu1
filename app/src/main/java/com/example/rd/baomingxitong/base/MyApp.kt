package com.example.rd.baomingxitong.base

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import com.example.rd.baomingxitong.Http.RetrofitHelper
import com.example.rd.baomingxitong.entity.TeacherHttpLoginGet
import com.example.rd.baomingxitong.entity.UserHttpLoginGet
import com.example.rd.baomingxitong.fuzhu.ProgressHandler
import com.example.rd.baomingxitong.fuzhu.ProgressObservable
import com.example.rd.baomingxitong.fuzhu.SubscribeOnNextListener
import com.example.rd.baomingxitong.views.zhujiemian
import com.google.gson.Gson

/**
 * Created by rd on 2017/9/27.
 */
class MyApp: Application()
{
    lateinit var context: Context
    lateinit var contextNow: Context
    @Volatile var user: UserHttpLoginGet ?=null
    var NetBoolean = false
    override fun onCreate() {
        super.onCreate()
        GreenDaoUse.init(this)
        context = applicationContext
        instances = this
    }

    companion object {
        lateinit var instances: MyApp
    }

}