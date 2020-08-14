package tw.dh46.codelab.implicitintentsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/**
 * The receiver app has a very restrictive Intent filter
 * that matches only exact URI protocol (http) and host (developer.android.com).
 * Any other URI opens in the default web browser.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processIntent();
    }

    /**
     * Process the received Intent
     */
    private void processIntent() {
        Intent intent = getIntent();
        // Intent data is ALWAYS a URI Object
        Uri uri = intent.getData();

        if (uri != null) {
            String uriString = getString(R.string.uri_label) + uri.toString();
            TextView textView = findViewById(R.id.text_uri_message);
            textView.setText(uriString);
        }
    }
}