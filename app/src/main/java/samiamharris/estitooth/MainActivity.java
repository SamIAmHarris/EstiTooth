package samiamharris.estitooth;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BeaconManager beaconManager;
    private Region region;

    Switch blackSwitch;
    Switch stickerSwitch;
    Switch whiteSwitch;

    Button updateBeaconsButton;
    Button clearBeaconsButton;

    boolean searchingForBeacons = false;

    private Beacon currentBeacon;

    private static final String TAG = "Estitooth-MainActivity";

    public static final int MINT_BEACON = 53140;
    public static final int BLUEBERRY_BEACON = 47233;
    public static final int ICE_BEACON = 36878;

    MediaPlayer mediaPlayer;

    List<Beacon> currentBeacons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackSwitch = (Switch) findViewById(R.id.black_beacon_switch);
        blackSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent bakingIntent = new Intent(getBaseContext(), EnteredBakingActivity.class);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        // the context of the activity
                        MainActivity.this, null
                );
                ActivityCompat.startActivity(MainActivity.this, bakingIntent, options.toBundle());
            }
        });
        stickerSwitch = (Switch) findViewById(R.id.sticker_beacon_switch);
        stickerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent roosterIntent = new Intent(getBaseContext(), RoosterActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        // the context of the activity
                        MainActivity.this, null
                );
                ActivityCompat.startActivity(MainActivity.this, roosterIntent, options.toBundle());


            }
        });
        whiteSwitch = (Switch) findViewById(R.id.white_beacon_switch);

//        updateBeaconsButton = (Button) findViewById(R.id.update_beacon_button);
//        updateBeaconsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(searchingForBeacons) {
//                    searchingForBeacons = false;
//                    updateBeaconsButton.setText("UPDATE BEACON STATUS");
//                } else {
//                    searchingForBeacons = true;
//                    updateBeaconsButton.setText("SEARCHING FOR BEACONS");
//                }
//            }
//        });

//        clearBeaconsButton = (Button) findViewById(R.id.clear_beacon_button);
//        clearBeaconsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                blackSwitch.setChecked(false);
//                stickerSwitch.setChecked(false);
//                whiteSwitch.setChecked(false);
//            }
//        });


        beaconManager = new BeaconManager(this);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    Log.i(TAG, "found beacons " + String.valueOf(list.size()));
                    Utils.Proximity proximity = Utils.computeProximity(nearestBeacon);
                    Log.i(TAG, proximity.toString());
                    if(currentBeacon == null || nearestBeacon.getMajor() != currentBeacon.getMajor()) {
                        if(proximity == Utils.Proximity.IMMEDIATE || proximity == Utils.Proximity.NEAR) {
                            currentBeacon = nearestBeacon;
                            blackSwitch.setChecked(false);
                            stickerSwitch.setChecked(false);
                            whiteSwitch.setChecked(false);
                            switch (nearestBeacon.getMajor()) {
                                case BLUEBERRY_BEACON:
                                    Intent bakingIntent = new Intent(getBaseContext(), EnteredBakingActivity.class);
                                    startActivity(bakingIntent);

//                                    blackSwitch.setChecked(true);
//                                    MediaPlayer elephantPLayer = MediaPlayer.create(getBaseContext(), R.raw.elephant);
//                                    elephantPLayer.start();
//                                    Toast.makeText(getBaseContext(), "elephant", Toast.LENGTH_SHORT).show();
//                                    sendNotification("An elephant has arrived", "Elephant", "content");
                                    break;
                                case MINT_BEACON:
                                    Intent roosterIntent = new Intent(getBaseContext(), RoosterActivity.class);
                                    startActivity(roosterIntent);

//                                    stickerSwitch.setChecked(true);
//                                    MediaPlayer crowPlayer = MediaPlayer.create(getBaseContext(), R.raw.crow);
//                                    crowPlayer.start();
//                                    Toast.makeText(getBaseContext(), "crow", Toast.LENGTH_SHORT).show();
//                                    sendNotification("A crow has flown in", "Crow", "content");
                                    break;
                                case ICE_BEACON:
                                    whiteSwitch.setChecked(true);
                                    MediaPlayer bearPlayer = MediaPlayer.create(getBaseContext(), R.raw.bear);
                                    bearPlayer.start();
                                    Toast.makeText(getBaseContext(), "bear", Toast.LENGTH_SHORT).show();
                                    sendNotification("A bear is charging", "Bear", "content");
                                    break;
                            }
                        }
                    } else {
                        Log.i(TAG,"Current beacon closest " + proximity.name());
                    }
                } else {
                    if(currentBeacon != null) {
                        currentBeacon = null;
                    }
                    blackSwitch.setChecked(false);
                    stickerSwitch.setChecked(false);
                    whiteSwitch.setChecked(false);
                }
            }
        });

        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
    }


    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                //beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        super.onPause();
    }

    public void sendNotification(String eventDescription, String title, String content) {
        int notificationId = 001;
        // Build intent for notification content
        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent mainPendingIntent =
                PendingIntent.getActivity(this, 0, mainIntent, 0);

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

        Log.i(TAG, "notification sent");

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    private static Beacon findNearestBeacon(List<Beacon> beacons) {
        Beacon nearestBeacon = null;
        double nearestBeaconsDistance = -1;
        for (Beacon beacon : beacons) {
            double distance = Utils.computeAccuracy(beacon);
            if (distance > -1 &&
                    (distance < nearestBeaconsDistance || nearestBeacon == null)) {
                nearestBeacon = beacon;
                nearestBeaconsDistance = distance;
            }
        }

        Log.d(TAG, "Nearest beacon: " + nearestBeacon + ", distance: " + nearestBeaconsDistance);
        return nearestBeacon;
    }

}
