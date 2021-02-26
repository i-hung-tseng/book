package com.tom.book.room

data class Contact(
    val contactNum:Int,
    val contactName:String,
    val contactPrice:Int
) {} // end data class.

var contacts = arrayListOf<Contact>(
    Contact(1,"2",2),
    Contact(2,"2",3),
)
