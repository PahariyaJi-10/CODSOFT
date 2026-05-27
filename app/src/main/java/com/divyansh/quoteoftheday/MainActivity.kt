package com.divyansh.quoteoftheday

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    val quotes = listOf(

        Pair("Dream big and dare to fail.", "Norman Vaughan"),

        Pair("Push yourself because no one else will.", "Unknown"),

        Pair("Success is not final, failure is not fatal.", "Winston Churchill"),

        Pair("Stay positive, work hard, make it happen.", "Unknown"),

        Pair("Believe you can and you're halfway there.", "Theodore Roosevelt"),

        Pair("Do something today that your future self will thank you for.", "Unknown"),

        Pair("Great things never come from comfort zones.", "Unknown"),

        Pair("Don’t stop until you’re proud.", "Unknown"),

        Pair("Small steps every day lead to big results.", "Unknown"),

        Pair("Your only limit is your mind.", "Unknown"),

        Pair("Discipline is stronger than motivation.", "Unknown"),

        Pair("Success starts with self-belief.", "Unknown"),

        Pair("Focus on the goal, not the obstacles.", "Unknown"),

        Pair("Work hard in silence, let success make the noise.", "Frank Ocean"),

        Pair("Consistency is the key to success.", "Unknown"),

        Pair("The harder you work, the luckier you get.", "Gary Player"),

        Pair("Don’t watch the clock; do what it does. Keep going.", "Sam Levenson"),

        Pair("Failure is the opportunity to begin again more intelligently.", "Henry Ford"),

        Pair("Everything you can imagine is real.", "Pablo Picasso"),

        Pair("Doubt kills more dreams than failure ever will.", "Suzy Kassem")
    )

    var currentQuote by remember {
        mutableStateOf(quotes.random())
    }

    var favorites by remember {
        mutableStateOf(setOf<Pair<String, String>>())
    }

    val isFavorite = favorites.contains(currentQuote)

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

                    Crossfade(
                        targetState = currentQuote.first,
                        label = ""
                    ) { quote ->

                        Text(
                            text = "\"$quote\"",
                            color = Color.White,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 34.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Crossfade(
                        targetState = currentQuote.second,
                        label = ""
                    ) { author ->

                        Text(
                            text = "- $author",
                            color = Color.LightGray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
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

                        favorites =
                            if (isFavorite) {
                                favorites - currentQuote
                            } else {
                                favorites + currentQuote
                            }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                            if (isFavorite)
                                Color.Red
                            else
                                MaterialTheme.colorScheme.primary
                    )
                ) {

                    Text("❤")
                }

                Button(
                    onClick = {

                        shareQuote(
                            context,
                            "${currentQuote.first} - ${currentQuote.second}"
                        )
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text("Share")
                }
            }
        }
    }
}

fun shareQuote(context: Context, quote: String) {

    val sendIntent = Intent().apply {

        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, quote)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)

    context.startActivity(shareIntent)
}