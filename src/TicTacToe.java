import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private JFrame frame = new JFrame("Tic-Tac-Toe");
    private JPanel panel = new JPanel();
    private JButton[] buttons = new JButton[9];
    private boolean player1Turn = true;

    // Define color palette
    private final Color DARK_BROWN = new Color(0x6F4E37);
    private final Color BROWN = new Color(0xA67B5B);
    private final Color LIGHT_BROWN = new Color(0xECB176);
    private final Color LIGHTEST_BROWN = new Color(0xFED8B1);

    public TicTacToe() {
        // Set up the frame
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(DARK_BROWN);

        // Set up the panel with GridLayout
        panel.setLayout(new GridLayout(3, 3));
        frame.add(panel);

        // Initialize buttons and add them to the panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(LIGHT_BROWN);
            buttons[i].setForeground(DARK_BROWN);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        // Set the panel background color
        panel.setBackground(BROWN);

        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getText().equals("")) {
            if (player1Turn) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(LIGHTEST_BROWN);
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(LIGHTEST_BROWN);
            }
            player1Turn = !player1Turn;
            checkForWinner();
        }
    }

    private void checkForWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                announceWinner(board[i][0]);
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                announceWinner(board[0][i]);
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            announceWinner(board[0][0]);
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            announceWinner(board[0][2]);
        }

        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetBoard();
        }
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(frame, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
