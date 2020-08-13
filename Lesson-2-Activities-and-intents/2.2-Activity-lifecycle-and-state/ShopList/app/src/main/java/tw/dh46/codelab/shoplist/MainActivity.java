package tw.dh46.codelab.shoplist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 123;
    private LinearLayout llListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llListContainer = findViewById(R.id.ll_list_container);

        if (savedInstanceState != null) {
            String[] itemList = savedInstanceState.getStringArray("selected");
            if (itemList != null) {
                for (String item : itemList) {
                    addItemView(item);
                }
            }
        }
    }

    private void addItemView(String item) {
        TextView textView = new TextView(this);
        textView.setText(item);
        textView.setPadding(8,8,8,8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(24,24,24,24);
        textView.setLayoutParams(layoutParams);
        llListContainer.addView(textView);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (llListContainer != null && llListContainer.getChildCount() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i =0; i < llListContainer.getChildCount(); i++) {
                TextView item = (TextView) llListContainer.getChildAt(i);
                String listItem = item.getText().toString();
                if (i != (llListContainer.getChildCount() -1)) {
                    sb.append(listItem).append(",");
                } else {
                    sb.append(listItem);
                }
            }
            String result = sb.toString();
            String[] resultArray = result.split(",");
            outState.putStringArray("selected", resultArray);
        }
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            if (data != null) {
                String item = data.getStringExtra("item");
                if (item != null) addItemView(item);
            }
        }
    }
}