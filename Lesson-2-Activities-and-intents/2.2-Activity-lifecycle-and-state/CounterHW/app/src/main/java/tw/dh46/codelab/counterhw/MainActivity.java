package tw.dh46.codelab.counterhw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCount = findViewById(R.id.tv_count);

//        if (savedInstanceState != null) {
//            // 一般還原習慣寫在這裡
//            mCount = savedInstanceState.getInt("count");
//            tvCount.setText(String.valueOf(mCount));
//        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // 這裡也可用來還原
        mCount = savedInstanceState.getInt("count");
        tvCount.setText(String.valueOf(mCount));
    }

    public void count(View view) {
        if (tvCount != null) {
            mCount++;
            tvCount.setText(String.valueOf(mCount));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", mCount);
    }
}