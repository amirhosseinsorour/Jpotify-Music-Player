package Logic;

public class Playlist extends SongInfo implements Removable {

    public Playlist(String name) {
        super(name);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }
}
