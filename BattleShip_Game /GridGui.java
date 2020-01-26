import java.awt.*;
import javax.swing.*;


public class GridGui extends JFrame {

    public GridGui(){
        createview();
        setTitle("Battleships");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JLabel name,ship1,ship2;
    JButton submit_name,submit_1,submit_2;


    public void createview() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.getContentPane().add(panel);

        JPanel center = new JPanel(new BorderLayout());
        panel.add(center,BorderLayout.CENTER);

        JPanel north = new JPanel(new BorderLayout());
        panel.add(north,BorderLayout.NORTH);

        JPanel south = new JPanel(new BorderLayout());
        panel.add(south,BorderLayout.SOUTH);

        name = new JLabel();
        name.setPreferredSize(new Dimension(200,30));
        name.setText("Enter your name:");
        north.add(name,BorderLayout.WEST);

        JTextField name_input = new JTextField();
        name_input.setPreferredSize(new Dimension(100,20));
        north.add(name_input,BorderLayout.CENTER);

        submit_name = new JButton("Submit");
        north.add(submit_name,BorderLayout.EAST);

        ship1 = new JLabel();
        ship1.setPreferredSize(new Dimension(200,30));
        ship1.setText("Enter Coordinates for ship 1: ");
        center.add(ship1,BorderLayout.WEST);

        JTextField submit_11 = new JTextField();
        submit_11.setPreferredSize(new Dimension(100,20));
        center.add(submit_11,BorderLayout.CENTER);

        submit_1 = new JButton("Submit");
        center.add(submit_1,BorderLayout.EAST);

        ship2 = new JLabel();
        ship2.setPreferredSize(new Dimension(200,30));
        ship2.setText("Enter Coordinates for ship 2: ");
        south.add(ship2,BorderLayout.WEST);

        JTextField submit_12 = new JTextField();
        submit_12.setPreferredSize(new Dimension(100,20));
        south.add(submit_12,BorderLayout.CENTER);

        submit_2 = new JButton("Submit");
        south.add(submit_2,BorderLayout.EAST);


    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GridGui();
            }
        });
    }
}