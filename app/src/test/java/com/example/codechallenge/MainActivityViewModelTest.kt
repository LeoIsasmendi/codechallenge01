package com.example.codechallenge

import com.example.codechallenge.data.City
import com.example.codechallenge.repository.IDataRepository
import org.junit.Assert

import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainActivityViewModelTest {

    private val repository: IDataRepository = mock()
    private val viewModel: MainActivityViewModel = MainActivityViewModel(repository)

    @Test
    fun `test update selected city`() {
        val selectedCity = viewModel.selectedCity.value
        Assert.assertNotNull(selectedCity)
        Assert.assertEquals(selectedCity.name, "")
        Assert.assertEquals(selectedCity.country, "")
        Assert.assertNull(selectedCity.coord)

        val country = City("country", "name")
        viewModel.updateSelectedCity(country)

        Assert.assertEquals(country, viewModel.selectedCity.value)
    }

    @Test
    fun `test filterBy return empty list`() {
        Mockito.`when`(repository.getCities()).thenReturn(listOf(City("country", "name")))
        val viewModel = MainActivityViewModel(repository)
        viewModel.filterBy(" ")
        Assert.assertTrue(viewModel.filteredCities.value.isEmpty())
    }
}