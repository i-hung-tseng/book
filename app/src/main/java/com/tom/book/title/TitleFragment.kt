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
import com.tom.book.room.Book
import viewModel.TitleViewModel


class TitleFragment : Fragment() {

    lateinit var binding: FragmentTitleBinding
    lateinit var titleAdapter: TitleAdapter
    lateinit var myRecyclerView: RecyclerView
    lateinit var myViewModel: TitleViewModel
    lateinit var dataset: ArrayList<Book>




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



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated","onViewCreated")

        // TODO: 2021/3/10 無法取道值，感覺是因為
//        val enterBookName = binding.edBookname.text.toString()
//        val enterBookePrice = binding.edPrice.text.toString()


//        myViewModel.bookList.observe(viewLifecycleOwner, {
//            Log.d("Fragment observe", "bookList be observed: ${myViewModel.bookList}")
//            titleAdapter.notifyDataSetChanged()
//        }

        // TODO: 2021/3/10 改成observe 
        myViewModel.bookList.observe(viewLifecycleOwner, object:Observer<ArrayList<Book>>{
            override fun onChanged(t: ArrayList<Book>) {
                Log.d("bookList obser", "obser")
//                titleAdapter.contacts = t
                //每次有更改後，就進入下面的funtion
                  titleAdapter.updateList(t)
//                titleAdapter.notifyDataSetChanged()
            }
        })

        // TODO: 2021/3/10 想要把這邊得到的資料放在外面，就不用在add跟search時拿到
//        val enterBookName = binding.edBookname.text.toString()
//        val enterBookePrice = binding.edPrice.text.toString()

        binding.btnAdd.setOnClickListener {
            add()
        }


        //通常來說blank層級會比較高，判斷沒有輸入值用blank會比較好
        binding.btnSerach.setOnClickListener {
            if (myViewModel.sampleBookList.isNotEmpty()) {
                val enterBookName = binding.edBookname.text.toString()
                val enterBookePrice = binding.edPrice.text.toString()
                if (enterBookName.isNotBlank() || enterBookePrice.isNotBlank()) {

                    // TODO: 2021/3/6 加上雙重查詢(Price)

                    search(enterBookName,enterBookePrice)
                    Log.d("Fragment' Click search", "btn.search $enterBookName")
                } else if (enterBookName.isBlank() && enterBookePrice.isBlank()) {
                    Toast.makeText(requireActivity(), "貼心小T醒❤ 你他媽把字填完 >< 💦", Toast.LENGTH_LONG).show()
                }
            } else {
                myViewModel.doInitBookeList()
            }
        }

    }

    //參數裡面可以先有參數值，{}裡面的參數也可以進行操作，但是實際上是什麼數值還不確定，要等呼叫他的時候決定
    //點擊Item後
    fun itemSelected(display: Int): Unit {
        binding.apply {
            edBookname.setText(dataset[display].bookName)
            edPrice.setText(dataset[display].bookPrice.toString())
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
            val afterFilterList = myViewModel.sampleBookList.filter { cont -> cont.bookName.contains(newBookName) } as ArrayList<Book>
            Log.d("Fragment add", "add if NotEmpty sampleBookList:${myViewModel.sampleBookList}")
            if (afterFilterList.isNotEmpty()) {
                addButHaveOne(newBookName, newPrice.toInt())
                Toast.makeText(requireActivity(),"已經有同名之書籍^^",Toast.LENGTH_LONG).show()
            } else {
                val item1 = Book(newBookName, newPrice.toInt())
                myViewModel.add(item1)
                Log.d("Fragment add", "bookList:${myViewModel.bookList}")
                editTextClearandNotifyChanged()
            }
        } else {
            Toast.makeText(requireActivity(), "請輸入有效名稱!", Toast.LENGTH_LONG).show()

        }
    }


    private fun remove(position: Int) {
        myViewModel.remove(dataset[position])
    }


    private fun modify(position: Int) {
        Log.d("Fragment's fun modify", "Fragment's modify is work")
        val newBookName = binding.edBookname.text.toString()
        val newPrice = binding.edPrice.text.toString()
        if (newBookName.isNotEmpty() && newPrice.isNotEmpty()) {
            val item1 = Book(newBookName, newPrice.toInt())
            myViewModel.modify(position, item1)
            editTextClearandNotifyChanged()
        } else {
            Toast.makeText(requireActivity(), "重新輸入修改內容!", Toast.LENGTH_LONG).show()
        }
    }

    private fun search(enterBookName:String,enterBookePrice:String) {
//        myViewModel.booksList
        myViewModel.search(enterBookName,enterBookePrice)



//            if(searchBook in myViewModel.booksList.contains() && searchPrice.toInt() in myViewModel.booksList){
//
//                Toast.makeText(requireActivity(),"KeyWord in the bookList",Toast.LENGTH_LONG).show()
//            }
//            else{
//                Toast.makeText(requireActivity(),"KeyWord is not in the bookList",Toast.LENGTH_LONG).show()
//            }
    }

    private fun addButHaveOne(enterBookName:String,enterPrice:Int){
        val newDataList = myViewModel.sampleBookList.filter { cont -> cont.bookName.contains(enterBookName) } as ArrayList<Book>
        myViewModel.addedButHaveOne(newDataList)

    }


    private fun editTextClearandNotifyChanged() {
        binding.apply {
            edBookname.text.clear()
            edPrice.text.clear()
        }
//        titleAdapter.notifyDataSetChanged()
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
        titleAdapter = TitleAdapter(
                object : IOnClickHandler {
                    override fun onAction(pos: Int) {
                        //this代表當下物件，已經有給匿名類別了 所以this後面只能跑出 IOnClickHandler介面的東西
                        this@TitleFragment.itemSelected(pos)
                    }
                },
        )
        myRecyclerView.adapter = titleAdapter
    }


    private fun initViewModel() {
        //透過ViewModelProvider實例化下面的MyViewModel
        myViewModel = ViewModelProvider(requireActivity()).get(TitleViewModel::class.java)
        //指派一個變數，裡面有myViewModel裡面固定的ArrayList
        dataset = myViewModel.sampleBookList



    }
}
