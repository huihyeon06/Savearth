import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public Main(){
        setTitle("Savearth");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        ImageIcon login = loadImageIcon("images/id.png");

        if (login != null) {
            int width = login.getIconWidth();
            int height = login.getIconHeight();
            JButton imageButton = new JButton(login);
            imageButton.setPreferredSize(new Dimension(width, height));

            c.add(imageButton);
        }

        setSize(1100, 600);
        setVisible(true);
    }
    public static void main(String[] args) {
        Main m = new Main();
    }
    private static ImageIcon loadImageIcon(String path) {
        try {
            // 이미지 파일을 로드하여 ImageIcon으로 반환
            BufferedImage image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}