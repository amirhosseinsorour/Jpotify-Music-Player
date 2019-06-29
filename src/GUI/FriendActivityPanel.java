package GUI;

import Logic.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendActivityPanel extends JPanel implements ActionListener{
    private GridBagConstraints gbc ;
    private JButton addFriend ;
    private HashMap<JButton , String> friends ;
    private ShowSongsPanel showSongsPanel ;

    /**
     * panel for showing friends name and activity
     * and add new friend
     */

    public FriendActivityPanel() {
        setLayout(new GridBagLayout());
        setSize(100, 200);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        setBackground(new Color(0xD6000821, true));

        friends = new HashMap<>();

        JLabel friendActivity = new JLabel("Friend Activity");
        friendActivity.setForeground(new Color(-1));
        friendActivity.setFont(new Font(null, Font.BOLD, 16));
        friendActivity.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 10, 5);
        add(friendActivity, gbc);

        addFriend = new JButton(new ImageIcon(new ImageIcon("src\\Icons\\AddFriend.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        addFriend.setText("Add New Friend");
        addFriend.addActionListener(this);
        addFriend.setBackground(new Color(0x3E769C));
        addFriend.setFocusPainted(false);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 30, 5);
        add(addFriend, gbc);

    }

    public void setShowSongsPanel(ShowSongsPanel showSongsPanel) {
        this.showSongsPanel = showSongsPanel;
    }

    /**
     *
     * add new friend to the panel
     * @param name username of the new friend
     */

    private void addNewFriend(String name){
        JButton newFriend = new JButton(name);
        newFriend.addActionListener(this);
        newFriend.setBackground(new Color(0x3E769C));
        newFriend.setFocusPainted(false);
        friends.put(newFriend , name);

        gbc.gridy++ ;
        add(newFriend , gbc);
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton)e.getSource();
        if(buttonPressed.equals(addFriend)){
            String name = JOptionPane.showInputDialog(null , "Enter UserName" , "Add New Friend" , JOptionPane.INFORMATION_MESSAGE);
            if(name == null || name.isEmpty())
                return;
            addNewFriend(name);
        }
        if(friends.keySet().contains(buttonPressed)){
            String friendsName = friends.get(buttonPressed);
            File friendSongsFolder = new File(friendsName);
            if(friendSongsFolder.exists() && friendSongsFolder.isDirectory()){
                File [] friendSongFiles = friendSongsFolder.listFiles();
                if(friendSongFiles == null)
                    return;
                ArrayList<Song>  friendSongs = new ArrayList<>();
                for(File file : friendSongFiles){
                    try {
                        Song song = new Song(file.getPath());
                        friendSongs.add(song);
                    }catch (Exception ignored){}
                }
                MainPanel.updateSongsPanel();
                showSongsPanel.createNorthPanel("\"" + friendsName + "\" 's Shared Playlist : ");
                showSongsPanel.updatePanelBySong(friendSongs , showSongsPanel);
            }
        }
    }
}
