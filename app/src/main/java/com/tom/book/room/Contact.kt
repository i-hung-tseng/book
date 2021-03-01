package com.tom.book.room

data class Contact(
    val contactNum:Int,
    val contactName:String,
    val contactPrice:Int
) {} // end data class.

//自訂義arrayList內容
var contacts = arrayListOf<Contact>(
    Contact(1,"2",2),
    Contact(2,"2",3),
)
