import javax.swing.*;

public class Start extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f;

    public Start() {
        // f 초기화
        f = new JFrame();
        f.setLayout(new java.awt.FlowLayout());
        f.setSize(1100, 600);
        f.setLocation(250,100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        Start start = new Start();

        // Start 클래스의 f 창을 보이게 설정
        start.f.setVisible(true);
    }
}
