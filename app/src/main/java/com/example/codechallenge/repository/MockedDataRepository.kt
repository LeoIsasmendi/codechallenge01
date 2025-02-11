package com.example.codechallenge.repository

import com.example.codechallenge.data.City
import com.example.codechallenge.utils.Utils

class MockedDataRepository : IDataRepository {
    override fun getCities(): List<City> {
        return Utils().getMockedCity()
    }
}