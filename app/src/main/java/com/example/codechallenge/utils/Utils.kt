package com.example.codechallenge.utils

import com.example.codechallenge.data.City
import com.example.codechallenge.data.Coordinates

class Utils {
    fun getMockedCity(): List<City> {
        return listOf(
            City(
                country = "UA",
                name = "Hurzuf",
                _id = 707860,
                coord = Coordinates(lon = 34.283333, lat = 44.549999),
                favorite = true
            ),
            City(
                country = "RU",
                name = "Novinki",
                _id = 519188,
                coord = Coordinates(lon = 37.666668, lat = 55.683334),
                favorite = true
            ),
            City(
                country = "NP",
                name = "Gorkhā",
                _id = 1283378,
                coord = Coordinates(lon = 84.633331, lat = 28.0)
            ),
            City(
                country = "IN",
                name = "State of Haryāna",
                _id = 1270260,
                coord = Coordinates(lon = 76.0, lat = 29.0)
            ),
            City(
                country = "UA",
                name = "Holubynka",
                _id = 708546,
                coord = Coordinates(lon = 33.900002, lat = 44.599998)
            ),
            City(
                country = "NP",
                name = "Bāgmatī Zone",
                _id = 1283710,
                coord = Coordinates(lon = 85.416664, lat = 28.0)
            ),
            City(
                country = "RU",
                name = "Mar’ina Roshcha",
                _id = 529334,
                coord = Coordinates(lon = 37.611111, lat = 55.796391)
            ),
            City(
                country = "IN",
                name = "Republic of India",
                _id = 1269750,
                coord = Coordinates(lon = 77.0, lat = 20.0)
            ),
            City(
                country = "NP",
                name = "Kathmandu",
                _id = 1283240,
                coord = Coordinates(lon = 85.316666, lat = 27.716667)
            ),
            City(
                country = "UA",
                name = "Laspi",
                _id = 703363,
                coord = Coordinates(lon = 33.733334, lat = 44.416668)
            ),
            City(
                country = "VE",
                name = "Merida",
                _id = 3632308,
                coord = Coordinates(lon = -71.144997, lat = 8.598333)
            ),
            City(
                country = "RU",
                name = "Vinogradovo",
                _id = 473537,
                coord = Coordinates(lon = 38.545555, lat = 55.423332)
            ),
            City(
                country = "IQ",
                name = "Qarah Gawl al ‘Ulyā",
                _id = 384848,
                coord = Coordinates(lon = 45.6325, lat = 35.353889)
            ),
            City(
                country = "RU",
                name = "Cherkizovo",
                _id = 569143,
                coord = Coordinates(lon = 37.728889, lat = 55.800835)
            ),
            City(
                country = "UA",
                name = "Alupka",
                _id = 713514,
                coord = Coordinates(lon = 34.049999, lat = 44.416668)
            ),
            City(
                country = "DE",
                name = "Lichtenrade",
                _id = 2878044,
                coord = Coordinates(lon = 13.40637, lat = 52.398441)
            ),
            City(
                country = "RU",
                name = "Zavety Il’icha",
                _id = 464176,
                coord = Coordinates(lon = 37.849998, lat = 56.049999)
            ),
            City(
                country = "IL",
                name = "‘Azriqam",
                _id = 295582,
                coord = Coordinates(lon = 34.700001, lat = 31.75)
            )
        )
    }
}