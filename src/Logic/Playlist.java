package Logic;

public class Playlist extends SongInfo{

    public Playlist(String name) {
        super(name);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }

}
