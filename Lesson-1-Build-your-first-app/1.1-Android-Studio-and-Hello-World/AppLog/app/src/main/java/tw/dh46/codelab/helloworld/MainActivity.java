package tw.dh46.codelab.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // By convention, log tags are defined as constants for the Activity
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Hello World"); // Debug
        Log.i(TAG, "Hello World"); // Info
        Log.e(TAG, "Hello World"); // Error
        Log.v(TAG, "Hello World"); // Verbose
        Log.w(TAG, "Hello World"); // Warning

        /**
         * The result in Logcat pane should look like these lines below:
         * 2020-08-05 16:25:35.351 7010-7010/tw.dh46.codelab.helloworld D/MainActivity: Hello World
         * 2020-08-05 16:25:35.351 7010-7010/tw.dh46.codelab.helloworld I/MainActivity: Hello World
         * 2020-08-05 16:25:35.351 7010-7010/tw.dh46.codelab.helloworld E/MainActivity: Hello World
         * 2020-08-05 16:25:35.351 7010-7010/tw.dh46.codelab.helloworld V/MainActivity: Hello World
         * 2020-08-05 16:25:35.351 7010-7010/tw.dh46.codelab.helloworld W/MainActivity: Hello World
         */
    }
}