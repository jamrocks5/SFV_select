import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


class GUI extends JFrame{

    private JToggleButton[] buttons = new JToggleButton[MainClass.NUM_FIGHTERS];
    private IconData[] fighters = new IconData[MainClass.NUM_FIGHTERS];
    private String location = "/Random.png";
    private String logo = "/sfv-logo.png";
    private URL imgURL = getClass().getResource(location);
    private URL urlLogo = getClass().getResource(logo);
    private ImageIcon randomIcon = new ImageIcon(imgURL);
    private ImageIcon logoIcon= new ImageIcon(urlLogo);
    private JButton doRandomButton = new JButton(randomIcon);
    private JButton logoButton= new JButton(logoIcon);
    private Border myBorder = new LineBorder(Color.BLACK);
    private boolean flipFlag = false;
    private final int ROWS = 4;
    private final int COL = 6;
    private final int WIDTH= 122 * COL;
    private final int HEIGHT = (142 * ROWS) + (9*ROWS);


    GUI(IconData[] fighters){
        this.fighters = fighters;
        // Set up view/Frame. Create panel.
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Street Fighter V - Random Select");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(ROWS, COL));
        logoButton.setBorder(myBorder);

        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JToggleButton(fighters[i].getGrayIcon());
            //buttons[i].setBorderPainted(false);
            buttons[i].setBorder(myBorder);
            buttons[i].setSelectedIcon(fighters[i].getIcon());

            // Bad way to hard code it, will adjust later
            if(fighters[i].getName().equals("Ryu")){
                accountPanel.add(logoButton);
            }
            accountPanel.add(buttons[i]);
        }

        doRandomButton.setBorder(myBorder);
        doRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rando = new Random();
                ArrayList<String> list = new ArrayList<>(MainClass.NUM_FIGHTERS);

                //iterate over buttons and see which are selected
                //add selected into list.
                for(int i=0; i < buttons.length; i++){
                    if(buttons[i].isSelected()){
                        if(fighters[i].getName().equals("Urien") || fighters[i].getName().equals("Juri"))
                        {
                            continue;
                        }
                        list.add(fighters[i].getName());
                    }
                }
                if(list.size() == 0){
                    displayMessage("Must choose at least 1 fighter", false);
                    return;
                }

                String[] choices = list.toArray(new String[list.size()]);

                // Do randomIcon and print message
                displayMessage(choices[rando.nextInt(choices.length)], true);
            }
        });
        accountPanel.add(doRandomButton);

        logoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipFlag = !flipFlag;
                for(int i = 0; i < MainClass.NUM_FIGHTERS; i++) {
                    buttons[i].setSelected(flipFlag);
                }
            }
        });

        this.add(accountPanel);
    }


    private void displayMessage(String name, boolean success) {
        if(!success) {
            JOptionPane.showMessageDialog(this, name, "Error", JOptionPane.ERROR_MESSAGE);
        }else
        JOptionPane.showMessageDialog(this, getIcon(name), "Play " + name, JOptionPane.PLAIN_MESSAGE);
    }

    private ImageIcon getIcon(String name){
        for(int i = 0; i < MainClass.NUM_FIGHTERS; i++){
            if(fighters[i].getName().equals(name)){
                return fighters[i].getIcon();
            }
        }
        return null;
    }
}
