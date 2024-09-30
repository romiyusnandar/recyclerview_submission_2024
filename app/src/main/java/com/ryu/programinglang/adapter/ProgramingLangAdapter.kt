package com.ryu.programinglang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryu.programinglang.ProgramingLang
import com.ryu.programinglang.R
import com.ryu.programinglang.databinding.ItemRowBinding

class ProgramingLangAdapter(private val listProgramingLang: ArrayList<ProgramingLang>) : RecyclerView.Adapter<ProgramingLangAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProgramingLang.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listProgramingLang[position]
        holder.bind(name, description, photo)
    }

    class ListViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, description: String, photo: String) {
            binding.tvTitle.text = name
            binding.tvDescription.text = description

            Glide.with(binding.root.context)
                .load(photo)
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .override(120, 120)
                .into(binding.imgItemPhoto)
        }
    }
}
