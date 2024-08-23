package com.example.chats.ui.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chats.R
import com.example.chats.data.model.User
import com.example.connectme.ui.profile.ProfileViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {
    val userProfile by profileViewModel.userProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Profile") })
        }
    ) {
        userProfile?.let { user ->
            ProfileContent(user = user)
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

}

@Composable
fun ProfileContent(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Text(text = "Phone: ${user.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Name: ${user.name}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "City: ${user.city}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Date of Birth: ${user.dateOfBirth}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Zodiac Sign: ${user.zodiacSign}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "About: ${user.about}", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
fun PreviewProfileContent() {
    ProfileContent(
        user = User(
            id = 1,
            name = "John Doe",
            phoneNumber = "+1234567890",
            city = "New York",
            dateOfBirth = "1990-01-01",
            zodiacSign = "Capricorn",
            about = "Lorem ipsum dolor sit amet.",
            profilePictureUrl = "https://via.placeholder.com/150"
        )
    )
}