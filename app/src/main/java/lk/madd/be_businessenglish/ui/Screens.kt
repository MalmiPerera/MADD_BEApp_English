package lk.madd.be_businessenglish.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import lk.madd.be_businessenglish.R
import lk.madd.be_businessenglish.Routes

@Composable
fun OnboardScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(8.dp))
        Surface(tonalElevation = 0.dp, shape = RoundedCornerShape(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.business_woman),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        // Indicators placeholder
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.size(8.dp))
            Spacer(Modifier.width(8.dp))
            Box(Modifier.size(8.dp))
            Spacer(Modifier.width(8.dp))
            Box(Modifier.size(8.dp))
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Learn Business English\nOn The Go",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            lineHeight = 32.sp
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Track progress. Learn offline.",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Monitor your progress and download lessons for offline access, ensuring uninterrupted learning.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onGetStarted,
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Get started")
        }
    }
}

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
    onCreateAccount: () -> Unit,
    onForgot: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign In", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        Text("Email", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            placeholder = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        emailError?.let { Text(it, color = MaterialTheme.colorScheme.tertiary, fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)) }

        Spacer(Modifier.height(12.dp))
        Text("Password", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            placeholder = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        passError?.let { Text(it, color = MaterialTheme.colorScheme.tertiary, fontSize = 12.sp, modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)) }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                emailError = if (email.contains("@")) null else "Invalid email"
                passError = if (password.length >= 6) null else "Wrong password"
                if (emailError == null && passError == null) onSignIn()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) { Text("Sign In") }

        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            onClick = { /* Google Sign-In later */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) { Text("Continue with Google") }

        Spacer(Modifier.height(24.dp))
        TextButton(onClick = onCreateAccount) { Text("Create account") }
        TextButton(onClick = onForgot) { Text("Forgot password") }
    }
}

@Composable
fun SignUpScreen(onBack: () -> Unit, onSignedUp: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agree by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        bottomBar = {
            // Bottom navigation with Profile selected
            NavigationBar {
                val labels = listOf("Home", "Courses", "My Learning", "Downloads", "Profile")
                labels.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = index == 4,
                        onClick = { /* prototype only */ },
                        label = { Text(label) },
                        icon = { }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            // Top bar with back and centered title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(Modifier.weight(1f))
                Text("Sign up", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.weight(1f))
                Spacer(Modifier.width(22.dp))
            }

            Spacer(Modifier.height(16.dp))

            // Rounded filled fields similar to mock
            val fieldShape = RoundedCornerShape(12.dp)
            val fieldColors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                focusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0f),
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0f)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name") },
                singleLine = true,
                shape = fieldShape,
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            if (nameError != null) Text(nameError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            Spacer(Modifier.height(12.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                singleLine = true,
                shape = fieldShape,
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            if (emailError != null) Text(emailError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            Spacer(Modifier.height(12.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                singleLine = true,
                shape = fieldShape,
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            if (passError != null) Text(passError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)

            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = agree, onCheckedChange = { agree = it })
                Spacer(Modifier.width(8.dp))
                Text("I agree to the Terms of Service")
            }

            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {
                    // Basic validations
                    nameError = if (name.isBlank()) "Name is required" else null
                    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
                    emailError = if (!email.matches(emailRegex)) "Enter a valid email" else null
                    passError = if (password.length < 6) "Password must be at least 6 characters" else null
                    if (agree && nameError == null && emailError == null && passError == null) onSignedUp()
                },
                enabled = agree,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(28.dp)
            ) { Text("Sign up") }
        }
    }
}

@Composable
fun HomeScreen(nav: NavController) {
    var selected by remember { mutableStateOf(0) }
    val items = listOf(Routes.Home, Routes.Courses, Routes.MyLearning, Routes.Downloads, Routes.Profile)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, route ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                            when (route) {
                                Routes.Home -> { /* stay */ }
                                Routes.Courses -> nav.navigate(Routes.Courses)
                                Routes.MyLearning -> nav.navigate(Routes.MyLearning)
                                Routes.Downloads -> nav.navigate(Routes.Downloads)
                                Routes.Profile -> nav.navigate(Routes.Profile)
                            }
                        },
                        label = { Text(route.replaceFirstChar { it.uppercase() }) },
                        icon = { }
                    )
                }
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Welcome back, Business English Learner", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(16.dp))
            Text("Recommended For You")
            Spacer(Modifier.height(8.dp))
            Text("Skill Tracks: Meetings, Presentations, Negotiation ...")
            Spacer(Modifier.height(24.dp))
            Button(onClick = { nav.navigate(Routes.Lesson) }) { Text("Open a Lesson") }
        }
    }
}

@Composable fun CoursesScreen() { SimpleCenter("Courses") }
@Composable fun MyLearningScreen() { SimpleCenter("My Learning") }
@Composable fun DownloadsScreen() { SimpleCenter("Downloads") }
@Composable fun ProfileScreen(onSettings: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Profile")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onSettings) { Text("Settings") }
    }
}
@Composable fun SettingsScreen() { SimpleCenter("Settings") }
@Composable fun LessonScreen(onAttachments: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Business English - Lesson Player")
        Spacer(Modifier.height(12.dp))
        Button(onClick = onAttachments) { Text("Attachments") }
        Spacer(Modifier.height(12.dp))
        Button(onClick = { /* play/pause */ }) { Text("Play/Pause") }
        Spacer(Modifier.height(12.dp))
        Button(onClick = { /* test */ }) { Text("Test Yourself") }
    }
}
@Composable fun AttachmentsScreen() { SimpleCenter("Attachments") }
@Composable fun TestScreen(onFinish: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Question 1/5: Which of the following is the best way to start a business meeting?")
        Spacer(Modifier.height(16.dp))
        repeat(4) { idx -> OutlinedButton(onClick = { }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Text("Option ${idx+1}") } }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onFinish, modifier = Modifier.fillMaxWidth()) { Text("Submit") }
    }
}
@Composable fun QuizResultScreen(onClose: () -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Congratulations!")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onClose) { Text("Back to Home") }
    }
}
@Composable fun NotificationsScreen() { SimpleCenter("Notifications") }

@Composable
private fun SimpleCenter(label: String) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Text(label) }
}
