package com.example.rd.jk1504mvvm.httpException

import android.widget.Toast
import com.example.rd.baomingxitong.base.MyApp

/**
 * Created by rd on 2017/9/22.
 */

public class ApiException: Exception
{
    var code: Int
    lateinit var msg: String
    companion object {
        val USER_NOT_EXIST = 100
        val WRONG_PASSWORD = 101
        public fun getApiMsg(code: Int): String
        {
            when(code)
            {
                100 ->
                    return "注册失败"
                101 ->
                    return "账号密码错误"
                103 ->
                    return "更新用户信息失败"
                555 ->
                    return "网络无信号"
                151 -> return "学院获取失败"
                152 -> return "专业获取失败"
                153 -> return "班级获取失败"

                else -> return "未知错误"
            }
        }
    }
    constructor(throwable: Throwable,code: Int):super(throwable)
    {
        this.code = code
    }
    constructor(code: Int,msg: String)
    {
        this.code = code
        this.msg = msg
    }

}