package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;
import javafx.scene.LightBase;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class Test extends JSlider{

    public Test() {
    }

    public static void main(String[] args) throws Exception{
        Song song = new Song("Homayoun-Shajarian-The-Lords-of-the-Secrets-Tasnif-on-Rumi-S.mp3");          //temporary
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        Thread.sleep(3000);
        mainPanel.playerPanel.updatePanel(new Song("Ebi - Jane Javani.mp3"));
        Library library = new Library();


//        Library library = new Library();
//        JFileChooser jfc = new JFileChooser();
//        jfc.setDialogTitle("Add new Song");
//        jfc.setMultiSelectionEnabled(true);
//        jfc.setCurrentDirectory(new File("D:"));
//        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        jfc.setAcceptAllFileFilterUsed(false);
//        jfc.addChoosableFileFilter(new FileNameExtensionFilter("mp3 Files" , "mp3"));
//        int returnValue = jfc.showOpenDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            File[] selectedFiles = jfc.getSelectedFiles();
//            for (File file : selectedFiles) {
//                try {
//                    Song newSong = new Song(file.getAbsolutePath().replace("\\" , "\\\\"));
//                    if(newSong == null)
//                        System.out.println("null");
//                    Library.addSong(newSong);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                System.err.println(file.getAbsolutePath().replace("\\" , "\\\\"));}
//            }
//        }
//
//        ShowSongsPanel showSongsPanel = new ShowSongsPanel();
//
//        JFrame frame = new JFrame();
//        JScrollPane scrollPane = new JScrollPane(showSongsPanel);
////        scrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(800,650);
//        frame.setVisible(true);
//        frame.setLayout(new BorderLayout());
//        frame.add(scrollPane , BorderLayout.CENTER);



//        TimerTask task = new TimerTask() {
//            int i = 0 ;
//            @Override
//            public void run() {
//                i++ ;
//                System.out.println(i);
//                if(i == 10)
////                    try {
//                        cancel();
////                    }catch (Exception ignored){}
//            }
//        };
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(task , 0 , 1000);

//        JFrame frame = new JFrame();
//        frame.setLayout(new FlowLayout());
////        JProgressBar progressBar = new JProgressBar(0 , 194);
//        JSlider slider = new JSlider(0,194,0);
//        frame.add(slider);
//        frame.setSize(300,300);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setVisible(true);



    }
}
