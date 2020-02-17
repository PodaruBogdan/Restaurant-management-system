package presentationLayer;
import businessLayer.BaseProduct;
import businessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBase extends JFrame {
    private JTextField jtf1,jtf2,jtf3,jtf4;
    public AddBase(final AdministratorGUI frame) {
        this.setTitle("Add base product");
        this.pack();

        JPanel content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        JPanel line1=new JPanel();
        line1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel line2=new JPanel();
        line2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel line3=new JPanel();
        line3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel line4=new JPanel();
        line4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jtf1=new JTextField(5);
        line1.add(new JLabel("page : "));
        line1.add(jtf1);
        jtf2=new JTextField(5);
        line2.add(new JLabel(" price : "));
        line2.add(jtf2);
        jtf3=new JTextField(15);
        line3.add(new JLabel("name : "));
        line3.add(jtf3);
        jtf4=new JTextField(5);
        line4.add(new JLabel("quantity : "));
        line4.add(jtf4);

        content.add(line1);
        content.add(line2);
        content.add(line3);
        content.add(line4);
        JButton button=new JButton("add");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int page = Integer.parseInt(jtf1.getText());
                    float price=Float.parseFloat(jtf2.getText());
                    String name=jtf3.getText();
                    int quantity=Integer.parseInt(jtf4.getText());
                    MenuItem item=new BaseProduct(page,price,name,quantity);
                    boolean can=frame.getProcessing().createMenuItem(item);
                    if(can) {
                        frame.getCoresp().put(frame.getMenuTable().getRowCount(), item);
                        frame.addRow((DefaultTableModel) frame.getMenuTable().getModel(), item);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"invalid field value");
                }
            }
        });
        content.add(button);
        JPanel realContent=new JPanel();
        realContent.setLayout(new GridBagLayout());
        realContent.add(content);
        this.setContentPane(realContent);
        this.setSize(new Dimension(400, 400));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
