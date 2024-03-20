package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView[] dampingLevels;
    TextView[] stiffnessLevels;
    SpringAnimation anim;
    ToggleButton axis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        axis=findViewById(R.id.axisToggle);

        dampingLevels=new TextView[]{
                findViewById(R.id.dampingHigh),
                findViewById(R.id.dampingMedium),
                findViewById(R.id.dampingLow),
                findViewById(R.id.dampingNoBounce)
        };
        stiffnessLevels=new TextView[]{
                findViewById(R.id.stiffnessHigh),
                findViewById(R.id.stiffnessMedium),
                findViewById(R.id.stiffnessLow),
                findViewById(R.id.stiffnessVeryLow)
        };
        Button startButton=(Button) this.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bouncyAnimation();
                stiffnessAnimation();
            }
        });

        Button stopButton=(Button) this.findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAnimation();
            }
        });
    }

    protected void bouncyAnimation(){
        float bounceArr[]={SpringForce.DAMPING_RATIO_HIGH_BOUNCY,SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY,SpringForce.DAMPING_RATIO_LOW_BOUNCY,SpringForce.DAMPING_RATIO_NO_BOUNCY};

        for(int i=0;i<4;i++){
            anim = new SpringAnimation(dampingLevels[i],axis.isChecked()? SpringAnimation.TRANSLATION_X : SpringAnimation.TRANSLATION_Y);
            anim.setSpring(new SpringForce());
            anim.getSpring().setDampingRatio(bounceArr[i]);
            anim.setStartVelocity(10000);
            anim.getSpring().setFinalPosition(0);
            anim.setStartValue(0);
            anim.start();
        }
    }
    protected void stiffnessAnimation(){
        float stiffnessArr[]={SpringForce.STIFFNESS_HIGH,SpringForce.STIFFNESS_MEDIUM,SpringForce.STIFFNESS_LOW,SpringForce.STIFFNESS_VERY_LOW};

        for(int i=0;i<4;i++){
            anim = new SpringAnimation(stiffnessLevels[i],axis.isChecked()? SpringAnimation.TRANSLATION_X : SpringAnimation.TRANSLATION_Y);
            anim.setSpring(new SpringForce());
            anim.getSpring().setStiffness(stiffnessArr[i]);
            anim.setStartVelocity(10000);
            anim.getSpring().setFinalPosition(0);
            anim.setStartValue(0);
            anim.start();
        }
    }
    protected void cancelAnimation(){
        anim.cancel();
    }
}