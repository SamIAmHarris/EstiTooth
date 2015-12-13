package samiamharris.estitooth;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class GatherIngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather_ingredients);

        ImageButton continueButton = (ImageButton) findViewById(R.id.continue_ingredients_fab);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cookieDoughIntent = new Intent(getBaseContext(), CookieDoughActivity.class);
                startActivity(cookieDoughIntent);
            }
        });

        final RelativeLayout chocoRelativeLayout = (RelativeLayout) findViewById(R.id.choco_chips_relative);
        final TextView chocoTextView = (TextView) findViewById(R.id.choco_chips_text);
        final ImageView chocoCheckView = (ImageView) findViewById(R.id.choco_chips_check);

        chocoRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chocoRelativeLayout.setBackground(getResources().getDrawable(R.drawable.checked_list_background));
                chocoTextView.setTextColor(getResources().getColor(R.color.cookie_text_color));
                chocoCheckView.setVisibility(View.VISIBLE);
            }
        });

        final RelativeLayout cookieRelativeLayout = (RelativeLayout) findViewById(R.id.cookie_dough_relative);
        final TextView cookieTextView = (TextView) findViewById(R.id.cookie_dough_text);
        final ImageView cookieCheckView = (ImageView) findViewById(R.id.cookie_dough_check);

        cookieRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cookieRelativeLayout.setBackground(getResources().getDrawable(R.drawable.checked_list_background));
                cookieTextView.setTextColor(getResources().getColor(R.color.cookie_text_color));
                cookieCheckView.setVisibility(View.VISIBLE);
            }
        });

        final RelativeLayout bakingRelativeLayout = (RelativeLayout) findViewById(R.id.baking_pan_relative);
        final TextView bakingTextView = (TextView) findViewById(R.id.baking_pan_text);
        final ImageView bakingCheckView = (ImageView) findViewById(R.id.baking_pan_check);

        bakingRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bakingRelativeLayout.setBackground(getResources().getDrawable(R.drawable.checked_list_background));
                bakingTextView.setTextColor(getResources().getColor(R.color.cookie_text_color));
                bakingCheckView.setVisibility(View.VISIBLE);
            }
        });

    }
}
