package presentationLayer;
import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditPopUp extends JFrame implements ActionListener{
    private JPanel realContent;
    private MenuItem item;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
    private int i;
    private JComboBox comboBox;
    public EditPopUp(final AdministratorGUI frame, final MenuItem item, final int i) {
        this.item=item;
        this.i=i;
        this.setTitle("Edit panel");
        this.pack();
        final JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JButton button = new JButton("edit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(item instanceof BaseProduct) {
                    int page = Integer.parseInt(jtf1.getText());
                    float price=Float.parseFloat(jtf2.getText());
                    String name=jtf3.getText();
                    int quantity=Integer.parseInt(jtf4.getText());
                    MenuItem newItem=new BaseProduct(page,price,name,quantity);
                    frame.getProcessing().deleteMenuItem(item);
                    frame.getProcessing().createMenuItem(newItem);
                    ((DefaultTableModel) frame.getMenuTable().getModel()).removeRow(i);
                    frame.addRow((DefaultTableModel) frame.getMenuTable().getModel(),newItem);
                    frame.getCoresp().put(i,newItem);
                }
                else if(item instanceof CompositeProduct){
                    String name=jtf5.getText();
                    int quantity=Integer.parseInt(jtf6.getText());
                    CompositeProduct newItem=new CompositeProduct(name,quantity);
                    for(MenuItem it:((CompositeProduct)item).getComponents()){
                        newItem.addComponent(it);
                    }

                    frame.getProcessing().editMenuItem(item,newItem);
                    ((DefaultTableModel) frame.getMenuTable().getModel()).removeRow(i);
                    frame.addRow((DefaultTableModel) frame.getMenuTable().getModel(),newItem);
                    frame.getCoresp().put(i,newItem);
                }
            }
        });
        if(item instanceof BaseProduct){
            JPanel line1=new JPanel();
            line1.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel line2=new JPanel();
            line2.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel line3=new JPanel();
            line3.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel line4=new JPanel();
            line4.setLayout(new FlowLayout(FlowLayout.LEFT));
            line1.add(new JLabel("page : "));
            jtf1=new JTextField(5);
            line1.add(jtf1);
            line2.add(new JLabel("price : "));
            jtf2=new JTextField(5);
            line2.add(jtf2);
            line3.add(new JLabel("name : "));
            jtf3=new JTextField(15);
            line3.add(jtf3);
            line4.add(new JLabel("quantity : "));
            jtf4=new JTextField(5);
            line4.add(jtf4);
            content.add(line1);
            content.add(line2);
            content.add(line3);
            content.add(line4);
            jtf1.setText(""+((BaseProduct)item).getPage());
            jtf2.setText(""+((BaseProduct)item).computePrice());
            jtf3.setText(""+((BaseProduct)item).getName());
            jtf4.setText(""+((BaseProduct)item).getQuantity());
        }else if(item instanceof CompositeProduct){
            JPanel line5=new JPanel();
            line5.setLayout(new FlowLayout(FlowLayout.LEFT));
            JPanel line6=new JPanel();
            line6.setLayout(new FlowLayout(FlowLayout.LEFT));
            line5.add(new JLabel("name : "));
            jtf5=new JTextField(15);
            line5.add(jtf5);
            line6.add(new JLabel("quantity : "));
            jtf6=new JTextField(5);
            line6.add(jtf6);
            content.add(line5);
            content.add(line6);
            jtf5.setText(""+((CompositeProduct)item).getName());
            jtf6.setText(""+((CompositeProduct)item).getQuantity());
        }
        content.add(button);
        realContent = new JPanel();
        realContent.setLayout(new GridBagLayout());
        realContent.add(content);
        Timer t=new Timer(100,this);
        this.setContentPane(realContent);
        this.setSize(new Dimension(400, 400));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        this.setContentPane(realContent);
    }
}
