package epitech.eip.smartconf.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import epitech.eip.smartconf.BaseClass.BaseActivity
import epitech.eip.smartconf.MainActivity
import epitech.eip.smartconf.R

class SplashScreenActivity: BaseActivity() {

    private val SPLASHSCREEN_TIME: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splashscreen_layout)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASHSCREEN_TIME)
    }
}