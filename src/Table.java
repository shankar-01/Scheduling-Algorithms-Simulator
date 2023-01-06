import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
class Table extends JPanel{
    Table(Node[] nodes){
        String[] cname = {"Process", "CPU Burst"}; // column names
        String[][] data = new String[nodes.length][cname.length];
        for(int i=0; i<data.length; i++){
            data[i][0] = "P" +nodes[i].id;
            data[i][1] = ""+nodes[i].getCb();
        }
        setBounds(430, 0, 400, 200);
        setLayout(null);
        JTable table = new JTable(data, cname);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        sp.setBounds(0, 0, 400, 200);
    }
}
