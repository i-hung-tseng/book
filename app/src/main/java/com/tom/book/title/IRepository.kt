//package com.tom.book.title
//
//import com.tom.book.room.Contact
//
//interface IRepository {
//    fun getItems(itemCallback: ItemCallback)
//
//    interface ItemCallback {
//
//        fun onItemsResult(items: List<Contact>)
//    }
//}
//
//class Repository : IRepository {
//
//    override fun getItems(itemCallback: IRepository.ItemCallback) {
//        val list = mutableListOf<Contact>()
//        (1..20).forEach {
//            list.add(Contact(1,"tho",2))
//        }
//        itemCallback.onItemsResult(list)
//    }
//}