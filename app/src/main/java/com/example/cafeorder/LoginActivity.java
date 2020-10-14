package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText EditTextName;
    private EditText EditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditTextName = findViewById(R.id.editTextTextName);
        EditTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onClickCreateOrder(View view) {
        // в методе onClickCreateOrder, мы должны получить текст введённый пользователем, для того чтобы избавиться от лишних пробелов, нужно вызвать метод trim
        String name = EditTextName.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();
        // теперь мы должны запустить активность в том случае, если и имя и пароль содержат какие либо символы, сделаем проверку
        if (!name.isEmpty() && !password.isEmpty()) {


            Intent intent = new Intent(this, CreateOrderActivity.class);
            // в интент мы должны вложить имя и пароль, это делается при помощи метода putExtra
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            startActivity(intent);
        } else {
            // метод Toast содержит три параметра: контекст, строку и длительность на которую она выплывает , чтобы показать строку нужно также вызвать метод show, важно занести текст в строковые ресурсы
            Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }
    }
}