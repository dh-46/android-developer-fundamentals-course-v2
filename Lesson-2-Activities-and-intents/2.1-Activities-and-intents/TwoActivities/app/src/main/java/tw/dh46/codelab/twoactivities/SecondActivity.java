package tw.dh46.codelab.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = BuildConfig.APPLICATION_ID + ".REPLY";
    private EditText mReplyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mReplyEditText = findViewById(R.id.edit_text_second);
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            if (msg != null) {
                TextView tvMessage = findViewById(R.id.text_message);
                tvMessage.setText(msg);
            }
        }
    }


    public void returnReply(View view) {
        if (mReplyEditText != null) {
            String msg = mReplyEditText.getText().toString();
            Intent intent = new Intent();
            intent.putExtra(EXTRA_REPLY, msg);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}