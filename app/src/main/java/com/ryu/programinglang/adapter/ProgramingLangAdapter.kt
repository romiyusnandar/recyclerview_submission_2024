package com.ryu.programinglang.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryu.programinglang.ProgramingLang
import com.ryu.programinglang.R
import com.ryu.programinglang.databinding.ItemRowBinding
import com.ryu.programinglang.ui.DetailActivity

class ProgramingLangAdapter(private val listProgramingLang: ArrayList<ProgramingLang>) : RecyclerView.Adapter<ProgramingLangAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProgramingLang.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, history, founder, developed, version) = listProgramingLang[position]
        holder.bind(name, description, photo, history, founder, developed, version)
    }

    class ListViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, description: String, photo: String, history: String, founder: String, developed: String, version: String) {
            binding.tvTitle.text = name
            binding.tvDescription.text = description

            Glide.with(binding.root.context)
                .load(photo)
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .override(85, 85)
                .into(binding.imgItemPhoto)

            itemView.setOnClickListener{
                val intentDetail = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_NAME, name)
                    putExtra(DetailActivity.EXTRA_DESCRIPTION, description)
                    putExtra(DetailActivity.EXTRA_PHOTO, photo)
                    putExtra(DetailActivity.EXTRA_HISTORY, history)
                    putExtra(DetailActivity.EXTRA_FOUNDER, founder)
                    putExtra(DetailActivity.EXTRA_DEVELOPED, developed)
                    putExtra(DetailActivity.EXTRA_VERSION, version)
                }
                itemView.context.startActivity(intentDetail)
            }
        }
    }
}
