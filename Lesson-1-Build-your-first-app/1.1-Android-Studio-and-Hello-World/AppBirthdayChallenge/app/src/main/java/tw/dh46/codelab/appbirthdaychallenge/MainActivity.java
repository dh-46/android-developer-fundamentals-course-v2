package tw.dh46.codelab.appbirthdaychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            throw new IllegalStateException();
        } catch (IllegalStateException e) {
            Log.e(TAG, "onCreate: catch me if you can haha", e);
        }
    }
}