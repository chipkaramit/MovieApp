package com.amit.systemplusassignment.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.amit.systemplusassignment.R;

/**
 * Created by amitchipkar on 04/02/18.
 */

public class SplashActivity extends Activity
{
//    ImageView ivFrameZero = null, ivFrameOne = null,ivFrameTwo = null,ivFrameThree = null,
//            ivFrameFour = null, ivFrameFive = null,ivFrameSix = null,ivFrameSeven = null,
//            ivFrameEight = null, ivFrameNine = null,ivFrameTen = null,ivFrameEleven = null;

//    Animation animationBlink = null, animationFadeIn = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        ivFrameZero = findViewById(R.id.iv_frame_zero);
//        ivFrameOne = findViewById(R.id.iv_frame_one);
//        ivFrameTwo = findViewById(R.id.iv_frame_two);
//        ivFrameThree = findViewById(R.id.iv_frame_three);
//        ivFrameFour = findViewById(R.id.iv_frame_four);
//        ivFrameFive = findViewById(R.id.iv_frame_five);
//        ivFrameSix = findViewById(R.id.iv_frame_six);
//        ivFrameSeven = findViewById(R.id.iv_frame_seven);
//        ivFrameEight = findViewById(R.id.iv_frame_eight);
//        ivFrameNine = findViewById(R.id.iv_frame_nine);
//        ivFrameTen = findViewById(R.id.iv_frame_ten);
//        ivFrameEleven = findViewById(R.id.iv_frame_eleven);


//        animationBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_blink);
//        animationFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_fadein);

        Thread splashThread = new Thread()
        {
            @Override
            public void run()
            {
                try {


//                    ivFrameZero.startAnimation(animationFadeIn);
//                    ivFrameOne .startAnimation(animationFadeIn);
//                    ivFrameTwo.startAnimation(animationFadeIn);
//                    ivFrameThree.startAnimation(animationFadeIn);
//                    ivFrameFour.startAnimation(animationFadeIn);
//                    ivFrameFive.startAnimation(animationFadeIn);
//                    ivFrameSix.startAnimation(animationFadeIn);
//                    ivFrameSeven.startAnimation(animationFadeIn);
//                    ivFrameEight.startAnimation(animationFadeIn);
//                    ivFrameNine.startAnimation(animationFadeIn);
//                    ivFrameTen.startAnimation(animationFadeIn);
//                    ivFrameEleven.startAnimation(animationFadeIn);


                    sleep(3000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        splashThread.start();


    }
}
