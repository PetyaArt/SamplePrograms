package com.example.petya.lesson116tasksandbackstack;

import android.content.Intent;
import android.view.View;

public class ActivityD extends MainActivity{
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ActivityD.class));
    }
}
