package com.example.rd.baomingxitong.ViewModel

import android.app.AlertDialog
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import android.widget.Toast
import com.example.rd.baomingxitong.Http.RetrofitHelper
import com.example.rd.baomingxitong.R
import com.example.rd.baomingxitong.base.MyApp
import com.example.rd.baomingxitong.databinding.ActivityRegisterBinding
import com.example.rd.baomingxitong.entity.UserHttpLoginGet
import com.example.rd.baomingxitong.entity.UserHttpRegisterSend
import com.example.rd.baomingxitong.entity.UserInfo
import com.example.rd.baomingxitong.entity.Xuexiao.Banji
import com.example.rd.baomingxitong.entity.Xuexiao.Xueyuan
import com.example.rd.baomingxitong.entity.Xuexiao.Zhuanye
import com.example.rd.baomingxitong.fuzhu.ProgressHandler
import com.example.rd.baomingxitong.fuzhu.ProgressObservable
import com.example.rd.baomingxitong.fuzhu.SubscribeOnNextListener
import com.example.rd.baomingxitong.views.RegisterActivity
import com.example.rd.baomingxitong.views.zhujiemian
import java.util.regex.Pattern

/**
 * Created by rd on 2017/9/24.
 */
object UserViewModel
{
    lateinit var userInfo: UserInfo
    lateinit var binding: ActivityRegisterBinding
    lateinit var registerActivity: RegisterActivity
    lateinit var userRegisterSend: UserHttpRegisterSend

    fun init(activityRegisterBinding: ActivityRegisterBinding,registerActivity: RegisterActivity)
    {
        this.binding = activityRegisterBinding
        this.registerActivity = registerActivity
        this.binding.userModel = this
        userInfo = UserInfo()
        userRegisterSend = UserHttpRegisterSend()
    }

    fun onClickZhuCe(view: View)
    {
        userRegisterSend.nicheng = userInfo.nicheng.get()
        userRegisterSend.jianjie = userInfo.jianjie.get()
        userRegisterSend.mima = userInfo.mima.get()
        userRegisterSend.qq = userInfo.qq.get()
        userRegisterSend.shouji = userInfo.shouji.get()
        userRegisterSend.weixin = userInfo.weixin.get()
        userRegisterSend.xingming = userInfo.xingming.get()
        var xh = userInfo.xuehao.get()
        userRegisterSend.xuehao = userInfo.xuehao.get()

        if (judgeUser())
        {
            ProgressHandler.initProgressDialog(registerActivity)
            var mOnNextListener = object : SubscribeOnNextListener<UserHttpLoginGet?>
            {
                override fun onNext(t: UserHttpLoginGet?) {
                        MyApp.instances.user = t
                }

                override fun onStart() {
                    ProgressHandler.showProgressDialog()
                }

                override fun onComplete() {
                    ProgressHandler.disMissProgressDialog()
                    if (MyApp.instances.user!=null)
                    {
                        registerActivity.startActivity(Intent(registerActivity,zhujiemian::class.java))
                        StudentViewModel.loginXsActivity.finish()
                    }
                }

            }
            RetrofitHelper.insance.UserRigister(ProgressObservable(registerActivity,mOnNextListener), userRegisterSend)
        }
    }
    fun judgeUser(): Boolean
    {
        if (userRegisterSend.xuehao.length>10||userRegisterSend.xuehao.length<5)
        {
            Toast.makeText(registerActivity,"用户名最少5位，最多10位",Toast.LENGTH_SHORT).show()
            return false
        }
        if (!userRegisterSend.xuehao.get(0).isLetter())
        {
            Toast.makeText(registerActivity,"用户名需以字母开头",Toast.LENGTH_SHORT).show()
            return false
        }
        var flag = false
        for (i in userRegisterSend.xuehao)
        {
            if (i.isUpperCase())
                flag = true
        }
        if (!flag)
        {
            Toast.makeText(registerActivity,"用户名需包含至少一个大写字母",Toast.LENGTH_SHORT).show()
            return false
        }
        if (isSpecialChar(userRegisterSend.xuehao))
        {
            Toast.makeText(registerActivity,"用户名不能包含特殊字符",Toast.LENGTH_SHORT).show()
            return false
        }
        if (!userRegisterSend.mima.equals(userInfo.mima1.get()))
        {
            Toast.makeText(registerActivity,"两次密码不一致",Toast.LENGTH_SHORT).show()
            return false
        }
        if (userRegisterSend.mima.length>12||userRegisterSend.mima.length<6)
        {
            Toast.makeText(registerActivity,"密码最少6位，最多12位",Toast.LENGTH_SHORT).show()
            return false
        }
        if (isSpecialChar(userRegisterSend.mima))
        {
            Toast.makeText(registerActivity,"密码不能包含特殊字符",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun isSpecialChar(str: String): Boolean {
        val regEx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t"
        val p = Pattern.compile(regEx)
        val m = p.matcher(str)
        return m.find()
    }
}