import javax.swing.*;

public class Frame extends JFrame {

     Frame(){
        this.add(new Panel_gry());
        this.setTitle("TETRIS BLANKI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);
         this.setVisible(true);

         }



}
