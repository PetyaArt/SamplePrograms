package com.example.petya.seunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;

public class SunsetFragment extends Fragment {

    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private View mSunReflectionView;
    private View mSeaView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;
    private boolean mFlag = true;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);

        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);
        mSunReflectionView = view.findViewById(R.id.sunReflection);
        mSeaView = view.findViewById(R.id.sea);

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);

        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        return view;
    }

    private void startAnimation() {
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
        float sunXLeft = mSunView.getLeft();
        float sunXRight = mSunView.getRight();


        float sun2YStart = mSunReflectionView.getTop();
        float sun2YEnd = mSeaView.getHeight();

        if (mFlag) {

            ObjectAnimator heightAnimator = ObjectAnimator
                    .ofFloat(mSunView, "y", sunYStart, sunYEnd)
                    .setDuration(5000);
            heightAnimator.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator sunsetSkyAnimation = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                    .setDuration(5000);
            sunsetSkyAnimation.setEvaluator(new ArgbEvaluator());

            ObjectAnimator nightSkyAnimator = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor)
                    .setDuration(2500);
            nightSkyAnimator.setEvaluator(new ArgbEvaluator());

            ObjectAnimator jitterAnimator = ObjectAnimator
                    .ofFloat(mSunView, "x", sunXLeft - 1, sunXLeft + 1)
                    .setDuration(100);
            jitterAnimator.setRepeatCount(100);
            jitterAnimator.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator height2Animator = ObjectAnimator
                    .ofFloat(mSunReflectionView, "y", sun2YStart, 0 - sun2YStart * 2)
                    .setDuration(5000);
            height2Animator.setInterpolator(new AccelerateInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet
                    .play(heightAnimator)
                    .with(sunsetSkyAnimation)
                    .with(jitterAnimator)
                    .with(height2Animator)
                    .before(nightSkyAnimator);
            animatorSet.start();
            mFlag = false;
        } else {
            ObjectAnimator heightAnimator = ObjectAnimator
                    .ofFloat(mSunView, "y", sunYEnd, sunYStart)
                    .setDuration(5000);
            heightAnimator.setInterpolator(new AccelerateInterpolator());

            ObjectAnimator sunsetSkyAnimation = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mBlueSkyColor)
                    .setDuration(5000);
            sunsetSkyAnimation.setEvaluator(new ArgbEvaluator());

            ObjectAnimator nightSkyAnimator = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mNightSkyColor, mSunsetSkyColor)
                    .setDuration(2500);
            nightSkyAnimator.setEvaluator(new ArgbEvaluator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet
                    .play(nightSkyAnimator)
                    .before(heightAnimator)
                    .before(sunsetSkyAnimation);
            animatorSet.start();
            mFlag = true;
        }

    }
}
