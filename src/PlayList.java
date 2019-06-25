import Logic.Song;

import java.util.ArrayList;

public class PlayList {
  private   String name;
    ArrayList<Song> songs;

    public PlayList(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
