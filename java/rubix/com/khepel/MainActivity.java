package rubix.com.khepel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( !KhepelService.started ) {
            startService(new Intent(this, KhepelService.class));
            Log.i("sasan","service started");
        }

        mainActivity = this;
    }

    public void viewTask(View view) {

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        intent.putExtra("taskId",Integer.parseInt(String.valueOf(view.getTag())) );


    }

    public void defineGoal(View view) {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }
}
