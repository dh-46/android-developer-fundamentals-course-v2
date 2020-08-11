package tw.dh46.codelab.threepassages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null) {
            int type = intent.getIntExtra(MainActivity.EXTRA_MSG_TYPE, 0);
            switch (type) {
                case 1:
                    findViewById(R.id.tv_passage_1).setVisibility(View.VISIBLE);
                    break;
                case 2:
                    findViewById(R.id.tv_passage_2).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    findViewById(R.id.tv_passage_3).setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }
}