import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Story extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f2;
    private JFrame frame4;

    public Story(JFrame frame4) {

        JButton beforeButton = new JButton("이전");

        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(false);
                frame4.setVisible(true);
            }
        });

        // f 초기화
        f2 = new JFrame("Story");
        f2.setLayout(new java.awt.FlowLayout());
        f2.setSize(1100, 600);
        f2.setLocation(250,100);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel imagePanel = new ImagePanel("images/background.png");

        f2.setContentPane(imagePanel);
        f2.add(beforeButton);

    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        JFrame frame4 = new JFrame();
        Story story = new Story(frame4);

        // Start 클래스의 f 창을 보이게 설정
        story.f2.setVisible(true);
    }
}


