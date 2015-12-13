package samiamharris.estitooth;

import android.app.Application;

import com.estimote.sdk.BeaconManager;

/**
 * Created by SamMyxer on 11/5/15.
 */
public class EstiToothApplication extends Application {

    private BeaconManager beaconManager;
    private static final String TAG = "EstiTooth-Testing";


    @Override
    public void onCreate() {
        super.onCreate();
    }
//        beaconManager = new BeaconManager(getApplicationContext());
//        //beaconManager.setBackgroundScanPeriod(1000, 1000);
//
//
//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManager.startMonitoring(new Region(
//                        "monitored region",
//                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
//                        47233, 53259));
//            }
//        });
//
//        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
//            @Override
//            public void onEnteredRegion(Region region, List<Beacon> list) {
//                Log.i(TAG, "onEnteredRegion");
//                showNotification(
//                        "DANGER DANGER DANGER",
//                        "You have entered sam's personal space");
//            }
//
//            @Override
//            public void onExitedRegion(Region region) {
//                Log.i(TAG, "onExitedRegion");
//                showNotification(
//                        "CONGRATULATIONS",
//                        "You have exited sam's personal space");
//            }
//        });
//
//    }
//
//
//    public void showNotification(String title, String message) {
//        Intent notifyIntent = new Intent(this, MainActivity.class);
//        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
//                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .build();
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, notification);
//    }
}
