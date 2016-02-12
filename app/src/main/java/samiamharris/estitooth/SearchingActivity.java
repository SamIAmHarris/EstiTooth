package samiamharris.estitooth;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.squareup.picasso.Picasso;

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

        ImageView searchLogo = (ImageView) findViewById(R.id.searching_image_view);
        Picasso.with(this).load(R.drawable.search).centerInside().fit().into(searchLogo);


        beaconManager = new BeaconManager(this);
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                respondToBeaconDiscovered(list);
            }
        });

        Button roosterButton = (Button) findViewById(R.id.launch_rooster_button);
        Button cookiesButton = (Button) findViewById(R.id.launch_cookies_button);

        roosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRoosterIntent();
            }
        });

        cookiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBakingIntent();
            }
        });
    }

    public void respondToBeaconDiscovered(List<Beacon> list) {
        if (!list.isEmpty()) {
            Log.i("Search", "beacon discovered");
            Beacon nearestBeacon = list.get(0);
            if(currentBeacon != null && nearestBeacon != null) {
                Log.i("Search", "nearest:" + String.valueOf(nearestBeacon.getMajor())
                        + "current:" + String.valueOf(currentBeacon.getMajor()));
            }
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
                Log.i("Search", "start ranging");
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        Log.i("Search", "stop ranging");
        beaconManager.stopRanging(region);
        super.onPause();
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

    public void sendNotification(String eventDescription, String title, String content) {
        int notificationId = 001;
        Intent searchIntent = new Intent(this, SearchingActivity.class);
        PendingIntent mainPendingIntent =
                PendingIntent.getActivity(this, 0, searchIntent, 0);

        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(eventDescription);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle(title)
                        .setContentText(eventDescription)
                        .setContentIntent(mainPendingIntent)
                        .setStyle(bigStyle);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }


}
