package ba.unsa.etf.bp.udat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginRequestData;
import ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models.LoginResponseData;

public class LoginActivity extends AppCompatActivity implements LoginRequestTask.OnLoginRequestDone{
    Button buttonLogin, buttonCancel;
    EditText edUsername, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCancel = findViewById(R.id.buttonCancel);

        edUsername = findViewById(R.id.editTextUsername);
        edPassword = findViewById(R.id.editTextPassword);

        final LoginActivity self = this;

        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequestData data = new LoginRequestData(edUsername.getText().toString(),
                        edPassword.getText().toString());
                new LoginRequestTask(self).execute(data);

            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onDone(LoginResponseData responseData) {
        if (responseData != null && responseData.getJwt() != null) {
            SessionService.startSession(responseData, getBaseContext());
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Pogresni podaci!",Toast.LENGTH_SHORT).show();
        }
    }
}
