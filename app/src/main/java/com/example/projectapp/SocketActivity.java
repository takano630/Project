package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.net.*;
import java.io.*;
import android.util.Log;
public class SocketActivity extends AppCompatActivity {
    void Connect(){
        Socket cSocket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try{
            cSocket = new Socket("127.0.0.1", 4321);

            //送信
            writer = new PrintWriter(cSocket.getOutputStream(), true);

            //受診
            reader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));

            while (true) {
                writer.println("2");

                Log.d("test","サーバー側回答：" + reader.readLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (cSocket != null) {
                    cSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("終了");
        }
    }
}


