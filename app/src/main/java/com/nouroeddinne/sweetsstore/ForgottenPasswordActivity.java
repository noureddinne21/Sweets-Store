package com.nouroeddinne.sweetsstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgottenPasswordActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotten_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button2);
        editText = findViewById(R.id.editTextText2);

        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(v -> {

            resetPassword(editText.getText().toString());

        });



    }


    public void resetPassword(String email){

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgottenPasswordActivity.this, "Send secces check you email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgottenPasswordActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(ForgottenPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }










}