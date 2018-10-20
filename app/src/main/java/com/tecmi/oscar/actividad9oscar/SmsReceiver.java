package com.tecmi.oscar.actividad9oscar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RECEIVER", intent.getAction().toString());

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Log.d("RECEIVER", "Hola amigo");

            final Bundle bundle = intent.getExtras();
            Log.d("RECEIVER", bundle.get("pdus").toString());
            final Object[] pdusObj = (Object[]) bundle.get("pdus");
            for (int i = 0; i < pdusObj.length; i++){

                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                String message = currentMessage.getMessageBody();

                Log.d("RECEIVER", "numer00: "+phoneNumber+" Mensaje: "+message );

                if(message.matches("(?i).*Oreo.*")){

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.mx/"));
                context.startActivity(browserIntent);}


            }

        }

    }
}
