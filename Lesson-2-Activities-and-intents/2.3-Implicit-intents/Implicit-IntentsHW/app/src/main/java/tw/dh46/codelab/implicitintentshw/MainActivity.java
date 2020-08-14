package tw.dh46.codelab.implicitintentshw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri uri = Uri.parse(url);

        /**
         * Intent.ACTION_VIEW: to view the given data
         * Intent.ACTION_EDIT: to edit the given data
         * Intent.ACTION_DIAL: to dial a phone number
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            // Use the resolveActivity() method and the Android package manager
            // to find an Activity that can handle your implicit Intent
            startActivity(intent);
        } else {
            Log.e(TAG, "openWebsite: Cannot handle this!");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        // Geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.e(TAG, "openLocation: Cannot handle this!");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = getString(R.string.mime_text_plain);
        ShareCompat.IntentBuilder
                .from(this) // The Activity that launches this share Intent (this).
                .setType(mimeType) // The MIME type of the item to be shared.
                .setChooserTitle(R.string.share_title) // The title that appears on the system app chooser.
                .setText(txt) // The actual text to be shared
                .startChooser(); // Show the system app chooser and send the Intent.
    }

    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}