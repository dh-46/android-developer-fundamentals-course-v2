package tw.dh46.codelab.helloconstraint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button btnZero;
    private Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        btnCount = findViewById(R.id.button_count);
        btnZero = findViewById(R.id.button_zero);
    }

    public void showToast(View view) {
        // 2 seconds for LENGTH_SHORT 3.5 seconds for LENGTH_LONG
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));

        if (mCount % 2 == 0) {
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        }

        if (btnZero != null) {
            btnZero.setBackgroundColor(Color.RED);
        }

    }

    public void setZero(View view) {
        view.setBackgroundColor(Color.GRAY);
        mCount = 0;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
        if (btnCount != null) {
            btnCount.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        }
    }
}