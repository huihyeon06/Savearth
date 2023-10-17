import javax.swing.*;

public class Story extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f2;

    public Story() {
        // f 초기화
        f2 = new JFrame("Story");
        f2.setLayout(new java.awt.FlowLayout());
        f2.setSize(1100, 600);
        f2.setLocation(250,100);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        Story story = new Story();

        // Start 클래스의 f 창을 보이게 설정
        story.f2.setVisible(true);
    }
}
