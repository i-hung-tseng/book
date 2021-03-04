package com.tom.book.title


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tom.book.room.Contact


class TitleViewModel:ViewModel(){

    var listLiveData:MutableLiveData<ArrayList<Contact>> = MutableLiveData()



    private var _bookesList: ArrayList<Contact> = arrayListOf<Contact>(
            Contact("如果你要吃飯，就吃麵",100),
            Contact("被喜歡的勇氣",150),
            Contact("教你用1百元的鈔票，換到2個50元硬幣",200),
            Contact("面試經驗分享_WangWang",250),
            Contact("格雷的媽媽為何取名叫做格雷媽媽",300),
    )


    //下面一定要用val
    val booksList: ArrayList<Contact>
        get() = _bookesList


//    var empty: LiveData<Boolean> = Transformations.map(listLiveData){
//        it.isEmpty()
//    }

    fun getData():ArrayList<Contact> {
//        newlist =  arrayListOf<Contact>(
//                Contact(1,"2",2),
//                Contact(2,"2",3),
//                Contact(3,"哈",5),
//                Contact(4,"哈",5)
//        )

        return booksList

    }

    fun add(item:Contact){
        _bookesList.add(0,item)
    }

    fun remove(item: Contact){
        _bookesList.remove(item)
    }

    fun modify(item: Contact){
//        _bookesList.set()
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


