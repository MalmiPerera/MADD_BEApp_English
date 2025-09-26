package lk.madd.be_businessenglish

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener { finish() }

        val nameEt = findViewById<EditText>(R.id.etName)
        val emailEt = findViewById<EditText>(R.id.etEmail)
        val passEt = findViewById<EditText>(R.id.etPassword)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)

        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnSignUp)?.setOnClickListener {
            val name = nameEt.text?.toString()?.trim().orEmpty()
            val email = emailEt.text?.toString()?.trim().orEmpty()
            val password = passEt.text?.toString().orEmpty()

            val emailValid = email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            when {
                name.isEmpty() -> toast("Name is required")
                !emailValid -> toast("Enter a valid email")
                password.length < 6 -> toast("Password must be at least 6 characters")
                !cbTerms.isChecked -> toast("Please agree to the Terms of Service")
                else -> {
                    toast("Signed up successfully")
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
