package com.tom.book.room

import androidx.room.Entity

//data class Book(
//    val contactName:String,
//    val contactPrice:Int
//) {} // end data class.

//確認為什麼需要init
@Entity(tableName = "book_table")
data class Book(
    var bookName:String = "",
    var bookPrice:Int = 0
)