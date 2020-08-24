package tw.dh46.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if (getIntent() != null) {
            String orderMsg = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
            if (orderMsg != null) {
                TextView tvOrder = findViewById(R.id.order_textview);
                tvOrder.setText("Order: " + orderMsg);
            }
        }
    }
}