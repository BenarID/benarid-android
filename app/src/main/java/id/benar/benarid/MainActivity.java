package id.benar.benarid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText textInput = (EditText) findViewById(R.id.urlInput);
        final Button urlButton = (Button) findViewById(R.id.urlButton);
        Button browsePortalsButton = (Button) findViewById(R.id.button_portals_browse);

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no-op
            }

            @Override
            public void afterTextChanged(Editable s) {
                urlButton.setEnabled(s.length() > 0);
            }
        });

        urlButton.setEnabled(false);
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = textInput.getText().toString();
                openBrowser(url);
            }
        });
        browsePortalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPortalsList();
            }
        });
    }

    private void openBrowser(String url) {
        Intent browserIntent = new Intent(this, BrowserActivity.class);
        browserIntent.putExtra("url", url);
        startActivity(browserIntent);
    }

    private void openPortalsList() {
        Intent portalsListIntent = new Intent(this, PortalsActivity.class);
        startActivity(portalsListIntent);
    }

}
