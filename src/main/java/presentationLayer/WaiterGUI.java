package presentationLayer;

import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import businessLayer.RestaurantProcessing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WaiterGUI extends JFrame {
    private RestaurantProcessing processing;
    private JTable ordersTable;
    private WaiterGUI frame;
    private HashMap<Integer, Order> coresp;
    public WaiterGUI(final RestaurantProcessing processing){
        coresp=new HashMap<Integer, Order>();
        frame=this;
        this.processing=processing;
        this.pack();
        ordersTable=createTable();

        TablePanel menuPanel=new TablePanel(ordersTable,"ORDERS");
        JPanel upperBar=new JPanel();
        JPanel lowerBar=new JPanel();
        lowerBar.setLayout(new BoxLayout(lowerBar,BoxLayout.Y_AXIS));
        upperBar.setLayout(new GridLayout(1,2));
        JScrollPane scrollPane=new JScrollPane(menuPanel);
        scrollPane.setWheelScrollingEnabled(true);
        menuPanel.getAddBase().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddOrder(frame);
            }
        });
        menuPanel.getAddComposite().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int size=frame.getOrdersTable().getRowCount();
                boolean exist=false;
                for(int i=0;i<size;i++){
                    if((Boolean)frame.getOrdersTable().getValueAt(i,4)){
                        exist=true;
                    }
                }
                if(exist==false){
                    JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
                    return;
                }
                HashMap<Order, ArrayList<MenuItem>> orders=(HashMap<Order, ArrayList<MenuItem>>) ((Restaurant)frame.getProcessing()).getOrders();
                for(int i=0;i<size;i++){
                    if((Boolean)frame.getOrdersTable().getValueAt(i,4)) {
                        Order ord=coresp.get(i);
                        ArrayList<MenuItem> ordered= orders.get(ord);
                        processing.generateBill(ord,ordered);
                    }
                }
            }
        });


        this.setTitle("Waiter");
        this.setContentPane(scrollPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(dim.width/2,dim.height/2));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public JTable createTable() {
        String[] columnNames = new String[]{"ID","Date","table","Info","Check"};
        DefaultTableModel tableModel = new MyTableModel(columnNames);
        JTable table = new JTable(tableModel);
        MultiLineCellEditor renderer=new MultiLineCellEditor();
        table.setDefaultRenderer(String.class, renderer);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        HashMap<Order,ArrayList<MenuItem>> orders=(HashMap<Order, ArrayList<MenuItem>>) ((Restaurant)processing).getOrders();
        for (Map.Entry<Order,ArrayList<MenuItem>> entry:orders.entrySet()) {
            Order order=entry.getKey();
            addRow(table,entry.getKey(),entry.getValue(),entry.getValue().size());
        }
        table.setDefaultEditor(Object.class, null);
        return table;
    }
    public void addRow (JTable table , Order order,ArrayList<MenuItem> items,int rowSize){
        DefaultTableModel model=(DefaultTableModel)table.getModel();
        ArrayList<Object> oneRowData = new ArrayList<Object>();
        oneRowData.add(0,order.getOrderID());
        oneRowData.add(1,order.getStringDate());
        oneRowData.add(2,order.getTable());
        StringBuilder stringBuilder=new StringBuilder();
        ArrayList<MenuItem> ordered=items;
        for(int j=0;j<ordered.size();j++){
            stringBuilder.append(ordered.get(j).getName());
            stringBuilder.append("\n");
        }
        oneRowData.add(3,stringBuilder.toString());
        oneRowData.add(4,false);
        Object[] oneRowDataArray = new Object[oneRowData.size()];
        oneRowDataArray=oneRowData.toArray(oneRowDataArray);
        model.addRow(oneRowDataArray);
        coresp.put(model.getRowCount()-1,order);
        table.setRowHeight(model.getRowCount()-1,rowSize*16);
    }

    public RestaurantProcessing getProcessing() {
        return processing;
    }

    public JTable getOrdersTable() {
        return ordersTable;
    }

    public WaiterGUI getFrame() {
        return frame;
    }

    public HashMap<Integer, Order> getCoresp() {
        return coresp;
    }
}

