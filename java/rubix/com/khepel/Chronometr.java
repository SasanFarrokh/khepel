package rubix.com.khepel;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Sasan on 2016-08-07.
 */
public class Chronometr {

    public long time;
    Activity instance;
    TextView seconds;
    TextView minutes;
    TextView hours;
    TextView day;
    TextView month;
    TextView year;

    public Chronometr(long time, Activity instance) {
        this.instance = instance;
        this.seconds = (TextView) instance.findViewById(R.id.seconds);
        this.minutes = (TextView) instance.findViewById(R.id.minute);
        this.hours = (TextView) instance.findViewById(R.id.hour);
        this.day = (TextView) instance.findViewById(R.id.day);
        this.month = (TextView) instance.findViewById(R.id.month);
        this.year = (TextView) instance.findViewById(R.id.year);
        if (time > 0)
            this.time = time;
        else this.time = 0l;
        setTimer();
        countDown();
    }

    public void setTimer() {

        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        /*seconds.setText(String.valueOf(calendar.get(Calendar.SECOND)));
        minutes.setText(String.valueOf(calendar.get(Calendar.MINUTE)));
        hours.setText(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        day.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        month.setText(String.valueOf(calendar.get(Calendar.MONTH)));
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));*/
        long time = this.time / 1000;
        seconds.setText(String.valueOf((time) % 60));
        minutes.setText(String.valueOf((time / 60) % 60));
        hours.setText(String.valueOf((time / 60 / 60) % 24));
        day.setText(String.valueOf((time / 60 / 60 / 24) % 30));
        month.setText(String.valueOf((time / 60 / 60 / 24 / 30) % 12));
        year.setText(String.valueOf((time / 60 / 60 / 24 / 30 / 12)));
    }

    private void countDown() {
        CountDownTimer timer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Chronometr.this.time -= 1000;
                setTimer();
            }

            @Override
            public void onFinish() {
                Toast.makeText(KhepelService.instance, "FINISHED !", Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder notification = new NotificationCompat.Builder(KhepelService.instance);
                notification.setSmallIcon(android.R.drawable.ic_menu_share);
                notification.setContentTitle("Doidi ya maro oskol kardi ?");
                notification.setContentText("oskol :|");
                NotificationManager notManager = (NotificationManager) instance.getSystemService(Context.NOTIFICATION_SERVICE);
                notManager.notify(1,notification.build());
            }
        };
        timer.start();
    }

}
