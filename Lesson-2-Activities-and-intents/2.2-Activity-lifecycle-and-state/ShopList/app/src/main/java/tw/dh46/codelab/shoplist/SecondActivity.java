package tw.dh46.codelab.shoplist;

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
    }

    public void onItemSelected(View view) {
        TextView tvItem = (TextView) view;
        String item = tvItem.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("item", item);
        setResult(RESULT_OK, intent);
        finish();
    }
}