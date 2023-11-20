import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Rank extends JFrame {
    public JFrame f3;
    private JFrame frame4;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/savearth";
    private static final String DB_USER = "huihyeon";
    private static final String DB_PASSWORD = "1111";
    private JTextArea rankTextArea;

    public Rank(JFrame frame4) {
        JButton beforeButton = new JButton("이전");

        beforeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f3.setVisible(false);
                frame4.setVisible(true);
            }
        });

        rankTextArea = new JTextArea(30, 50);
        rankTextArea.setEditable(false);
        Font font = new Font("C:\\Windows\\Fonts", Font.PLAIN, 16);
        rankTextArea.setFont(font);

        JScrollPane scrollPane = new JScrollPane(rankTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 500));

        f3 = new JFrame("Ranking");
        f3.setLayout(new java.awt.FlowLayout());
        f3.setSize(1100, 600);
        f3.setLocation(250, 100);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f3.setVisible(true);
        f3.add(scrollPane);
        f3.add(beforeButton);
        scrollPane.repaint();
        fetchAndDisplayRanking();
    }

    public void fetchAndDisplayRanking() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String selectSql = "SELECT nickname, time FROM user ORDER BY time ASC"; // 시간순으로 정렬된 랭킹 조회
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                ResultSet resultSet = selectStatement.executeQuery();

                StringBuilder ranking = new StringBuilder();
                int rank = 1;
                while (resultSet.next()) {
                    String userNickname = resultSet.getString("nickname");
                    Timestamp userTimestamp = resultSet.getTimestamp("time");

                    // 초와 밀리초를 포함한 시간 정보를 표시
                    long milliseconds = userTimestamp.getTime(); // 타임스탬프를 milliseconds로 변환
                    long seconds = milliseconds / 1000;

                    if(userTimestamp==null){
                        ranking.append(rank).append(". "+"\t").append(userNickname)
                                .append("\t"+": "+"\t").append(" 기록 없음 ")
                                .append("\n");
                    }
                    ranking.append(rank).append(". "+"\t").append(userNickname)
                            .append("\t"+": "+"\t").append(seconds).append(" 초 ")
                            .append("\n");
                    rank++;
                }

                SwingUtilities.invokeLater(() -> {
                    rankTextArea.setText(ranking.toString()); // UI 갱신 스레드 안에서 텍스트 설정
                    //f3.repaint(); // 창 새로고침
                });

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // 오류 처리
        }
    }

    public static void main(String[] args) {
        JFrame frame4 = new JFrame();
        Rank rank = new Rank(frame4); // 랭킹 데이터 가져와 화면에 표시
        rank.f3.setVisible(false);
    }
}
