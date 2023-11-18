import javax.swing.*;
import java.awt.*;
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
        f.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        f.setSize(1100, 600);
        f.setLocation(250, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        progressBar = new JProgressBar(0, 10);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JButton fillButton = new JButton("게이지바 채우기");
        JButton startButton =new JButton("시작");
        JButton beforeButton = new JButton("이전");

        JLabel timerLabel = new JLabel("00:00");

        // 이전 버튼 설정
        beforeButton.setPreferredSize(new Dimension(100, 30)); // 버튼 크기 설정 (임의의 크기)
        gbc.insets = new Insets(0, 0, -400, 500); // 오른쪽 여백 추가
        f.add(beforeButton, gbc);

        // 시작 버튼 설정
        startButton.setPreferredSize(new Dimension(100, 30)); // 버튼 크기 설정 (임의의 크기)
        gbc.insets = new Insets(0, 0, -400, 250); // 오른쪽 여백 추가
        f.add(startButton, gbc);

        // progress bar 위치와 여백 설정
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 20); // 여백 조정
        f.add(progressBar, gbc);
        progressBar.setVisible(false);

        // timer label 위치와 여백 설정
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 20); // 여백 조정
        f.add(timerLabel, gbc);
        timerLabel.setVisible(false);

        // fill button 위치와 여백 설정
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20); // 여백 조정
        gbc.anchor = GridBagConstraints.CENTER; // 가운데 정렬
        gbc.weighty = 1.0; // 버튼이 공간을 차지하는 정도 설정
        f.add(fillButton, gbc);
        fillButton.setVisible(false);

        timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressValue >= 10) {
                    ((Timer) e.getSource()).stop();
                    long endTime = System.currentTimeMillis(); // 타이머 완료 시간을 저장
                    long elapsedTime = endTime - startTime; // 경과 시간 계산 (밀리초 단위)
                    double seconds = elapsedTime / 1000.0; // 전체 초 계산
//                    // 초와 밀리초를 JLabel에 표시
//                    timerLabel.setText(String.format("%.2f s", seconds));
                    int secs = (int) (seconds % 60);
                    int millisecs = (int) (elapsedTime % 1000);
                    // 시간을 시분초밀리초 형태로 표시
                    timerLabel.setText(String.format("%02d:%02d", secs, millisecs));
                    if (elapsedTime > 30000) { // 30초(30000 밀리초)보다 적은 경우
                        JOptionPane.showMessageDialog(f, "지구를 지키지 못했습니다!");
                    } else {
                        JOptionPane.showMessageDialog(f, "Next Level");
                        progressValue=0;
                        progressBar.setValue(progressValue);
                        progressBar.setVisible(false);
                        fillButton.setVisible(false);
                        timerLabel.setVisible(false);
                        startButton.setVisible(true);
                        beforeButton.setVisible(true);
                    }
                    //JOptionPane.showMessageDialog(f, "게이지바가 100%로 채워지는 데 " + elapsedTime / 1000 + " 초 소요되었습니다.");
                } else {
                    progressValue--;
                    progressBar.setValue(progressValue);
                }
            }
        });

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

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime = System.currentTimeMillis();
                startButton.setVisible(false); // 시작 버튼을 안보이게 설정
                beforeButton.setVisible(false); // 이전 버튼을 안보이게 설정
                timerLabel.setVisible(true);
                progressBar.setVisible(true);
                fillButton.setVisible(true);
                timerLabel.setText("00:00");
                f.revalidate();
            }
        });


        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                frame4.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame4 = new JFrame(); // frame4를 생성
        // frame4를 설정하고 초기화하는 코드 작성

        Start start = new Start(frame4); // Start 클래스에 frame4를 전달하여 객체 생성
        start.f.setVisible(true);
    }
}
