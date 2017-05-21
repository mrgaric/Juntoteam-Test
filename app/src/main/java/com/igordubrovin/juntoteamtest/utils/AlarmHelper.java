package com.igordubrovin.juntoteamtest.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.igordubrovin.juntoteamtest.receiver.AlarmReceiver;

/**
 * Created by Ксения on 21.05.2017.
 */

public class AlarmHelper {
    public static void setUpAlarm(Context context) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        long timeInterval = AlarmManager.INTERVAL_HOUR;
        final android.app.AlarmManager am = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final PendingIntent pi = PendingIntent.getBroadcast(context, ProjectConstants.REQUEST_CODE_ALARM_PI,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        final android.app.AlarmManager.AlarmClockInfo alarmClockInfo = new android.app.AlarmManager.AlarmClockInfo(System.currentTimeMillis()
                + timeInterval, pi);
        am.setAlarmClock(alarmClockInfo, pi);
    }
}
