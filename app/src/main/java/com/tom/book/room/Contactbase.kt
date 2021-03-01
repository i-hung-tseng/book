//package com.tom.book.room
//
//import ContactEntity
//import android.content.Context
//import android.os.AsyncTask
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [(ContactEntity::class)],version = 1)
//
////代表一個TABLE
//abstract class Contactbase:RoomDatabase(){
//
//    //其他地方需呼叫時，只需要加上class名稱.即可呼叫
//    companion object{
//        private var DB_NAME = "db_Name"
//    }
//
//    abstract fun getContactDao():ContactDao
//    override fun init(applicationContext: Context) {
//        AsyncTask.execute({
//            var contactDatabase = Room.databaseBuilder(applicationContext,Contactbase::class.java,Contactbase.DB_NAME)
//                .build()
//        })
//    }
//
//}