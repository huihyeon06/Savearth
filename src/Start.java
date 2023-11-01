import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    // f 변수를 public으로 선언
    public JFrame f;
    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;

    public Start() {
        // f 초기화
        f = new JFrame();
        f.setLayout(new java.awt.FlowLayout());
        f.setSize(1100, 600);
        f.setLocation(250, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 프로그래스바 초기화
        progressBar = new JProgressBar(0, 100); // 최소값과 최대값 설정 (0부터 100까지)
        progressBar.setValue(0); // 초기값 설정
        progressBar.setStringPainted(true); // 값 표시 설정

        // 타이머 초기화
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressValue < 100) {
                    progressValue++;
                    progressBar.setValue(progressValue);
                } else {
                    ((Timer) e.getSource()).stop(); // 게이지 바가 100에 도달하면 타이머를 멈춤
                }
            }
        });

        // 버튼 초기화
        JButton startButton = new JButton("게이지바 채우기");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timer.isRunning()) {
                    progressValue = 0;
                    progressBar.setValue(progressValue);
                    timer.start(); // 타이머 시작
                }
            }
        });

        // 프레임에 프로그래스바와 버튼 추가
        f.add(progressBar);
        f.add(startButton);
    }

    public static void main(String[] args) {
        // Start 클래스의 객체를 생성
        Start start = new Start();

        // Start 클래스의 f 창을 보이게 설정
        start.f.setVisible(true);
    }
}
