package tw.dh46.codelab.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

        Log.d(TAG, "--------");
        Log.d(TAG, "onCreate");

        if (savedInstanceState != null) {
            // 處理Configuration Changed
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        Log.d(TAG, "--------");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // 在螢幕旋轉前或是config changed前儲存需要儲存的資料
        // 有些Layout會自動儲存並restore 像是 EditText
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }
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