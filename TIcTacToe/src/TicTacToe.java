import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener
{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel= new JPanel();
    JPanel buttonPanel= new JPanel();
    JLabel textField= new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;
    int actionBoardOccupancy =0;
    public TicTacToe()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(70,70,70));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(56, 65, 65));
        textField.setForeground(new Color(114, 210, 17, 255));
        textField.setFont(new Font("Ink Free", Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Kółko i krzyżyk");
        textField.setOpaque(true);

        titlePanel.setLayout((new BorderLayout()));
        titlePanel.setBounds(0,0,800,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(150,150,150));

        for (int i = 0; i < 9; i++)
        {
            buttons[i]= new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }


        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < 9; i++)
        {
            if(e.getSource()==buttons[i])
            {
                if(player1Turn)
                {
                    if(buttons[i].getText()=="")
                    {
                        actionBoardOccupancy++;
                        buttons[i].setForeground(new Color(200,0,0));
                        buttons[i].setText("O");
                        player1Turn=false;
                        textField.setText("X turn");
                        check();
                    }
                }
                else
                {
                    if(buttons[i].getText()=="")
                    {
                        actionBoardOccupancy++;
                        buttons[i].setForeground(new Color(0,0,200));
                        buttons[i].setText("X");
                        player1Turn=true;
                        textField.setText("O turn");
                        check();
                    }
                }

            }
    }



    }

    private void draw() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("Draw, no one wins");
    }

    public void firstTurn()
    {

        try
        {
            Thread.sleep(1500);
        } catch (InterruptedException e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }

        if(random.nextInt(2)==0)
        {
            player1Turn=true;
            textField.setText("O turn");
        }
        else
        {
            player1Turn = false;
            textField.setText("X turn");
        }
    }
    public void check()
    {   //X win conditions
        for (int i = 0; i < 9; i+=3)
        {
            if(
                    (buttons[i].getText()=="X")&&
                            (buttons[i+1].getText()=="X")&&
                            (buttons[i+2].getText()=="X"))
            {
                xWins(i,i+1,i+2);
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if(
                    (buttons[i].getText()=="X")&&
                            (buttons[i+3].getText()=="X")&&
                            (buttons[i+6].getText()=="X"))
            {
                xWins(i,i+3,i+6);
            }
        }
        if(
                (buttons[0].getText()=="X")&&
                        (buttons[4].getText()=="X")&&
                        (buttons[8].getText()=="X"))
        {
            xWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="X")&&
                        (buttons[4].getText()=="X")&&
                        (buttons[6].getText()=="X"))
        {
            xWins(2,4,6);
        }
        //O win conditions
        for (int i = 0; i < 9; i+=3)
        {
            if(
                    (buttons[i].getText()=="O")&&
                            (buttons[i+1].getText()=="O")&&
                            (buttons[i+2].getText()=="O"))
            {
                oWins(i,i+1,i+2);
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if(
                    (buttons[i].getText()=="O")&&
                            (buttons[i+3].getText()=="O")&&
                            (buttons[i+6].getText()=="O"))
            {
                oWins(i,i+3,i+6);
            }
        }
        if(
                (buttons[0].getText()=="O")&&
                        (buttons[4].getText()=="O")&&
                        (buttons[8].getText()=="O"))
        {
            oWins(0,4,8);
        }
        if(
                (buttons[2].getText()=="O")&&
                        (buttons[4].getText().equals("O"))&&
                        (buttons[6].getText()=="O"))
        {
            oWins(2,4,6);
        }
        if(actionBoardOccupancy >=9)
        draw();

    }
    public void xWins(int a, int b, int c)
    {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("X wins");
        actionBoardOccupancy =0;

    }
    public void oWins(int a, int b, int c)
    {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
        actionBoardOccupancy=0;
    }
}
