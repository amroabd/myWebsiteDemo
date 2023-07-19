package com.is.mysendwhatsappauto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.is.mysendwhatsappauto.service.MyAccessibilityService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;




public class MainActivity extends AppCompatActivity {
    private final static String ACCOUNT_SID = "AC0a68bf92c64b8bcafed35c04f43b3ff7";
    private final static String AUTH_TOKEN = "12fdfd9dea2614162405f72747ed5560";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //callAccess();

        findViewById(R.id.sendWhats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageWhatsApp();
            }
        });

    }
    private void callAccess(){

        AccessibilityServiceManager serviceManager = new AccessibilityServiceManager(this);
        findViewById(R.id.sendWhats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (serviceManager.hasAccessibilityServicePermission(MyAccessibilityService.class)){
                    String message = "Your message",to = "967775523818";
                    startActivity(
                            new Intent(Intent.ACTION_SENDTO,
                                    Uri.parse(
                                            String.format("https://api.whatsapp.com/send?phone=%s&text=%s", to, message)
                                    )
                            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    );
                }else{
                    serviceManager.requestUserForAccessibilityService(MainActivity.this);
                }
        }
    });
    }

    public static void sendMessageWhatsApp() {
        //HttpClient.().useSystemProperties().build();
        Log.d("WhatsAppSender", "2-ph-start-> sendMessageWhatsApp");
        // Find your Account Sid and Token at twilio.com/console
        String toPhoneNumber = "whatsapp:+967775523818";
        final String fromPhoneNumber = "whatsapp:+14155238886";//14155238886
        String text = "message client whats";
        //Message
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ///----------
        try {
            Message message1 = Message.creator(ACCOUNT_SID,new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromPhoneNumber), text).create();
            Log.d("TAG", "getSid:" + message1.getSid());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}