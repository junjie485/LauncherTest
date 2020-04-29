package com.kuaqu.launchertest

import com.google.gson.annotations.SerializedName

class ListBean() {

    constructor(img: Int, name: String, content: String, date: String):this() {
        this.img = img
        this.name = name
        this.content = content
        this.date = date
    }

    var img:Int=0
    @SerializedName("name")
     var name:String=""
    @SerializedName("content")
     var content=""

     var date=""
}