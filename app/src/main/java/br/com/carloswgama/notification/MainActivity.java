package br.com.carloswgama.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btChamaAlerta(View v) {

        //Serviço do Notificações
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder  builder;
        //Controle da vibração
        long duracao[] = {0, 1000, 1000, 1000};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //A partir da versão 8.0
            String canalID = "meu_app_id";
            NotificationChannel canal =
                    new NotificationChannel(
                            canalID,
                            "Meu Canal",
                            NotificationManager.IMPORTANCE_LOW
                    );
            canal.setLightColor(Color.RED);
            canal.setVibrationPattern(duracao);

            notificationManager.createNotificationChannel(canal);

            builder = new Notification.Builder(this, canalID);
        } else {

            // Anteiror a versão 8.0
            builder = new Notification.Builder(this);
            builder.setLights(Color.RED, 500, 1000)
                    .setVibrate(duracao);

        }
        //Indentificar da notificação
        int notificacaoID = 1;


        //Usar imagem
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.mipmap.avatar_user);

        //Abrir uma Activity
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                notificacaoID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        Notification notification = builder
                .setContentTitle("Título da notificação")
                .setContentText("Texto da notificação")
                .setSmallIcon(R.drawable.ic_alert)
                .setLargeIcon(avatar)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(notificacaoID, notification);







        Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show();
    }
}
