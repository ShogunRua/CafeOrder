package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView TextViewHello;
    private TextView TextViewEditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String name;
    private String password;
    // создаём переменную, которая будет хранить название выбранного напитка
    private String drink;

    //список добавок мы будем формировать при помощи метода StringBuilder
    private StringBuilder builderAdditions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        drink = getString(R.string.button_tea);
        TextViewHello = findViewById(R.id.TextViewHello);
        // нужно сформировать текст приветствия, в зависимости от имени введённого пользователя
        String hello = String.format(getString(R.string.TextViewHello), name);
        // теперь необходимо установить текст у нашего textView
        TextViewHello.setText(hello);
        TextViewEditions = findViewById(R.id.TextViewEditions);
        String additions = String.format(getString(R.string.TextViewEditions), drink);
        TextViewEditions.setText(additions);
        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
// передадим в качестве метода кнопку RadioButton
        RadioButton button = (RadioButton) view;
        int id = button.getId();
        if (id == R.id.radioButton_tea) {
            drink = getString(R.string.button_tea);
            spinnerTea.setVisibility(view.VISIBLE);
            spinnerCoffee.setVisibility(view.INVISIBLE);
            checkBoxLemon.setVisibility(view.VISIBLE);
        } else if (id == R.id.radioButton_coffee) {
            drink = getString(R.string.button_coffee);
            spinnerTea.setVisibility(view.INVISIBLE);
            spinnerCoffee.setVisibility(view.VISIBLE);
            checkBoxLemon.setVisibility(view.INVISIBLE);
        }
        String additions = String.format(getString(R.string.TextViewEditions), drink);
        TextViewEditions.setText(additions);


    }

    public void onClickSendOrder(View view) {
        builderAdditions.setLength(0);
        if (checkBoxMilk.isChecked()) {
    builderAdditions.append(getString(R.string.checkboxMilk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.checkboxSugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.button_tea))) {
            builderAdditions.append(getString(R.string.checkboxLemon)).append(" ");
        }
        String optionOfDrink = "";
        if(drink.equals(getString(R.string.button_tea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }else{
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
        String additions;
        if(builderAdditions.length() > 0){
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        }else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent );


    }
}