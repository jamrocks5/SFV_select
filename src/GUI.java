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
    private URL imgURL = getClass().getResource(location);


    private ImageIcon randomIcon = new ImageIcon(imgURL);
    private JButton doRandomButton = new JButton(randomIcon);
    private Border myBorder = new LineBorder(Color.BLACK);


    GUI(IconData[] fighters){
        this.fighters = fighters;
        // Set up view/Frame. Create panel.
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1212, 735);
        this.setTitle("Street Fighter V - Random Select");
        this.setResizable(false);
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(3, 6));

        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JToggleButton(fighters[i].getGrayIcon());
            //buttons[i].setBorderPainted(false);
            buttons[i].setBorder(myBorder);
            buttons[i].setSelectedIcon(fighters[i].getIcon());
            accountPanel.add(buttons[i]);
        }

        doRandomButton.setBorder(myBorder);
        doRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rando = new Random();
                ArrayList<String> list = new ArrayList<>(1);

                //iterate over buttons and see which are selected
                //add selected into list.
                for(int i=0; i < buttons.length; i++){
                    if(buttons[i].isSelected()){
                        list.add(fighters[i].getName());
                    }
                }
                if(list.size() == 0){
                    displayMessage("Must choice at least 1 fighter", false);
                    return;
                }

                String[] choices = list.toArray(new String[list.size()]);

                // Do randomIcon and print message
                displayMessage(choices[rando.nextInt(choices.length)], true);
            }
        });
        accountPanel.add(doRandomButton);
        this.add(accountPanel);
    }

    private void displayMessage(String name, boolean success) {
        if(!success) {
            JOptionPane.showMessageDialog(this, name);
        }else
        JOptionPane.showMessageDialog(this, "", "Play " + name, JOptionPane.PLAIN_MESSAGE, getIcon(name));
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
