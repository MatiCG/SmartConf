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
import kotlinx.android.synthetic.main.act_login_layout.*
class MainAuthFragment: BaseFragment() {
    override fun getLayout(): Int { return R.layout.act_login_layout }
    override fun shouldShowActionBar(): Boolean { return false }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctn_btn.setOnClickListener {
            showLoader(true)
            when(ctn_btn.text) {
                getString(R.string.fui_continue) -> {
                    ctn_btn.text = getString(R.string.btn_login)
                    enterEmail()
                }
                getString(R.string.btn_login) -> {
                    loginUser()
                }
                getString(R.string.btn_register) -> {
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
            input_email.error = "Please enter an email address"
            showLoader(false)
        } else {
            mAuth.fetchSignInMethodsForEmail(input_email.text.toString())
                .addOnCompleteListener {task ->
                    email.isEnabled = false
                    showLoader(false)
                    password.visibility = View.VISIBLE
                    if (task.result?.signInMethods?.size == 0) {
                        password.hint = "Create your password..."
                        ctn_btn.text = getString(R.string.btn_register)
                    } else {
                        password.hint = "Enter your password..."
                        ctn_btn.text = getString(R.string.btn_login)
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
        ctn_btn.visibility = View.INVISIBLE.takeIf { status } ?: View.VISIBLE
    }
}