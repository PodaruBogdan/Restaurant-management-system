package presentationLayer;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TablePanel extends JPanel {
    private String name;
    private JTable table;
    private JButton add1;
    private JButton add2;
    private JButton edit;
    private JButton rmv;

    /**<p>public TablePanel(JTable table,String name,boolean setOrder)</p>
     * Sets the content of a JPanel with a JTable given as its argument , a name(for identification)
     * and a speficier for rather to render or not the order button.
     * Each table from the database can use this template for the application.
     * @param table - the table to be inserted
     * @param name - the name for identification
     */
    public TablePanel(JTable table,String name){
        table.getTableHeader().setBackground(Color.yellow);
        table.setBackground(Color.cyan);

        this.setBackground(Color.lightGray);
        this.name=name;
        this.table=table;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10,10,10,10));

        JPanel header=new JPanel();
        header.setBackground(Color.white);
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel title=new JLabel(name);
        title.setFont(new Font("Arial",Font.ITALIC,20));
        header.add(title);

        final JPanel body=new JPanel();
        body.setLayout(new BoxLayout(body,BoxLayout.Y_AXIS));
        body.add(table.getTableHeader());
        body.add(table);

        JPanel footer=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.white);

        if(name.equals("MENU")) {
            add1 = new JButton("add base");
            add2 = new JButton("add composite");
            edit = new JButton("edit");
            rmv = new JButton("remove");
            footer.add(add1);
            footer.add(add2);
            footer.add(edit);
            footer.add(rmv);
        }else if(name.equals("ORDERS")){
            add1 = new JButton("add order");
            add2 = new JButton("bill");
            footer.add(add1);
            footer.add(add2);
        }


        this.add(header);
        this.add(body);
        this.add(footer);

    }
    public void setName(String newName){
        this.name=newName;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getAddBase() {
        return add1;
    }
    public JButton getAddComposite() {
        return add2;
    }

    public JButton getRmv() {
        return rmv;
    }

    public JButton getEdit() {
        return edit;
    }

}
