package Logic;

public class Playlist extends SongInfo implements Removable {
    /**
     *
     * @param name name of the playlist
     * superclass of favorites and shared playlist
     * song can be removed from playlist
     */

    public Playlist(String name) {
        super(name);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }
}
