package rubix.com.khepel;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {


    public static Main2Activity instance;
    LinearLayout BullShit;
    ImageView done;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        instance = this;
        done = (ImageView) findViewById(R.id.done);
        done.setAlpha(0f);
    }

    public void submit(View view) {
        khafanBazar();
        KhepelService.instance.chronoStart("bodos",Calendar.getInstance().getTimeInMillis() + 15000);
    }

    private void khafanBazar() {
        BullShit = (LinearLayout) findViewById(R.id.BullShit);
        BullShit.animate().alpha(0).yBy(-500f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                BullShit.setVisibility(View.GONE);
                done.setScaleX(0);
                done.setScaleY(0);
                done.animate().alpha(1).rotationBy(360f).setDuration(2000).scaleX(1).scaleY(1)
//                        .x(getWindow().getDecorView().getWidth()/2)
//                        .y(getWindow().getDecorView().getHeight()/2)
                        .start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();

//        startService(new Intent(this, KhepelService.class));


    }
}
