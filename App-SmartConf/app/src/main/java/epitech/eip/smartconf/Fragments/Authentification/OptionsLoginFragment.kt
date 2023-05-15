package epitech.eip.smartconf.Fragments.Authentification

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.Model.User
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.auth_sigbtns_layout.*

class OptionsLoginFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.auth_sigbtns_layout }
    override fun shouldShowActionBar(): Boolean { return false }
    override fun shouldUseCustomBack(): Boolean { return true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continue_button.setOnClickListener {
            showLoader(true)
            when(continue_button.text) {
                "continue" -> {
                    enterEmail()
                }
                "login" -> {
                    loginUser()
                }
                "register" -> {
                    registerUser()
                }
            }
        }
    }

    fun loginUser() {
        mAuth.signInWithEmailAndPassword(input_email.text.toString(), input_password.text.toString())
            .addOnCompleteListener(getAct()) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    showLoader(false)
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun registerUser() {
        mAuth.createUserWithEmailAndPassword(input_email.text.toString(), input_password.text.toString())
            .addOnCompleteListener(getAct()) { task ->
                if (task.isSuccessful) {
                    addUserDataToDatabase(input_email.text.toString())
                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    showLoader(false)
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserDataToDatabase(email: String) {
        val user = mAuth.currentUser!!
        val mUser = User(user.uid, email)
        val ref = FirebaseDatabase.getInstance().getReference("users")

        ref.child(user.uid).setValue(mUser)
    }

    fun enterEmail() {
        if (input_email.text.toString() == "" || !isValidEmail(input_email.text.toString())) {
            input_email.error = "Set a fucking email address bitch!"
            showLoader(false)
        } else {
            mAuth.fetchSignInMethodsForEmail(input_email.text.toString())
                .addOnCompleteListener {task ->
                    email.isEnabled = false
                    showLoader(false)
                    password.visibility = View.VISIBLE
                    if (task.result?.signInMethods?.size == 0) {
                        title_card.text = "Register"
                        password.hint = "Create your password..."
                        continue_button.text = "register"
                    } else {
                        title_card.text = "Login"
                        password.hint = "Enter your password..."
                        continue_button.text = "login"
                    }
                }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun showLoader(status: Boolean) {
        loader.visibility = View.VISIBLE.takeIf { status } ?: View.INVISIBLE
        continue_button.visibility = View.INVISIBLE.takeIf { status } ?: View.VISIBLE
    }
}