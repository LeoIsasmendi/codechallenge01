package com.example.codechallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallenge.data.City
import com.example.codechallenge.repository.IDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: IDataRepository) : ViewModel() {
    private val _cities = MutableStateFlow(listOf<City>())

    private val _filteredCities = MutableStateFlow(listOf<City>())
    val filteredCities: StateFlow<List<City>> = _filteredCities.asStateFlow()

    private val _filter = MutableStateFlow("")
    val filter: StateFlow<String> = _filter.asStateFlow()

    private val _city = MutableStateFlow(City())
    val selectedCity = _city.asStateFlow()

    init {
        initList()
    }

    private fun initList() {
        viewModelScope.launch {
            _cities.value = repository.getCities().sortedWith(compareBy<City> { it.name }.thenBy { it.country })
            _filteredCities.value = _cities.value
        }
    }

    fun filterBy(filter: String) {
        _filter.value = filter
        if (filter.isEmpty()) {
            _filteredCities.value = _cities.value
        } else if (filter.isNotBlank()) {
            val upperFilter = filter.uppercase()
            val result = _cities.value.filter {
                it.name.uppercase().contains(upperFilter)
            }
            _filteredCities.value = result
        }
    }

    fun updateSelectedCity(city: City) {
        _city.value = city
    }

    fun toggleFavorite(city: City) {
        _cities.value.find { it._id == city._id }?.favorite = !city.favorite
    }

}