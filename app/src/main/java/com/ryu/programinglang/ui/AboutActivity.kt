package com.ryu.programinglang.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ryu.programinglang.R
import com.ryu.programinglang.databinding.ActivityAboutBinding
import com.ryu.programinglang.utility.UiUtils

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UiUtils.setupStatusBar(this)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.about)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imgUrl = "https://firebasestorage.googleapis.com/v0/b/bangkit-dashboard/o/production%2F2024-B2%2Fprofiles%2Fc6cb53cc-dc3b-4ec8-b6d9-6ceb0fb97c18.jpg?alt=media&token=adc703a4-08e6-416b-8ef5-0435bf4acb51"
        Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.person)
            .error(R.drawable.person)
            .into(binding.imgProfile)

        binding.tvEmail.setOnClickListener {
            openEmailClient(getString(R.string.email_title))
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openEmailClient(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, getString(R.string.email_missing), Toast.LENGTH_SHORT).show()
        }
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