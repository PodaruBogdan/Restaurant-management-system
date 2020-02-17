package presentationLayer;
import businessLayer.*;
import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddOrder extends JFrame {
    private JTextField jtf1,jtf2;
    private HashMap<Integer,MenuItem> map;
    private JCheckBox[] checkBoxes;
    public AddOrder(final WaiterGUI frame) {
        this.setTitle("Add base product");
        this.pack();

        map=new HashMap<Integer, MenuItem>();
        JPanel content=new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        JPanel line1=new JPanel();
        line1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel line2=new JPanel();
        line2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jtf1=new JTextField(5);
        line1.add(new JLabel("id : "));
        line1.add(jtf1);
        jtf2=new JTextField(5);
        line2.add(new JLabel(" table : "));
        line2.add(jtf2);
        content.add(line1);
        content.add(line2);

        JPanel line3=new JPanel();
        line3.setLayout(new BoxLayout(line3,BoxLayout.Y_AXIS));

        HashSet<MenuItem> menu=(HashSet<MenuItem>) ((Restaurant)frame.getProcessing()).getMenu();
        int size=menu.size();
        JPanel[] panels=new JPanel[size];
        checkBoxes=new JCheckBox[size];
        int i=0;
        for(Iterator<MenuItem> it=menu.iterator();it.hasNext();) {
            panels[i] = new JPanel();
            checkBoxes[i] = new JCheckBox();
            panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            panels[i].add(checkBoxes[i]);
            MenuItem item=it.next();
            panels[i].add(new JLabel(item.getName()));
            map.put(i,item);
            line3.add(panels[i]);
            i++;
        }



        JScrollPane scrollPane=new JScrollPane(line3);
        scrollPane.setMinimumSize(new Dimension(150,200));
        content.add(scrollPane);


        JButton button=new JButton("add");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(jtf1.getText());
                    int table=Integer.parseInt(jtf2.getText());
                    Order item=new Order(id,table);
                    HashSet<MenuItem> menu=(HashSet<MenuItem>) ((Restaurant)frame.getProcessing()).getMenu();
                    int size=menu.size();
                    ArrayList<MenuItem> ordered=new ArrayList<MenuItem>();
                    for(int i=0;i<size;i++){
                        if(checkBoxes[i].isSelected()) {
                           MenuItem mi=map.get(i);
                           MenuItem clone=(MenuItem) mi.clone();
                           ordered.add(clone);
                        }
                    }
                    frame.getProcessing().createOrder(item,ordered);
                    frame.addRow(frame.getOrdersTable(),item,ordered,ordered.size());

                }catch (NumberFormatException ex){
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
