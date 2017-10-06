package br.com.carloswgama.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MeuFirebaseMessage extends FirebaseMessagingService {
    public MeuFirebaseMessage() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String titulo = remoteMessage.getNotification().getTitle();
        String texto = remoteMessage.getNotification().getBody();

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentTitle(titulo)
                .setContentText(texto)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }
}
