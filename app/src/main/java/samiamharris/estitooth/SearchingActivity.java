package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;

/**
 * Created by SamMyxer on 1/28/16.
 */
public class SearchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterExitTransitions();
        setContentView(R.layout.activity_searching);

        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            public void run() {
                sendBakingIntent();
            }
        }, 5000);
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

    public void sendBakingIntent() {
        Intent bakingIntent = new Intent(getBaseContext(), EnteredBakingActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                SearchingActivity.this, null
        );
        ActivityCompat.startActivity(SearchingActivity.this, bakingIntent, options.toBundle());
    }

}
