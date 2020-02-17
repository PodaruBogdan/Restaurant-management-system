package presentationLayer;

import businessLayer.CompositeProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComposite extends JFrame {
    private JTextField jtf1,jtf2;
    public AddComposite(final AdministratorGUI frame) {
        this.setTitle("Add base product");
        this.pack();

        JPanel content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        JPanel line1=new JPanel();
        line1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel line2=new JPanel();
        line2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jtf1=new JTextField(15);
        line1.add(new JLabel("name : "));
        line1.add(jtf1);
        jtf2=new JTextField(5);
        line2.add(new JLabel(" quantity : "));
        line2.add(jtf2);


        content.add(line1);
        content.add(line2);
        JButton button=new JButton("add");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check for checkboxes here
                int size=frame.getMenuTable().getRowCount();
                boolean exist=false;
                for(int i=0;i<size;i++){
                    if((Boolean)frame.getMenuTable().getValueAt(i,4)){
                        exist=true;
                    }
                }
                if(exist==false){
                    JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
                    return;
                }
                try {
                    String name=jtf1.getText();
                    int quantity=Integer.parseInt(jtf2.getText());
                    CompositeProduct item=new CompositeProduct(name,quantity);
                    for(int i=0;i<size;i++){
                        if((Boolean)frame.getMenuTable().getValueAt(i,4)){
                            item.addComponent(frame.getCoresp().get(i));
                        }
                    }
                    boolean can=frame.getProcessing().createMenuItem(item);
                    if(can) {
                        frame.addRow((DefaultTableModel) frame.getMenuTable().getModel(), item);
                        frame.getCoresp().put(frame.getMenuTable().getRowCount(), item);
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
