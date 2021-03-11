package com.tom.book.title


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tom.book.room.Contact


class TitleViewModel:ViewModel() {


//      private var _bookesList = MutableLiveData<Contact>()
//      val booksList:LiveData<Contact>
//        get() = _bookesList


//          private fun resetList(){
//            booksList = mutableListOf<Contact>(
//            Contact("帶你分析中國老鐵情懷",199),
//            Contact("如何度過悠閒的禮拜四下午?",500),
//            Contact("富爸爸與窮爸爸",200),
//            Contact("色彩學經典",250),
//            Contact("高雄17天必去觀光景點",300),
//            Contact("深入直擊福原愛家庭內幕",99),
//          )
//      }

    private val initBookeList :ArrayList<Contact> = arrayListOf(
        Contact("帶你分析中國老鐵情懷", 199),
        Contact("如何度過悠閒的禮拜四下午?", 500),
        Contact("富爸爸與窮爸爸", 200),
        Contact("色彩學經典", 250),
        Contact("高雄17天必去觀光景點", 300),
        Contact("深入直擊福原愛家庭內幕", 99),
    )




    val sampleBookList:ArrayList<Contact>
    get() = _sampleBookList

//
    private val _sampleBookList: ArrayList<Contact> = arrayListOf<Contact>()

    private val _bookesList: MutableLiveData<ArrayList<Contact>> = MutableLiveData<ArrayList<Contact>>(
//           sampleBookList
    )

    val bookList:LiveData<ArrayList<Contact>>
    get() = _bookesList

//    var booklistLiveData : MutableLiveData<List<Contact>> = MutableLiveData()
//    fun getData(){
//            val _bookesList : MutableLiveData<Contact> =
//                 MutableLiveData <Contact>(
//                Contact("帶你分析中國老鐵情懷",199),
//                Contact("如何度過悠閒的禮拜四下午?",500),
//                Contact("富爸爸與窮爸爸",200),
//                Contact("色彩學經典",250),
//                Contact("高雄17天必去觀光景點",300),
//                Contact("深入直擊福原愛家庭內幕",99))
//
//    }

//    //下面一定要用val
//    val booksList: ArrayList<Contact>
//        get() = _bookesList


//    var empty: LiveData<Boolean> = Transformations.map(listLiveData){
//        it.isEmpty()
//    }


    fun add(item: Contact) {
        Log.d("viewModle fun add ", "viewModel fun add is work")
        _sampleBookList.add(0, item)
        _bookesList.postValue(_sampleBookList)
    }

    fun remove(item: Contact) {
        _sampleBookList.remove(item)
        _bookesList.postValue(_sampleBookList)
    }

    fun modify(position: Int, item: Contact) {
        Log.d("viewModel fun modify", "viewModel fun modify is work")
        _sampleBookList.set(position, item)
        _bookesList.postValue(_sampleBookList)
    }


    fun search(enterBookName:String,enterBookePrice:String) {
        // TODO: 2021/3/7 為何不能直接用 _bookList = newDataList?
        val newDataList = sampleBookList.filter { cont -> cont.contactName.contains(enterBookName) } as ArrayList<Contact>
        _sampleBookList.clear()
        _sampleBookList.addAll(newDataList)
        Log.d("ViewModel fun search", "viewModel search _bookList:$_bookesList")
        _bookesList.postValue(_sampleBookList)
    }

    fun doInitBookeList(){
        _sampleBookList.addAll(initBookeList)
        _bookesList.postValue(_sampleBookList)
    }

    fun addedButHaveOne(newDataList: ArrayList<Contact>){
        _sampleBookList.clear()
        _sampleBookList.addAll(newDataList)
        Log.d("ViewModel fun search", "viewModel search _bookList:$_bookesList")
        _bookesList.postValue(_sampleBookList)
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


