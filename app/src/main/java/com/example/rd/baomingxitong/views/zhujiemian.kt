package com.example.rd.baomingxitong.views

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.widget.Toast
import com.example.rd.baomingxitong.R
import com.example.rd.baomingxitong.base.MyApp
import com.example.rd.baomingxitong.databinding.ActivityZhujiemianBinding

class zhujiemian : FragmentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityZhujiemianBinding = DataBindingUtil.setContentView(this,R.layout.activity_zhujiemian)
        binding.zhujiemian = this
        shezhiMenu(binding)
    }

    override fun onResume() {
        super.onResume()
        MyApp.instances.contextNow = this
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun shezhiMenu(binding: ActivityZhujiemianBinding)
    {
        binding.nav.setNavigationItemSelectedListener {
            item ->
            if (item.itemId.equals(R.id.favorite))
                startActivity(Intent(this,shezhiActivity::class.java))
            binding.drawerlayout.closeDrawer(binding.nav)
            return@setNavigationItemSelectedListener true
        }
        binding.usernemu.setOnClickListener {
            if (binding.drawerlayout.isDrawerOpen(binding.nav))
                binding.drawerlayout.closeDrawer(binding.nav)
            else
                binding.drawerlayout.openDrawer(binding.nav)
        }
    }

}
