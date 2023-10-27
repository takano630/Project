package com.example.projectapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
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

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import java.net.*;
import java.io.*;
import java.util.*;
public class SocketActivity extends AppCompatActivity {
    static Socket s;
    static OutputStream sout;
    static InputStream sin;

    private final static int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final static int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    private final static String BR = System.getProperty("line.separator");

    private TextView textView;

    private EditText editText;

    private TextView recieveView;

    static String text;
    static String str;

    static BufferedReader br;
    static PrintWriter pw;

    static Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        text = "";
        str = "";

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            text = extras.getString("text");
        }

        textView = new TextView(this);
        textView.setText("入力画面");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(16);
        layout.addView(textView);

        editText = new EditText(this);
        editText.setText("");
        editText.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
        layout.addView(editText);


        Button button = new Button(this);
        button.setText("send");
        //button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        layout.addView(button);

        recieveView = new TextView(this);
        recieveView.setText("start");
        recieveView.setTextColor(Color.BLACK);
        recieveView.setTextSize(16);
        layout.addView(recieveView);

        new Thread(() -> {
            try{
                s = new Socket(text, 4321);
                sout = s.getOutputStream();
                sin = s.getInputStream();
                br = new BufferedReader(new InputStreamReader(sin));
                pw = new PrintWriter(new OutputStreamWriter(sout),true);
                pw.println("hello android");

                while(s != null) {
                    str = br.readLine();
                    if(str == null){
                        continue;
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            recieveView.setText(str + BR + recieveView.getText());
                        }
                    });
                }


            }catch(UnknownHostException | SecurityException e) {
                e.printStackTrace();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();


        button.setOnClickListener(v -> {
            new Thread(() -> {
                pw.println(editText.getText().toString());
                editText.setText("");
            }).start();
        });



    }
}