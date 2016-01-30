package samiamharris.estitooth;

import android.os.Bundle;
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
    }

    public void setEnterExitTransitions() {
        Transition up = new Slide(Gravity.TOP);
        Transition right = new Slide(Gravity.RIGHT);

        up.excludeTarget(android.R.id.statusBarBackground, true);
        up.excludeTarget(android.R.id.navigationBarBackground, true);

        right.excludeTarget(android.R.id.statusBarBackground, true);
        right.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setExitTransition(up);
        getWindow().setEnterTransition(right);
    }

}
