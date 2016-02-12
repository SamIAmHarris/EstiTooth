package samiamharris.estitooth;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class RoosterActivity extends AppCompatActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    MediaPlayer roosterPlayer;

    boolean isPaused = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transition left = new Slide(Gravity.LEFT);
        Transition right = new Slide(Gravity.BOTTOM);

        left.excludeTarget(android.R.id.statusBarBackground, true);
        left.excludeTarget(android.R.id.navigationBarBackground, true);

        right.excludeTarget(android.R.id.statusBarBackground, true);
        right.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setExitTransition(left);
        getWindow().setEnterTransition(right);


        setContentView(R.layout.activity_rooster);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setClipToPadding(false);
        mPager.setPadding(260, 0, 260, 0);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(1);

        final ImageButton button = (ImageButton) findViewById(R.id.rooster_play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaused) {
                    button.setImageDrawable(getResources().getDrawable(R.drawable.ic_nav_pause));
                    roosterPlayer.start();
                    isPaused = false;
                } else {
                    button.setImageDrawable(getResources().getDrawable(R.drawable.ic_nav_play));
                }
            }
        });

        roosterPlayer = MediaPlayer.create(getBaseContext(), R.raw.rooster_crowing_sound);
        roosterPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPaused = true;
                button.setImageDrawable(getResources().getDrawable(R.drawable.ic_nav_play));
            }
        });

    }
    
    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new OtherAnimalFragment();
                case 1:
                    return new RoosterFragment();
                case 2:
                    return new OwlFragment();
                case 3:
                    return new DuckFragment();
            }
            return new DuckFragment();
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
