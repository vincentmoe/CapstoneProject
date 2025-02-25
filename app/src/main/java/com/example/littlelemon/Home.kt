package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Home(navController: NavController, menu: List<MenuItemRoom>) {

    var starters by remember { mutableStateOf(true) }
    var main by remember { mutableStateOf(true) }
    var desserts by remember { mutableStateOf(true) }
    var drinks by remember { mutableStateOf(true) }
    var searchPhrase by remember { mutableStateOf("") }

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
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .size(200.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(70.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate(Profile.route) },
            )
        }
        Column(
            modifier = Modifier
                .background(Color(0xFF495E57)) //0xFF495E57
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14)
            )
            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
            ) {
                Column {
                    Text(
                        text = "Chicago",
                        fontSize = 24.sp,
                        color = Color(0xFFEDEFEE),
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(0.6f)
                    )
                    Text(
                        text = "We are a family owned Mediterranean restaurant, " +
                                "focused on traditional recipes served with a modern twist.",
                        fontSize = 18.sp,
                        color = Color(0xFFEDEFEE),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                )
            }
            // Search box
            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                label = { Text(text = "Search Today Menu", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp, end = 8.dp,
                        top = 2.dp, bottom = 2.dp
                    )
            )
        }
        Column {
            Text(
                text = "ORDER FOR DELIVERY!",
                modifier = Modifier.padding(start = 40.dp, 8.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                Modifier.fillMaxWidth(),
                Arrangement.SpaceAround,
            ) {
                Button(
                    onClick = { starters = true; main = false; desserts = false; drinks = false },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBDABB))
                ) { Text(text = "Starters", color = Color.Black) }
                Button(
                    onClick = { starters = false; main = true; desserts = false; drinks = false },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBDABB))
                ) { Text(text = "Main", color = Color.Black) }
                Button(
                    onClick = { starters = false; main = false; desserts = true; drinks = false },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBDABB))
                ) { Text("Desserts", color = Color.Black) }
                Button(
                    onClick = { starters = false; main = false; desserts = false; drinks = true },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFBDABB))
                ) { Text("Drinks", color = Color.Black) }
            }
        }

        // Search box empty check and Categories
        val menuCate: List<MenuItemRoom>
        if (searchPhrase.isNotBlank()) {
            // Show All Menu at first before click any button
            if (starters && main && desserts && drinks) {
                MenuItemsList(items = menu.filter { it.title.contains( searchPhrase, ignoreCase = true) })
            }
            else if (starters) {
                menuCate = menu.filter { it.category.contains("starters", ignoreCase = true) }
                MenuItemsList(items = menuCate.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                })
            } else if (main) {
                menuCate = menu.filter { it.category.contains("mains", ignoreCase = true) }
                MenuItemsList(items = menuCate.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                })
            } else if (desserts) {
                menuCate = menu.filter { it.category.contains("desserts", ignoreCase = true) }
                MenuItemsList(items = menuCate.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                })
            } else if (drinks) {
                menuCate = menu.filter { it.category.contains("drinks", ignoreCase = true) }
                MenuItemsList(items = menuCate.filter {
                    it.title.contains(searchPhrase, ignoreCase = true)
                })
            }
        } else {
            // Show All Menu at first before click any button
            if (starters && main && desserts && drinks) {
                MenuItemsList( items = menu )
            }
            else if (starters) {
                menuCate = menu.filter { it.category.contains("starters", ignoreCase = true) }
                MenuItemsList(items = menuCate)
            } else if (main) {
                menuCate = menu.filter { it.category.contains("mains", ignoreCase = true) }
                MenuItemsList(items = menuCate)
            } else if (desserts) {
                menuCate = menu.filter { it.category.contains("desserts", ignoreCase = true) }
                MenuItemsList(items = menuCate)
            } else if (drinks) {
                menuCate = menu.filter { it.category.contains("drinks", ignoreCase = true) }
                MenuItemsList(items = menuCate)
            }
        }
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp),

        ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                        )
                        Text(
                            text = "$ " + "${menuItem.price}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    /*    GlideImage(
                            model = menuItem.image,
                            contentDescription = "Menu Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            alignment = Alignment.BottomCenter,
                            contentScale = ContentScale.Fit,
                        ) */
                    AsyncImage(
                        model = menuItem.image,
                        contentDescription = menuItem.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        placeholder = painterResource(id = R.drawable.placeholder), // placeholder image
                        error = painterResource(id = R.drawable.placeholder), // error image
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit,
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    thickness = 1.dp,
                    color = Color(0xFFF4CE14)
                )
            }
        )
    }
}
/*
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController, MenuItemRoom)
}
*/