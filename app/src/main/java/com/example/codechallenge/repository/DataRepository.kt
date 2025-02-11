package com.example.codechallenge.repository

import android.content.Context
import android.content.res.Resources
import androidx.annotation.RawRes
import com.example.codechallenge.R
import com.example.codechallenge.data.City
import com.google.gson.GsonBuilder

class DataRepository(private val context: Context) : IDataRepository {

    private fun Resources.getRawTextFile(@RawRes id: Int) =
        openRawResource(id).bufferedReader().use { it.readText() }

    override fun getCities(): List<City> {
        val txtFile = context.resources.getRawTextFile(R.raw.cities)
        val gsonBuilder = GsonBuilder().serializeNulls()
        val gson = gsonBuilder.create()
        val list: List<City> = gson.fromJson(txtFile, Array<City>::class.java).toList()
        return list
    }
}