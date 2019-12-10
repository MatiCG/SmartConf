package epitech.eip.smartconf.Fragments.Authentification

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.Model.User
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_login_layout.*

class LoginPageFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_login_layout }
    override fun shouldShowActionBar(): Boolean { return false }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val email = email_field.text.toString()
            val password = password_field.text.toString()

            logUser(email, password)
        }

    }

    private fun logUser(email: String, password: String) {
        if (!validateForm(email, password))
            return
        showLoader(true)
        mAuth.signInWithEmailAndPassword(email_field.text.toString(), password_field.text.toString())
            .addOnCompleteListener(getAct()) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    showLoader(false)
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
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

    private fun showLoader(status: Boolean) {
        load.visibility = View.VISIBLE.takeIf { status } ?: View.GONE
        btn_login.visibility = View.GONE.takeIf { status } ?: View.VISIBLE
    }
}