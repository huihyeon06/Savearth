import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JTextField usernicknameField;
    private static JTextField lusernameField;
    private static JPasswordField lpasswordField;

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
        frame3.setSize(300, 200);
        frame3.setLocation(650,300);
        frame3.setVisible(false);

        //창 4에 내용을 추가
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
}
