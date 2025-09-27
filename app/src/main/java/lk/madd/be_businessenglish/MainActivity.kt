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
import lk.madd.be_businessenglish.ui.CourseDetailScreen
import lk.madd.be_businessenglish.ui.CoursePlayerScreen
import lk.madd.be_businessenglish.ui.QuizStartScreen
import lk.madd.be_businessenglish.ui.QuizQuestionScreen
import lk.madd.be_businessenglish.ui.CourseCompletedScreen

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
    const val CourseDetail = "course_detail"
    const val CoursePlayer = "course_player"
    const val QuizStart = "quiz_start"
    const val QuizQuestion = "quiz_question"
    const val CourseCompleted = "course_completed"
    const val Attachments = "attachments"
    const val Notifications = "notifications"
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) { AppNavHost(nav) }
}

@Composable
fun AppNavHost(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.Onboard) {
        // Entry / Auth
        composable(Routes.Onboard) { OnboardScreen { nav.navigate(Routes.SignIn) } }
        composable(Routes.SignIn) {
            SignInScreen(
                onSignIn = {
                    nav.navigate(Routes.Home) {
                        popUpTo(Routes.Onboard) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onCreateAccount = { nav.navigate(Routes.SignUp) },
                onForgot = { /* TODO: forgot */ }
            )
        }
        composable(Routes.SignUp) {
            SignUpScreen(
                onBack = { nav.popBackStack() },
                onSignedUp = {
                    nav.navigate(Routes.Home) {
                        popUpTo(Routes.Onboard) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Primary tabs
        composable(Routes.Home) { HomeScreen(nav) }
        composable(Routes.Courses) { CoursesScreen() }
        composable(Routes.MyLearning) { MyLearningScreen() }
        composable(Routes.Downloads) { DownloadsScreen() }
        composable(Routes.Profile) { ProfileScreen { nav.navigate(Routes.Settings) } }
        composable(Routes.Settings) { SettingsScreen() }

        // Course funnel
        composable(Routes.CourseDetail) { CourseDetailScreen(
            onEnroll = { nav.navigate(Routes.CoursePlayer) },
            onPreview = { nav.navigate(Routes.Lesson) },
            onAttachments = { nav.navigate(Routes.Attachments) }
        ) }
        composable(Routes.CoursePlayer) { CoursePlayerScreen(
            onOpenLesson = { nav.navigate(Routes.Lesson) },
            onDownloadAll = { nav.navigate(Routes.Downloads) },
            onStartQuiz = { nav.navigate(Routes.QuizStart) }
        ) }

        // Lesson and attachments
        composable(Routes.Lesson) { LessonScreen { nav.navigate(Routes.Attachments) } }
        composable(Routes.Attachments) { AttachmentsScreen(onBack = { nav.popBackStack() }) }

        // Quiz flow
        composable(Routes.QuizStart) { QuizStartScreen { nav.navigate(Routes.QuizQuestion) } }
        composable(Routes.QuizQuestion) {
            QuizQuestionScreen(
                onComplete = { nav.navigate(Routes.CourseCompleted) { launchSingleTop = true } }
            )
        }
        composable(Routes.CourseCompleted) {
            CourseCompletedScreen(
                onGoToMyLearning = {
                    nav.navigate(Routes.MyLearning) {
                        popUpTo(Routes.Home) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onBack = { nav.popBackStack() },
                onViewCourse = { nav.navigate(Routes.CourseDetail) },
                onShare = { /* TODO: Implement share sheet */ }
            )
        }

        // Notifications
        composable(Routes.Notifications) { NotificationsScreen() }
    }
}