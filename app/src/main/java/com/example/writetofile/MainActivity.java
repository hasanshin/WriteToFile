package com.example.writetofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button btnInputText, btnReadFile;
    EditText editTextToFile, editTextReadFile;
    final String fileName = "name";
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInputText = findViewById(R.id.btnInputText);
        btnReadFile = findViewById(R.id.btnReadFile);
        editTextReadFile = findViewById(R.id.editTextReadFile);
        editTextToFile = findViewById(R.id.editTextToFile);

        btnInputText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // отрываем поток для записи
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(fileName, MODE_PRIVATE)));
                    // пишем данные
                    bw.write(editTextToFile.getText().toString());
                    // закрываем поток
                    bw.close();
                    Log.d(LOG_TAG, "Файл записан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*try {
                    FileWriter fileWriter =new FileWriter(fileName,true);
                    fileWriter.write(editTextToFile.getText().toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });
        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // открываем поток для чтения
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            openFileInput(fileName)));
                    String str = "";
                    // читаем содержимое
                    while ((str = br.readLine()) != null) {
                        Log.d(LOG_TAG, str);
                        editTextReadFile.setText(str);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}