
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.lang.*;
public class Snake extends JPanel {
    JButton start;
    JFrame s;
    Snake(){

        s=new JFrame();
        start = new JButton("Czesc Blanka, chcialas gre to masz! nacisnij na ekran by zaczac");
        start.setBackground(Color.pink);
        start.setFont(new Font("Ink Free",Font.BOLD,30));

        start.setPreferredSize(new Dimension(100,100));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==start){
                    new Frame();
                }
            }
        });
        s.setPreferredSize(new Dimension(800,800));
        s.add(start);
        s.setTitle("TETRIS BLANKI");
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.setResizable(false);
        s.pack();

        s.setLocationRelativeTo(null);
        s.setVisible(true);

    }



    public static void main(String[] args){

        new Snake();

    }

}
