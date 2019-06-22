package Logic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class SongTimer implements ChangeListener {

    private Timer timer ;
    private TimerTask task ;
    private boolean isPlaying ;
    private int songLength ;
    private JSlider slider ;
    private JLabel timePassed ;

    public SongTimer(JSlider slider , JLabel timePassed) {
        timer = new Timer();
        this.slider = slider ;
        this.timePassed = timePassed ;
        slider.addChangeListener(this);
    }

    public void setSongStatus(boolean isPlaying){
        this.isPlaying = isPlaying ;
    }

    public void setMaxTime(int songLength) {
        this.songLength = songLength;
    }

    public void setTask(){
        task = new TimerTask() {

            @Override
            public void run() {
                if (isPlaying){
                    slider.setValue(slider.getValue() + 1);
                    timePassed.setText(getTimePassed());
                }
//                if(secondsPassed == songLength){
//                    secondsPassed = 0 ;
//                    return;
//                }
            }
        };
    }

    public void start(){
        timer.scheduleAtFixedRate(task,0,1000);
    }

    private String getTimePassed(){
        int timePassed = slider.getValue();
        int minute = timePassed / 60 ;
        int second = timePassed  - ( minute * 60) ;

        return minute + ":" + ( (second<10) ? ("0" + second) : (second) ) ;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource().equals(slider)){

        }
    }
}
