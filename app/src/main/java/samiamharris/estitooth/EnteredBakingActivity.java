package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by SamMyxer on 12/4/15.
 */
public class EnteredBakingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered_baking);

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
