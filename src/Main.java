import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public Main() {
        setTitle("Savearth");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();

        ImageIcon login = loadImageIcon("Savearth/images/id.png");

        if (login != null) {
            int width = login.getIconWidth();
            int height = login.getIconHeight();
            JButton imageButton = new JButton(login);
            imageButton.setPreferredSize(new Dimension(width, height));

            // 첫 번째 카드
            JPanel card1 = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.anchor=GridBagConstraints.SOUTH;
            card1.add(imageButton, gbc);

            // 두 번째 카드
            JPanel card2 = new JPanel();
            card2.add(new JLabel("This is the second card."));

            // 카드에 이름 지정
            cardPanel.add(card1, "Card 1");
            cardPanel.add(card2, "Card 2");

            // 버튼을 클릭하여 다음 카드로 전환
            imageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.next(cardPanel); // 다음 카드로 이동
                }
            });

            c.add(cardPanel);
        }

        setSize(1100, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    private static ImageIcon loadImageIcon(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}