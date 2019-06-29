package GUI;

import Logic.Main;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JFrame {

    public InfoPanel() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(280 ,320);
        setIconImage(new ImageIcon("src\\Icons\\JPotifyInfo.png").getImage());
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBackground(new Color(0xE20B1E35, true));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.gridx = 0 ;
        gbc.gridy = 0 ;

        JLabel jPotifyLabel = new JLabel();
        jPotifyLabel.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\JPotify.png").getImage().getScaledInstance(200,60,Image.SCALE_AREA_AVERAGING)));
        infoPanel.add(jPotifyLabel , gbc);

        JLabel teammate1 = new JLabel("AmirHossein Sorour");
        teammate1.setFont(new Font(null, Font.BOLD, 15));
        teammate1.setHorizontalAlignment(JLabel.CENTER);
        teammate1.setForeground(new Color(0x48C6FD));
        gbc.gridy++ ;
        infoPanel.add(teammate1 , gbc);

        JLabel id1 = new JLabel("9731028");
        id1.setFont(new Font(null, Font.BOLD, 15));
        id1.setHorizontalAlignment(JLabel.CENTER);
        id1.setForeground(new Color(0x48C6FD));
        gbc.gridy++ ;
        infoPanel.add(id1 , gbc);

        JLabel teammate2 = new JLabel("Faranak Hosseini Hashemi");
        teammate2.setFont(new Font(null, Font.BOLD, 15));
        teammate2.setHorizontalAlignment(JLabel.CENTER);
        teammate2.setForeground(new Color(0x48C6FD));
        gbc.gridy++ ;
        infoPanel.add(teammate2 , gbc);

        JLabel id2 = new JLabel("9731080");
        id2.setFont(new Font(null, Font.BOLD, 15));
        id2.setHorizontalAlignment(JLabel.CENTER);
        id2.setForeground(new Color(0x48C6FD));
        gbc.gridy++ ;
        infoPanel.add(id2 , gbc);

        JLabel appName = new JLabel("JPotify Music Player V.01");
        appName.setFont(new Font(null, Font.BOLD, 18));
        appName.setForeground(new Color(0x48C6FD));
        appName.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy++ ;
        infoPanel.add(appName , gbc);

        JLabel date = new JLabel("From 18 June , 2019");
        date.setFont(new Font(null, Font.BOLD, 15));
        date.setForeground(new Color(0x48C6FD));
        date.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy++ ;
        infoPanel.add(date , gbc);

        JLabel userName = new JLabel("Username :  " + Main.userName);
        userName.setHorizontalAlignment(JLabel.CENTER);
        userName.setForeground(new Color(-1));
        gbc.gridy++ ;
        infoPanel.add(userName , gbc);

        add(infoPanel , BorderLayout.NORTH);

    }
}
