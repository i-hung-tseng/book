package com.tom.book.room

import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Book(
//    val contactName:String,
//    val contactPrice:Int
//) {} // end data class.

//確認為什麼需要init
@Entity(tableName = "book_table")
data class Book(
//    @PrimaryKey(autoGenerate = true)
//    var bookId:Long = 0L,
    var bookName:String = "",
    var bookPrice:Int = 0
)