package com.example.techseeker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Message extends AppCompatActivity {

    EditText editPhone, editContent;
    Button btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        editPhone = findViewById(R.id.editMNumber);
        editContent = findViewById(R.id.editContent);
        btnMessage = findViewById(R.id.btnMessage);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS);
                if (permission == PackageManager.PERMISSION_GRANTED){
                    myMessage();
                }
                else {
                    ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.SEND_SMS},
                            0);

                }
            }
        });
    }

    public void myMessage(){
        String phone = editPhone.getText().toString().trim();
        String content = editContent.getText().toString().trim();
        if (!editPhone.getText().toString().equals(("")) || !editContent.getText().toString().equals((""))) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, content, null, null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please enter your number or message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
                if (grantResults.length >= 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    myMessage();
                }
                else {
                    Toast.makeText(this, "No permission for message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}