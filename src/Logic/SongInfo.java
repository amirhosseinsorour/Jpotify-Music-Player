package Logic;

import java.util.ArrayList;

public abstract class SongInfo {
    protected ArrayList<Song> songs ;
    private String name;

    public SongInfo(String name) {
        songs = new ArrayList<>();
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song){
        if(!songs.contains(song))
            songs.add(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
