
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.lang.*;
public class Panel_gry extends JPanel implements ActionListener{

    static final int szerokosc = 800;
    static final int wysokosc = 800;
    static final int rkratek = 25;
    static final int ikratek = (szerokosc*wysokosc)/(rkratek*rkratek);
    static final int czas = 75;
    final int x[] = new int[ikratek];
    final int y[] = new int[ikratek];
    int waz = 6;
    int zjedzone;
    int jedzenieX;
    int jedzenieY;
    int punkty;
    char kierunek = 'R';
    boolean trwa_gra=false;
    static boolean r = true;
    Timer zegar;
    Random los;

    Panel_gry(){
        los = new Random();
        this.setPreferredSize(new Dimension(szerokosc,wysokosc));
        this.setBackground(Color.black);
        this.setFocusable(true);
       this.addKeyListener(new Przycisk());
        start();
    }

    public void start(){
        noweJedznie();
        trwa_gra=true;
        zegar = new Timer(czas,this);
        zegar.start();


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        rysuj(g);
    }

    public void rysuj(Graphics g){

        if(trwa_gra){
            for (int i = 0; i < wysokosc / rkratek; i++) {
                g.drawLine(i * rkratek, 0, i * rkratek, wysokosc);
                g.drawLine(0, i * rkratek, szerokosc, i * rkratek);
            }
            g.setColor(Color.red);
            g.fillOval(jedzenieX, jedzenieY, rkratek, rkratek);

            for (int i = 0; i < waz; i++) {
                if (i == 0) {
                    g.setColor(new Color(10, 130, 100));
                    g.fillRect(x[i], y[i], rkratek, rkratek);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], rkratek, rkratek);
                }
            }

            g.setColor(Color.green);
            g.setFont(new Font("Verdana",Font.BOLD,20));
            FontMetrics met = getFontMetrics(g.getFont());
            g.drawString("Punkty: "+zjedzone,(szerokosc-met.stringWidth("Punkty: "+zjedzone))/2,g.getFont().getSize());

        }
        else{
            gameOver(g);
        }


    }
    public void noweJedznie(){
        jedzenieX=los.nextInt((szerokosc/rkratek))*rkratek;
        jedzenieY=+los.nextInt((wysokosc/rkratek))*rkratek;;
    }

    public void ruch(){
        for(int i=waz;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(kierunek){
            case 'U':
                y[0]=y[0]-rkratek;
                break;

            case 'D':
                y[0]=y[0]+rkratek;
                break;
            case 'L':
                x[0]=x[0]-rkratek;
                break;
            case 'R':
                x[0]=x[0]+rkratek;
                break;
        }
    }
    public void czy_jedzenie(){
    if((x[0]== jedzenieX)&&(y[0]==jedzenieY)){
        waz++;
        zjedzone++;
        noweJedznie();
    }
    }
    public void zderzenie(){
        for(int i= waz;i>0;i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                trwa_gra = false;
                break;
            }
        }
        if(x[0]<0){
            trwa_gra=false;
        }
        if(x[0]>szerokosc){
            trwa_gra=false;
        }
        if(y[0]<0){
            trwa_gra=false;
        }
        if(y[0]>wysokosc){
            trwa_gra=false;
        }
        if(!trwa_gra){
            zegar.stop();
        }
    }
    public void gameOver(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Verdana",Font.BOLD,20));
        FontMetrics met = getFontMetrics(g.getFont());
        g.drawString("Punkty: "+zjedzone,(szerokosc-met.stringWidth("Punkty: "+zjedzone))/2,g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("BLANKA, TRENUJ DALEJ (Nacisnij R)",(szerokosc - metrics.stringWidth("BLANKA, TRENUJ DALEJ (Nacisnij R)"))/2,wysokosc/2);
        JButton restart =new JButton();

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(trwa_gra){
            ruch();
            czy_jedzenie();
            zderzenie();
        }
        repaint();
    }
    public class Przycisk extends KeyAdapter {

        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(kierunek!='R'){
                        kierunek='L';
                    } break;
                case KeyEvent.VK_RIGHT:
                    if(kierunek!='L'){
                        kierunek='R';
                    } break;

                case KeyEvent.VK_UP:
                    if(kierunek!='D'){
                        kierunek='U';
                    } break;

                case KeyEvent.VK_DOWN:
                    if(kierunek!='U'){
                        kierunek='D';
                    } break;
                case KeyEvent.VK_R: {new Snake();}
                    break;
            }
        }
    }
}



