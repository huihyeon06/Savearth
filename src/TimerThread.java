import javax.swing.*;

public class TimerThread implements Runnable{
    private JLabel timerLabel;

    public TimerThread(JLabel timerLabel) {
        this.timerLabel = timerLabel;
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            int seconds = (int) (elapsedTime / 1000); // 초
            int milliseconds = (int) (elapsedTime % 1000); // 밀리초

            // 시간을 "초:밀리초" 형식으로 표시
            String time = String.format("%02d:%02d", seconds, milliseconds);
            timerLabel.setText(time);

            try {
                Thread.sleep(1); // 1밀리초마다 업데이트
            } catch (InterruptedException e) {
                return; // 예외가 발생하면 해당 스레드가 종료됩니다
            }
        }
    }
}
