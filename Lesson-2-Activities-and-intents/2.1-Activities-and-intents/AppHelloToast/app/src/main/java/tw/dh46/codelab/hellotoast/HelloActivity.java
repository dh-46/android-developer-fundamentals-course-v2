package tw.dh46.codelab.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Intent intent = getIntent();
        if (intent != null) {
            int count = intent.getIntExtra(MainActivity.EXTRA_COUNT, 0);
            ((TextView)findViewById(R.id.text_count)).setText(String.valueOf(count));
        }
    }
}