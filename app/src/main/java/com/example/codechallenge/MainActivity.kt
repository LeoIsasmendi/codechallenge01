package com.example.codechallenge

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codechallenge.data.Coordinates
import com.example.codechallenge.repository.DataRepository
import com.example.codechallenge.repository.MockedDataRepository
import com.example.codechallenge.ui.theme.CodeChallengeTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val repository = DataRepository(applicationContext)
            val viewModel = MainActivityViewModel(repository)
            val screenOrientation = LocalConfiguration.current.orientation
            CodeChallengeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxWidth()
                ) { innerPadding ->
                    NavigationComponent(navController, innerPadding, viewModel, screenOrientation)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: MainActivityViewModel,
    screenOrientation: Int
) {
    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable("home") {
            MainScreen(innerPadding, navController, viewModel, screenOrientation)
        }
        composable("map") {
            MapScreen(innerPadding, viewModel, screenOrientation)
        }
    }
}

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: MainActivityViewModel,
    screenOrientation: Int
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .testTag("main_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            FilterScreen(viewModel, navController, screenOrientation)
        }

        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MapScreen(innerPadding, viewModel, screenOrientation)
            }

        }
    }
}

@Composable
fun FilterScreen(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
    screenOrientation: Int,
) {
    val text = viewModel.filter.collectAsStateWithLifecycle()
    val cities = viewModel.filteredCities.collectAsStateWithLifecycle()

    TextField(value = text.value,
        placeholder = { Text("City...") },
        isError = text.value.isBlank(),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("input"),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { newValue ->
            viewModel.filterBy(newValue)
        })

    LazyColumn(modifier = Modifier.testTag("cities")) {
        items(cities.value, key = { item -> item._id }) { item ->
            Row(modifier = Modifier.padding(start = 8.dp, end = 10.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                        .clickable {
                            viewModel.updateSelectedCity(item)
                            if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
                                navController.navigate("map")
                            }
                        }, text = item.name + ", " + item.country
                )
                Icon(
                    modifier = Modifier.height(36.dp).clickable {
                        viewModel.toggleFavorite(item)
                    },
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = if (item.favorite) Color.Blue else Color.LightGray
                )
            }
        }
    }
}


@Composable
fun MapScreen(
    innerPadding: PaddingValues,
    viewModel: MainActivityViewModel,
    screenOrientation: Int,
) {
    val isMapLoaded = remember { mutableStateOf(false) }
    val zoom = 12f
    val marker = viewModel.selectedCity.collectAsState()
    val coordinates = marker.value.coord ?: Coordinates(0.0, 0.0)
    val latlng = LatLng(coordinates.lat, coordinates.lon)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlng, zoom)
    }

    if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE && isMapLoaded.value) {
        cameraPositionState.move(
            update = CameraUpdateFactory.newLatLngZoom(latlng, zoom)
        )
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) innerPadding else PaddingValues())
            .testTag("maps"),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            isMapLoaded.value = true
        }
    ) {
        Marker(
            state = MarkerState(position = latlng),
            title = marker.value.name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityView() {
    MainScreen(
        innerPadding = PaddingValues(),
        navController = rememberNavController(),
        viewModel = MainActivityViewModel(MockedDataRepository()),
        screenOrientation = Configuration.ORIENTATION_PORTRAIT
    )
}