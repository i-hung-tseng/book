package com.tom.book.title

import androidx.lifecycle.ViewModel
import com.tom.book.room.Contact


class TitleViewModel:ViewModel() {



}


class MyViewModel(){
    fun getDataSet():ArrayList<Contact>{
       val database = arrayListOf<Contact>(
            Contact(1,"2",3),
            Contact(2,"2",3),
            Contact(3,"2",3),
        )

//        return contacts
        //return 給剛剛Fragment誰呼叫它
        return database
    }
}