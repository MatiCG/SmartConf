package epitech.eip.smartconf.Utils

import com.google.firebase.auth.FirebaseAuth

class User {
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var email: String
    private lateinit var mAuth: FirebaseAuth

    fun User(Fname: String, Lname: String, email: String) {
        mAuth = FirebaseAuth.getInstance()
        this.firstName = Fname
        this.lastName = Lname
        this.email = email
    }

    fun getUserEmail(): String? { return mAuth.currentUser?.email }

}