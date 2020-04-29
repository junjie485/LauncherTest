package com.kuaqu.launchertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.text.BreakIterator
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var timer:CountDownTimer

    val isPhoneValid :Boolean
    get() {
        val number=codeEdit.text.toString()
        if(number.isEmpty()){
            showToast(this,"验证码不能为空")
            return false
        }else if(!number.matches("^[0-9]*\$".toRegex())){
            showToast(this,"验证码只能是数字")
            return false
        }
        return true
    }

    private var handler:AskHandler=AskHandler()
    inner class AskHandler:Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1->if(sendBtn.isClickable){
                    var data1=msg.arg1
                }
                2->
                {
                    codeEdit.setText("21090")
                    sendBtn.isClickable=false
                }
                3->{
                    val hashMap=HashMap<Int,String>()
                    hashMap.put(1,"小明")
                    hashMap.put(2,"小华")
                    hashMap.put(3,"晓东")
                    println(".....traversing hashmap.......")
                    for(key in hashMap.keys){
                        println("Element at key $key = ${hashMap[key]}")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViews()
    }

    private fun setupViews() {
        timer=STSTimer(60*1000,1000);
        sendBtn.setOnClickListener(){
            var number=userEdit.text.toString();
            if(number.isEmpty()){
                showToast(this,"手机号不能为空")
                return@setOnClickListener  //无法像java直接return
            }
            val pattern = "^1\\d{10}\$"
            if (!Pattern.matches(pattern, number)) {
                Toast.makeText(this,"手机号格式不正确",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendBtn.isClickable=false
            timer.start()

            Handler().postDelayed({

            },1000)
        }

        loginBtn.setOnClickListener(){
            if(isPhoneValid){
                val intent=Intent(this,SecondActivity::class.java)
                startActivity(intent)
            }

        }

    }

    inner class STSTimer (millisInFuture:Long,countDownInterval: Long): CountDownTimer(millisInFuture,countDownInterval) {
        override fun onFinish() {
            sendBtn.isClickable=true
            sendBtn.text="获取验证码"
        }

        override fun onTick(p0: Long) {
            sendBtn.text=(String.format("已发送(%1\$ds)",p0/1000))
        }
    }

    companion object{
        const val TAG="LoginActivity"
    }
}
