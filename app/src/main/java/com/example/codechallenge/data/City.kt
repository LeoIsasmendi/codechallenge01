package com.example.codechallenge.data

data class City(
    val country: String = "",
    val name: String = "",
    val _id: Int = 0,
    val coord: Coordinates? = null,
    var favorite: Boolean = false
)
