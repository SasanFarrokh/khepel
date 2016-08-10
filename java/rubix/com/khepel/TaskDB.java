package rubix.com.khepel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sasan on 2016-08-09.
 */
public class TaskDB extends SQLiteOpenHelper {

    private static TaskDB instance;

    public static final String DB_NAME = "motivate.db";
    public static final String TASK_TABLE = "tasks";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "name";
    public static final String TASK_TIME = "time";

    public static TaskDB getInstance(Context context) {
        if (instance == null)
            return new TaskDB(context);
        else return instance;
    }

    private TaskDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TASK_TABLE + " (" +
                TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TASK_NAME + " VARCHAR(255) NOT NULL," +
                TASK_TIME + " INTEGER NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(db);
    }

    public long insertTask(String name, long time) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, name);
        values.put(TASK_TIME, time);

        return db.insert(TASK_TABLE, null, values);
    }

    public long getTaskTime(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String[] selection = {name};
        String[] projection = {TASK_TIME};
        Cursor c = db.query(TASK_TABLE, projection, TASK_NAME + "=?", selection, null, null, TASK_TIME);
        c.moveToFirst();
        if (c.getCount() > 0)
            return c.getLong(0);
        c.close();
        return 0;
    }
}
