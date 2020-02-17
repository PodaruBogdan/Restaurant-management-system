package presentationLayer;
import businessLayer.*;
import businessLayer.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class AdministratorGUI extends JFrame  {
    private RestaurantProcessing processing;
    private JTable menuTable;
    private AdministratorGUI frame;
    private HashMap<Integer,MenuItem> coresp;
    public AdministratorGUI(final RestaurantProcessing processing){
        coresp=new HashMap<Integer, MenuItem>();
        frame=this;
        this.processing=processing;
        this.pack();
        menuTable=createTable();

        TablePanel menuPanel=new TablePanel(menuTable,"MENU");
        JPanel upperBar=new JPanel();
        JPanel lowerBar=new JPanel();
        lowerBar.setLayout(new BoxLayout(lowerBar,BoxLayout.Y_AXIS));
        upperBar.setLayout(new GridLayout(1,2));
        JScrollPane scrollPane=new JScrollPane(menuPanel);
        scrollPane.setWheelScrollingEnabled(true);
        menuPanel.getAddBase().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBase(frame);
            }
        });
        menuPanel.getAddComposite().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddComposite(frame);
            }
        });
        menuPanel.getEdit().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size=frame.getMenuTable().getRowCount();
                int nr=0;
                for(int i=0;i<size;i++){
                    if((Boolean)frame.getMenuTable().getValueAt(i,4)){
                        nr++;
                        if(nr>1){
                            JOptionPane.showMessageDialog(null,"Please select exactly 1 item to edit");
                            return;
                        }
                    }
                }
                if(nr==0){
                    JOptionPane.showMessageDialog(null,"Please select exactly 1 item to edit");
                    return;
                }
                int j=0;
                for(int i=0;i<size;i++){
                    if((Boolean)frame.getMenuTable().getValueAt(i,4)){
                        j=i;
                        break;
                    }
                }
                MenuItem item=frame.getCoresp().get(j);
                new EditPopUp(frame,item,j);
            }
        });
        menuPanel.getRmv().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete the selected items?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    for(int i=0;i<size;i++){
                        if((Boolean)frame.getMenuTable().getValueAt(i,4)) {
                            MenuItem item = coresp.get(i);
                            ((DefaultTableModel) menuTable.getModel()).removeRow(i);
                            processing.deleteMenuItem(item);
                            coresp.remove(i);
                            for(int j=i+1;j<size;j++){
                                MenuItem aux=coresp.get(j);
                                coresp.put(j-1,aux);
                                coresp.remove(j);
                            }
                            i=0;
                            size--;
                        }
                    }
                }
            }
        });

        this.setTitle("Admin");
        this.setContentPane(scrollPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(dim.width/2,dim.height/2));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public JTable createTable() {
        String[] columnNames = new String[]{"Name","Composed by","Quantity","Price","Check"};
        DefaultTableModel tableModel = new MyTableModel(columnNames);
        JTable table = new JTable(tableModel);
        for (Iterator<MenuItem> it=((Restaurant)processing).getMenu().iterator();it.hasNext();) {
            MenuItem item=it.next();
            coresp.put(tableModel.getRowCount(),item);
            addRow(tableModel,item);
        }
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        return table;
    }
    public void addRow (DefaultTableModel model ,MenuItem item){
        ArrayList<Object> oneRowData = new ArrayList<Object>();
        oneRowData.add(0,item.getName());
        String[] composed=item.getComposedBy();
        StringBuilder stringBuilder=new StringBuilder();
        for(int j=0;j<composed.length;j++){
            stringBuilder.append(composed[j]);
            stringBuilder.append(",");
        }
        oneRowData.add(1,stringBuilder.toString());
        oneRowData.add(2,item.getQuantity());
        oneRowData.add(3,item.computePrice());
        oneRowData.add(4,false);
        Object[] oneRowDataArray = new Object[oneRowData.size()];
        oneRowDataArray=oneRowData.toArray(oneRowDataArray);
        model.addRow(oneRowDataArray);
    }

    public RestaurantProcessing getProcessing() {
        return processing;
    }

    public JTable getMenuTable() {
        return menuTable;
    }

    public HashMap<Integer, MenuItem> getCoresp() {
        return coresp;
    }


}
