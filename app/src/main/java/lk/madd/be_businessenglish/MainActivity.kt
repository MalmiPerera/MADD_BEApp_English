package lk.madd.be_businessenglish

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import lk.madd.be_businessenglish.ui.AttachmentsScreen
import lk.madd.be_businessenglish.ui.CoursesScreen
import lk.madd.be_businessenglish.ui.DownloadsScreen
import lk.madd.be_businessenglish.ui.HomeScreen
import lk.madd.be_businessenglish.ui.LessonScreen
import lk.madd.be_businessenglish.ui.MyLearningScreen
import lk.madd.be_businessenglish.ui.NotificationsScreen
import lk.madd.be_businessenglish.ui.OnboardScreen
import lk.madd.be_businessenglish.ui.ProfileScreen
import lk.madd.be_businessenglish.ui.SettingsScreen
import lk.madd.be_businessenglish.ui.SignInScreen
import lk.madd.be_businessenglish.ui.SignUpScreen
import lk.madd.be_businessenglish.ui.TestScreen
import lk.madd.be_businessenglish.ui.QuizResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Match onboarding status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.background_gray)
        setContent { AppRoot() }
    }
}

object Routes {
    const val Onboard = "onboard"
    const val SignIn = "signin"
    const val SignUp = "signup"
    const val Home = "home"
    const val Courses = "courses"
    const val MyLearning = "mylearning"
    const val Downloads = "downloads"
    const val Profile = "profile"
    const val Settings = "settings"
    const val Lesson = "lesson"
    const val Attachments = "attachments"
    const val Test = "test"
    const val QuizResult = "quizresult"
    const val Notifications = "notifications"
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        AppNavHost(nav)
    }
}

@Composable
fun AppNavHost(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.Onboard) {
        composable(Routes.Onboard) { OnboardScreen { nav.navigate(Routes.SignIn) } }
        composable(Routes.SignIn) { SignInScreen(onSignIn = { nav.navigate(Routes.Home) }, onCreateAccount = { nav.navigate(Routes.SignUp) }, onForgot = { }) }
        composable(Routes.SignUp) { SignUpScreen(onBack = { nav.popBackStack() }, onSignedUp = { nav.navigate(Routes.Home) }) }
        composable(Routes.Home) { HomeScreen(nav) }
        composable(Routes.Courses) { CoursesScreen() }
        composable(Routes.MyLearning) { MyLearningScreen() }
        composable(Routes.Downloads) { DownloadsScreen() }
        composable(Routes.Profile) { ProfileScreen { nav.navigate(Routes.Settings) } }
        composable(Routes.Settings) { SettingsScreen() }
        composable(Routes.Lesson) { LessonScreen { nav.navigate(Routes.Attachments) } }
        composable(Routes.Attachments) { AttachmentsScreen() }
        composable(Routes.Test) { TestScreen { nav.navigate(Routes.QuizResult) } }
        composable(Routes.QuizResult) { QuizResultScreen { nav.navigate(Routes.Home) } }
        composable(Routes.Notifications) { NotificationsScreen() }
    }
}