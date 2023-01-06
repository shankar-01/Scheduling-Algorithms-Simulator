import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
class MyGraph extends JPanel{
    public MyGraph(double aw, double at, double ar) {
        setBounds(0, 0, 400, 200);
        setBackground(Color.white);
        setLayout(null);
        JLabel awl = new JLabel();
        JLabel atl = new JLabel();
        JLabel arl = new JLabel();
        setHeight(awl, atl, arl, aw, at, ar);
        awl.setBounds(20, 200-awl.getHeight(), awl.getWidth(), awl.getHeight());
        atl.setBounds(100, 200-atl.getHeight(), atl.getWidth(), atl.getHeight());
        arl.setBounds(180, 200-arl.getHeight(), arl.getWidth(), arl.getHeight());

        awl.setText(""+aw);
        atl.setText(""+at);
        arl.setText(""+ar);

        awl.setBackground(Color.GREEN);
        awl.setOpaque(true);
        atl.setOpaque(true);
        arl.setOpaque(true);
        atl.setBackground(Color.RED);
        arl.setBackground(Color.BLUE);

        atl.setVerticalAlignment(SwingConstants.TOP);
        awl.setVerticalAlignment(SwingConstants.TOP);
        arl.setVerticalAlignment(SwingConstants.TOP);
        atl.setHorizontalAlignment(SwingConstants.CENTER);
        awl.setHorizontalAlignment(SwingConstants.CENTER);
        arl.setHorizontalAlignment(SwingConstants.CENTER);

        add(awl);
        add(atl);
        add(arl);


        JLabel waitingLabel = new JLabel("Average Waiting time");
        JLabel turnaroundLabel = new JLabel("Average turnaround time");
        JLabel responseLabel = new JLabel("Average response time");
        waitingLabel.setOpaque(true);
        turnaroundLabel.setOpaque(true);
        responseLabel.setOpaque(true);
        waitingLabel.setBackground(Color.green);
        turnaroundLabel.setBackground(Color.red);
        responseLabel.setBackground(Color.blue);

        waitingLabel.setForeground(Color.WHITE);
        turnaroundLabel.setForeground(Color.WHITE);
        responseLabel.setForeground(Color.WHITE);

        add(waitingLabel);
        add(turnaroundLabel);
        add(responseLabel);

        waitingLabel.setBounds(260, 50, 160, 20);
        turnaroundLabel.setBounds(260, 80, 160, 20);
        responseLabel.setBounds(260, 110, 160, 20);
    }
    public void setHeight(JLabel a, JLabel b, JLabel c, double va, double vb, double vc){
        double d = 0;
        if(va>=vb && va>=vc){
            d = va;
        }
        else if(vb>=va && vb>=vc){
            d = vb;
        }
        else if(vc>=va && vc>=vb){
            d = vc;
        }

        a.setSize(55, (int)Math.round(150*va/d));
        b.setSize(55, (int)Math.round(150*vb/d));
        c.setSize(55, (int)Math.round(150*vc/d));
    }
}
