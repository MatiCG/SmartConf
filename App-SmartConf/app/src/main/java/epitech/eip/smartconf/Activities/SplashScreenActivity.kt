package epitech.eip.smartconf.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R

class SplashScreenActivity: BaseActivity() {

    private val SPLASHSCREEN_TIME: Long = 2000
//    private lateinit var auth: FirebaseAuth
    private var USER_CONNECTED: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splashscreen_layout)


        Handler().postDelayed({
            //auth = FirebaseAuth.getInstance()
            //USER_CONNECTED = true.takeIf { auth.currentUser != null } ?: false
            /*
            val intent = when (USER_CONNECTED) {
                true -> {
                    Intent(this, MainActivity::class.java)
                }
                false -> {
                    Intent(this, LoginActivity::class.java)
                }
            }
             */
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASHSCREEN_TIME)
    }
}