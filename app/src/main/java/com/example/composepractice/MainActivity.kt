package com.example.composepractice

import SyncedTabsList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.composepractice.customtab.SyncedTabData
import com.example.composepractice.customtab.TabbedScreen
import com.example.composepractice.ui.theme.ComposePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabbedScreen()
                }
            }
        }
    }
}

@Composable
fun SyncedTabs() {
    SyncedTabsList(
        syncedTabsContent = generateContent(),
        isTab = { it is SyncedTabData.Header },
        syncedTab = { tab, _ ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = tab.title,
                style = MaterialTheme.typography.labelSmall
            )
        },
        syncedItem = {
            Column {
                it.description?.let { it1 ->
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = it1,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = it.title,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    )
}



private fun generateContent(): List<SyncedTabData> = buildList {
    repeat(6) {
        if (it % 3 == 0) {
            add(SyncedTabData.Header("Header - $it"))
        } else {
            add(SyncedTabData.Item("Header - $it", "saedcxqwatuyevq wxufyh $it"))
        }
    }
}



@Preview
@Composable
fun ProfileInformationTabLeftPreview() {
   ComposePracticeTheme {
      
   }
}
