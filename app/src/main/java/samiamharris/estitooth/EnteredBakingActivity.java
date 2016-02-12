package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class EnteredBakingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered_baking);

        ImageView milkLogo = (ImageView) findViewById(R.id.cookies_milk_image_view);
        Picasso.with(this).load(R.drawable.cookies_milk_main).centerInside().fit().into(milkLogo);

        Transition left = new Slide(Gravity.LEFT);
        Transition right = new Slide(Gravity.BOTTOM);

        left.excludeTarget(android.R.id.statusBarBackground, true);
        left.excludeTarget(android.R.id.navigationBarBackground, true);

        right.excludeTarget(android.R.id.statusBarBackground, true);
        right.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setExitTransition(left);
        getWindow().setEnterTransition(right);

        ImageButton playButton = (ImageButton) findViewById(R.id.play_fab);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cookieDoughIntent = new Intent(getBaseContext(), GatherIngredientsActivity.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        // the context of the activity
                        EnteredBakingActivity.this, null
                );
                ActivityCompat.startActivity(EnteredBakingActivity.this, cookieDoughIntent, options.toBundle());
            }
        });

    }
}
