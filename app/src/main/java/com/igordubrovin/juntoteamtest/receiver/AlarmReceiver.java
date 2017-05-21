package com.igordubrovin.juntoteamtest.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.igordubrovin.juntoteamtest.service.AlarmService;
import com.igordubrovin.juntoteamtest.utils.AlarmHelper;

/**
 * Created by Ксения on 21.05.2017.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmHelper.setUpAlarm(context);
        context.startService(new Intent(context, AlarmService.class));
    }
}
