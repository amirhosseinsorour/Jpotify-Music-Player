package Logic;

import java.util.ArrayList;
import java.util.HashSet;

public class Album {

    private ArrayList<Song> songs ;
    private String nameOfAlbum;

    public Album(String name) {
        songs = new ArrayList<>();
        nameOfAlbum = name ;
    }

    public String getName() {
        return nameOfAlbum;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}