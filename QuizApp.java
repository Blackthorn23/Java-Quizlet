import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private int currentQuestion = 0;
    private int attempts = 3;
    private String userName;

    private String[][] questions = {
            {"What is the language used in this OOP course?", "java"},
            {"What is the command to print an output?", "System.out.println"},
            {"How to compile Test.java using command prompt?", "javac Test.java"},
            {"How to run Test.java using command prompt?", "java Test"},
            {"Which package contains mathematical functions?\nA. java.lang.*  | B. java.awt.*\nC. java.util.Scanner  | D. java.util.ArrayList", "a"},
            {"Where does a Java program start executing instructions from?\nA. class  | B. source file\nC. main method  | D. object", "c"},
            {"What is the output of the following code?\n\npublic class AddTwoNumbers {\n  public static void main(String[] args) {\n    num1 = 5, num2 = 15, sum;\n    sum = num1 + num2;\n    System.out.println(+ sum );\n  }\n}\n\nA. 20  | B. Compilation error", "compilation error"},
            {"What is the output of this Java program?\n\npublic class MyFirstJavaProgram {\n  static void main(String []args) {\n    System.out.println('Hello World');\n  }\n}\n\nA. Hello World  | B. String  | C. MyFirstJavaProgram  | D. Compilation error", "d"}
    };

    public QuizApp() {
        setTitle("Java Quiz App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Welcome Screen for Name Input
        userName = JOptionPane.showInputDialog("What is your name?");
        JOptionPane.showMessageDialog(null, "Hello " + userName + ", welcome to the Java Quiz!");

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(questionLabel, BorderLayout.NORTH);

        answerField = new JTextField();
        add(answerField, BorderLayout.CENTER);

        submitButton = new JButton("Submit Answer");
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText("<html>" + questions[currentQuestion][0].replace("\n", "<br>") + "</html>");
            answerField.setText("");
            attempts = 3; // Reset attempts for new question
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Completed! Well done, " + userName + "!");
            System.exit(0);
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase();
        String correctAnswer = questions[currentQuestion][1];

        if (userAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct!");
            currentQuestion++;
            loadQuestion();
        } else {
            attempts--;
            if (attempts > 0) {
                JOptionPane.showMessageDialog(this, "Wrong answer! Try again. Attempts left: " + attempts);
            } else {
                JOptionPane.showMessageDialog(this, "Out of attempts! Moving to next question.");
                currentQuestion++;
                loadQuestion();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuizApp().setVisible(true);
        });
    }
}
