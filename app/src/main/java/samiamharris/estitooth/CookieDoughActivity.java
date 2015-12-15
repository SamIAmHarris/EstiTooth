package samiamharris.estitooth;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class CookieDoughActivity extends AppCompatActivity {


    ImageView cookieOne;
    ImageView cookieTwo;
    ImageView cookieThree;
    ImageView cookieFour;
    ImageView cookieFive;
    ImageView cookieSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_dough);

        cookieOne = (ImageView) findViewById(R.id.cookie_1);
        cookieTwo = (ImageView) findViewById(R.id.cookie_2);
        cookieThree = (ImageView) findViewById(R.id.cookie_3);
        cookieFour = (ImageView) findViewById(R.id.cookie_4);
        cookieFive = (ImageView) findViewById(R.id.cookie_5);
        cookieSix = (ImageView) findViewById(R.id.cookie_6);

        AnimatorSet setOne = fadeViewAnimation(cookieOne);
        AnimatorSet setTwo = fadeViewAnimation(cookieTwo);
        AnimatorSet setThree = fadeViewAnimation(cookieThree);
        AnimatorSet setFour = fadeViewAnimation(cookieFour);
        AnimatorSet setFive = fadeViewAnimation(cookieFive);
        AnimatorSet setSix = fadeViewAnimation(cookieSix);

        AnimatorSet allTogetherSet = new AnimatorSet();
        allTogetherSet.playSequentially(setOne, setTwo, setThree, setFour, setFive, setSix);
        allTogetherSet.start();


        final ImageButton continueButton = (ImageButton) findViewById(R.id.continue_cookie_fab);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueButton.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        });
    }

    public AnimatorSet fadeViewAnimation(View v) {
        v.setAlpha(0f);
        v.setVisibility(View.VISIBLE);

        ObjectAnimator moveRight = ObjectAnimator.ofFloat(v, View.TRANSLATION_X,
                -dpToPixels(24));
        moveRight.setDuration(450);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, View.ALPHA,
                100f);
        fadeIn.setDuration(50);

        ObjectAnimator shrinkX = ObjectAnimator.ofFloat(v, View.SCALE_X,
                0.55f);
        shrinkX.setDuration(450);

        ObjectAnimator shrinkY = ObjectAnimator.ofFloat(v, View.SCALE_Y,
                0.55f);
        shrinkY.setDuration(450);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(fadeIn, moveRight, shrinkX, shrinkY);
        return set;
    }

    public float dpToPixels(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


}
