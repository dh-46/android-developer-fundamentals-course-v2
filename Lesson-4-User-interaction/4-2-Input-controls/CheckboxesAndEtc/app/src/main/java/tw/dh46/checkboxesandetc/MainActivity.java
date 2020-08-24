package tw.dh46.checkboxesandetc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedList = new ArrayList<>();
    }

    public void selectCheckBox(View view) {
        CheckBox checkBox = (CheckBox) view;
        String text = (String) checkBox.getText();
        boolean isChecked = checkBox.isChecked();
        if (isChecked) {
            selectedList.add(text);
        } else {
            selectedList.remove(text);
        }
    }

    public void showToast(View view) {
        if (selectedList != null && selectedList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Toppings: ");
            for (String item : selectedList) {
                stringBuilder.append(item);
                if (selectedList.indexOf(item) != (selectedList.size() -1)) {
                    stringBuilder.append(" ");
                }
            }
            String result = stringBuilder.toString();
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}