package com.tom.book.title

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tom.book.databinding.AdapterTitleBinding
import com.tom.book.room.Contact


        //建構值代表，我今天呼叫這個Adapter時候，就已經實例化，我一定要傳入這些參數，並且我利用這些參數去做一些事情
class TitleAdapter(
        private val contacts:ArrayList<Contact>,
        //Interface 是不能直接實例化，一定要透過class才能實例化
        private var myClickHandler: IOnClickHandler
): RecyclerView.Adapter<TitleAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: AdapterTitleBinding) : RecyclerView.ViewHolder
    (binding.root) {
        fun bind(item: Contact) {
            //透過綁定 viewHolder裡面的layout位置(透過id)，去賦予(dataClass裡面變數的值)
            //binding.teBookName.text = item.contactNum.toString()
            //賦予ViewHolder內Layout裡面自己定義的variable名稱，給dataClass裡面的值
            //全部位置都是透過viewHolder裡面的 text去決定如(adapterData.contactName)
            binding.adapterData = item
            binding.executePendingBindings()
        }
    }

//    fun setOnItemClickListener(listener:OnItemClickListener){
//        this.onItemClickListener = listener
//    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTitleBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contacts.size

    //當Recyclerview滑動時，需要更新資料時，會丟欲顯示之position，當盡到funtion時，就已經更新了

    override fun onBindViewHolder(holder: TitleAdapter.ViewHolder, position: Int) {

        var pickedItem = contacts[position]
        //綁定contacts 裡面的 特定位置 position

        holder.bind(pickedItem)

        holder.itemView.setOnClickListener {
            Log.d("setOnClickListener", " posit: $pickedItem")
            myClickHandler.onAction(position)
        }



//            myClickHandler.onAction(pos = position)
//        setOnClickListener的原本方式
            //View.OnClickListener 是一個interface，並且無法直接被實例化
            //所以需要讓一個class繼承，並且實例化該class
//
//        }


    }
}


    //Interface是因為有時候funtion會在其他地方定義是因為，需要在其他地方如Fragment存取資料
    interface IOnClickHandler {
//        fun onAction(binding: AdapterTitleBinding,position: Int){
        abstract fun onAction(pos:Int): Unit
    }

