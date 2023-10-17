import javax.swing.*;

public class Rank extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f3;

    public Rank() {
        // f 초기화
        f3 = new JFrame("Ranking");
        f3.setLayout(new java.awt.FlowLayout());
        f3.setSize(1100, 600);
        f3.setLocation(250,100);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        Rank rank = new Rank();

        // Start 클래스의 f 창을 보이게 설정
        rank.f3.setVisible(true);
    }
}
