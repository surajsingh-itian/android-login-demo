package com.phyder.myhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.phyder.myhelper.net.LoginRequest;
import com.phyder.myhelper.net.LoginResponse;
import com.phyder.myhelper.net.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mUsername;
    private TextInputLayout mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (TextInputLayout) findViewById(R.id.tilUsername);
        mPassword = (TextInputLayout) findViewById(R.id.tilPassword);
    }

    public void doLogin(View view) {
        String username = (mUsername.getEditText() != null)
                ? mUsername.getEditText().getText().toString().trim() : null;
        String password = (mPassword.getEditText() != null)
                ? mPassword.getEditText().getText().toString().trim() : null;

        mUsername.setError(null);
        mPassword.setError(null);

        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_required_username));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_required_password));
            return;
        }

        serverLogin(username, password);
    }

    private void serverLogin(@NonNull String username, @NonNull String password) {
        MyHelperApp app = (MyHelperApp) getApplication();
        Retrofit retrofit = app.getRetrofit();

        LoginService loginService = retrofit.create(LoginService.class);
        Call<LoginResponse> login = loginService.login(new LoginRequest(username, password));

        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse res = response.body();
                    if (res.isStatus()) {
                        openDashboard(res);
                    } else {
                        showError(getString(R.string.error_invalid_username_password));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                showError(getString(R.string.error_server_connection));
            }
        });


    }

    private void openDashboard(@NonNull LoginResponse response) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, response.getDisplayName());
        startActivity(intent);
        finish();
    }

    private void showError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }
}
