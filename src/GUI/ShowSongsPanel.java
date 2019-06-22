package GUI;

import Logic.Library;
import Logic.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class ShowSongsPanel extends JPanel implements ActionListener {

    public static ArrayList<JButton> songsAsButtons ;
    public static HashSet<Song> songsToShow ;
    private static GridBagConstraints gbc ;

    public ShowSongsPanel() {
        songsAsButtons = new ArrayList<JButton>();
        setLayout(new GridBagLayout());
        setBackground(new Color(0x7EB4D3));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(20 ,20,20,20);
    }

    public void updatePanel(){
        songsToShow = Library.allSongs ;
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        for(Song song : songsToShow){
            JButton songAsButton = new JButton(new ImageIcon(new ImageIcon(song.getImage()).getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            songAsButton.setText(song.getTitle());
            songAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            songAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            songAsButton.addActionListener(this);
            songsAsButtons.add(songAsButton);
            add(songAsButton , gbc);
            gbc.gridx ++ ;
            if(gbc.gridx == 3){
                gbc.gridx = 0 ;
                gbc.gridy ++ ;
            }
        }
        System.out.println(Library.allSongs.size());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
