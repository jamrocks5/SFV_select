import javax.swing.*;
import java.net.URL;

class IconData {

    private ImageIcon icon;
    private ImageIcon grayIcon;
    private  String name;

    IconData(String name){
        String location = "/" + name + ".png";
        URL imgURL = getClass().getResource(location);
        icon = new ImageIcon(imgURL);
        location = "/gray_" + name + ".png";
        imgURL = getClass().getResource(location);
        grayIcon = new ImageIcon(imgURL);
        this.name = name;
    }

    ImageIcon getIcon() {
        return icon;
    }

    ImageIcon getGrayIcon() {
        return grayIcon;
    }

    String getName() {
        return name;
    }
}
