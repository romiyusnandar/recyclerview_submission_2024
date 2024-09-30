package com.ryu.programinglang.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryu.programinglang.ProgramingLang
import com.ryu.programinglang.R
import com.ryu.programinglang.adapter.ProgramingLangAdapter
import com.ryu.programinglang.databinding.ActivityMainBinding
import com.ryu.programinglang.utility.UiUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listProgramingLangAdapter: ProgramingLangAdapter
    private val list = ArrayList<ProgramingLang>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UiUtils.setupStatusBar(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.actionbar_title)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.rvList.setHasFixedSize(true)

        list.addAll(getListProgramingLang())
        showRecyclerList()

        binding.main.setOnRefreshListener {
            list.clear()
            list.addAll(getListProgramingLang())
            listProgramingLangAdapter.notifyDataSetChanged()
            binding.main.isRefreshing = false
        }
    }

    private fun getListProgramingLang(): ArrayList<ProgramingLang> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listProgramingLang = ArrayList<ProgramingLang>()
        for (i in dataTitle.indices) {
            val lang = ProgramingLang(dataTitle[i], dataDescription[i], dataPhoto[i])
            listProgramingLang.add(lang)
        }
        return listProgramingLang
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        listProgramingLangAdapter = ProgramingLangAdapter(list)
        binding.rvList.adapter = listProgramingLangAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
