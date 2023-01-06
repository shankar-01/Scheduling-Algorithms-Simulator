import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class Node{
    private static int count = 1;
    private int cb = 0;//cpu burst
    int id = 0;
    Node(int cb){
        this.cb = cb;
        id = count++;
    }
    public int getCb(){
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }
}