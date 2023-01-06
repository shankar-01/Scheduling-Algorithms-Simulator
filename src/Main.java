import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main extends JFrame {
    public JPanel upper = new JPanel();
    public JPanel lower = new JPanel();
    public JTextField minBurst = new JTextField();
    public JTextField maxBurst = new JTextField();
    public JTextField jobs = new JTextField();
    public JRadioButton fifo = new JRadioButton("FIFO");
    public JRadioButton sjf = new JRadioButton("SJF");
    public JRadioButton roundRobin = new JRadioButton("Round Robin");

    public JButton simulate = new JButton("Simulate");

    public Font font = new Font("MV Boli", Font.BOLD, 30);

    public Main(){
        upper.setBounds(0,0,800,150);
        upper.setLayout(null);
        upper.setBackground(Color.red);
        JLabel title = new JLabel("Simulation Scheduling Algorithms");
        title.setFont(font);
        title.setBounds(100,50,600,50);
        title.setBackground(Color.red);
        upper.add(title);

        lower.setBounds(0,150,800, 450);
        lower.setLayout(null);
        lower.setBackground(Color.gray);

        JLabel minimumBurst = new JLabel("Add Minimum Burst");
        minimumBurst.setBounds(20,30, 150,30);
        lower.add(minimumBurst);
        minBurst.setBounds(200,30,100,30);
        lower.add(minBurst);

        JLabel maximumBurst = new JLabel("Add Maximum Burst");
        maximumBurst.setBounds(20,70,150,30);
        lower.add(maximumBurst);
        maxBurst.setBounds(200,70,100,30);
        lower.add(maxBurst);

        JLabel numOfJobs = new JLabel("Jobs");
        numOfJobs.setBounds(20,110,150,30);
        lower.add(numOfJobs);
        jobs.setBounds(200,110,100,30);
        lower.add(jobs);

        ButtonGroup group = new ButtonGroup();
        group.add(fifo);
        fifo.setBounds(400,30,150,30);
        lower.add(fifo);
        group.add(sjf);
        sjf.setBounds(400,70,150,30);
        lower.add(sjf);
        group.add(roundRobin);
        roundRobin.setBounds(400,110,150,30);
        lower.add(roundRobin);

        simulate.setBounds(400,200,150,30);
        simulate.setBackground(Color.GREEN);
        lower.add(simulate);

        setLayout(null);
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(upper);
        add(lower);

        setVisible(true);

        minBurst.addKeyListener(new KeysListener());
        maxBurst.addKeyListener(new KeysListener());
        jobs.addKeyListener(new KeysListener());
        fifo.setSelected(true);
        listen();
    }

    public void listen(){
        simulate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GanttChart chart = new GanttChart();
                Node[] nodes = createProcesses();
                if(sjf.isSelected()){
                    chart.sjf(nodes);
                }
                else if(fifo.isSelected()){
                    chart.fcfs(nodes);
                }
                else if(roundRobin.isSelected()){
                    String q = JOptionPane.showInputDialog(new JFrame(), "Enter quantum", minBurst.getText());
                    if(q == null || Integer.parseInt(q) == 0){
                        chart.rr(nodes, Integer.parseInt(minBurst.getText()));
                        //dispose();
                        return;
                    }
                    chart.rr(nodes, Integer.parseInt(q));
                }
                //dispose();
            }
        });
    }
    private Node[] createProcesses(){
        Node[] nodes = new Node[Integer.parseInt(jobs.getText())];
        Random random = new Random();
        for (int i = 0; i < nodes.length; i++) {
            int min = Integer.parseInt(minBurst.getText());
            int max = Integer.parseInt(maxBurst.getText());
            nodes[i] = new Node(min + random.nextInt(max-min+1));
        }
        return nodes;
    }
    public static void main(String[] args) {
        new Main();
    }
}
