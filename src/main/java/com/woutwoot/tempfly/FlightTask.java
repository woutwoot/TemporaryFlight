package com.woutwoot.tempfly;

/**
 * Created by WoutP on 3/03/2016.
 */
public class FlightTask implements Runnable {

    @Override
    public void run() {
        for(Flyer f : Main.instance.getFlyers().values()){
            f.tick();
        }
    }

}
