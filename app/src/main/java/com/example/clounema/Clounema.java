package com.example.clounema;

import android.app.Application;

public class Clounema extends Application {
    private int globalSwitch = 0;

    public int getGlobalSwitch() {
        return globalSwitch;
    }

    public void setGlobalSwitchTo(int globalSwitch) {
        this.globalSwitch = globalSwitch;
    }
}
