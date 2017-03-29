package com.android.mis.javac.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.javac.Home.HomeActivity;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * A login screen that offers login via admission_no/password.
 */
public class LoginActivity extends AppCompatActivity implements Callback{


    // UI references.
    private AutoCompleteTextView mAdmnView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private View mErrorView;
    private HashMap<String,String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mAdmnView = (AutoCompleteTextView) findViewById(R.id.admn_no);
       // populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.loader);
        mErrorView = findViewById(R.id.err);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mAdmnView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mAdmnView.getText().toString();
        String password = mPasswordView.getText().toString();
        params = new HashMap<String, String>();
        params.put("username",username);
        params.put("password",password);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mAdmnView.setError(getString(R.string.error_field_required));
            focusView = mAdmnView;
            cancel = true;
        } else if (!isEmailValid(username)) {
            mAdmnView.setError(getString(R.string.error_invalid_email));
            focusView = mAdmnView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else{
            NetworkRequest nr = new NetworkRequest(LoginActivity.this,mProgressView,mErrorView,this,"post", Urls.server_protocol,Urls.login_url,params,false,false,0);
            nr.setSnackbar_message(Urls.error_connection_message);
            nr.initiateRequest();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length()>0;
    }


    @Override
    public void performAction(String result, int tag) {
        try{
            Log.d("result",result);
            JSONObject json = new JSONObject(result);
            Boolean success = json.getBoolean("success");
            if(success){
                String token = json.getString("token");
                SessionManagement session = new SessionManagement(getApplicationContext());
                session.createLoginSession(token);
                Bundle bundle = new Bundle();
                Util.moveToActivity(LoginActivity.this,HomeActivity.class,bundle);
            }
            else{
                Util.viewSnackbar(findViewById(android.R.id.content),json.getString("err_msg"));
            }
        }catch (Exception e){
            Log.e("Exception",e.toString());
            Util.viewSnackbar(findViewById(android.R.id.content),Urls.parsing_error_message);
        }
    }
}

