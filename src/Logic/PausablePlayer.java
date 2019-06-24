package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/*
public class Main {
    public static void main(String[] args) throws Exception{
        Logic.Song song = new Logic.Song("Homayoun-Shajarian-The-Lords-of-the-Secrets-Tasnif-on-Rumi-S.mp3");

        Thread thread = new Thread(song , "1");
        thread.start();

        if(new Scanner(System.in).nextLine().equals("pause"))
            thread.stop();
        if(new Scanner(System.in).nextLine().equals("play"))
            thread.join();
        System.out.println("s");



//        RandomAccessFile file = new RandomAccessFile("artwork.jpg", "rw");
//        file.write(song.getImage());
//        file.close();


    }
}
*/
public class PausablePlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;

    // the player actually doing all the work
    private final Player player;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;

    public PausablePlayer(final InputStream inputStream) throws JavaLayerException {
        this.player = new Player(inputStream);
    }

    public PausablePlayer(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.player = new Player(inputStream, audioDevice);
    }

    /**
     * Starts playback (resumes if paused)
     */
    public void play() throws JavaLayerException {
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

    /**
     * Pauses playback. Returns true if new state is PAUSED.
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    /**
     * Resumes playback. Returns true if the new state is PLAYING.
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
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

    /**
     * Closes the player, regardless of current state.
     */
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

    public static void main(String[] argv) throws Exception{
        try {
//            FileInputStream input = new FileInputStream("Ebi - Jane Javani.mp3");
//            AdvancedPlayer player = new AdvancedPlayer(input);
//            player.play();


//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Homayoun-Shajarian-The-Lords-of-the-Secrets-Tasnif-on-Rumi-S.mp3"));
//            Clip clip =AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//            clip.start();


        } catch (Exception e) {e.printStackTrace();}

    }

}