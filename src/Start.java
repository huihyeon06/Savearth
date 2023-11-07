import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    public JFrame f;
    private JFrame frame4;
    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;
    private long startTime;

    public Start(JFrame frame4) {

        this.frame4=frame4;

        f = new JFrame();
        f.setLayout(new java.awt.FlowLayout());
        f.setSize(1100, 600);
        f.setLocation(250, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressValue >= 100) {
                    ((Timer) e.getSource()).stop();
                    long endTime = System.currentTimeMillis(); // 타이머 완료 시간을 저장
                    long elapsedTime = endTime - startTime; // 경과 시간 계산 (밀리초 단위)
                    if (elapsedTime > 30000) { // 30초(30000 밀리초)보다 적은 경우
                        JOptionPane.showMessageDialog(f, "지구를 지키지 못했습니다!");
                    } else {
                        JOptionPane.showMessageDialog(f, "Next Level");
                    }
                    //JOptionPane.showMessageDialog(f, "게이지바가 100%로 채워지는 데 " + elapsedTime / 1000 + " 초 소요되었습니다.");
                } else {
                    progressValue--;
                    progressBar.setValue(progressValue);
                }
            }
        });

        JButton fillButton = new JButton("게이지바 채우기");
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // progressValue가 100에 도달하면 초기화
                progressValue++;
                progressBar.setValue(progressValue);
                timer.start();
                //startTime = System.currentTimeMillis();
            }
        });

        JButton startButton =new JButton("시작");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime = System.currentTimeMillis();
                f.remove(startButton);
                f.add(progressBar);
                f.add(fillButton);
                f.revalidate();
            }
        });

        JButton beforeButton = new JButton("이전");

        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                frame4.setVisible(true);
            }
        });

        f.add(startButton);
        f.add(beforeButton);
    }

    public static void main(String[] args) {
        JFrame frame4 = new JFrame(); // frame4를 생성
        // frame4를 설정하고 초기화하는 코드 작성

        Start start = new Start(frame4); // Start 클래스에 frame4를 전달하여 객체 생성
        start.f.setVisible(true);
    }
}
