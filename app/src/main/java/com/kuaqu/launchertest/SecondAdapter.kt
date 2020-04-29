package com.kuaqu.launchertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SecondAdapter(private val activity: SecondActivity, private val list: List<ListBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var listner:itemListner
    interface itemListner{
        fun onItemClick(position:Int)
    }
    fun setListnerCallBack(l:itemListner){
        this.listner=l
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(activity).inflate(R.layout.second_list_item, parent, false)
        return DefaultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder: DefaultViewHolder = viewHolder as DefaultViewHolder

        holder.imgTv.setImageResource(list.get(position).img)
        holder.nameTv.text=list.get(position).name
        holder.contentTv.text=list.get(position).content
        holder.dateTv.text=list.get(position).date
        holder.itemView.setOnClickListener {
            listner.onItemClick(position)
        }
    }

    class DefaultViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var imgTv = view.findViewById<ImageView>(R.id.imgIv)
        var nameTv = view.findViewById<TextView>(R.id.nameTv)
        var contentTv = view.findViewById<TextView>(R.id.contentTv)
        var dateTv = view.findViewById<TextView>(R.id.dateTv)
    }
}