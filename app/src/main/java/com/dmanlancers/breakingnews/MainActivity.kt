package com.dmanlancers.breakingnews

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.dmanlancers.breakingnews.core.BiometricHelper
import com.dmanlancers.breakingnews.presentation.navigation.Navigation
import com.dmanlancers.breakingnews.presentation.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.R)
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val biometricHelper by lazy { BiometricHelper(this) }
    private var isAuthenticated by mutableStateOf(false)


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biometricHelper.authenticateWithBiometrics(
            onSuccess = {
                isAuthenticated = true
            },
            onFailure = {
                finishActivity(0)

            }
        )
        setContent {
            NewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        TopAppBar(
                            title = {
                                Text(
                                    text = getString(R.string.app_name),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                        )
                        MyApp {
                            Navigation()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}