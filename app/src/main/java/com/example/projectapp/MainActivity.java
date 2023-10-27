package com.example.projectapp;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.app.Activity;
import android.graphics.Color;
import android.content.Intent;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.projectapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import java.net.*;
import java.io.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import java.net.*;
import java.io.*;
import java.util.*;
public class MainActivity extends Activity implements View.OnClickListener {
        private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
        private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;
        private final static int REQUEST_TEXT = 0;
        private TextView textView;
        private EditText editText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        textView = new TextView(this);
        textView.setText("IPアドレスの入力（””をつけないで）");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(16);
        layout.addView(textView);

        editText = new EditText(this);
        editText.setText("");
        editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
        layout.addView(editText);

        Button button = new Button(this);
        button.setText("接続");
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        layout.addView(button);
    }


    public void onClick(View v){
        Intent intent = new Intent(this, SocketActivity.class);

        intent.putExtra("text", editText.getText().toString());

        startActivityForResult(intent, REQUEST_TEXT);
    }
}
