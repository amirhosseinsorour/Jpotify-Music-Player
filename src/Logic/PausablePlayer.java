package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.*;

public class PausablePlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    // the player actually doing all the work
    private AdvancedPlayer player;
    // locking object used to communicate with player thread
    private final Object playerLock = new Object();
    private int playerStatus = NOTSTARTED;

    public PausablePlayer(Song songToPlay , int secondToPlay) throws JavaLayerException , FileNotFoundException {
        this.player = new AdvancedPlayer(new FileInputStream(songToPlay.getAddress()));
        int frameToPlay = (int) (secondToPlay * songToPlay.getFrameCount() / songToPlay.getLengthInSeconds());
        player.play(frameToPlay , frameToPlay);
    }
    /**
     * Starts playback (resumes if paused)
     */
    public void play()  {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    public void pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
        }
    }

    private void resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
        }
    }

    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }

    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }

}