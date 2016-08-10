package rubix.com.khepel;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sasan on 2016-08-07.
 */
public class KhepelService extends Service {

    public static boolean started = false;
    public static KhepelService instance;

    @Override
    public void onCreate() {
        super.onCreate();
        started = true;
        instance = this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void chronoStart(String name, long time) {
        TaskDB db = TaskDB.getInstance(this);

        long eTime = db.getTaskTime(name);
        if (eTime <= Calendar.getInstance().getTimeInMillis()) {
            db.onUpgrade(db.getWritableDatabase(), 0, 0);
            long id = db.insertTask(name, time);
        } else time = eTime;
        new Chronometr(time - Calendar.getInstance().getTimeInMillis(), Main2Activity.instance);
    }

}
