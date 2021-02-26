//package com.tom.book.room
//
//
//import androidx.room.*
//

// TODO: 2021/2/26 class:後面是型態，funtion後面是回傳值
//@Dao
//interface ContactDao{
//
//    @Query("SELECT * FROM" + ContactEntity.TABLE_NAME)
//    fun getAll():ArrayList<Contact>
//
//    //再多加一個Query
//    @Query("SELECT*FROM" + ContactEntity.TABLE_NAME + "where contactNum Like:Num LIMIT 1 " )
//
//
//    @Insert
//    fun insert(contact: ContactEntity):Long
//
//    @Update
//    fun update(contact: ContactEntity):Int
//
//    @Delete
//    fun delete(contact: ContactEntity):Int
//}