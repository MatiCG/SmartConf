package epitech.eip.smartconf.Activities

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.R

class LoginActivity: BaseActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login_layout)

//        load(OptionsAuthFragment())
    }

    fun load(fragment: BaseFragment) {
        placeFragment(fragment, R.id.authentification_section)
    }
/*
        mAuth = FirebaseAuth.getInstance()

        signin_btn.setOnClickListener {
            val email = email_field.text.toString()
            val password = password_field.text.toString()

            createAccount(email, password)
        }
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm(email, password))
            return
        load.visibility = View.VISIBLE
        signin_btn.visibility = View.INVISIBLE
        mAuth.signInWithEmailAndPassword(email_field.text.toString(), password_field.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    load.visibility = View.GONE
                    signin_btn.visibility = View.VISIBLE
                    Toast.makeText(applicationContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun validateForm(email: String, password: String): Boolean {
        var valid = true

        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            email_field.error = "Required."
            valid = false
        } else {
            email_field.error = null
        }

        if (TextUtils.isEmpty(password)) {
            password_field.error = "Required."
            valid = false
        } else {
            password_field.error = null
        }

        return valid
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

 */
}