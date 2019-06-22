package Logic;

import com.mpatric.mp3agic.Mp3File;

import javazoom.jl.player.Player;

import java.io.FileInputStream;
public class Song extends Mp3File{

    private String songAddress ;

    public Song(String address) throws Exception{
        super(address);
        songAddress = address ;
    }

    /**get the title of the song
     *
     * @return a string that is the title of song
     */
    public String getTitle(){
        if(hasId3v1Tag())//if the format of our song is Id3v1tag
            return getId3v1Tag().getTitle();
        else if(hasId3v2Tag())
            return getId3v2Tag().getTitle();
        else return "" ;
    }

    /**
     *
     * @return a string that is the artist of song
     */

    public String getArtist(){
        if(hasId3v1Tag())
            return getId3v1Tag().getArtist();
        else if(hasId3v2Tag())
            return getId3v2Tag().getArtist();
        else return "" ;
    }


    /**
     *
     * @return the album of song
     */
    public String getAlbum(){
        if(hasId3v1Tag())
            return getId3v1Tag().getAlbum();
        else if(hasId3v2Tag())
            return getId3v2Tag().getAlbum();
        else return "" ;
    }

    public String getTrack(){
        if(hasId3v1Tag())
            return getId3v1Tag().getTrack();
        else if(hasId3v2Tag())
            return getId3v2Tag().getTrack();
        else return "" ;
    }

    //getYear Here if necessary

    public byte[] getImage(){
        if(hasId3v2Tag())
            return getId3v2Tag().getAlbumImage();
        else return null ;
    }

    public String getAddress(){
        return songAddress;
    }

    public String getSongLength(){
        int time = (int)getLengthInSeconds();
        int minute = time / 60;
        int second = time % 60;
        return minute + ":" + ( (second < 10) ? ("0" + second) : second );
    }

    public void play() throws Exception {
        FileInputStream input = new FileInputStream(songAddress);
        Player player = new Player(input);
        player.play();

    }

    /**
     * @return a boolean
     */@Override
    public boolean equals(Object obj){
        Song song = (Song)obj ;
        if(song.getTitle().equals(this.getTitle()) && song.getArtist().equals(this.getArtist()))
            return true ;
        else return false ;
    }

   /* @Override
    public void run() {
        try {
            this.play();
        }catch (Exception ignored){}
    }*/
}
