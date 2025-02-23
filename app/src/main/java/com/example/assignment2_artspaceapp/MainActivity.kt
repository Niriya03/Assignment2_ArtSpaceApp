package com.example.assignment2_artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2_artspaceapp.ui.theme.Assignment2_ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2_ArtSpaceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableStateOf(0) }

    val artworks = listOf(
        Artwork(R.drawable.art1, "Mona Lisa", "Leonardo da Vinci", "(1517)"),
        Artwork(R.drawable.art2, "Girl with a Pearl Earring", "Johannes Vermeer", "(1665)"),
        Artwork(R.drawable.art3, "Self-Portrait with Thorn Necklace and Hummingbird", "Frida Kahlo", "(1940)")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 80.dp)
                .padding(horizontal = 20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RectangleShape
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = artworks[currentArtwork].imageRes),
                    contentDescription = artworks[currentArtwork].title,
                    modifier = Modifier
                        .aspectRatio(1f),
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .background(Color(0xCFCED0E0))
                    .padding(16.dp)
            ) {
                Text(
                    text = artworks[currentArtwork].title,
                    fontSize = 26.sp
                )
                Text(
                    text = artworks[currentArtwork].artist,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = artworks[currentArtwork].year,
                    fontSize = 18.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentArtwork == 0) {
                            currentArtwork = artworks.size - 1
                        } else {
                            currentArtwork--
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = {
                        if (currentArtwork == artworks.size - 1) {
                            currentArtwork = 0
                        } else {
                            currentArtwork++
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    Text("Next")
                }
            }
        }
    }
}

data class Artwork(val imageRes: Int, val title: String, val artist: String, val year: String)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2_ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}