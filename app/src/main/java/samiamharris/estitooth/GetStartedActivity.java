package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by SamMyxer on 1/28/16.
 */
public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterExitTransitions();
        setContentView(R.layout.activity_get_started);

        ImageView bearImageView = (ImageView) findViewById(R.id.bear);
        ImageView ovenImageView = (ImageView) findViewById(R.id.oven_image);
        ImageView bookImageView = (ImageView) findViewById(R.id.book);

        Picasso.with(this).load(R.drawable.book).centerCrop().fit().into(bookImageView);
        Picasso.with(this).load(R.drawable.bear).centerCrop().fit().into(bearImageView);
        Picasso.with(this).load(R.drawable.cook).centerCrop().fit().into(ovenImageView);

        Button blueButton = (Button) findViewById(R.id.get_started_toy_button);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedIntentToSearchingActivity();
            }
        });
    }

    public void setEnterExitTransitions() {
        Transition left = new Slide(Gravity.LEFT);
        Transition right = new Slide(Gravity.RIGHT);

        left.excludeTarget(android.R.id.statusBarBackground, true);
        left.excludeTarget(android.R.id.navigationBarBackground, true);

        right.excludeTarget(android.R.id.statusBarBackground, true);
        right.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setExitTransition(left);
        getWindow().setEnterTransition(right);
    }

    public void animatedIntentToSearchingActivity() {
        Intent roosterIntent = new Intent(GetStartedActivity.this, SearchingActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                GetStartedActivity.this, null
        );
        ActivityCompat.startActivity(GetStartedActivity.this, roosterIntent, options.toBundle());
    }
}
