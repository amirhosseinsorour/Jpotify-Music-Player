package Logic;

import java.util.ArrayList;

public abstract class SongInfo {
    ArrayList<Song> songs ;
    private String name;

    /**
     *superclass of playlist , album , artist
     * never instantiated
     */
    SongInfo(String name) {
        songs = new ArrayList<>();
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public boolean addSong(Song song){
        if(songs.contains(song))
            return false ;
        songs.add(song);
        return true;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
