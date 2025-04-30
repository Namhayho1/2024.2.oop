package hust.soict.hespi.aims.screen.manager;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Book; // Vẫn cần import để biết kiểu
import hust.soict.hespi.aims.media.CompactDisc;
import hust.soict.hespi.aims.media.DigitalVideoDisc;
import hust.soict.hespi.aims.media.Playable;
import hust.soict.hespi.aims.media.Track;
import hust.soict.hespi.aims.media.MediaComparatorByCostTitle; // Import Comparator
import hust.soict.hespi.aims.media.MediaComparatorByTitleCost;// Import Comparator


import javax.swing.*;
import java.awt.*;
// Bỏ import ActionListener vì dùng lambda
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreManagerScreen extends JFrame {

    private static final long serialVersionUID = 1L;

    private Store store;
    private JPanel centerPanel;
    private JScrollPane scrollPane;

    public StoreManagerScreen(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Store cannot be null.");
        }
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        viewStore(); // Hiển thị view store ban đầu
        setTitle("Store Manager");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JPanel createHeader() {
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

    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> viewStore());
        menuOptions.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        // ====> SỬA ACTION LISTENERS Ở ĐÂY <====
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            // Tạo và hiển thị dialog AddBookToStoreScreen
            AddBookToStoreScreen addBookDialog = new AddBookToStoreScreen(this, store);
            addBookDialog.setVisible(true);
            // Sau khi dialog đóng, cập nhật lại view store
            viewStore();
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCdItem = new JMenuItem("Add CD");
        addCdItem.addActionListener(e -> {
            // Tạo và hiển thị dialog AddCompactDiscToStoreScreen
            AddCompactDiscToStoreScreen addCdDialog = new AddCompactDiscToStoreScreen(this, store);
            addCdDialog.setVisible(true);
            // Sau khi dialog đóng, cập nhật lại view store
            viewStore();
        });
        smUpdateStore.add(addCdItem);

        JMenuItem addDvdItem = new JMenuItem("Add DVD");
        addDvdItem.addActionListener(e -> {
            // Tạo và hiển thị dialog AddDigitalVideoDiscToStoreScreen
            AddDigitalVideoDiscToStoreScreen addDvdDialog = new AddDigitalVideoDiscToStoreScreen(this, store);
            addDvdDialog.setVisible(true);
            // Sau khi dialog đóng, cập nhật lại view store
            viewStore();
        });
        smUpdateStore.add(addDvdItem);

        menuOptions.add(smUpdateStore);
        menuBar.add(menuOptions);
        return menuBar;
    }

    JPanel createViewStorePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3, 5, 5));
        List<Media> mediaInStore = store.getItemsInStore();
        if (mediaInStore != null) {
             // Kiểm tra xem Comparator đã được khởi tạo chưa trước khi sort
             if (Media.COMPARE_BY_TITLE_COST != null) {
                 mediaInStore.sort(Media.COMPARE_BY_TITLE_COST);
             } else {
                 System.err.println("Warning: Media.COMPARE_BY_TITLE_COST is null. Skipping sort.");
             }

            for (Media media : mediaInStore) {
                MediaStore cell = new MediaStore(media, store);
                panel.add(cell);
            }
        }
        return panel;
    }

    void viewStore() {
        changeCenterPanel(createViewStorePanel());
    }

    void changeCenterPanel(JPanel newCenterPanel) {
        Container cp = getContentPane();
        if (scrollPane != null) {
            cp.remove(scrollPane);
        }
        this.centerPanel = newCenterPanel;
        this.scrollPane = new JScrollPane(centerPanel);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cp.add(scrollPane, BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
    }

    // --- XÓA CÁC HÀM createAddBookPanel, createAddCdPanel, createAddDvdPanel ---
    // JPanel createAddBookPanel() { ... } // Xóa hàm này
    // JPanel createAddCdPanel() { ... } // Xóa hàm này
    // JPanel createAddDvdPanel() { ... } // Xóa hàm này


    // --- Inner class MediaStore giữ nguyên ---
    class MediaStore extends JPanel {
        private static final long serialVersionUID = 1L;
        private Media media;
        private Store store; // Giữ lại nếu cần cho các action khác

        public MediaStore(Media media, Store store) {
            this.media = media;
            this.store = store; // Lưu store
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel(media.getTitle());
            titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 14));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel costLabel = new JLabel(String.format("%.2f $", media.getCost()));
            costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(Box.createVerticalGlue());
            this.add(titleLabel);
            this.add(costLabel);
            this.add(Box.createVerticalStrut(5));

            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            if (media instanceof Playable) {
                JButton playButton = new JButton("Play");
                playButton.addActionListener(e -> {
                    JDialog playDialog = createPlayDialog((Playable) media);
                    playDialog.setVisible(true);
                });
                buttonsPanel.add(playButton);
            }

             // TODO: Thêm nút "Remove" vào MediaStore
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove '" + media.getTitle() + "'?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    store.removeMediaById(media.getId()); // Giả sử Store có hàm remove theo ID
                    // Cập nhật lại view store sau khi xóa
                    // Cách đơn giản là gọi lại viewStore() từ JFrame cha
                    // Cần tìm cách truy cập JFrame cha hoặc dùng listener pattern
                    // ((StoreManagerScreen) SwingUtilities.getWindowAncestor(this)).viewStore(); // Cách lấy JFrame cha

                    // Hoặc đơn giản hơn là gọi lại viewStore trực tiếp nếu MediaStore là inner class
                     viewStore(); // Gọi hàm của lớp ngoài
                }
            });
            buttonsPanel.add(removeButton);


            this.add(buttonsPanel);
            this.add(Box.createVerticalGlue());

            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setPreferredSize(new Dimension(200, 150));
        }

         private JDialog createPlayDialog(Playable playableMedia) {
            JDialog dialog = new JDialog(StoreManagerScreen.this, "Playing Media", true);
            dialog.setSize(350, 250);
            dialog.setLocationRelativeTo(StoreManagerScreen.this);

            Container dialogCp = dialog.getContentPane();
            dialogCp.setLayout(new BorderLayout(10, 10));

            JTextArea displayArea = new JTextArea();
            displayArea.setEditable(false);
            displayArea.setLineWrap(true);
            displayArea.setWrapStyleWord(true);
            displayArea.setMargin(new Insets(5,5,5,5));

            StringBuilder sb = new StringBuilder();
            sb.append("Playing: ").append(media.getTitle()).append("\n");
            sb.append("-----------------------------\n");

             if (playableMedia instanceof CompactDisc) {
                CompactDisc cd = (CompactDisc) playableMedia;
                sb.append("Artist: ").append(cd.getArtist() != null ? cd.getArtist() : "N/A").append("\n");
                sb.append("Total Length: ").append(cd.getLength()).append(" seconds\n");
                sb.append("Tracks:\n");
                if (cd.getTracks() != null && !cd.getTracks().isEmpty()) {
                    int trackNum = 1;
                    for (Track track : cd.getTracks()) {
                        sb.append("  ").append(trackNum++).append(". ").append(track.getTitle()).append(" (").append(track.getLength()).append("s)\n");
                    }
                } else {
                    sb.append("  (No tracks listed)\n");
                }
            } else if (playableMedia instanceof DigitalVideoDisc) {
                DigitalVideoDisc dvd = (DigitalVideoDisc) playableMedia;
                sb.append("Director: ").append(dvd.getDirector() != null ? dvd.getDirector() : "N/A").append("\n");
                sb.append("Length: ").append(dvd.getLength()).append(" minutes\n");
            } else if (playableMedia instanceof Track) {
                Track track = (Track) playableMedia;
                 sb.append("Length: ").append(track.getLength()).append(" seconds\n");
            }

            sb.append("-----------------------------\n");
            sb.append("(Simulating playback - Check console output for details)");

            displayArea.setText(sb.toString());
            dialogCp.add(new JScrollPane(displayArea), BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dialog.dispose());
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
            buttonPanel.add(closeButton);
            dialogCp.add(buttonPanel, BorderLayout.SOUTH);

            try {
                System.out.println("\n--- Attempting to play: " + media.getTitle() + " ---");
                playableMedia.play();
                System.out.println("--- Finished simulating play for: " + media.getTitle() + " ---\n");
            } catch (Exception ex) {
                 displayArea.append("\n\nError during playback simulation:\n" + ex.getMessage());
                 System.err.println("### Error calling play() for " + media.getTitle() + ": " + ex.getMessage() + " ###");
                 ex.printStackTrace();
            }

            return dialog;
        }
    }

    // --- Phương thức main giữ nguyên ---
     public static void main(String[] args) {
        Store myStore = new Store();

        // Thêm dữ liệu mẫu (đã sửa constructor CD)
        int id1 = myStore.generateId(); Book book1 = new Book(id1, "The Lord of the Rings", "Fantasy", 19.99f); book1.addAuthor("J.R.R. Tolkien"); myStore.addMedia(book1);
        int id2 = myStore.generateId(); DigitalVideoDisc dvd1 = new DigitalVideoDisc(id2, "Inception", "Sci-Fi", "Christopher Nolan", 148, 15.50f); myStore.addMedia(dvd1);
        int id3 = myStore.generateId(); CompactDisc cd1 = new CompactDisc(id3, "Dark Side of the Moon", "Rock", 12.80f, null, "Pink Floyd"); cd1.addTrack(new Track("Speak to Me/Breathe", 233)); cd1.addTrack(new Track("Time", 413)); cd1.addTrack(new Track("Money", 382)); myStore.addMedia(cd1);
        int id4 = myStore.generateId(); Book book2 = new Book(id4, "Dune", "Sci-Fi", 18.00f); book2.addAuthor("Frank Herbert"); myStore.addMedia(book2);
        int id5 = myStore.generateId(); DigitalVideoDisc dvd2 = new DigitalVideoDisc(id5, "Interstellar", "Sci-Fi", "Christopher Nolan", 169, 16.00f); myStore.addMedia(dvd2);
        int id6 = myStore.generateId(); Book book3 = new Book(id6, "Pride and Prejudice", "Romance", 9.99f); book3.addAuthor("Jane Austen"); myStore.addMedia(book3);
        int id7 = myStore.generateId(); CompactDisc cd2 = new CompactDisc(id7, "Abbey Road", "Rock", 14.50f, null, "The Beatles"); cd2.addTrack(new Track("Come Together", 260)); cd2.addTrack(new Track("Something", 182)); cd2.addTrack(new Track("Here Comes the Sun", 185)); myStore.addMedia(cd2);

        System.out.println("Initial Store Content:");
        myStore.printStore();
        System.out.println("\nLaunching Store Manager Screen...");

        SwingUtilities.invokeLater(() -> {
            new StoreManagerScreen(myStore); // Chú ý: Tên lớp đúng là StoreManagerScreen
        });
    }
}