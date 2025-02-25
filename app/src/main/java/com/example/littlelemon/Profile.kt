package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val preferencesHelper = remember { PreferencesHelper(context) }
    val firstName = preferencesHelper.getData("firstName", "")
    val lastName = preferencesHelper.getData("lastName", "")
    val userEmail = preferencesHelper.getData("userEmail", "")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.littlelemonlogo),
                contentDescription = "Logo Image",
                contentScale = ContentScale.Companion.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .size(200.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Companion.Fit,
                modifier = Modifier
                    .height(70.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate(Profile.route) },
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .height(100.dp)
                .padding(10.dp),
            Alignment.Center
        ) {
            Text(
                text = "Profile Information", color = Color(0xFFFFFFFF),
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
            )
        }
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "first name   :   $firstName", color = Color(0xFF333333),
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Text(
                text = "last name   :   $lastName", color = Color(0xFF333333),
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Text(
                text = "email address : $userEmail", color = Color(0xFF333333),
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start, modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
        Box(Modifier.height(250.dp), Alignment.BottomEnd) {
            Button(
                onClick = {
                    preferencesHelper.clearData()
                    navController.navigate(Onboarding.route)
                },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFF4CE14)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(text = "Logout", color = Color(0xFF333333), fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController)
}