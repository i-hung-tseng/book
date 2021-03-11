package com.tom.book.title

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tom.book.R
import com.tom.book.databinding.AdapterTitleBinding
import com.tom.book.room.Contact


        //建構值代表，我今天呼叫這個Adapter時候，就已經實例化，我一定要傳入這些參數，並且我利用這些參數去做一些事情
class TitleAdapter(
        //Interface 是不能直接實例化，一定要透過class才能實例化
        private var myClickHandler: IOnClickHandler,
//        private val searchNewList:ArrayList<Contact>
): RecyclerView.Adapter<TitleAdapter.ViewHolder>() {

            //先製作一個空的質
            private val contacts:ArrayList<Contact> = arrayListOf()

            //清除所有資料，再給一個新的質
             fun updateList(list:ArrayList<Contact>){
                contacts.clear()
                contacts.addAll(list)
                notifyDataSetChanged()
            }

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

        // TODO: 2021/3/10 這邊原本沒有參考adapter_title的Layout，但是還是可以拿到資料
        val binding = DataBindingUtil.inflate<AdapterTitleBinding>(LayoutInflater.
        from(parent.context), R.layout.adapter_title, parent, false)
        return ViewHolder(binding)

        //原本是寫下面的
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = AdapterTitleBinding.inflate(inflater)
//        return ViewHolder(binding)


    }

    override fun getItemCount(): Int = contacts.size

    //當Recyclerview滑動時，需要更新資料時，會丟欲顯示之position，當盡到funtion時，就已經更新了

    override fun onBindViewHolder(holder: TitleAdapter.ViewHolder, position: Int) {

        var pickedItem = contacts[position]
        //綁定contacts 裡面的 特定位置 position

        holder.bind(pickedItem)

        holder.itemView.setOnClickListener {
            Log.d("setOnClickListener", " nowPosition: $position")
            myClickHandler.onAction(position)
        }



//            myClickHandler.onAction(pos = position)
//        setOnClickListener的原本方式
            //View.OnClickListener 是一個interface，並且無法直接被實例化
            //所以需要讓一個class繼承，並且實例化該class
//
//        }



    }
//            private fun filter(searchNewList: ArrayList<Contact>){
//            contacts.clear()
//            contacts.addAll(searchNewList)
//
//
//            }
}


    //Interface是因為有時候funtion會在其他地方定義是因為，需要在其他地方如Fragment存取資料
    interface IOnClickHandler {
//        fun onAction(binding: AdapterTitleBinding,position: Int){
        //
        //Interface介面純粹設計要有這個funtion，但是裡面的定義是要到實作他的時候才去定義
        abstract fun onAction(pos:Int): Unit
    }




