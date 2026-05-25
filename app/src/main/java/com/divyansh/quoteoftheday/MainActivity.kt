package com.divyansh.quoteoftheday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuoteApp()
        }
    }
}

@Composable
fun QuoteApp() {

    val quotes = listOf(

        Pair(
            "Dream big and dare to fail.",
            "Norman Vaughan"
        ),

        Pair(
            "Push yourself because no one else will.",
            "Unknown"
        ),

        Pair(
            "Success is not final, failure is not fatal.",
            "Winston Churchill"
        ),

        Pair(
            "Stay positive, work hard, make it happen.",
            "Unknown"
        )
    )

    var currentQuote by remember {
        mutableStateOf(quotes.random())
    }

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F2027),
            Color(0xFF203A43),
            Color(0xFF2C5364)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                text = "Quote of the Day",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.12f)
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "\"${currentQuote.first}\"",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 34.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "- ${currentQuote.second}",
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(
                    onClick = {
                        currentQuote = quotes.random()
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("New Quote")
                }

                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("❤")
                }
            }
        }
    }
}