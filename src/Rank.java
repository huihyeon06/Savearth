import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.Instant;

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
            String selectSql = "SELECT nickname, time FROM user ORDER BY time ASC"; // 밀리초 단위로 저장된 필드를 가져옴
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                ResultSet resultSet = selectStatement.executeQuery();

                StringBuilder ranking = new StringBuilder();
                int rank = 1;

                while (resultSet.next()) {
                    String userNickname = resultSet.getString("nickname");
                    long milliseconds = resultSet.getLong("time");

                    if (milliseconds > 0) {
                        double seconds = milliseconds / 1000.0; // 밀리초를 초로 변환
                        ranking.append(rank).append(". \t").append(userNickname)
                                .append("\t: \t").append(String.format("%.2f", seconds)) // 두 자리까지 표시
                                .append(" 초\n");
                        rank++;
                    }
                }
                rankTextArea.setText(ranking.toString());

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        JFrame frame4 = new JFrame();
        Rank rank = new Rank(frame4); // 랭킹 데이터 가져와 화면에 표시
        rank.f3.setVisible(false);
    }
}
