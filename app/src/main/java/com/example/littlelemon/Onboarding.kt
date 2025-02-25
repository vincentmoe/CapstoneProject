package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    val preferencesHelper = remember { PreferencesHelper(context) }
    var firstName by remember { mutableStateOf(preferencesHelper.getData("firstName", "")) }
    var lastName by remember { mutableStateOf(preferencesHelper.getData("lastName","")) }
    var userEmail by remember { mutableStateOf(preferencesHelper.getData("userEmail","")) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.littlelemonlogo), contentDescription = "Logo Image",
                contentScale = ContentScale.Companion.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .size(200.dp),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .height(130.dp)
        ) {
            Text(
                text = "Let's get to know you", color = Color(0xFFFFFFFF),
                fontSize = 26.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp), Alignment.Center
        ) {
            Text(
                text = "Personal information", color = Color(0xFF333333),
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "First name", color = Color(0xFF333333),
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            )
            TextField(
                value = firstName, onValueChange = { newValue ->
                    firstName = newValue.filter { it.isLetter() || it.isWhitespace() }
                },
                placeholder = { Text(text = "your Firstname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF495E57), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF495E57),
                    unfocusedTextColor = Color(0xFF495E57),
                    focusedContainerColor = Color.White,   // Background color when focused
                    unfocusedContainerColor = Color.White, // Background color when not focused
                    disabledContainerColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,   // Removes default underline
                    unfocusedIndicatorColor = Color.Transparent, // Removes default underline
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "Last name", color = Color(0xFF333333),
                fontSize = 18.sp,
                textAlign = TextAlign.Start, modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            )
            TextField(
                value = lastName, onValueChange = { newValue ->
                    lastName = newValue.filter { it.isLetter() || it.isWhitespace() }
                },
                placeholder = { Text(text = "your Lastname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF495E57), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF495E57),
                    unfocusedTextColor = Color(0xFF495E57),
                    focusedContainerColor = Color.White,   // Background color when focused
                    unfocusedContainerColor = Color.White, // Background color when not focused
                    disabledContainerColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,   // Removes default underline
                    unfocusedIndicatorColor = Color.Transparent, // Removes default underline
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "Email", color = Color(0xFF333333),
                fontSize = 18.sp,
                textAlign = TextAlign.Start, modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            )
            TextField(
                value = userEmail, onValueChange = { userEmail = it },
                placeholder = { Text(text = "your email address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF495E57), shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFF495E57),
                    unfocusedTextColor = Color(0xFF495E57),
                    focusedContainerColor = Color.White,   // Background color when focused
                    unfocusedContainerColor = Color.White, // Background color when not focused
                    disabledContainerColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,   // Removes default underline
                    unfocusedIndicatorColor = Color.Transparent, // Removes default underline
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
        Box(Modifier.height(250.dp), Alignment.BottomEnd) {
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFF4CE14)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(text = "Register", color = Color(0xFF333333), fontSize = 16.sp)
            }
            if (showDialog) {

                if (firstName.isNotEmpty() && lastName.isNotEmpty() && userEmail.isNotEmpty()) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                        preferencesHelper.saveData("firstName", firstName)
                        preferencesHelper.saveData("lastName", lastName)
                        preferencesHelper.saveData("userEmail", userEmail)
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                Button(onClick = { showDialog = false }) { Text("Ok") }
                            },
                            title = { Text("Welcome to Little Lemon") },
                            text = { Text("Registration Successful !") }
                        )
                        navController.navigate(Home.route)
                    } else {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                Button(onClick = { showDialog = false }) { Text("Ok") }
                            },
                            title = { Text("Invalid Email") },
                            text = { Text("Please enter your valid email !") }
                        )
                    }
                }
                else {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(onClick = { showDialog = false }) { Text("Ok") }
                        },
                        title = { Text("Empty Data") },
                        text = { Text("Please enter all data !") }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    Onboarding(navController)
}