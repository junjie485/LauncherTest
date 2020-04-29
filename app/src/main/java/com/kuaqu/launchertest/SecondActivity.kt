package com.kuaqu.launchertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_second.*
import java.util.ArrayList

class SecondActivity : AppCompatActivity() ,SecondAdapter.itemListner{
    //lateinit 延时初始化，之作用于var 。lazy 只作用于val
    //继承与接口用,号隔开，系统会自动区分
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: SecondAdapter
    private lateinit var mList:MutableList<ListBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initData()
    }

    private fun initData() {

        titleTv.setText("代码设置标题")
        mList=ArrayList()
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter= SecondAdapter(this,mList)
        recyclerView.adapter =adapter

        for(index in 0..10){
            mList.add(ListBean(R.mipmap.ic_launcher,"你的名字","第$index 条数据","2020-4-21"))
        }
        adapter.notifyDataSetChanged()
        adapter.setListnerCallBack(this)

        for(bean in mList){
            println("${bean.content},${bean.name}")
        }

        //点击事件的两种写法
        titleTv.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {

            }
        })
        titleTv.setOnClickListener {

        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,"点击了$position",Toast.LENGTH_SHORT).show()
    }
}
