import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rank extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f3;
    private JFrame frame4;

    public Rank(JFrame frame4) {

        JButton beforeButton = new JButton("이전");

        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f3.setVisible(false);
                frame4.setVisible(true);
            }
        });

        // f 초기화
        f3 = new JFrame("Ranking");
        f3.setLayout(new java.awt.FlowLayout());
        f3.setSize(1100, 600);
        f3.setLocation(250,100);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.add(beforeButton);

    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        JFrame frame4 = new JFrame();
        Rank rank = new Rank(frame4);

        // Start 클래스의 f 창을 보이게 설정
        rank.f3.setVisible(true);
    }
}
