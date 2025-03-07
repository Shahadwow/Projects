package pathfinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Pathfinder extends JFrame {

    // For first Frame:
    private JFrame frame1;
    private JLabel introLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JTextField nameField;
    private JTextField emailField;
    private JPanel F1Panel1;
    private JPanel F1Panel2;
    private JButton next;
    private JButton adminlogin;

    // For login Frame:
    private JFrame framelog;
    private JPanel FlogPanel1;
    private JPanel FlogPanel2;
    private JLabel username;
    private JLabel password;
    private JTextField user;
    private JTextField pass;
    private JButton login;
    private JPanel FlogPanel3;
    private JButton back;
    private String usercontent;

    // For admin Frame:
    private JFrame Framedmin;
    private JButton startPage;

    // For second Frame:
    private JFrame frame2;
    private JLabel startLabel;
    private JButton button;

    // For third Frame
    private JFrame frame3;
    private JPanel F3panel;
    private JLabel mbti;
    private JLabel resulttext;

    //file name 
    String filepath = "usersinfo.txt";

    //mbti characters    
    private ImageIcon ENFJimage = new ImageIcon(getClass().getResource("/images/ENFJ.png"));
    Image img1 = ENFJimage.getImage();
    Image scaledImg1 = img1.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage1 = new ImageIcon(scaledImg1);
    JLabel ENFJ = new JLabel(scaledImage1, SwingConstants.CENTER);

    private ImageIcon ENFPimage = new ImageIcon(getClass().getResource("/images/ENFP.png"));
    Image img2 = ENFPimage.getImage();
    Image scaledImg2 = img2.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage2 = new ImageIcon(scaledImg2);
    JLabel ENFP = new JLabel(scaledImage2, SwingConstants.CENTER);

    private ImageIcon ENTJimage = new ImageIcon(getClass().getResource("/images/ENTJ.png"));
    Image img3 = ENTJimage.getImage();
    Image scaledImg3 = img3.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage3 = new ImageIcon(scaledImg3);
    JLabel ENTJ = new JLabel(scaledImage3, SwingConstants.CENTER);

    private ImageIcon ENTPimage = new ImageIcon(getClass().getResource("/images/ENTP.png"));
    Image img4 = ENTPimage.getImage();
    Image scaledImg4 = img4.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage4 = new ImageIcon(scaledImg4);
    JLabel ENTP = new JLabel(scaledImage4, SwingConstants.CENTER);

    private ImageIcon ESFJimage = new ImageIcon(getClass().getResource("/images/ESFJ.png"));
    Image img5 = ESFJimage.getImage();
    Image scaledImg5 = img5.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage5 = new ImageIcon(scaledImg5);
    JLabel ESFJ = new JLabel(scaledImage5, SwingConstants.CENTER);

    private ImageIcon ESFPimage = new ImageIcon(getClass().getResource("/images/ESFP.png"));
    Image img6 = ESFPimage.getImage();
    Image scaledImg6 = img6.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage6 = new ImageIcon(scaledImg6);
    JLabel ESFP = new JLabel(scaledImage6, SwingConstants.CENTER);

    private ImageIcon ESTJimage = new ImageIcon(getClass().getResource("/images/ESTJ.png"));
    Image img7 = ESTJimage.getImage();
    Image scaledImg7 = img7.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage7 = new ImageIcon(scaledImg7);
    JLabel ESTJ = new JLabel(scaledImage7, SwingConstants.CENTER);

    private ImageIcon ESTPimage = new ImageIcon(getClass().getResource("/images/ESTP.png"));
    Image img8 = ESTPimage.getImage();
    Image scaledImg8 = img8.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage8 = new ImageIcon(scaledImg8);
    JLabel ESTP = new JLabel(scaledImage8, SwingConstants.CENTER);

    private ImageIcon INFJimage = new ImageIcon(getClass().getResource("/images/INFJ.png"));
    Image img9 = INFJimage.getImage();
    Image scaledImg9 = img9.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage9 = new ImageIcon(scaledImg9);
    JLabel INFJ = new JLabel(scaledImage9, SwingConstants.CENTER);

    private ImageIcon INFPimage = new ImageIcon(getClass().getResource("/images/INFP.png"));
    Image img10 = INFPimage.getImage();
    Image scaledImg10 = img10.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage10 = new ImageIcon(scaledImg10);
    JLabel INFP = new JLabel(scaledImage10, SwingConstants.CENTER);

    private ImageIcon INTJimage = new ImageIcon(getClass().getResource("/images/INTJ.png"));
    Image img11 = INTJimage.getImage();
    Image scaledImg11 = img11.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage11 = new ImageIcon(scaledImg11);
    JLabel INTJ = new JLabel(scaledImage11, SwingConstants.CENTER);

    private ImageIcon INTPimage = new ImageIcon(getClass().getResource("/images/INTP.png"));
    Image img12 = INTPimage.getImage();
    Image scaledImg12 = img12.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage12 = new ImageIcon(scaledImg12);
    JLabel INTP = new JLabel(scaledImage12, SwingConstants.CENTER);

    private ImageIcon ISFJimage = new ImageIcon(getClass().getResource("/images/ISFJ.png"));
    Image img13 = ISFJimage.getImage();
    Image scaledImg13 = img13.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage13 = new ImageIcon(scaledImg13);
    JLabel ISFJ = new JLabel(scaledImage13, SwingConstants.CENTER);

    private ImageIcon ISFPimage = new ImageIcon(getClass().getResource("/images/ISFP.png"));
    Image img14 = ISFPimage.getImage();
    Image scaledImg14 = img14.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage14 = new ImageIcon(scaledImg14);
    JLabel ISFP = new JLabel(scaledImage14, SwingConstants.CENTER);

    private ImageIcon ISTJimage = new ImageIcon(getClass().getResource("/images/ISTJ.png"));
    Image img15 = ISTJimage.getImage();
    Image scaledImg15 = img15.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage15 = new ImageIcon(scaledImg15);
    JLabel ISTJ = new JLabel(scaledImage15, SwingConstants.CENTER);

    private ImageIcon ISTPimage = new ImageIcon(getClass().getResource("/images/ISTP.png"));
    Image img16 = ISTPimage.getImage();
    Image scaledImg16 = img16.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
    ImageIcon scaledImage16 = new ImageIcon(scaledImg16);
    JLabel ISTP = new JLabel(scaledImage16, SwingConstants.CENTER);

    //file user name
    private String name;

    //mbti string
    String mbtistr = "";

    //MBTI 
    private int E = 0;
    private int I = 0;

    private int S = 0;
    private int N = 0;

    private int F = 0;
    private int T = 0;

    private int P = 0;
    private int J = 0;

    String[] mbtiarr = new String[4];

    // Questions
    private JLabel q1 = new JLabel("1. When you are at a party or social activity, you: ");
    private JLabel q2 = new JLabel("2. When you go to a social event or celebration, you often:");
    private JLabel q3 = new JLabel("3. Which is easier for you:");
    private JLabel q4 = new JLabel("4. When you face a problem and have to make a decision, what affects you most:");
    private JLabel q5 = new JLabel("5. When you work on something, do you prefer the work to be:");
    private JLabel q6 = new JLabel("6. When you choose:");
    private JLabel q7 = new JLabel("7. Do you like people:");
    private JLabel q8 = new JLabel("8. In your life and work:");
    private JLabel q9 = new JLabel("9. If you had to choose between the following managers, which would you prefer:");
    private JLabel q10 = new JLabel("10. If you had to choose between the following two teams, which would you rather be a member of:");
    private JLabel q11 = new JLabel("11. When you work, do you prefer:");
    private JLabel q12 = new JLabel("12. Mostly the way you work:");
    private JLabel q13 = new JLabel("13. When you find yourself facing a boring job or task:");
    private JLabel q14 = new JLabel("14. Do you feel like you understand more by:");
    private JLabel q15 = new JLabel("15. When someone asks you a question:");
    private JLabel q16 = new JLabel("16. You prefer:");
    private JLabel q17 = new JLabel("17. When you are discussing with another person:");
    private JLabel q18 = new JLabel("18. When you are assigned a task:");
    private JLabel q19 = new JLabel("19. Mostly I strive to be:");
    private JLabel q20 = new JLabel("20. When you are assigned a task:");
    private JLabel q21 = new JLabel("21. When you work alone:");
    private JLabel q22 = new JLabel("22. When a colleague of yours makes a mistake, the best way to correct their mistake is:");
    private JLabel q23 = new JLabel("23. When discussing or negotiating, you feel more comfortable:");
    private JLabel q24 = new JLabel("24. You like about yourself:");
    private JLabel q25 = new JLabel("25. You prefer:");
    private JLabel q26 = new JLabel("26. You learn best when:");
    private JLabel q27 = new JLabel("27. When you are asked something, you often:");
    private JLabel q28 = new JLabel("28. Your self-satisfaction comes from:");
    private JLabel q29 = new JLabel("29. Which is worse in your opinion:");
    private JLabel q30 = new JLabel("30. When you're working on a routine, you like to:");
    private JLabel q31 = new JLabel("31. Which one attracts you, and you prefer to work in:");
    private JLabel q32 = new JLabel("32. Do you often feel you're:");
    private JLabel q33 = new JLabel("33. Which one do you prefer:");
    private JLabel q34 = new JLabel("34. When you want to make a decision, you feel comfortable when you have to deal with:");

    // Radio Buttons
    private JRadioButton radio1 = new JRadioButton("Interact with a lot of people, including strangers.");
    private JRadioButton radio2 = new JRadioButton("Interact with a few people, most of whom you already know.");

    private JRadioButton radio3 = new JRadioButton("You leave early and feel exhausted and bored.");
    private JRadioButton radio4 = new JRadioButton("You stay up late, and even when you leave, you still feel the energy in your body.");

    private JRadioButton radio5 = new JRadioButton("Living with reality.");
    private JRadioButton radio6 = new JRadioButton("Imagining and anticipating what will happen in the future.");

    private JRadioButton radio7 = new JRadioButton("Persuasion, through facts and evidence.");
    private JRadioButton radio8 = new JRadioButton("Feelings, from considering the condition of the parties involved.");

    private JRadioButton radio9 = new JRadioButton("It has a specific delivery date.");
    private JRadioButton radio10 = new JRadioButton("It has an open delivery date.");

    private JRadioButton radio11 = new JRadioButton("You often carefully examine the options before you make a choice.");
    private JRadioButton radio12 = new JRadioButton("You tend to be often reckless.");

    private JRadioButton radio13 = new JRadioButton("Who have a great imagination.");
    private JRadioButton radio14 = new JRadioButton("Who are realists.");

    private JRadioButton radio15 = new JRadioButton("You prefer to do routine work that you have already tried and whose results are clear to you.");
    private JRadioButton radio16 = new JRadioButton("You prefer to do new things that you have not tried and do not know about the results of doing them.");

    private JRadioButton radio17 = new JRadioButton("A manager with bad manners and a dry attitude, but he understands the work and you can learn a lot from him.");
    private JRadioButton radio18 = new JRadioButton("A manager who does not understand the work, which forces you to rely on yourself to learn. But he has high morals, and he treats you like his son.");

    private JRadioButton radio19 = new JRadioButton("A team whose members are all in agreement and work in harmony.");
    private JRadioButton radio20 = new JRadioButton("A team whose members have intense discussions and a lot of exchange of ideas.");

    private JRadioButton radio21 = new JRadioButton("Working alone.");
    private JRadioButton radio22 = new JRadioButton("Work in a team or with another person.");

    private JRadioButton radio23 = new JRadioButton("Flexible so that you relax in periods, then push yourself before the due date.");
    private JRadioButton radio24 = new JRadioButton("Neat and organized, so that you try to balance work and relaxation, and thus finish work before its due date.");

    private JRadioButton radio25 = new JRadioButton("You stick to your work schedule and commit yourself to completing the part required of you.");
    private JRadioButton radio26 = new JRadioButton("You try to postpone work as much as possible, so that it no longer becomes an option.");

    private JRadioButton radio27 = new JRadioButton("Listening to the explanations and discussions of others.");
    private JRadioButton radio28 = new JRadioButton("Share with others, and review what you understood from the idea to them.");

    private JRadioButton radio29 = new JRadioButton("You respond immediately according to the information you know.");
    private JRadioButton radio30 = new JRadioButton("You ask them to wait until you research and confirm what you know about the topic before answering them.");

    private JRadioButton radio31 = new JRadioButton("Work on one task and finish it before moving on to a new task.");
    private JRadioButton radio32 = new JRadioButton("Working on several tasks and duties at the same time.");

    private JRadioButton radio33 = new JRadioButton("You are usually hasty, and come up with many ideas even if you are not sure of their validity.");
    private JRadioButton radio34 = new JRadioButton("Usually, do not rush to present your ideas until you have thought them through well.");

    private JRadioButton radio35 = new JRadioButton("You would prefer to have the steps and things you are required to accomplish explained.");
    private JRadioButton radio36 = new JRadioButton("You prefer to be given the goal of the task, and leave the method of implementation to your imagination.");

    private JRadioButton radio37 = new JRadioButton("The best at everything.");
    private JRadioButton radio38 = new JRadioButton("Success in general, and excellence in some matters only.");

    private JRadioButton radio39 = new JRadioButton("You think about what is required of you, and analyze it before you start working on it.");
    private JRadioButton radio40 = new JRadioButton("You start working on it, and think about the results later.");

    private JRadioButton radio41 = new JRadioButton("You need a lot of rest time, which is often spent talking to someone else.");
    private JRadioButton radio42 = new JRadioButton("You do not need a lot of rest, and even if you do, you prefer it to be moments of calm and relaxation.");

    private JRadioButton radio43 = new JRadioButton("Honesty and directness, even if it hurts their feelings.");
    private JRadioButton radio44 = new JRadioButton("Trying to understand their position and identify the reasons behind what they did.");

    private JRadioButton radio45 = new JRadioButton("While reviewing the facts, identifying relevant details.");
    private JRadioButton radio46 = new JRadioButton("The moment an agreement is reached, or the discussion ends.");

    private JRadioButton radio47 = new JRadioButton("The kindness of your heart, your understanding of others, and your concern for them.");
    private JRadioButton radio48 = new JRadioButton("Your knowledge, and the power of your unwavering opinion.");

    private JRadioButton radio49 = new JRadioButton("To learn a few skills and master them well.");
    private JRadioButton radio50 = new JRadioButton("To learn many things, and master what you need to master when you need to.");

    private JRadioButton radio51 = new JRadioButton("You imagine and try to guess what will happen when the data changes.");
    private JRadioButton radio52 = new JRadioButton("When you try yourself and see the results change.");

    private JRadioButton radio53 = new JRadioButton("Try to convince the other party of your view on it, by following the method of proving and presenting what you know.");
    private JRadioButton radio54 = new JRadioButton("Invite the other party to get to know your view of the thing, by describing and explaining what you know about it.");

    private JRadioButton radio55 = new JRadioButton("Knowing what people expect you to accomplish, and trying to achieve it.");
    private JRadioButton radio56 = new JRadioButton("Knowing your own ambitions and goals, and trying to achieve them.");

    private JRadioButton radio57 = new JRadioButton("Injustice.");
    private JRadioButton radio58 = new JRadioButton("Cruelty.");

    private JRadioButton radio59 = new JRadioButton("Do it in the traditional way.");
    private JRadioButton radio60 = new JRadioButton("Do it in your own way.");

    private JRadioButton radio61 = new JRadioButton("Design, research and planning.");
    private JRadioButton radio62 = new JRadioButton("Production, implementation and distribution.");

    private JRadioButton radio63 = new JRadioButton("Spontaneous rather than careful.");
    private JRadioButton radio64 = new JRadioButton("More careful than spontaneous");

    private JRadioButton radio65 = new JRadioButton("Arranging and planning things before they happen.");
    private JRadioButton radio66 = new JRadioButton("Let things happen and then deal with them.");

    private JRadioButton radio67 = new JRadioButton("Regulations and laws.");
    private JRadioButton radio68 = new JRadioButton("Emotions and feelings.");

    private JPanel F2Panel;

    private JScrollPane scrollPane;

    public Pathfinder() {//constructer

        // For first Frame:
        frame1 = new JFrame();
        frame1.setSize(600, 200);
        frame1.setTitle("PathFinder");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setBackground(Color.PINK);

        introLabel = new JLabel("Welcome to PathFinder ");
        introLabel.setForeground(Color.BLACK);
        nameLabel = new JLabel("Enter your name");
        emailLabel = new JLabel("Enter your e-mail (Optional):");

        next = new JButton("Next");
        next.setForeground(Color.BLACK);
        next.addActionListener(new NextButtonListener());

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        adminlogin = new JButton("Admin Login");

        F1panelbuild();
        frame1.setLayout(new FlowLayout());
        frame1.add(introLabel);
        frame1.add(F1Panel1);
        frame1.add(F1Panel2);

        //panel for next , admin buttons
        JPanel nextadmin = new JPanel();
        nextadmin.setLayout(new GridLayout(2, 1));
        nextadmin.add(next);
        nextadmin.add(adminlogin);

        frame1.add(nextadmin);

        adminlogin.addActionListener(new AdminButton());

        // For second Frame:
        frame2 = new JFrame();
        frame2.setSize(1000, 500);
        frame2.setTitle("The MBTI test");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        startLabel = new JLabel("The test will take less than 10 minitues, please be yourself and"
                + " answer the questions honsetly.");

        button = new JButton("Result");
        button.addActionListener(new Action());

        frame2.setLocationRelativeTo(null);
        F2panelbuild();

        frame2.setContentPane(scrollPane);

        frame3 = new JFrame();
        frame3.setTitle("Result");
        frame3.setSize(600, 620);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setLocationRelativeTo(null);

        //login frame
        framelog = new JFrame();

        framelog.setTitle("login");
        framelog.setSize(400, 200);
        framelog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framelog.setLocationRelativeTo(null);
        user = new JTextField(20);
        pass = new JTextField(20);
        username = new JLabel("Username");
        password = new JLabel("Password");
        login = new JButton("Login");

        Flogpanelbuild();
        framelog.setLayout(new GridLayout(3, 1));

        framelog.add(FlogPanel1);

        framelog.add(FlogPanel2);

        framelog.add(FlogPanel3);

        login.addActionListener(new LoginButton());

        frame1.setVisible(true);

    }

    void F1panelbuild() {

        F1Panel1 = new JPanel();
        F1Panel1.add(new JLabel());
        F1Panel1.add(nameLabel);

        F1Panel1.add(nameField);
        F1Panel1.add(new JLabel());

        F1Panel2 = new JPanel();
        F1Panel2.add(new JLabel());
        F1Panel2.add(emailLabel);

        F1Panel2.add(emailField);
        F1Panel2.add(new JLabel());

        F1Panel1.setLayout(new GridLayout(1, 4));
        F1Panel2.setLayout(new GridLayout(1, 4));

    }

    private class NextButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            CheckName n = new CheckName();

            String namecheck = nameField.getText();
            if (!(n.isValidName(nameField.getText()))) {
                JOptionPane.showMessageDialog(null, "Invalid Name: Name should contain only letters with at least one letter, please write again.");
            }

            if (n.isValidName(nameField.getText())) {

                frame1.setVisible(false);
                frame2.setVisible(true);
            }
        }
    }

    private class AdminButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            frame1.setVisible(false);
            framelog.setVisible(true);

        }
    }

    private class LoginButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            CheckLogin Objchecklog = new CheckLogin();

            boolean checklogbool = Objchecklog.checkUserPass(user.getText(), pass.getText());

            if (checklogbool == true) {
                Framedmin = new JFrame();

                Framedmin.setTitle("Admin");
                Framedmin.setSize(400, 200);
                Framedmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Framedmin.setLocationRelativeTo(null);
                FileReader(filepath);
                JTextArea Fileusers = new JTextArea(usercontent);
                Fileusers.setLineWrap(true);
                Fileusers.setWrapStyleWord(true);
                Fileusers.setEditable(false);

                startPage = new JButton("START PAGE");
                startPage.addActionListener(new StartPageButton());

                JPanel startPanel = new JPanel();
                startPanel.add(new JLabel());
                startPanel.add(startPage);
                startPanel.add(new JLabel());
                JScrollPane scrollPane2 = new JScrollPane(Fileusers);
                Framedmin.add(scrollPane2, BorderLayout.CENTER);

                Framedmin.add(startPanel, BorderLayout.SOUTH);

                Framedmin.setVisible(true);

                framelog.setVisible(false);
            }

            if (checklogbool == false) {

                JOptionPane.showMessageDialog(null, "Incorrect Username or Password");

            }

        }

    }

    private class StartPageButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            frame1.setVisible(true);
            Framedmin.setVisible(false);

        }
    }

    public void Flogpanelbuild() {

        FlogPanel1 = new JPanel();
        FlogPanel1.add(username);
        FlogPanel1.add(user);

        FlogPanel2 = new JPanel();
        FlogPanel2.add(password);
        FlogPanel2.add(pass);

        FlogPanel1.setLayout(new FlowLayout());
        FlogPanel2.setLayout(new FlowLayout());
        back = new JButton("Back");

        FlogPanel3 = new JPanel();
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(back);
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(login);
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());
        FlogPanel3.add(new JLabel());

        back.addActionListener(new BackButton());

        FlogPanel3.setLayout(new GridLayout(3, 3));

    }

    private class BackButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            framelog.setVisible(false);
            frame1.setVisible(true);

        }
    }

    public void FileReader(String file) {

        try {
            // Create a FileReader
            FileReader fileReader = new FileReader(file);

            // Create a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            usercontent = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                usercontent = usercontent + line + "\n";

            }

            // Close the BufferedReader
            bufferedReader.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No User Date Found.");
        }
    }

    public void F2panelbuild() {

        F2Panel = new JPanel();
        // questions and answers:

        F2Panel.add(startLabel);
        startLabel.setForeground(Color.BLUE);
        F2Panel.add(q1);
        F2Panel.add(radio1);
        F2Panel.add(radio2);
        F2Panel.add(q2);
        F2Panel.add(radio3);
        F2Panel.add(radio4);
        F2Panel.add(q3);
        F2Panel.add(radio5);
        F2Panel.add(radio6);
        F2Panel.add(q4);
        F2Panel.add(radio7);
        F2Panel.add(radio8);
        F2Panel.add(q5);
        F2Panel.add(radio9);
        F2Panel.add(radio10);
        F2Panel.add(q6);
        F2Panel.add(radio11);
        F2Panel.add(radio12);
        F2Panel.add(q7);
        F2Panel.add(radio13);
        F2Panel.add(radio14);
        F2Panel.add(q8);
        F2Panel.add(radio15);
        F2Panel.add(radio16);
        F2Panel.add(q9);
        F2Panel.add(radio17);
        F2Panel.add(radio18);
        F2Panel.add(q10);
        F2Panel.add(radio19);
        F2Panel.add(radio20);
        F2Panel.add(q11);
        F2Panel.add(radio21);
        F2Panel.add(radio22);
        F2Panel.add(q12);
        F2Panel.add(radio23);
        F2Panel.add(radio24);
        F2Panel.add(q13);
        F2Panel.add(radio25);
        F2Panel.add(radio26);
        F2Panel.add(q14);
        F2Panel.add(radio27);
        F2Panel.add(radio28);
        F2Panel.add(q15);
        F2Panel.add(radio29);
        F2Panel.add(radio30);
        F2Panel.add(q16);
        F2Panel.add(radio31);
        F2Panel.add(radio32);
        F2Panel.add(q17);
        F2Panel.add(radio33);
        F2Panel.add(radio34);
        F2Panel.add(q18);
        F2Panel.add(radio35);
        F2Panel.add(radio36);
        F2Panel.add(q19);
        F2Panel.add(radio37);
        F2Panel.add(radio38);
        F2Panel.add(q20);
        F2Panel.add(radio39);
        F2Panel.add(radio40);
        F2Panel.add(q21);
        F2Panel.add(radio41);
        F2Panel.add(radio42);
        F2Panel.add(q22);
        F2Panel.add(radio43);
        F2Panel.add(radio44);
        F2Panel.add(q23);
        F2Panel.add(radio45);
        F2Panel.add(radio46);
        F2Panel.add(q24);
        F2Panel.add(radio47);
        F2Panel.add(radio48);
        F2Panel.add(q25);
        F2Panel.add(radio49);
        F2Panel.add(radio50);
        F2Panel.add(q26);
        F2Panel.add(radio51);
        F2Panel.add(radio52);
        F2Panel.add(q27);
        F2Panel.add(radio53);
        F2Panel.add(radio54);
        F2Panel.add(q28);
        F2Panel.add(radio55);
        F2Panel.add(radio56);
        F2Panel.add(q29);
        F2Panel.add(radio57);
        F2Panel.add(radio58);
        F2Panel.add(q30);
        F2Panel.add(radio59);
        F2Panel.add(radio60);
        F2Panel.add(q31);
        F2Panel.add(radio61);
        F2Panel.add(radio62);
        F2Panel.add(q32);
        F2Panel.add(radio63);
        F2Panel.add(radio64);
        F2Panel.add(q33);
        F2Panel.add(radio65);
        F2Panel.add(radio66);
        F2Panel.add(q34);
        F2Panel.add(radio67);
        F2Panel.add(radio68);
//        F2Panel.add(button);

        ButtonGroup group1 = new ButtonGroup();
        group1.add(radio1);
        group1.add(radio2);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(radio3);
        group2.add(radio4);

        ButtonGroup group3 = new ButtonGroup();
        group3.add(radio5);
        group3.add(radio6);

        ButtonGroup group4 = new ButtonGroup();
        group4.add(radio7);
        group4.add(radio8);

        ButtonGroup group5 = new ButtonGroup();
        group5.add(radio9);
        group5.add(radio10);

        ButtonGroup group6 = new ButtonGroup();
        group6.add(radio11);
        group6.add(radio12);

        ButtonGroup group7 = new ButtonGroup();
        group7.add(radio13);
        group7.add(radio14);

        ButtonGroup group8 = new ButtonGroup();
        group8.add(radio15);
        group8.add(radio16);

        ButtonGroup group9 = new ButtonGroup();
        group9.add(radio17);
        group9.add(radio18);

        ButtonGroup group10 = new ButtonGroup();
        group10.add(radio19);
        group10.add(radio20);

        ButtonGroup group11 = new ButtonGroup();
        group11.add(radio21);
        group11.add(radio22);

        ButtonGroup group12 = new ButtonGroup();
        group12.add(radio23);
        group12.add(radio24);

        ButtonGroup group13 = new ButtonGroup();
        group13.add(radio25);
        group13.add(radio26);

        ButtonGroup group14 = new ButtonGroup();
        group14.add(radio27);
        group14.add(radio28);

        ButtonGroup group15 = new ButtonGroup();
        group15.add(radio29);
        group15.add(radio30);

        ButtonGroup group16 = new ButtonGroup();
        group16.add(radio31);
        group16.add(radio32);

        ButtonGroup group17 = new ButtonGroup();
        group17.add(radio33);
        group17.add(radio34);

        ButtonGroup group18 = new ButtonGroup();
        group18.add(radio35);
        group18.add(radio36);

        ButtonGroup group19 = new ButtonGroup();
        group19.add(radio37);
        group19.add(radio38);

        ButtonGroup group20 = new ButtonGroup();
        group20.add(radio39);
        group20.add(radio40);

        ButtonGroup group21 = new ButtonGroup();
        group21.add(radio41);
        group21.add(radio42);

        ButtonGroup group22 = new ButtonGroup();
        group22.add(radio43);
        group22.add(radio44);

        ButtonGroup group23 = new ButtonGroup();
        group23.add(radio45);
        group23.add(radio46);

        ButtonGroup group24 = new ButtonGroup();
        group24.add(radio47);
        group24.add(radio48);

        ButtonGroup group25 = new ButtonGroup();
        group25.add(radio49);
        group25.add(radio50);

        ButtonGroup group26 = new ButtonGroup();
        group26.add(radio51);
        group26.add(radio52);

        ButtonGroup group27 = new ButtonGroup();
        group27.add(radio53);
        group27.add(radio54);

        ButtonGroup group28 = new ButtonGroup();
        group28.add(radio55);
        group28.add(radio56);

        ButtonGroup group29 = new ButtonGroup();
        group29.add(radio57);
        group29.add(radio58);

        ButtonGroup group30 = new ButtonGroup();
        group30.add(radio59);
        group30.add(radio60);

        ButtonGroup group31 = new ButtonGroup();
        group31.add(radio61);
        group31.add(radio62);

        ButtonGroup group32 = new ButtonGroup();
        group32.add(radio63);
        group32.add(radio64);

        ButtonGroup group33 = new ButtonGroup();
        group33.add(radio65);
        group33.add(radio66);

        ButtonGroup group34 = new ButtonGroup();
        group34.add(radio67);
        group34.add(radio68);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button);
        F2Panel.add(buttonPanel);
        buttonPanel.setBackground(Color.PINK);
        F2Panel.setLayout(
                new GridLayout(0, 1, 0, 30));
        F2Panel.setBackground(Color.PINK);
        scrollPane = new JScrollPane(F2Panel);

    }

    public void F3panelBuild() {

        F3panel = new JPanel();

        name = nameField.getText();

        for (int i = 0; i < 4; i++) {

            String n = mbtiarr[i];
            mbtistr += n;
        }

        if (mbtistr.equals("ENFP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ENFP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ENFJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ENFJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ENTP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ENTP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ENTJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ENTJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ESFP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ESFP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ESFJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ESFJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ESTJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ESTJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ESTP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ESTP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ISTP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ISTP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ISFP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ISFP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("INTP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(INTP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("INFP")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(INFP);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ISFJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ISFJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("ISTJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(ISTJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("INFJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(INFJ);

            resulttext.setForeground(Color.WHITE);
        }

        if (mbtistr.equals("INTJ")) {
            String namembti = name + ", your MBTI is ";
            resulttext = new JLabel(namembti + mbtistr);
            F3panel.add(resulttext);
            F3panel.add(INTJ);

            F3panel.setBackground(Color.MAGENTA);

            resulttext.setForeground(Color.WHITE);
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath, true));
            String content = "user Name: " + nameField.getText();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            if (!(emailField.getText().isEmpty())) {

                String content2 = "user Email: " + emailField.getText();
                bufferedWriter.write(content2);
                bufferedWriter.newLine();

            }
            String content3 = "user MBTI: " + mbtistr;
            bufferedWriter.write(content3);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException x) {
            x.printStackTrace();
        }

        F3panel.setBackground(Color.PINK);

        INTJ.setForeground(Color.WHITE);
        ISTJ.setForeground(Color.WHITE);
        ENFJ.setForeground(Color.WHITE);
        ESFJ.setForeground(Color.WHITE);
        ESTJ.setForeground(Color.WHITE);
        ENTJ.setForeground(Color.WHITE);
        ISTP.setForeground(Color.WHITE);
        ISFP.setForeground(Color.WHITE);
        ISFJ.setForeground(Color.WHITE);
        ENFP.setForeground(Color.WHITE);
        ESTP.setForeground(Color.WHITE);
        ESFP.setForeground(Color.WHITE);
        ENTP.setForeground(Color.WHITE);
        INTP.setForeground(Color.WHITE);
        INFP.setForeground(Color.WHITE);
        INFJ.setForeground(Color.WHITE);

    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if ((radio1.isSelected() | radio2.isSelected())
                    & (radio3.isSelected() | radio4.isSelected())
                    & (radio5.isSelected() | radio6.isSelected())
                    & (radio7.isSelected() | radio8.isSelected())
                    & (radio9.isSelected() | radio10.isSelected())
                    & (radio11.isSelected() | radio12.isSelected())
                    & (radio13.isSelected() | radio14.isSelected())
                    & (radio15.isSelected() | radio16.isSelected())
                    & (radio17.isSelected() | radio18.isSelected())
                    & (radio19.isSelected() | radio20.isSelected())
                    & (radio21.isSelected() | radio22.isSelected())
                    & (radio23.isSelected() | radio24.isSelected())
                    & (radio25.isSelected() | radio26.isSelected())
                    & (radio27.isSelected() | radio28.isSelected())
                    & (radio29.isSelected() | radio30.isSelected())
                    & (radio31.isSelected() | radio32.isSelected())
                    & (radio33.isSelected() | radio34.isSelected())
                    & (radio35.isSelected() | radio36.isSelected())
                    & (radio37.isSelected() | radio38.isSelected())
                    & (radio39.isSelected() | radio40.isSelected())
                    & (radio41.isSelected() | radio42.isSelected())
                    & (radio43.isSelected() | radio44.isSelected())
                    & (radio45.isSelected() | radio46.isSelected())
                    & (radio47.isSelected() | radio48.isSelected())
                    & (radio49.isSelected() | radio50.isSelected())
                    & (radio51.isSelected() | radio52.isSelected())
                    & (radio53.isSelected() | radio54.isSelected())
                    & (radio55.isSelected() | radio56.isSelected())
                    & (radio57.isSelected() | radio58.isSelected())
                    & (radio59.isSelected() | radio60.isSelected())
                    & (radio61.isSelected() | radio62.isSelected())
                    & (radio63.isSelected() | radio64.isSelected())
                    & (radio65.isSelected() | radio66.isSelected())
                    & (radio67.isSelected() | radio68.isSelected())) { //if statment to be sure all Qs answerd 

                if (e.getSource() == button) {

                    if (radio1.isSelected()) {
                        // E
                        ++E;
                    } else {
                        // I
                        ++I;
                    }

                    if (radio3.isSelected()) {
                        // I
                        ++I;
                    } else {
                        // E
                        ++E;
                    }

                    if (radio5.isSelected()) {
                        // S
                        ++S;
                    } else {
                        // N
                        ++N;
                    }

                    if (radio7.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio9.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio11.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio13.isSelected()) {
                        // N
                        ++N;
                    } else {
                        // S
                        ++S;
                    }

                    if (radio15.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio17.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio19.isSelected()) {
                        // F
                        ++F;
                    } else {
                        // T
                        ++T;
                    }

                    if (radio21.isSelected()) {
                        // I
                        ++I;
                    } else {
                        // E
                        ++E;
                    }

                    if (radio23.isSelected()) {
                        // P
                        ++P;
                    } else {
                        // J
                        ++J;
                    }

                    if (radio25.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio27.isSelected()) {
                        // I
                        ++I;
                    } else {
                        // E
                        ++E;
                    }

                    if (radio29.isSelected()) {
                        // P
                        ++P;
                    } else {
                        // J
                        ++J;
                    }

                    if (radio31.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio33.isSelected()) {
                        // P
                        ++P;
                    } else {
                        // J
                        ++J;
                    }

                    if (radio35.isSelected()) {
                        // S
                        ++S;
                    } else {
                        // N
                        ++N;
                    }

                    if (radio37.isSelected()) {
                        // S
                        ++S;
                    } else {
                        // N
                        ++N;
                    }

                    if (radio39.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio41.isSelected()) {
                        // E
                        ++E;
                    } else {
                        // I
                        ++I;
                    }

                    if (radio43.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio45.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio47.isSelected()) {
                        // F
                        ++F;
                    } else {
                        // T
                        ++T;
                    }

                    if (radio49.isSelected()) {
                        // N
                        ++N;
                    } else {
                        // S
                        ++S;
                    }

                    if (radio51.isSelected()) {
                        // N
                        ++N;
                    } else {
                        // S
                        ++S;
                    }

                    if (radio53.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio55.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio57.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    if (radio59.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio61.isSelected()) {
                        // N
                        ++N;
                    } else {
                        // S
                        ++S;
                    }

                    if (radio63.isSelected()) {
                        // P
                        ++P;
                    } else {
                        // J
                        ++J;
                    }

                    if (radio65.isSelected()) {
                        // J
                        ++J;
                    } else {
                        // P
                        ++P;
                    }

                    if (radio67.isSelected()) {
                        // T
                        ++T;
                    } else {
                        // F
                        ++F;
                    }

                    mbti();

                    F3panelBuild();
                    frame3.add(F3panel);

                    frame2.setVisible(false);

                    frame3.setVisible(true);

                }

            } else {
                //error meassage needs to select all buttons

                JOptionPane.showMessageDialog(null, "You need to answer all questions.");

            }

        }//end actionperformed method

    }//end action class

    public void mbti() {

        if (I > E) { // I,E
            mbtiarr[0] = "I";
        } else {
            mbtiarr[0] = "E";
        }

        if (S > N) { // S,N
            mbtiarr[1] = "S";
        } else {
            mbtiarr[1] = "N";
        }

        if (T > F) {// T,F
            mbtiarr[2] = "T";

        } else {
            mbtiarr[2] = "F";
        }

        if (P > J) {// P,J
            mbtiarr[3] = "P";
        } else {
            mbtiarr[3] = "J";
        }

    }

    public static void main(String[] args) {
        Pathfinder pathFinder = new Pathfinder();
    }

}
