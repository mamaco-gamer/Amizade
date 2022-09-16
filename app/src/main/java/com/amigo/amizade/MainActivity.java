package com.amigo.amizade;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToggleButton lightsButton;
    private TextView lightsText;
    private AutoCompleteTextView item;
    private Spinner spinner;
    private AutoCompleteTextView tv;
    private RadioGroup rg;
    private Button submitItem;
    private Button aliceButton;
    private ArrayList<String> list;
    private ArrayAdapter itemAdapter;
    private int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.list = new ArrayList<String>();
        this.itemAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        this.lightsButton = findViewById(R.id.lights_button);
        this.lightsText = findViewById(R.id.light_text);
        this.item = findViewById(R.id.item);
        this.spinner = findViewById(R.id.spinner);
        this.submitItem = findViewById(R.id.submit_button);
        this.rg = findViewById(R.id.cd_form);
        this.aliceButton = findViewById(R.id.alice_button);
        this.defaultColor = Color.argb(255, 255, 255, 255);

        this.item.setAdapter(this.itemAdapter);
        this.item.setOnLongClickListener(v -> arrayToTrash());
        this.spinner.setAdapter(this.itemAdapter);
        this.submitItem.setOnClickListener(v -> radioButtonCheck(v));
        this.lightsButton.setOnCheckedChangeListener((v, c) -> lightButtonBehavior(c));
        this.aliceButton.setOnClickListener(v -> goToAliceWorld());
    }

    protected void lightButtonBehavior(Boolean checked) {
        View view = findViewById(R.id.textView).getRootView();
        if (!checked) {
            this.lightsText.setTextColor(Color.WHITE);
            view.setBackgroundColor(Color.argb(100, 0, 0, 0));
            return;
        }
        this.lightsText.setTextColor(Color.BLACK);
        view.setBackgroundColor(this.defaultColor);
    }

    public void radioButtonCheck(View v) {
        switch(this.rg.getCheckedRadioButtonId()) {
        case R.id.insert_radio:
            this.itemAdapter.add(this.item.getText().toString());
            break;
        case R.id.delete_radio:
            this.itemAdapter.remove(this.item.getText().toString());
            break;
        }
    }
    
    protected Boolean arrayToTrash() {
        this.itemAdapter.clear();
        Toast.makeText(getApplicationContext(), "List cleared", Toast.LENGTH_SHORT).show();
        return true;
    }

    protected void goToAliceWorld() {
        Intent alice = new Intent(this, Activity2.class);
        startActivity(alice);
    }
}