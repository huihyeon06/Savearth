import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    public JFrame f;
    private JProgressBar progressBar;
    private Timer timer;
    private int progressValue;

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
                } else {
                    progressValue--;
                    progressBar.setValue(progressValue);
                }
            }
        });

        JButton startButton = new JButton("게이지바 채우기");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // progressValue가 100에 도달하면 초기화
                progressValue++;
                if(progressValue>100){
                    ((Timer) e.getSource()).stop();
                }
                progressBar.setValue(progressValue);
                timer.start();
            }
        });

        f.add(progressBar);
        f.add(startButton);
    }

    public static void main(String[] args) {
        Start start = new Start();
        start.f.setVisible(true);
    }
}
