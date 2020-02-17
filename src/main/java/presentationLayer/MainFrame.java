package presentationLayer;

import businessLayer.Restaurant;
import businessLayer.RestaurantProcessing;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private ChefGUI gui;
    private RestaurantProcessing processing;
    public MainFrame(final RestaurantProcessing processing) {
        gui=new ChefGUI();
        gui.setVisible(false);
        this.processing=processing;
        this.pack();

        JPanel content=new JPanel();//content with buttons
        content.setLayout(new GridLayout(5,1));

        JButton adminButton=new JButton("Admin");
        JButton waiterButton=new JButton("Waiter");
        JButton chefButton=new JButton("Chef");

        adminButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new AdministratorGUI(processing);
            }
        });
        waiterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WaiterGUI(processing);
            }
        });
        chefButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.setVisible(true);
            }
        });

        adminButton.setPreferredSize(new Dimension(200,60));
        content.add(adminButton);
        content.add(Box.createRigidArea(new Dimension(100,1)));
        content.add(waiterButton);
        content.add(Box.createRigidArea(new Dimension(100,1)));
        content.add(chefButton);

        JPanel space=new JPanel();//for center placement
        space.setLayout(new GridBagLayout());
        space.add(content);

        this.setTitle("Home");
        this.setContentPane(space);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Ask for confirmation before terminating the program.
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to close the application?",
                        "Close Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    RestaurantSerializator.serialize(((Restaurant)processing).getMenu());
                    System.exit(0);
                }
            }
        });



        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(width/4, height/2);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public ChefGUI getGui() {
        return gui;
    }
}
