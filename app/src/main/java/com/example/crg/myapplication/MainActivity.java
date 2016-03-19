package com.example.crg.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public static final String PRIFERENCE = "SURVEY";
    private EditText nameEditText;
    private EditText hobbyEditText;
    private CheckBox unitCheckBox;
    private RadioGroup unitProperty;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText) findViewById(R.id.name);
        hobbyEditText = (EditText) findViewById(R.id.hobby);
        unitCheckBox = (CheckBox) findViewById(R.id.isworking);
        unitProperty = (RadioGroup) findViewById(R.id.unit_property);
        radioButton1 = (RadioButton) findViewById(R.id.rb_unit1);
        radioButton2 = (RadioButton) findViewById(R.id.rb_unit2);
        radioButton3 = (RadioButton) findViewById(R.id.rb_unit3);
        unitCheckBox.setOnCheckedChangeListener(this);
        SharedPreferences preferences = getSharedPreferences(PRIFERENCE, MODE_PRIVATE);
        nameEditText.setText(preferences.getString("name",""));
        hobbyEditText.setText(preferences.getString("hobby", ""));
        unitCheckBox.setChecked(preferences.getBoolean("isworking", false));
        unitProperty.check(preferences.getInt("unitid", -1));
        onCheckedChanged(unitCheckBox,unitCheckBox.isChecked());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        radioButton1.setEnabled(isChecked);
        radioButton2.setEnabled(isChecked);
        radioButton3.setEnabled(isChecked);
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor editor = getSharedPreferences(PRIFERENCE, MODE_PRIVATE).edit();
        editor.putString("name", nameEditText.getText().toString());
        editor.putString("hobby", hobbyEditText.getText().toString());
        editor.putBoolean("isworking", unitCheckBox.isChecked());
        editor.putInt("unitid", unitProperty.getCheckedRadioButtonId());
        editor.commit();
        super.onStop();

    }
}
