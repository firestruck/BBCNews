package com.dmanlancers.breakingnews.core

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@RequiresApi(Build.VERSION_CODES.R)
class BiometricHelper(private val activity: FragmentActivity) {

    private val biometricPrompt: BiometricPrompt by lazy {
        createBiometricPrompt(
            onAuthenticationError = { errorCode, errorMessage ->
                // handle authentication error here
            },
            onAuthenticationSucceeded = {
                // handle authentication success here
            },
            onAuthenticationFailed = {
                // handle authentication failure here
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun authenticateWithBiometrics(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (canAuthenticate()) {
            biometricPrompt.authenticate(createPromptInfo())
        } else {
            onFailure()
        }
    }

    private fun canAuthenticate(): Boolean {
        val biometricManager = BiometricManager.from(activity)
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun createBiometricPrompt(
        onAuthenticationError: (Int, CharSequence) -> Unit,
        onAuthenticationSucceeded: () -> Unit,
        onAuthenticationFailed: () -> Unit
    ): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)
        return BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onAuthenticationSucceeded()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onAuthenticationFailed()
                }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setTitle("Biometric Authentication")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("quit")
            .build()
    }
}