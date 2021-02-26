//package com.tom.book.room
//
//import android.content.Context
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
//        private var instance:Contactbase? = null
//        private var DB_NAME = "db_Name"
//
//        fun getInstance(context: Context):Contactbase{
//            return instance?: Room.databaseBuilder(context,Contactbase::class.java, DB_NAME)
//                .fallbackToDestructiveMigration()
//                .build().also { instance = it }
//        }
//    }
//
//    abstract fun contactDao(): ContactDao
//    val dao = Contactbase.getInstance().contactDao()
//
//
//}