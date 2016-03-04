package com.woutwoot.tempfly;

/**
 * Created by WoutP on 4/03/2016.
 */
public class Tools {

    public static String secondsToTime(int totalSecs){
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        if(hours > 0){
            return String.format("%d hours, %d minutes and %d seconds", hours, minutes, seconds);
        }else{
            if(minutes > 0){
                return String.format("%d minutes and %d seconds", minutes, seconds);
            }else{
                return String.format("%d seconds", seconds);
            }
        }

    }

}
