package com.example.rd.baomingxitong.entity.LiaoTian;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by rd on 2017/11/2.
 */

@Entity
public class LTMsg
{
    @Id
    public Long id;
    public String sender;
    public Long xiangmuId;
    public String msg;
    public String time;
    public String xingming;
    @Generated(hash = 633513622)
    public LTMsg(Long id, String sender, Long xiangmuId, String msg, String time,
            String xingming) {
        this.id = id;
        this.sender = sender;
        this.xiangmuId = xiangmuId;
        this.msg = msg;
        this.time = time;
        this.xingming = xingming;
    }
    @Generated(hash = 604342249)
    public LTMsg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSender() {
        return this.sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public Long getXiangmuId() {
        return this.xiangmuId;
    }
    public void setXiangmuId(Long xiangmuId) {
        this.xiangmuId = xiangmuId;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getXingming() {
        return this.xingming;
    }
    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

   
}
