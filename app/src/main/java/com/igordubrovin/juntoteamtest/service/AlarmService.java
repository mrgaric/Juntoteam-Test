package com.igordubrovin.juntoteamtest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.igordubrovin.juntoteamtest.App;
import com.igordubrovin.juntoteamtest.R;
import com.igordubrovin.juntoteamtest.model.PrefManager;
import com.igordubrovin.juntoteamtest.model.ProducthuntApi;
import com.igordubrovin.juntoteamtest.utils.posts.Posts;
import com.igordubrovin.juntoteamtest.view.activityes.PostsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ксения on 21.05.2017.
 */

public class AlarmService extends Service {
    private int numberPosts;
    private String category;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PrefManager prefManager = App.getAppComponent().getPrefManager();
        ProducthuntApi producthuntApi = App.getAppComponent().getProducthuntApi();
        numberPosts = prefManager.getNumberPosts();
        category = prefManager.getCategorySlug();
        producthuntApi.getPosts(category)
                .enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        if (response.body() != null) {
                            int difference = response.body().getPosts().size() - numberPosts;
                            if (difference != 0){
                                String title = "New posts";
                                String contentText;
                                if (difference == 1)
                                    contentText = "New post: " + response.body().getPosts().get(0).getName();
                                else if (difference > 1)
                                     contentText = "Number of new posts: " + difference;
                                else
                                    contentText = "New posts of the day";
                                createNotification(title, contentText);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {

                    }
                });
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotification(String title, String contentText){
        Notification.Builder builder = new Notification.Builder(this);
        Intent notificationIntent = new Intent(this, PostsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(contentText)
                .setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
        Uri uriRing = android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = android.media.RingtoneManager.getRingtone(this, uriRing);
        ringtone.play();
    }
}
