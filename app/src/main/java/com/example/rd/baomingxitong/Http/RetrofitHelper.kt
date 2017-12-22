package com.example.rd.baomingxitong.Http

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.rd.baomingxitong.GreenDao.LTXiangmuDao
import com.example.rd.baomingxitong.ViewModel.*
import com.example.rd.baomingxitong.base.GreenDaoUse
import com.example.rd.baomingxitong.entity.*
import com.example.rd.baomingxitong.entity.LiaoTian.LTXiangmu
import com.example.rd.baomingxitong.entity.LiaoTian.LTXmHttp
import com.example.rd.baomingxitong.entity.Task.*
import com.example.rd.baomingxitong.entity.Xuexiao.Banji
import com.example.rd.baomingxitong.entity.Xuexiao.Pici
import com.example.rd.baomingxitong.entity.Xuexiao.Xueyuan
import com.example.rd.jk1504mvvm.httpException.ApiException
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.rd.baomingxitong.entity.Xuexiao.Zhuanye
import com.example.rd.baomingxitong.fuzhu.ProgressObservable
import kotlin.collections.ArrayList

/**
 * Created by rd on 2017/9/27.
 */

class RetrofitHelper
{
    companion object {
        private val DEFAULT_TIMEOUT: Long = 10
        public val insance: RetrofitHelper = RetrofitHelper()
    }

    private var retrofit: Retrofit
    private var userApi: UserApi
    private var taskApi: TaskApi
    private var builder: OkHttpClient.Builder

    constructor()
    {
        builder = OkHttpClient.Builder()

        var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        builder.connectTimeout(RetrofitHelper.DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .addNetworkInterceptor(logInterceptor)

        retrofit = Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(UserApi.BASE_URL)
                .build()

        userApi = retrofit.create(UserApi::class.java)
        taskApi = retrofit.create(TaskApi::class.java)
    }

    fun getUserLogin(observer: ProgressObservable<UserHttpLoginGet?>, userLogin: UserLogin)
    {
        var gson: Gson = Gson()
        var userJson = gson.toJson(userLogin)
        Log.d("json",userJson)
        var requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),userJson)
        userApi.getUserLogin(requestBody)
                .map {
                    t ->
                    if (t.code ==200)
                    {
                        return@map t.data
                    }
                    else
                        throw ApiException(101,ApiException.getApiMsg(101))
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (observer)
    }

    fun UserRigister(observer: ProgressObservable<UserHttpLoginGet?>,userRsend: UserHttpRegisterSend)
    {

        var gson: Gson = Gson()
        var registerJson = gson.toJson(userRsend)
        var requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),registerJson)
        userApi.UserRegister(requestBody)
                .map {
                    t ->
                    if (t.code ==200)
                    {
                        return@map t.data
                    }
                    else
                        throw ApiException(100,ApiException.getApiMsg(100))
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (observer)
    }

    fun UpdataUser(observer: ProgressObservable<String?>,userRsend: UserHttpRegisterSend)
    {

        var gson: Gson = Gson()
        var registerJson = gson.toJson(userRsend)
        var requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),registerJson)
        userApi.updataUser(requestBody)
                .map {
                    t ->
                    if (t.code ==200)
                    {
                        return@map t.data
                    }
                    else
                        throw ApiException(103,ApiException.getApiMsg(103))
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (observer)
    }
}
class HttpLogger: HttpLoggingInterceptor.Logger
{
    override fun log(message: String?) {
        Log.d("请求和响应",message)
    }
}

