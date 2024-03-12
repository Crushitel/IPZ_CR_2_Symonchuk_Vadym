package ua.lntu.edu.ipz_cr_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ua.lntu.edu.ipz_cr_2.ui.theme.IPZ_CR_2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CR_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignSystem()
                }
            }
        }
    }
}


@Composable
fun SignSystem() {
    SignInScreen()
}

@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var signedIn by remember { mutableStateOf(false) }

    if (signedIn) {
        SignInSuccess(email = email.text, onSignOut = {
            email = TextFieldValue()
            password = TextFieldValue()
            signedIn = false
        })
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Email")
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = MaterialTheme.shapes.small
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Password")
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = MaterialTheme.shapes.small
                    )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                        signedIn = true
                    }
                }
            ) {
                Text("Sign In")
            }
        }
    }
}


@Composable
fun SignInSuccess(email: String, onSignOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign In success")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Email: $email")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onSignOut) {
            Text("Sign Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInpreview() {
    IPZ_CR_2Theme {
        SignSystem()
    }
}