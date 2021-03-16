//package com.tom.book.room

//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.RoomMasterTable.TABLE_NAME
//
//@Entity(tableName = ContactEntity.TABLE_NAME)
//class ContactEntity{
//    //可以不用實例化就透過class呼叫裡面物件
//    companion object{
//        const val TABLE_NAME = "contactEntity"
//    }
//
//    @PrimaryKey(autoGenerate = true)
//    var contactNum:Int = 0
//    var contactName:String = ""
//    var contactPrice:Int = 0

//    沒寫 ColumnInfo跟 ignoreText
//}

//把data class Contact 寫死
//data class Contact(
//    val contactNum:Int,
//    val contactName:String,
//    val contactPrice:Int
//)
//
//val contacts = arrayListOf<Contact>(
//    Contact(1,"2",2),
//    Contact(1,"2",2),
//)