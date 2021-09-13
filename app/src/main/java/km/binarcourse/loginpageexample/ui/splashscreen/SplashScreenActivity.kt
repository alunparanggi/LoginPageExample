package km.binarcourse.loginpageexample.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import km.binarcourse.loginpageexample.R
import km.binarcourse.loginpageexample.data.preference.UserPreference
import km.binarcourse.loginpageexample.ui.homepage.HomeActivity
import km.binarcourse.loginpageexample.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setSplashTimer()
        supportActionBar?.hide()
    }

    private fun setSplashTimer() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                if (UserPreference(this@SplashScreenActivity).isUserLoggedIn) {
                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }

        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}