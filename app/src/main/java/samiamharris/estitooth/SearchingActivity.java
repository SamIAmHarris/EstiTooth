package samiamharris.estitooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.List;
import java.util.UUID;

/**
 * Created by SamMyxer on 1/28/16.
 */
public class SearchingActivity extends AppCompatActivity {

    BeaconManager beaconManager;
    private Region region;

    private Beacon currentBeacon;

    public static final int MINT_BEACON = 53140;
    public static final int BLUEBERRY_BEACON = 47233;
    public static final int ICE_BEACON = 36878;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterExitTransitions();
        setContentView(R.layout.activity_searching);

        beaconManager = new BeaconManager(this);
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                respondToBeaconDiscovered(list);
            }
        });
    }

    public void respondToBeaconDiscovered(List<Beacon> list) {
        if (!list.isEmpty()) {
            Beacon nearestBeacon = list.get(0);
            Utils.Proximity proximity = Utils.computeProximity(nearestBeacon);
            if(currentBeacon == null || nearestBeacon.getMajor() != currentBeacon.getMajor()) {
                if(proximity == Utils.Proximity.IMMEDIATE || proximity == Utils.Proximity.NEAR) {
                    currentBeacon = nearestBeacon;
                    switch (nearestBeacon.getMajor()) {
                        case BLUEBERRY_BEACON:
                            sendBakingIntent();
                            break;
                        case MINT_BEACON:
                            sendRoosterIntent();
                            break;
                        case ICE_BEACON:
                            break;
                    }
                }
            }
        } else {
            if(currentBeacon != null) {
                currentBeacon = null;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        super.onPause();
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

    public void sendRoosterIntent() {
        Intent roosterIntent = new Intent(getBaseContext(), RoosterActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                SearchingActivity.this, null
        );
        ActivityCompat.startActivity(SearchingActivity.this, roosterIntent, options.toBundle());
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
