package km.binarcourse.loginpageexample.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shashank.sony.fancytoastlib.FancyToast
import km.binarcourse.loginpageexample.R
import km.binarcourse.loginpageexample.data.preference.UserPreference
import km.binarcourse.loginpageexample.databinding.ActivityLoginBinding
import km.binarcourse.loginpageexample.ui.homepage.HomeActivity
import km.binarcourse.loginpageexample.utils.Constants

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setClickListener()

    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener {
            if(isFormLoginValid()){
                checkLogin()
            }
        }

        binding.btnFbLogin.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_fb_login_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }

        binding.btnGmailLogin.setOnClickListener {
            FancyToast.makeText(
                this,
                getString(R.string.text_gmail_login_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }
    }

    private fun isFormLoginValid(): Boolean {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        if(username.isEmpty()){
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.text_error_empty_username)

        } else {
            binding.tilUsername.isErrorEnabled = false
        }

        if(password.isEmpty()){
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.text_error_empty_password)

        } else {
            binding.tilPassword.isErrorEnabled = false
        }

        return isFormValid

    }

    private fun checkLogin(){
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if(username == Constants.DUMMY_USERNAME && password == Constants.DUMMY_PASSWORD) {
            FancyToast.makeText(
                this,
                getString(R.string.text_login_success),
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
            saveLoginData()
            navigateToHomePage()
        }
    }

    private fun navigateToHomePage(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun saveLoginData(){
        UserPreference(this).isUserLoggedIn = true
    }
}