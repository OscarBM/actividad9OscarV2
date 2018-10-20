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

                Log.d("RECEIVER", "numer000: "+phoneNumber+" Mensaje: "+message );

                if(message.matches("(?i).*Pokemon.*")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                             Uri.parse("https://www.youtube.com/watch?v=cAXLDCI5voM"));
                    context.startActivity(browserIntent);
                }

                if(message.matches("(?i).*perro.*")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com.mx/search?rlz=1C1CHBF_esMX816MX816&biw=1280&bih=579&ei=A3bKW47SB42UtQWxopyYCg&q=perro&oq=perro&gs_l=psy-ab.3..0i67k1l4j0l4j0i131k1j0.48937.51201.0.51891.10.10.0.0.0.0.103.785.8j1.10.0....0...1c.1.64.psy-ab..0.10.851.6..35i39k1j0i131i67k1.69.6dASzsvb4Eg"));
                    context.startActivity(browserIntent);
                }


            }

        }

    }
}
