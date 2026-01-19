package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dummy.GetDetails
import com.example.dummy.Main
import com.example.testapplication.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val main = Main()
    var result by remember { mutableStateOf("") }
    Column (   modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(
            onClick = {
                // Call getDeviceDetails() and simulate interface callback
                val data = main.getDeviceDetails()
                val callback = object : GetDetails {
                    override fun onSuccess(dataStr: String) {
                        result = dataStr
                    }

                    override fun onFailure(message: String) {
                        result = "Error: $message"
                    }
                }

                try {
                    callback.onSuccess(data.toString())
                } catch (e: Exception) {
                    callback.onFailure(e.message ?: "Unknown error")
                }
            }
        ) {
            Text("Get Device Details")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = result)
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestApplicationTheme {
        Greeting("Android")
    }
}