package com.tom.book.room


import androidx.room.*



//@Dao
//interface BookDao{
//
//    //* 叫出所有資料    這邊先用ArrayList
//    @Query("Select * from book_table")
//    fun getAlphabetizedBooks(): ArrayList<Book>
//
//    //新增
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(book: Book)
//
//    //* 查詢 裡面的Like 後面要填寫要查詢的資料，查詢資料，還傳整筆資料
//    @Query("delete from book_table where bookName Like bookName Limit 1")
//    suspend fun queryById(bookName:String):Book
//
//    // *刪除
//    @Delete
//    suspend fun deleteAll(book: Book):Int
//
//    // *修改
//    @Update
//    suspend fun update(book: Book)
//
//}