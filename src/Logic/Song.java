package Logic;

import com.mpatric.mp3agic.Mp3File;

import javazoom.jl.player.Player;

import javax.swing.*;
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
        String title = "";
        if(hasId3v1Tag())//if the format of our song is Id3v1tag
            title = getId3v1Tag().getTitle();
        else if(hasId3v2Tag())
            title = getId3v2Tag().getTitle();
        if(title.isEmpty() || title.equals(""))
            return "Unknown Title" ;
        else return title ;
    }

    /**
     *
     * @return a string that is the artist of song
     */

    public String getArtist(){
        String artist = "";
        if(hasId3v1Tag())//if the format of our song is Id3v1tag
            artist = getId3v1Tag().getArtist();
        else if(hasId3v2Tag())
            artist = getId3v2Tag().getArtist();
        if(artist.isEmpty() || artist.equals(""))
            return "Unknown Artist" ;
        else return artist ;
    }


    /**
     *
     * @return the album of song
     */
    public String getAlbum(){
        String album = "";
        if(hasId3v1Tag())//if the format of our song is Id3v1tag
            album = getId3v1Tag().getAlbum();
        else if(hasId3v2Tag())
            album = getId3v2Tag().getAlbum();
        if(album.isEmpty() || album.equals(""))
            return "Unknown Album" ;
        else return album ;
    }

    public String getTrack(){
        if(hasId3v1Tag())
            return getId3v1Tag().getTrack();
        else if(hasId3v2Tag())
            return getId3v2Tag().getTrack();
        else return "" ;
    }

    //getYear Here if necessary

    public ImageIcon getImage(){
        if(hasId3v2Tag() && getId3v2Tag().getAlbumImage() != null)
            return new ImageIcon(getId3v2Tag().getAlbumImage());
        else return new ImageIcon("src\\Icons\\Unknown.png") ;
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
     */
    @Override
    public boolean equals(Object obj){
        Song song = (Song)obj ;
        if(song.getTitle().equals(this.getTitle()) && song.getArtist().equals(this.getArtist()) && song.getLength() == getLength())
            return true ;
        else return false ;
    }

}
