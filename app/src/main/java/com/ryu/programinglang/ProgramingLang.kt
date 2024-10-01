package com.ryu.programinglang

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProgramingLang(
    val name: String,
    val description: String,
    val photo: String,
    val history: String,
    val founder: String,
    val developed: String,
    val version: String
) : Parcelable
