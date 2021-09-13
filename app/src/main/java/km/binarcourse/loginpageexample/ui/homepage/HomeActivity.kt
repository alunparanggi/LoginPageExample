package km.binarcourse.loginpageexample.ui.homepage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.shashank.sony.fancytoastlib.FancyToast
import km.binarcourse.loginpageexample.R
import km.binarcourse.loginpageexample.data.preference.UserPreference
import km.binarcourse.loginpageexample.databinding.ActivityHomeBinding
import km.binarcourse.loginpageexample.ui.login.LoginActivity
import km.binarcourse.loginpageexample.utils.Constants
import km.binarcourse.loginpageexample.utils.ProtectedMediaChromeClient

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.text_actionbar_homepage)

        //inflate webview
        setupWebView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.wvPage.settings.javaScriptEnabled = true
        binding.wvPage.settings.allowContentAccess = true
        binding.wvPage.settings.domStorageEnabled = true
        binding.wvPage.webChromeClient = ProtectedMediaChromeClient()
        binding.wvPage.loadUrl(Constants.URL_WEB_YOUTUBE)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_action_logout -> {
                FancyToast.makeText(
                    this,
                    getString(R.string.text_logout_success),
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    true
                ).show()

                deleteSessionData()
                navigateToLogin()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun deleteSessionData(){
        UserPreference(this).isUserLoggedIn = false
    }
}