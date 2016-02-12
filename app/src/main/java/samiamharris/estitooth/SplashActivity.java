package samiamharris.estitooth;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by SamMyxer on 1/28/16.
 */
public class SplashActivity extends AppCompatActivity {

    ImageView labsLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExitTransitionLeft();

        setContentView(R.layout.activity_splash);

        labsLogo = (ImageView) findViewById(R.id.labs_logo);
        Picasso.with(this).load(R.drawable.labs).centerCrop().fit().into(labsLogo);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                translateYAnimation(labsLogo);
            }
        }, 500);

        handler.postDelayed(new Runnable() {
            public void run() {
                animatedIntentToGetStartedActivity();
            }
        }, 2000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        labsLogo.setImageBitmap(null);
    }

    public void setExitTransitionLeft() {
        Transition left = new Slide(Gravity.LEFT);
        left.excludeTarget(android.R.id.statusBarBackground, true);
        left.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setExitTransition(left);
    }

    public void animatedIntentToGetStartedActivity() {
        Intent roosterIntent = new Intent(SplashActivity.this, GetStartedActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                SplashActivity.this, null
        );
        ActivityCompat.startActivity(SplashActivity.this, roosterIntent, options.toBundle());
    }


    public void translateYAnimation(View v) {
        int distanceMoved = convertDPToPixels(this, 150);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y,
                -distanceMoved);
        translateY.setDuration(1000);
        translateY.setInterpolator(new OvershootInterpolator());
        translateY.start();
    }

    public int convertDPToPixels(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
