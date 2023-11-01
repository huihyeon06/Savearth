import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;





public class Main {

    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JTextField usernicknameField;
    private static JTextField lusernameField;
    private static JPasswordField lpasswordField;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/savearth?useSSL=true&serverTimezone=UTC&requireSSL=true&verifyServerCertificate=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1111";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login();
        });
    }

    private static void Login() {
        JFrame frame1 = new JFrame("Savearth");
        JFrame frame2 = new JFrame("Login");
        JFrame frame3 = new JFrame("SignUp");
        JFrame frame4 = new JFrame("second");

        JButton loginButton = new JButton("로그인");
        JButton registerButton = new JButton("회원가입");

        JButton startButton = new JButton("시작");
        JButton storyButton = new JButton("프롤로그");
        JButton rankButton = new JButton("랭킹");

        lusernameField = new JTextField(20);
        lpasswordField = new JPasswordField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        usernicknameField = new JTextField(20);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
                frame3.setVisible(false);
            }
        });

        JButton lButton = new JButton("확인");
        lButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = lusernameField.getText();
                String password = new String(lpasswordField.getPassword());
                if (isValidLogin(username, password)) {
                    frame1.setVisible(false);
                    frame2.setVisible(false);
                    frame3.setVisible(false);
                    frame4.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패. 다시 시도하세요.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(true);
                frame2.setVisible(false);
            }
        });

        JButton rbutton = new JButton("확인");

        rbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernickname = usernicknameField.getText();
                String password = new String(passwordField.getPassword());
                String username = usernameField.getText();

                registerUserToDatabase(username, password, usernickname);

                frame3.setVisible(false);

            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Start start = new Start();
                start.f.setVisible(true);
                frame4.setVisible(false);
            }
        });
        storyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Story story = new Story();
                story.f2.setVisible(true);
                frame4.setVisible(false);
            }
        });
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rank rank = new Rank();
                rank.f3.setVisible(true);
                frame4.setVisible(false);
            }
        });

        // 창 1에 로그인 폼을 추가
        ImagePanel imagePanel = new ImagePanel("images/background.png"); // 이미지 파일의 경로를 설정
        frame1.setContentPane(imagePanel);
        frame1.setLayout(new java.awt.FlowLayout());
        frame1.add(loginButton);
        frame1.add(registerButton);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(1100, 600);
        frame1.setLocation(250,100);
        frame1.setVisible(true);

        // 창 2에 내용을 추가
        frame2.setLayout(new java.awt.FlowLayout());
        frame2.add(new JLabel("아이디:"));
        frame2.add(lusernameField);
        frame2.add(new JLabel("비밀번호:"));
        frame2.add(lpasswordField);
        frame2.add(lButton);
        frame2.setSize(300, 200);
        frame2.setLocation(650,300);
        frame2.setVisible(false);

        // 창 3에 내용을 추가
        frame3.setLayout(new java.awt.FlowLayout());
        frame3.add(new JLabel("닉네임:"));
        frame3.add(usernicknameField);
        frame3.add(new JLabel("아이디:"));
        frame3.add(usernameField); // 같은 JTextField를 사용하지만 다른 변수명을 사용하는 것이 좋습니다.
        frame3.add(new JLabel("비밀번호:"));
        frame3.add(passwordField);
        frame3.add(rbutton);
        frame3.setSize(300, 200);
        frame3.setLocation(650,300);
        frame3.setVisible(false);

        //창 4에 내용을 추가

        ImagePanel imagePanel2 = new ImagePanel("images/background.png"); // 이미지 파일의 경로를 설정
        frame4.setContentPane(imagePanel2);
        frame4.setLayout(new java.awt.FlowLayout());
        frame4.add(startButton);
        frame4.add(storyButton);
        frame4.add(rankButton);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setSize(1100, 600);
        frame4.setLocation(250,100);
        frame4.setVisible(false);
    }

    // 예시로 사용자 이름과 비밀번호가 "admin"일 때만 로그인 성공으로 간주
    private static boolean isValidLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
    private static void registerUserToDatabase(String username, String password, String usernickname) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO user (username, password, nickname) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, usernickname);
                    statement.executeUpdate();
                }
            }
        }  catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        try {
            // 이미지를 불러와 Image 객체에 저장
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 배경 이미지를 그림
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
