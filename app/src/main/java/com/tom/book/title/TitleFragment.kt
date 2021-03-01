package com.tom.book.title

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tom.book.R
import com.tom.book.databinding.FragmentTitleBinding
import com.tom.book.room.Contact
import com.tom.book.room.contacts


class TitleFragment : Fragment() {

    lateinit var binding : FragmentTitleBinding
    lateinit var titleAdapter: TitleAdapter
    lateinit var myRecyclerView: RecyclerView
    lateinit var myViewModel: TitleViewModel

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

        //recylcer綁定binding
        myRecyclerView = binding.recyclerviewView
        myRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        //實例化下面的MyViewModel，傳入建構值
        //companion object 的話不用實例化就可以點後面的()，就可以不用每次都實例化耗能
        //之後要放viewModel的實例化，  用 provider去拿到viewModel的實例

        //以下是還沒有改成ViewModel之前
//        val myViewModel: MyViewModel = MyViewModel()

        myViewModel = ViewModelProvider(requireActivity()).get(TitleViewModel::class.java)
//        val dataset: ArrayList<Contact> = myViewModel.getDataSet()   // 我需要一個 ArrayList<Contact>
        val dataset = myViewModel.getData()
        titleAdapter = TitleAdapter(dataset)
        binding.recyclerviewView.adapter = titleAdapter

        //改之前
//        myRecyclerView.adapter = titleAdapter

        myViewModel.listLiveData.observe(requireActivity(),
        Observer<List<Contact>> {
            titleAdapter.notifyDataSetChanged()
        })

//        val mainLinearLayoutManager = LinearLayoutManager(requireActivity())
//        mainLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        val mainView = binding.recyclerviewView
//        mainView.layoutManager = mainLinearLayoutManager
//        titleAdapter = TitleAdapter(contacts)
//        mainView.adapter = titleAdapter


        binding.btnAdd.setOnClickListener(){
            var newBookName = binding.edBookname.toString()
            var newprice = binding.edPrice.text.toString().toInt()
            if(newBookName.isNullOrBlank()){
                Toast.makeText(requireActivity(),"請輸入有效名稱!",Toast.LENGTH_LONG).show()
            }else {
                var item1 = Contact(1,newBookName,newprice)
                myViewModel.add(item1)
                binding.edBookname.text.clear()
                binding.edPrice.text.clear()
                Log.e("TitleFragment","enter edit_name&price")
                titleAdapter.notifyItemInserted(0)

            }
            titleAdapter.notifyDataSetChanged()
        }

        return binding.root


    }
}



