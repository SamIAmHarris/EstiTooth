package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class CookieDoughActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_dough);

        ImageButton continueButton = (ImageButton) findViewById(R.id.continue_cookie_fab);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CookieDoughActivity.this, "All Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
