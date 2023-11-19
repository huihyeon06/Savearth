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
        rankTextArea.setEditable(false); //수정 못하게 함

        JScrollPane scrollPane = new JScrollPane(rankTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //수직 스크롤 바를 항상 표시

        f3 = new JFrame("Ranking");
        f3.setLayout(new GridBagLayout());
        f3.setSize(1100, 600);
        f3.setLocation(250, 100);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 왼쪽 정렬
        gbc.gridy = 1; // 밑에 위치
        gbc.anchor = GridBagConstraints.WEST; // 왼쪽 정렬
        f3.add(beforeButton, gbc); // 버튼 추가

        gbc.gridx = 0; // 왼쪽 정렬
        gbc.gridy = 0; // 위쪽 위치
        gbc.fill = GridBagConstraints.BOTH; // 컴포넌트가 가로, 세로로 확장되도록 설정
        gbc.weightx = 1.0; // 버튼이 가로 방향으로 차지하는 공간 설정
        gbc.weighty = 1.0; // 버튼이 세로 방향으로 차지하는 공간 설정
        f3.add(scrollPane, gbc); // JTextArea 및 JScrollPane 추가
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
                    Timestamp userTime = resultSet.getTimestamp("time");
                    System.out.println(userNickname + ": " + userTime);
                    ranking.append(rank).append(". ").append(userNickname).append(": ").append(userTime).append("\n");
                    rank++;
                }

                rankTextArea.setText(ranking.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // 오류 처리
        }
    }

    public static void main(String[] args) {
        JFrame frame4 = new JFrame();
        Rank rank = new Rank(frame4);
        rank.fetchAndDisplayRanking();
        rank.f3.setVisible(true);
    }
}
