package tw.dh46.droidcafeinput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selectedItem;

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

        initSpinner();
    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            // Create ArrayAdapter using the string array and default spinner layout.
            ArrayAdapter<CharSequence> adapter =
                    ArrayAdapter.createFromResource(this,
                            R.array.labels_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setOnItemSelectedListener(this);
            // Apply the adapter to the spinner.
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            switch (view.getId()) {
                case R.id.sameday:
                    displayToast(getString(R.string.same_day_messenger_service));
                    break;
                case R.id.nextday:
                    displayToast(getString(R.string.next_day_ground_delivery));
                    break;
                case R.id.pickup:
                    displayToast(getString(R.string.pick_up));
                    break;
                default:
                    break;
            }
        }
    }

    public void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = parent.getItemAtPosition(position).toString();
        displayToast(selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
    }

    /**
     * 4.3: Menus and pickers Homework
     * @param view
     */
    public void showDatePicker(View view) {
        DialogFragment dialogFragment = new DatePickerDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * 4.3: Menus and pickers Homework
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public void showSelectedDate(int year, int month, int dayOfMonth) {
        Toast.makeText(this, "Date: " + year + "/" + month + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
    }
}