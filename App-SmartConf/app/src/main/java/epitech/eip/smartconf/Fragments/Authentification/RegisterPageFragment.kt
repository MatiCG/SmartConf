package epitech.eip.smartconf.Fragments.Authentification

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import epitech.eip.smartconf.BaseClass.BaseFragment
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R
import kotlinx.android.synthetic.main.frag_register_layout.*

class RegisterPageFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.frag_register_layout }
    override fun shouldShowActionBar(): Boolean { return false }
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        btn_register.setOnClickListener {
            val email = email_field.text.toString()
            val password = password_field.text.toString()

            registerUser(email, password)
        }
    }

    private fun registerUser(email: String, password: String) {
        if (!validateForm(email, password))
            return
        showLoader(true)
        mAuth.createUserWithEmailAndPassword(email_field.text.toString(), password_field.text.toString())
            .addOnCompleteListener(getAct()) { task ->
                if (task.isSuccessful) {
                    addUserDataToDatabase(email)
                    startActivity(Intent(context, MainActivity::class.java).putExtra("logged", true))
                } else {
                    showLoader(false)
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserDataToDatabase(email: String, Fname: String = "not_defined", Lname: String = "not_defined") {
        val user = mAuth.currentUser
        val map: MutableMap<String, String> = HashMap()
        if (user != null) {
            val ref = FirebaseDatabase.getInstance().getReference("users")
            map["email"] = email
            map["Fname"] = Fname
            map["Lname"] = Lname
            ref.child(user.uid).setValue(map)
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
        btn_register.visibility = View.GONE.takeIf { status } ?: View.VISIBLE
    }

}