package com.example.rd.baomingxitong.ViewModel

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.rd.baomingxitong.Http.RetrofitHelper
import com.example.rd.baomingxitong.base.MyApp
import com.example.rd.baomingxitong.databinding.ActivityLoginXsBinding
import com.example.rd.baomingxitong.entity.UserHttpLoginGet
import com.example.rd.baomingxitong.entity.UserInfo
import com.example.rd.baomingxitong.entity.UserLogin
import com.example.rd.baomingxitong.fuzhu.ProgressHandler
import com.example.rd.baomingxitong.fuzhu.ProgressObservable
import com.example.rd.baomingxitong.fuzhu.SubscribeOnNextListener
import com.example.rd.baomingxitong.views.LoginActivityXs
import com.example.rd.baomingxitong.views.RegisterActivity
import com.example.rd.baomingxitong.views.zhujiemian

/**
 * Created by rd on 2017/9/27.
 */

object StudentViewModel
{
    lateinit var userInfo: UserInfo
    lateinit var binding: ActivityLoginXsBinding
    lateinit var loginXsActivity: LoginActivityXs

    fun init(activityLoginXsBinding: ActivityLoginXsBinding,activityXs: LoginActivityXs)
    {
        binding = activityLoginXsBinding
        loginXsActivity = activityXs
        binding.xsModel = this
        userInfo = UserInfo()

    }

    fun onClickXuesheng(view: View)
    {
        var student: UserLogin = UserLogin(userInfo.xuehao.get(), userInfo.mima.get())
        ProgressHandler.initProgressDialog(loginXsActivity)
        var mOnNextListener = object :SubscribeOnNextListener<UserHttpLoginGet?>
        {
            override fun onNext(t: UserHttpLoginGet?) {
                MyApp.instances.user = t
            }

            override fun onComplete() {
                ProgressHandler.disMissProgressDialog()
                if (MyApp.instances.user!=null)
                {
                    loginXsActivity.startActivity(Intent(loginXsActivity, zhujiemian::class.java))
                    loginXsActivity.finish()
                }

            }

            override fun onStart() {
                ProgressHandler.showProgressDialog()
            }
        }
        RetrofitHelper.insance.getUserLogin(ProgressObservable<UserHttpLoginGet?>(loginXsActivity,mOnNextListener),student)
    }


    fun onClickXsDl(view: View)
    {
        loginXsActivity.startActivity(Intent(loginXsActivity,RegisterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}