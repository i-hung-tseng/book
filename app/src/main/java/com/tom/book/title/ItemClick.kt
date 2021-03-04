package com.tom.book.title

import android.view.View
import com.tom.book.room.Contact

interface OnItemClick {

    fun OnItemClick(contacts:Contact,position:Int){

    }
}