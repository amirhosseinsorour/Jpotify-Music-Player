package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class SuperAdvancedPlayer extends AdvancedPlayer implements Runnable{

    Song songToPlay ;
    Thread t ;
    int pauseMoment = 0 ;

    public SuperAdvancedPlayer(Song song) throws JavaLayerException , IOException {
        super(new FileInputStream(song.getAddress()));
        songToPlay = song ;
        Thread t = new Thread(this);
    }

    @Override
    public void play()throws JavaLayerException{
        this.run();
    }

    public void pause(){
        super.stop();
//        pauseMoment = SongTimer.secondsPassed ;
    }


    @Override
    public void run() {
        try {
            super.play(pauseMoment , (int) songToPlay.getLengthInSeconds());
        }catch (Exception e){e.printStackTrace();}
    }
}
