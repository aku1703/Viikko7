package com.example.viikko7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static Context context = null;
    private static String fileName;
    private TextView textView;
    private TextView textViewFileName;
    private EditText editText;
    private String stringFromUser;
    private Button button;
    private Button buttonSave;
    private String txtToBeCopied;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        button = (Button) findViewById(R.id.button1);
        buttonSave = (Button) findViewById(R.id.bt_saveFileName);
        textView = (TextView) findViewById(R.id.tv_text);
        textViewFileName = (TextView) findViewById(R.id.tv_fileNameFromUser);
        editText = (EditText) findViewById(R.id.editTextTextField);
        final EditText copyText = (EditText) findViewById(R.id.et_alkuteksti);
        result = (TextView) findViewById(R.id.tv_lopputeksti);



        final String textToTextField = "Empty string";
        textView.setText(textToTextField);

        try {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    stringFromUser = editText.getText().toString();
                    textView.setText(stringFromUser);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hello World!");
                textView.setText(stringFromUser);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName = textViewFileName.getText().toString();
                txtToBeCopied = copyText.getText().toString();
                writeFile(txtToBeCopied);
                readFile();
            }
        });

    }
        public static void writeFile(String txt) {
            try {
                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, MODE_APPEND));
                ows.write(txt);
                ows.close();

            } catch (IOException e) {
                Log.e("IOException", "Problem found");
            } finally {
                System.out.println("writefile menty loppuun");
            }
        }

        public void readFile() {
            String s = "";

            try {
                InputStream ins = context.openFileInput(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                String detail;
                while ((detail = br.readLine()) != null) {
                    s = s + detail + "\n";
                    result.setText(s);

                }
                ins.close();
                br.close();

            } catch (IOException e) {
                Log.e("IOException", "IOException problem found");
            } finally {
                System.out.println("readFile menty loppuun");
            }

        }
    }


