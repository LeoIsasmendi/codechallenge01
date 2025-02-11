package com.example.codechallenge.repository

import com.example.codechallenge.data.City

sealed interface IDataRepository {
    fun getCities(): List<City>
}