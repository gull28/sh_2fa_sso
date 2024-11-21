package com.example.sh_2fa_app.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sh_2fa_app.ui.AppColors
import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator
import dev.turingcomplete.kotlinonetimepassword.HmacAlgorithm
import dev.turingcomplete.kotlinonetimepassword.TimeBasedOneTimePasswordConfig
import kotlinx.coroutines.delay
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

data class GeneratedTotp(
    val code: String,
    val timer: Long
)

@Composable
fun TotpGenerator(secret: String) {
    var otp by remember { mutableStateOf("------") }
    var timeLeft by remember { mutableLongStateOf(30L) }

    fun generateTotp(secret: String, timeInterval: Long = 30L, digits: Int = 6): GeneratedTotp {
        val config = TimeBasedOneTimePasswordConfig(
            codeDigits = digits,
            hmacAlgorithm = HmacAlgorithm.SHA1,
            timeStep = timeInterval,
            timeStepUnit = TimeUnit.SECONDS
        )

        // for some reason TimeBasedOneTimePasswordGenerator class doesn't work with sha1
        // it generates the wrong code
        val timeBasedOneTimePasswordGenerator = GoogleAuthenticator(secret.toByteArray(Charset.defaultCharset()))

        val code = timeBasedOneTimePasswordGenerator.generate()
        val currentTime = System.currentTimeMillis()
        val timer = timeInterval - (currentTime / 1000 % timeInterval)

        return GeneratedTotp(code = code, timer = timer)
    }

    LaunchedEffect(secret) {
        while (true) {
            val totp = generateTotp(secret)
            otp = totp.code
            timeLeft = totp.timer
            delay(1000)
        }
    }

    TotpDisplay(otp, timeLeft)
}

@Composable
fun TotpDisplay(otp: String, timeLeft: Long) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = otp,
            color = AppColors.TextPrimary,
            fontSize = 56.sp,
            modifier = Modifier.padding(10.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable(
                    onClick = {
                        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData = ClipData.newPlainText("Copied Text", otp)
                        clipboardManager.setPrimaryClip(clipData)
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Icon(
                imageVector = Icons.Filled.ContentCopy,
                contentDescription = "Copy content",
                tint = Color.Gray
            )

            Text(
                text = "Copy",
                color = AppColors.TextSecondary,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }


        LinearProgressIndicator(
            progress = { timeLeft / 30F },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            color = AppColors.Primary,
        )
    }
}
