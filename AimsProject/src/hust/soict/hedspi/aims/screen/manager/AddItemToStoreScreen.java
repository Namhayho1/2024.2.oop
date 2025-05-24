package hust.soict.hedspi.aims.screen.manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.media.*;


public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;
    protected JTextField tfId;

    public AddItemToStoreScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Add Item to Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển đến màn hình View Store
                dispose();
                new StoreManagerScreen(store);
            }
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddBookToStoreScreen(store);
            }
        });

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddCompactDiscToStoreScreen(store);
            }
        });

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddDigitalVideoDiscToStoreScreen(store);
            }
        });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("ADD ITEM");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblId = new JLabel("ID: ");
        tfId = new JTextField(20);
        idPanel.add(lblId);
        idPanel.add(tfId);
        center.add(idPanel);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTitle = new JLabel("Title: ");
        tfTitle = new JTextField(50);
        titlePanel.add(lblTitle);
        titlePanel.add(tfTitle);
        center.add(titlePanel);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCategory = new JLabel("Category: ");
        tfCategory = new JTextField(20);
        categoryPanel.add(lblCategory);
        categoryPanel.add(tfCategory);
        center.add(categoryPanel);

        JPanel costPanel = new JPanel();
        costPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCost = new JLabel("Cost: ");
        tfCost = new JTextField(20);
        costPanel.add(lblCost);
        costPanel.add(tfCost);
        center.add(costPanel);

        return center;
    }

    // Phương thức trừu tượng để các lớp con triển khai
    protected abstract void addItemToStore();
}