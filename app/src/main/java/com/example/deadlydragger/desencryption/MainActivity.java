package com.example.deadlydragger.desencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64InputStream;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button click;
    private EditText textView;
    private TextView text,text2;
    private static Cipher ecipher;
    private static  Cipher dciper;
    private static SecretKey secretKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button) findViewById(R.id.click);
        text=(TextView) findViewById(R.id.textView);
        text2=(TextView) findViewById(R.id.textView2);
        textView=(EditText) findViewById(R.id.getText);
        click.setOnClickListener(this);
        try {
            secretKey= KeyGenerator.getInstance("DES").generateKey();
            ecipher=Cipher.getInstance("DES");
            dciper=Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE,secretKey);
            dciper.init(Cipher.DECRYPT_MODE,secretKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click:
                String editext=textView.getText().toString();
                encrypeKey(editext);
                String dec= encrypeKey(editext).toString();
              decrypt(dec);
                break;
        }
    }
 public  String encrypeKey(String str){
     try {
         byte[] utf8 = str.getBytes("UTF8");
         byte[] enc = ecipher.doFinal(utf8);
         text.setText(enc.toString());
         return new String(enc);

     }catch (Exception e){
         e.printStackTrace();
     }
    return  null;

 }
    public  String decrypt(String str){
        try {

            byte[] dcp = dciper.doFinal(str.getBytes());
            text2.setText(dcp.toString());
            return new String(dcp);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }

}
