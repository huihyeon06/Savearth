import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    public JFrame f;
    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;
    private long startTime;

    public Start() {
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
                    JOptionPane.showMessageDialog(f, "게이지바가 100%로 채워지는 데 " + elapsedTime / 1000 + " 초 소요되었습니다.");
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

        f.add(startButton);
    }

    public static void main(String[] args) {
        Start start = new Start();
        start.f.setVisible(true);
    }
}
