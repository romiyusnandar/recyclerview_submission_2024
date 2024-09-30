package com.ryu.programinglang.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.ryu.programinglang.ProgramingLang
import com.ryu.programinglang.R
import com.ryu.programinglang.databinding.ActivityDetailBinding
import com.ryu.programinglang.utility.UiUtils

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UiUtils.setupStatusBar(this)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val programingLanguage = intent.getStringExtra(EXTRA_NAME)
        val programingLangDesc = intent.getStringExtra(EXTRA_DESCRIPTION)
        val programingLangPhoto = intent.getStringExtra(EXTRA_PHOTO)

        supportActionBar?.title = "${getString(R.string.detail_title)} $programingLanguage"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.tvName.text = programingLanguage
        binding.tvDescription.text = programingLangDesc
        Glide.with(this)
            .load(programingLangPhoto)
            .placeholder(R.drawable.default_placeholder)
            .error(R.drawable.default_placeholder)
            .into(binding.programImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}