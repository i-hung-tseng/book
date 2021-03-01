package com.tom.book.title

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tom.book.room.Contact
import com.tom.book.room.contacts


class TitleViewModel:ViewModel(){

    var listLiveData:MutableLiveData<ArrayList<Contact>> = MutableLiveData()
    var newlist = arrayListOf<Contact>()

//    var empty: LiveData<Boolean> = Transformations.map(listLiveData){
//        it.isEmpty()
//    }

    init {
        getData()
    }

    fun getData():ArrayList<Contact> {

        return contacts
            }

    fun add(item:Contact){
        contacts.add(0,item)
    }

    fun remove(item: Contact){
        newlist.remove(item)
        val listLiveData1 = listLiveData
    }


}




//        })
//                }
//            }
//
//






//以下是沒繼承ViewModel的class
//class MyViewModel(){
//    fun getDataSet():ArrayList<Contact>{
//       val database = arrayListOf<Contact>(
//            Contact(1,"2",3),
//            Contact(2,"2",3),
//            Contact(3,"2",3),
//        )

//        return contacts
        //return 給剛剛Fragment誰呼叫它
//        return database
//    }
//}