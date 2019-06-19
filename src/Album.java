import java.util.HashSet;

public class Album {

    private HashSet<Song> songs ;
    private String nameOfAlbum;

    public Album(String name) {
        songs = new HashSet<Song>();
        nameOfAlbum = name ;
    }

    public String getName() {
        return nameOfAlbum;
    }

    public void addSong(Song song){
        songs.add(song);
    }

}