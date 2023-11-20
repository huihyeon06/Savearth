import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Story extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f2;
    private JFrame frame4;

    public Story(JFrame frame4) {

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("images/p1.png");
        imageLabel.setIcon(icon);

        // 패널 생성
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(imageLabel);

        JLabel imageLabel2 = new JLabel();
        ImageIcon icon2 = new ImageIcon("images/p2.png");
        imageLabel2.setIcon(icon2);

        // 패널 생성
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(imageLabel2);
        panel2.setVisible(false);

        JLabel imageLabel3 = new JLabel();
        ImageIcon icon3 = new ImageIcon("images/p3.png");
        imageLabel3.setIcon(icon3);

        // 패널 생성
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(imageLabel3);
        panel3.setVisible(false);

        JLabel imageLabel4 = new JLabel();
        ImageIcon icon4 = new ImageIcon("images/p4.png");
        imageLabel4.setIcon(icon4);

        // 패널 생성
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(imageLabel4);
        panel4.setVisible(false);

        JLabel imageLabel5 = new JLabel();
        ImageIcon icon5 = new ImageIcon("images/p5.png");
        imageLabel5.setIcon(icon5);

        // 패널 생성
        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout());
        panel5.add(imageLabel5);
        panel5.setVisible(false);

        JLabel imageLabel6 = new JLabel();
        ImageIcon icon6 = new ImageIcon("images/p6.png");
        imageLabel6.setIcon(icon6);

        // 패널 생성
        JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout());
        panel6.add(imageLabel6);
        panel6.setVisible(false);

        JLabel imageLabel7 = new JLabel();
        ImageIcon icon7 = new ImageIcon("images/p7.png");
        imageLabel7.setIcon(icon7);

        // 패널 생성
        JPanel panel7 = new JPanel();
        panel7.setLayout(new FlowLayout());
        panel7.add(imageLabel7);
        panel7.setVisible(false);

        JLabel imageLabel8 = new JLabel();
        ImageIcon icon8 = new ImageIcon("images/p8.png");
        imageLabel8.setIcon(icon8);

        // 패널 생성
        JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout());
        panel8.add(imageLabel8);
        panel8.setVisible(false);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.setVisible(false);
                panel2.setVisible(true);
            }
        });
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel2.setVisible(false);
                panel3.setVisible(true);
            }
        });
        panel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel3.setVisible(false);
                panel4.setVisible(true);
            }
        });
        panel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel4.setVisible(false);
                panel5.setVisible(true);
            }
        });
        panel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel5.setVisible(false);
                panel6.setVisible(true);
            }
        });
        panel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel6.setVisible(false);
                panel7.setVisible(true);
            }
        });
        panel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel7.setVisible(false);
                panel8.setVisible(true);
            }
        });
        panel8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel8.setVisible(false);
                f2.setVisible(false);
                frame4.setVisible(true);
            }
        });



        // f 초기화
        f2 = new JFrame("Story");
        f2.setLayout(new GridBagLayout());
        f2.setSize(1100, 600);
        f2.setLocation(250, 100);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.add(panel);
        f2.add(panel2);
        f2.add(panel3);
        f2.add(panel4);
        f2.add(panel5);
        f2.add(panel6);
        f2.add(panel7);
        f2.add(panel8);



    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        JFrame frame4 = new JFrame();
        Story story = new Story(frame4);

        // Start 클래스의 f 창을 보이게 설정
        story.f2.setVisible(true);
    }
}
