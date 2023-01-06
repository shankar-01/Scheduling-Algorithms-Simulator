import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GanttChart {
    static Color[] colors = {Color.green, Color.orange, Color.red, Color.blue, Color.MAGENTA};
    private class ProcessLabel extends JLabel{
        ProcessLabel next = null;
        ProcessLabel prev = null;
        final static int Height = 30;
        ProcessLabel(int pn, int length){
            setText("P"+pn);
            setHorizontalAlignment(SwingConstants.CENTER);
            setBounds(new Rectangle(length * 15, Height));
            setOpaque(true);
            setBackground(colors[pn%colors.length]);
        }
    }

    private ProcessLabel fpro = null;//first process to execute
    private ProcessLabel lpro = null;//last process to execute
    public void addProcess(int pn, int length){
        ProcessLabel np =new ProcessLabel(pn, length);
        if(fpro == null) {
            lpro = fpro = np;
        }
        else {
            lpro.next = np;
            np.prev = lpro;
            lpro = np;
        }
    }
    public void display(JFrame frame, int xA, int yA) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        ProcessLabel node = fpro;
        int x = 10;
        String str = "";
        while (node != null) {
            panel.add(node);
            JLabel label = new JLabel((x - 10) / 15 + "");
            panel.add(label);
            node.setBounds(x, 20, node.getWidth(), node.getHeight());
            label.setBounds(x, 50, node.getWidth(), node.getHeight() / 2);
            x += node.getWidth();
            node = node.next;
        }
        JLabel label = new JLabel((x - 10) / 15 + "");
        label.setBounds(x, 50, lpro.getWidth(), lpro.getHeight() / 2);
        panel.add(label);


        panel.setPreferredSize(new Dimension(x + 20, 100));
        JScrollPane jScrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(jScrollPane);
        jScrollPane.setBounds(xA, yA, 820, 100);
        System.out.println(jScrollPane.getVerticalScrollBarPolicy());
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        frame.setSize(850, 600);
        frame.setVisible(true);
    }
    public void fcfs(Node[] nodes) {
        JFrame frame = new JFrame("Gantt Chart");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(new Table(nodes));
        int time = 0;
        int wtime = 0;
        for (int i = 0; i < nodes.length; i++) {
            addProcess(nodes[i].id, nodes[i].getCb());
            time += nodes[i].getCb();
            wtime+=time;
        }
        frame.add(new MyGraph((float)(wtime-time)/nodes.length, (float)wtime/nodes.length, (float)(wtime-time)/nodes.length));
        display(frame, 0, 200);
    }
    public void sjf(Node[] nodes) {
        JFrame frame = new JFrame("Gantt Chart");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(new Table(nodes));
        sort(nodes);
        int time = 0;
        int wtime = 0;
        for (int i = 0; i < nodes.length; i++) {
            addProcess(nodes[i].id, nodes[i].getCb());
            time += nodes[i].getCb();
            wtime += time;
        }
        frame.add(new MyGraph((float)(wtime-time)/nodes.length, (float)wtime/nodes.length, (float)(wtime-time)/nodes.length));
        display(frame, 0, 200);
    }
    public void rr(Node[] nodes, int qu) {
        JFrame frame = new JFrame("Gantt Chart");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(new Table(nodes));
        boolean[] flags = new boolean[nodes.length];
        int count = nodes.length;
        int wtime = 0;
        int ttime = 0;
        int rtime = 0;
        int time = 0;
        for (int i = 0; completed(flags); i++) {
            if(flags[i%flags.length]){
                continue;
            }
            if(nodes[i%nodes.length].getCb() == 0){
                flags[i%flags.length] = true;
                continue;
            }
            if(nodes[i%nodes.length].getCb() < qu){
                addProcess(nodes[i%nodes.length].id, nodes[i%nodes.length].getCb());
                if(i<nodes.length){
                    rtime += time;
                }
                time += nodes[i%nodes.length].getCb();
                wtime += (count-1) * nodes[i%nodes.length].getCb();
                nodes[i%nodes.length].setCb(0);
                count--;
                ttime += time;

            }
            else{
                addProcess(nodes[i%nodes.length].id, qu);
                if(i<nodes.length){
                    rtime += time;
                }
                time += qu;
                nodes[i%nodes.length].setCb(nodes[i%nodes.length].getCb()-qu);
                wtime += (count-1) * qu;
                if(nodes[i%nodes.length].getCb() == 0) {
                    count--;
                    ttime += time;
                }
            }
        }
        frame.add(new MyGraph((float)wtime/nodes.length, (float)ttime/nodes.length, (float)rtime/nodes.length));
        display(frame, 0, 200);
    }
    private boolean completed(boolean[] flags){
        for (int i = 0; i < flags.length; i++) {
            if(!flags[i]){

                return true;
            }
        }
        return false;
    }
    private void sort(Node[] nodes){
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i; j < nodes.length; j++) {
                if(nodes[i].getCb() > nodes[j].getCb()){
                    Node node = nodes[i];
                    nodes[i] = nodes[j];
                    nodes[j] = node;
                }
            }
        }
    }
}
