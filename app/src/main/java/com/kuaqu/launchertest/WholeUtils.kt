package com.kuaqu.launchertest

import android.content.Context
import android.os.Looper
import android.widget.Toast

/*全局工具类*/

private var toast:Toast?=null

fun showToast(cotext:Context,text:String,duration: Int = Toast.LENGTH_SHORT){
    if(Looper.myLooper()== Looper.getMainLooper()){
        if(toast==null){
          toast= Toast.makeText(cotext,text,duration)
        }else{
            toast?.setText(text)
        }
        toast?.show()
    }
}