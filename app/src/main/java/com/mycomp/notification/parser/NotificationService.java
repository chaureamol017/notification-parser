package com.mycomp.notification.parser;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NotificationService extends NotificationListenerService {
    public List<String> notificationList = new ArrayList<>();
//    AppDatabase appDatabase;
    String packageName = "no-name";
    String title = "Untitled";
    String text = "empty";

    @Override
    public void onCreate() {
        super.onCreate();
//        appDatabase = new AppDatabase(getApplicationContext());
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (Key.jobCompleted) {
            notificationList.clear();
            Key.jobCompleted = false;
        }
        packageName = sbn.getPackageName();
            Bundle extras = sbn.getNotification().extras;
            if (extras != null) {
                title = extras.getString("android.title");
                if (title != null && !title.equals("Chat heads active")) {
                    text = extras.getString("android.text");
                    String notification = title + " : " + text;
                    notificationList.add(notification);
                    Log.e(TAG, notification);
                }
            }
    }
}
