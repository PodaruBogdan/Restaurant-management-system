package presentationLayer;

import businessLayer.CompositeProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ChefGUI extends JFrame implements Observer {
    private JTextArea area;
    static int count;
    public ChefGUI(){
        area=new JTextArea();
        area.setEditable(false);
        this.pack();
        JScrollPane pane=new JScrollPane(area);
        this.setContentPane(pane);
        this.setTitle("Chef");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(400,400));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void update(Observable o, Object arg) {
        if(arg instanceof CompositeProduct){
            count++;
            area.append(count+". "+"To cook : ");
            area.append(((CompositeProduct) arg).getName()+"\n");
        }
    }
}
