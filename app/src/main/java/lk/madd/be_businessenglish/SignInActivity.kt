package lk.madd.be_businessenglish

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Match the white background look with light status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // Back button finishes the activity
        findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvEmailError = findViewById<TextView>(R.id.tvEmailError)
        val tvPasswordError = findViewById<TextView>(R.id.tvPasswordError)

        // Sign In button validation
        findViewById<AppCompatButton>(R.id.btnSignIn)?.setOnClickListener {
            var hasError = false
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val password = etPassword.text?.toString().orEmpty()

            // Simple email regex
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
            if (email.isEmpty() || !email.matches(emailRegex)) {
                tvEmailError.text = getString(R.string.invalid_email)
                tvEmailError.visibility = View.VISIBLE
                hasError = true
            } else {
                tvEmailError.visibility = View.GONE
            }

            if (password.length < 6) {
                tvPasswordError.text = getString(R.string.wrong_password)
                tvPasswordError.visibility = View.VISIBLE
                hasError = true
            } else {
                tvPasswordError.visibility = View.GONE
            }

            if (!hasError) {
                // TODO: Integrate real auth flow
                finish() // For now, just go back
            }
        }

        // Google button placeholder handler
        findViewById<AppCompatButton>(R.id.btnGoogle)?.setOnClickListener {
            // TODO: Hook up Google Sign-In with Google Identity Services
        }
    }
}
