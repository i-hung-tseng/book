package com.tom.book.title

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tom.book.R
import com.tom.book.databinding.FragmentTitleBinding
import com.tom.book.room.Contact


class TitleFragment : Fragment() {

    lateinit var binding: FragmentTitleBinding
    lateinit var titleAdapter: TitleAdapter
    lateinit var myRecyclerView: RecyclerView
    lateinit var myViewModel: TitleViewModel
    lateinit var dataset: ArrayList<Contact>
    lateinit var updateList: ArrayList<Contact>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_title,
                container,
                false)

        initViewModel()
        initAdapter()
//        val enterBookName = binding.edBookname.text.toString()
//        val enterBookePrice = binding.edPrice.text.toString()

        binding.btnAdd.setOnClickListener {
            add()
        }

        //通常來說blank層級會比較高，判斷沒有輸入值用blank會比較好
        binding.btnSerach.setOnClickListener {
            val enterBookName = binding.edBookname.text.toString()
            val enterBookePrice = binding.edPrice.text.toString()
            Log.d("Fragment's search", "Fragment's search is work")
            if (enterBookName.isNotBlank() || enterBookePrice.isNotBlank()) {
                // TODO: 2021/3/6 加上雙重查詢(Price)
                val newDataList = myViewModel.booksList.filter { cont -> cont.contactName.contains(enterBookName)} as ArrayList<Contact>
                search(newDataList)
                Log.d("Fragment' Click search","after filter newData:$newDataList")
            } else {
                Toast.makeText(requireActivity(), "貼心小T醒❤ 你他媽把字填完 >< 💦", Toast.LENGTH_LONG).show()
            }
//
//            if (enterBookName.isNotBlank() && enterBookePrice.isBlank()) {
//                val newNameList = myViewModel.booksList.filter { cont -> cont.contactName.contains(enterBookName) } as ArrayList<Contact>
//                search(newNameList)
//                Log.d("Fragment' Click search", "after filter enterBookName newData:$newNameList")
//            }
//            else if (enterBookName.isBlank() && enterBookePrice.isNotBlank()){
//                val  newPriceList = myViewModel.booksList.filter { conPrice -> conPrice.contactPrice.toString().contains(enterBookePrice)} as ArrayList<Contact>
//                search(newPriceList)
//                Log.d("Fragment' Click search", "after filter enterBookePrice newData:$newPriceList")
//            }
//            else{
//                Toast.makeText(requireActivity(), "貼心小T醒❤ 你他媽把字填完 >< 💦", Toast.LENGTH_LONG).show()
//            }
        }
        return binding.root
    }

    //參數裡面可以先有參數值，{}裡面的參數也可以進行操作，但是實際上是什麼數值還不確定，要等呼叫他的時候決定
    //點擊Item後
    fun itemSelected(display: Int): Unit {
        binding.apply {
            edBookname.setText(dataset[display].contactName)
            edPrice.setText(dataset[display].contactPrice.toString())
        }
        Log.d("onItemSelected", "$display")

        binding.btnDelete.setOnClickListener {
            remove(display)
            editTextClearandNotifyChanged()
        }

        binding.btnModify.setOnClickListener {
            modify(display)

        }
    }


    private fun add() {
        //Int不可為空字串，所以在變數的時候不能轉成Int()
        val newBookName = binding.edBookname.text.toString()
        val newPrice = binding.edPrice.text.toString()
        if (newBookName.isNotEmpty() && newPrice.isNotEmpty()) {
            val item1 = Contact(newBookName, newPrice.toInt())
            myViewModel.add(item1)
            Log.d("Fragment fun add","Fragment fun add is work")
            editTextClearandNotifyChanged()
        } else {
            Toast.makeText(requireActivity(), "請輸入有效名稱!", Toast.LENGTH_LONG).show()

        }
        titleAdapter.notifyDataSetChanged()
    }


    private fun remove(position: Int){
        myViewModel.remove(dataset[position])
    }


    private fun modify(position: Int) {
        Log.d("Fragment's fun modify", "Fragment's modify is work")
        val newBookName = binding.edBookname.text.toString()
        val newPrice = binding.edPrice.text.toString()
        if (newBookName.isNotEmpty() && newPrice.isNotEmpty()) {
            val item1 = Contact(newBookName, newPrice.toInt())
            myViewModel.modify(position, item1)
            // TODO: 2021/3/6 add clear editText in here 
            editTextClearandNotifyChanged()
        } else {
            Toast.makeText(requireActivity(), "重新輸入修改內容!", Toast.LENGTH_LONG).show()
        }
    }

    private fun search(newDataList:ArrayList<Contact>) {
//        myViewModel.booksList
        myViewModel.search(newDataList)
        titleAdapter.notifyDataSetChanged()


//            if(searchBook in myViewModel.booksList.contains() && searchPrice.toInt() in myViewModel.booksList){
//
//                Toast.makeText(requireActivity(),"KeyWord in the bookList",Toast.LENGTH_LONG).show()
//            }
//            else{
//                Toast.makeText(requireActivity(),"KeyWord is not in the bookList",Toast.LENGTH_LONG).show()
//            }
    }


    private fun editTextClearandNotifyChanged() {
        binding.apply {
            edBookname.text.clear()
            edPrice.text.clear()
        }
        titleAdapter.notifyDataSetChanged()
    }


//(以下為為簡化的)

//        val mainLinearLayoutManager = LinearLayoutManager(requireActivity())
//        mainLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        val mainView = binding.recyclerviewView
//        mainView.layoutManager = mainLinearLayoutManager
//        titleAdapter = TitleAdapter(contacts)
//        mainView.adapter = titleAdapter





    private fun initAdapter() {
        //指定Recyclerview給LinearLayoutManager
        myRecyclerView = binding.recyclerviewView
        myRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())


        //實例化一個Adapter，並且裡面有我定義在Adapter的參數，包含了型態為ArrayList跟
        // 型態為IonClickHandler的Interface    ★Interface不能實例化，故要搭配Class
        //override 是先定義裡面需要做什麼，但是還沒有去呼叫它，所以要在Adapter呼叫
        titleAdapter = TitleAdapter(dataset, object : IOnClickHandler {
            override fun onAction(pos: Int) {
                //this代表當下物件，已經有給匿名類別了 所以this後面只能跑出 IOnClickHandler介面的東西
                this@TitleFragment.itemSelected(pos)
            }
        },)
        myRecyclerView.adapter = titleAdapter
    }


    private fun initViewModel() {
        //透過ViewModelProvider實例化下面的MyViewModel
        myViewModel = ViewModelProvider(requireActivity()).get(TitleViewModel::class.java)
        //指派一個變數，裡面有myViewModel裡面固定的ArrayList
        dataset = myViewModel.booksList

    }
}
