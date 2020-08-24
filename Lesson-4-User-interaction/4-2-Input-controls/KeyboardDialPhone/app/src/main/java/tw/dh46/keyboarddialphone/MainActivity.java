package tw.dh46.keyboarddialphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEdtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtPhone = findViewById(R.id.edt_phone);
        if (mEdtPhone != null) {
            mEdtPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    // 使用者按下送出按鈕
                    boolean handled = false; // 是否由override的方法處理
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handled = true;
                    }
                    return handled;
                }
            });
        }
    }

    /**
     * 撥電話
     */
    private void dialNumber() {
        if (mEdtPhone != null) {
            String phoneNum = "tel:" + mEdtPhone.getText().toString();
            Log.d(TAG, "dialNumber: " + phoneNum);

            Intent intent = new Intent(Intent.ACTION_DIAL);
            // Set the data for the intent as the phone number.
            intent.setData(Uri.parse(phoneNum));

            if (intent.resolveActivity(getPackageManager()) != null) {
                // 有APP可以處理這個Intent
                startActivity(intent);
            } else {
                Log.e(TAG, "dialNumber: no available apps to handle this intent.");
            }
        }
    }
}