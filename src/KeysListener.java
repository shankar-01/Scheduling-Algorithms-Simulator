import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
class KeysListener extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!((c>='0' && c<='9') || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
            e.consume();
        }
    }
}
