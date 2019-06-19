import java.util.HashSet;

public class Library {

    HashSet<Song> allSongs ;
    HashSet<Album> albums ;

    public Library() {
        /**
         * these hashsests are the lists that we can add something new to them without repetition.
         */
        allSongs = new HashSet<Song>();
        albums = new HashSet<Album>();
    }

    /**
     *
     * @param song
     */
    public void addSong(Song song){
        allSongs.add(song);
        Album album = isAlbum(song);
        if(album != null){
            album.addSong(song);
        }
        else {
            album = new Album(song.getAlbum());
            album.addSong(song);
        }
    }

    public void removeSong(Song song) {
        if (allSongs.contains(song)) {
            allSongs.remove(song);
        }
    }

    private Album isAlbum(Song song){
        String albumName = song.getAlbum() ;
        try {
            for (Album album : albums) {
                if (album.getName().equals(albumName))
                    return album;
            }
        }catch (NullPointerException ignored){}
        return null ;
    }
}
