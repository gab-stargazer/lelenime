package com.lelestacia.lelenime.feature.more.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lelestacia.lelenime.core.common.Constant.CHAT_GPT
import com.lelestacia.lelenime.core.common.Constant.FB_KAORI
import com.lelestacia.lelenime.core.common.Constant.FB_LELESTACIA
import com.lelestacia.lelenime.core.common.Constant.GITHUB_LELESTACIA
import com.lelestacia.lelenime.core.common.Constant.KAORI_PICTURE
import com.lelestacia.lelenime.core.common.Constant.LELESTACIA_PICTURE
import com.lelestacia.lelenime.feature.more.component.DeveloperCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = com.lelestacia.lelenime.core.common.R.drawable.lelenime),
                contentDescription = "Application Icon",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .heightIn(min = 128.dp, max = 176.dp)
                    .padding(top = 24.dp)
            )
            Text(
                text = "Version 2.0",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Pocket anime index made with Kotlin for you",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Powered by Jikan API and MAL",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Developed By",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 48.dp,
                        start = 24.dp
                    ),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            DeveloperCard(
                name = "Lelestacia",
                nickName = "Kamil-Malik",
                imageURL = LELESTACIA_PICTURE,
                githubURL = GITHUB_LELESTACIA,
                facebookURL = FB_LELESTACIA
            )

            DeveloperCard(
                name = "Kaori Miyazono",
                nickName = "Kao chan",
                imageURL = KAORI_PICTURE,
                githubURL = null,
                facebookURL = FB_KAORI
            )

            DeveloperCard(
                name = "Chat GPT",
                nickName = "GPT3",
                imageURL = CHAT_GPT,
                githubURL = null,
                facebookURL = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoreScreen() {
    AboutScreen(
        navController = rememberNavController()
    )
}
