package com.tom.book.title

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tom.book.databinding.AdapterTitleBinding
import com.tom.book.room.Contact


class TitleAdapter(private val contacts:ArrayList<Contact>):
    RecyclerView.Adapter<TitleAdapter.ViewHolder>() {



    inner class ViewHolder(val binding: AdapterTitleBinding):RecyclerView.ViewHolder
        (binding.root){
        fun bind(item: Contact){
            binding.adapterData = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent:ViewGroup, position: Int):ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTitleBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount():Int = contacts.size

    override fun onBindViewHolder(holder: TitleAdapter.ViewHolder, position: Int)=
        holder.bind(contacts[position])
    }