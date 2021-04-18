package com.thehindu.themain

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.thehindu.R
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient

class GoogleLoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var signInButton: SignInButton;
    private lateinit var googleApiClient: GoogleApiClient;
    private val SIGN_IN = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        val preferenceHelper = PreferenceHelper.getSharedPreferences(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        signInButton = findViewById(R.id.btn_login_with_google)

        if (PreferenceHelper.readBooleanFromPreference(LocalConstants.PREF_USER_LOGIN)) {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityIfNeeded(intent, SIGN_IN);
        }

        signInButton.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityIfNeeded(intent, SIGN_IN);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result!!.isSuccess) {
                startActivity(Intent(this, SideNavActivity::class.java));
                finish()
            } else {
                Toast.makeText(this, "Login is Failed"+result.status, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}