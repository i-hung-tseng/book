package com.tom.book.title

import android.os.Bundle
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
    val newBookName = binding.edBookname.text.toString()
    val newPrice = binding.edPrice.text.toString()



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_title,
                container,
                false
        )

        //指定Recyclerview給LinearLayoutManager
        myRecyclerView = binding.recyclerviewView
        myRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())


        //透過ViewModelProvider實例化下面的MyViewModel
        myViewModel = ViewModelProvider(requireActivity()).get(TitleViewModel::class.java)


        //指派一個變數，裡面有myViewModel裡面固定的ArrayList
        dataset = myViewModel.booksList

        //實例化一個Adapter，並且裡面有我定義在Adapter的參數，包含了型態為ArrayList跟
        // 型態為IonClickHandler的Interface    ★Interface不能實例化，故要搭配Class
        //override 是先定義裡面需要做什麼，但是還沒有去呼叫它，所以要在Adapter呼叫
        titleAdapter = TitleAdapter(dataset, object : IOnClickHandler {
            override fun onAction(pos: Int) {
                //this代表當下物件，已經有給匿名類別了 所以this後面只能跑出 IOnClickHandler介面的東西
                this@TitleFragment.itemSelected(pos)
            }
        })
        //把RecyclerView綁定給Adapter
        binding.recyclerviewView.adapter = titleAdapter


        //現在暫時無作用
//        myViewModel.listLiveData.observe(requireActivity(),
//        Observer<List<Contact>> {
//            titleAdapter.notifyDataSetChanged()
//        })

//        binding.btnAdd.setOnClickListener {
//            if (TextUtils.isEmpty(editBookName.text.toString())) {
//                var editBookName = binding.edBookname.text.toString()
//                var editBookPrice = binding.edPrice.text.toString().toInt()
//                val item1 = Contact(editBookName, editBookPrice)
//                myViewModel.add(item1)
//                binding.apply {
//                    edBookname.text.clear()
//                    edPrice.text.clear()
//                }
//            } else {
//                Toast.makeText(requireActivity(), "請輸入有效名稱!", Toast.LENGTH_LONG).show()
//            }
//        }


        binding.btnAdd.setOnClickListener {
           add()
        }
        return binding.root
//    }


//            val newBookName = binding.edBookname.text.toString()
//            val newPrice = binding.edPrice.text.toString().toInt()
//            val newPriceString = newPrice.toString()
//            if(newBookName.isBlank()||newPriceString.isBlank()){
//                Toast.makeText(requireActivity(),"請輸入有效名稱!",Toast.LENGTH_LONG).show()
//            }else {
//                val item1 = Contact(newBookName,newPrice)
//                myViewModel.add(item1)
//                binding.apply {
//                    edBookname.text.clear()
//                    edPrice.text.clear()
//                }
    }
        //參數裡面可以先有參數值，{}裡面的參數也可以進行操作，但是實際上是什麼數值還不確定，要等呼叫他的時候決定
        //點擊Item後
        fun itemSelected(display: Int): Unit {
            binding.apply {
                edBookname.setText(dataset[display].contactName)
                edPrice.setText(dataset[display].contactPrice.toString())
            }
        }

        fun add(){
            //Int不可為空字串，所以在變數的時候不能轉成Int()

            if (newBookName.isNotEmpty() && newPrice.isNotEmpty()) {
                val item1 = Contact(newBookName,newPrice.toInt())
                myViewModel.add(item1)
                binding.apply {
                    edBookname.text.clear()
                    edPrice.text.clear()
                }
            } else {
                Toast.makeText(requireActivity(), "請輸入有效名稱!", Toast.LENGTH_LONG).show()

            }
            titleAdapter.notifyDataSetChanged()
        }
//
//        fun modify(display: Int){
//            if(newBookName)
//
//        }




    }


//(以下為為簡化的)

//        val mainLinearLayoutManager = LinearLayoutManager(requireActivity())
//        mainLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        val mainView = binding.recyclerviewView
//        mainView.layoutManager = mainLinearLayoutManager
//        titleAdapter = TitleAdapter(contacts)
//        mainView.adapter = titleAdapter

