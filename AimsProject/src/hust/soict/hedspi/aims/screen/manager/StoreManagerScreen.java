package hust.soict.hedspi.aims.screen.manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.*;

public class StoreManagerScreen extends JFrame {
    private Store store;

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Phương thức tạo thanh menu
    private JMenuBar createMenuBar() {
        // Menu chính "Options"
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            // Refresh the current screen
            dispose();
            new StoreManagerScreen(store);
        });
        menu.add(viewStoreItem);

        // Sub-menu "Update Store"
        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store);
        });

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store);
        });

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(smUpdateStore);

        // Thanh menuBar và thêm menu vào
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    /** Tạo khu vực center (Grid 3×3) hiển thị 9 phần tử từ store */
    private JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();

        // Kiểm tra xem danh sách có phần tử nào không
        if (mediaInStore != null && !mediaInStore.isEmpty()) {
            int size = Math.min(9, mediaInStore.size());

            for (int i = 0; i < size; i++) {
                MediaStore cell = new MediaStore(mediaInStore.get(i));
                center.add(cell);
            }

            // Điền vào các ô còn lại nếu có ít hơn 9 phần tử
            for (int i = size; i < 9; i++) {
                center.add(new JPanel()); // Panel trống
            }
        } else {
            // Nếu không có phần tử nào, tạo 9 panel trống
            for (int i = 0; i < 9; i++) {
                center.add(new JPanel());
            }
        }

        return center;
    }

    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo Store
            Store store = new Store();

            try {
                // Tạo các đối tượng mẫu để hiển thị ban đầu
                // DVD
                DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
                DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);

                // CD
                CompactDisc cd1 = new CompactDisc(3, "Greatest Hits", "Music", 15.95f, "Michael Jackson", "Epic Records");

                // Book
                Book book1 = new Book(4, "Harry Potter", "Fantasy", 29.95f);

                // Thêm vào store
                store.addMedia(dvd1);
                store.addMedia(dvd2);
                store.addMedia(cd1);
                store.addMedia(book1);

                // In thông tin về store để debug
                System.out.println("Initial store items:");
                store.printStore();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error initializing the store: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Khởi động màn hình quản lý store
            new StoreManagerScreen(store);
        });
    }
}