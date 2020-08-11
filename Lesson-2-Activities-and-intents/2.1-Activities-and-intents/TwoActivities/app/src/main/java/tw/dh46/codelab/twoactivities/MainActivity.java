package tw.dh46.codelab.twoactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE = BuildConfig.APPLICATION_ID + ".MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.edit_text_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Log.d(TAG, "launchSecondActivity: Button Clicked!");

        if (mMessageEditText != null) {
            String msg = mMessageEditText.getText().toString();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(EXTRA_MESSAGE, msg);
//            startActivity(intent);
            startActivityForResult(intent, TEXT_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                    mReplyHeadTextView.setVisibility(View.VISIBLE);
                    mReplyTextView.setVisibility(View.VISIBLE);
                    mReplyTextView.setText(reply);
                }
            } else {
                mReplyTextView.setVisibility(View.INVISIBLE);
                mReplyHeadTextView.setVisibility(View.INVISIBLE);
            }
        }
    }
}