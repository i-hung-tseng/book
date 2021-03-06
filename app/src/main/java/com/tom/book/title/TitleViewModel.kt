package com.tom.book.title


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tom.book.room.Contact


class TitleViewModel:ViewModel(){

    var listLiveData:MutableLiveData<ArrayList<Contact>> = MutableLiveData()



    private var _bookesList: ArrayList<Contact> = arrayListOf<Contact>(
            Contact("你果你要吃飯，就吃麵",100),
            Contact("你",1),
            Contact("教你用1百元的鈔票，換到2個50元硬幣",200),
            Contact("面試經驗分享_How to be washed",250),
            Contact("格雷的媽媽為何取名叫做格雷媽媽",300),
            Contact("你南天氣晴~",350),
    )


    //下面一定要用val
    val booksList: ArrayList<Contact>
        get() = _bookesList


//    var empty: LiveData<Boolean> = Transformations.map(listLiveData){
//        it.isEmpty()
//    }


    fun add(item:Contact){
        _bookesList.add(0,item)
        Log.d("viewModle fun add ","viewModel fun add is work")
    }

    fun remove(item: Contact){
        _bookesList.remove(item)
    }

    fun modify(position:Int,item: Contact){
        Log.d("viewModel fun modify","viewModel fun modify is work")
        _bookesList.set(position,item)
    }


    fun search(newDataList:ArrayList<Contact>){
        _bookesList = newDataList
        Log.d("ViewModel fun search","viewModel search _bookList:$_bookesList")
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


