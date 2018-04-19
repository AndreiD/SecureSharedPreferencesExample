package bg.avid.encryptionhelper;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.securepreferences.SecurePreferences;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //put this after the user enters a pin

    //Note 1: see https://www.pocketmagic.net/android-unique-device-id/
    //Note 2: you can modify it // change it to something else
    String m_szDevIDShort = "35" + //we make this look like a valid IMEI
        Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
        Build.CPU_ABI.length() % 10 + Build.HOST.length() % 10 +
        Build.DISPLAY.length() % 10 + Build.DEVICE.length() % 10 +
        Build.PRODUCT.length() % 10 + Build.MANUFACTURER.length() % 10 +
        Build.MODEL.length() % 10 + Build.ID.length() % 10 +
        Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
        Build.USER.length() % 10; //13 digits

    SecurePreferences sPrefs = new SecurePreferences(this, "user_pin" + m_szDevIDShort, "my_user_prefs.xml");

    String yourSecretToken = "the_super_secret_token";

    //store them encrypted
    sPrefs.edit().putString("user_token", yourSecretToken).commit();

    //get them...
    String decrypted = sPrefs.getString("user_token", null);
    Log.d("> DECRYPTED > ", decrypted);
  }
}
