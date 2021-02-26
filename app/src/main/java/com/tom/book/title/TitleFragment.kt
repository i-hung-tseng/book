package com.tom.book.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

        myRecyclerView = binding.recyclerviewView
        myRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        //實例化下面的MyViewModel，傳入建構值
        //companion object 的話不用實例化就可以點後面的()，就可以不用每次都實例化耗能
        //之後要放viewModel的實例化，  用 provider去拿到viewModel的實例
        val myViewModel: MyViewModel = MyViewModel()
        val dataset: ArrayList<Contact> = myViewModel.getDataSet()   // 我需要一個 ArrayList<Contact>
        titleAdapter = TitleAdapter(dataset)
        myRecyclerView.adapter = titleAdapter

//        val mainLinearLayoutManager = LinearLayoutManager(requireActivity())
//        mainLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        val mainView = binding.recyclerviewView
//        mainView.layoutManager = mainLinearLayoutManager
//        titleAdapter = TitleAdapter(contacts)
//        mainView.adapter = titleAdapter

        return binding.root
    }
}



