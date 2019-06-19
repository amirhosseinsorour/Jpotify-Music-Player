import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws Exception{
        Song song = new Song("Homayoun-Shajarian-The-Lords-of-the-Secrets-Tasnif-on-Rumi-S.mp3");
        if(song.hasId3v1Tag())
            System.out.println("ID3v1");
        else if(song.hasId3v2Tag())
            System.out.println("ID3V2");
        else if(song.hasCustomTag())
            System.out.println("custom");
        System.out.println(song.getArtist());
        System.out.println(song.getAlbum());
        System.out.println(song.getTitle());
        System.out.println(song.getId3v1Tag().getYear());
        System.out.println(song.getId3v1Tag().getTrack());
        System.out.println(song.getLengthInSeconds());
        System.out.println(song.getBitrate());

        song.play();

//        RandomAccessFile file = new RandomAccessFile("artwork.jpg", "rw");
//        file.write(song.getImage());
//        file.close();


    }
}
