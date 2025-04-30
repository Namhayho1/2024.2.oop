package hust.soict.hespi.aims.screen.manager;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.CompactDisc;
import hust.soict.hespi.aims.media.Track;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    private static final long serialVersionUID = 1L;
    private JTextField tfArtist; // Trường riêng
    private JTextArea taTracks; // Trường riêng
    private JScrollPane trackScrollPane;

    public AddCompactDiscToStoreScreen(JFrame owner, Store store) {
        super(owner, store, "Add New Compact Disc");

        // --- Thêm trường riêng cho CD ---
        tfArtist = new JTextField(30);
        taTracks = new JTextArea(5, 30);
        taTracks.setLineWrap(true);
        taTracks.setWrapStyleWord(true);
        trackScrollPane = new JScrollPane(taTracks);

        // Thêm Artist
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Artist:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(tfArtist, gbc);

        // Thêm Tracks
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTH;
        formPanel.add(new JLabel("Tracks (Title,Length):"), gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 1; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        formPanel.add(trackScrollPane, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0; gbc.weighty = 0.0;

        // Thêm label hướng dẫn
        gbc.gridx = 1; gbc.gridy = 5;
        JLabel trackFormatLabel = new JLabel("<html><i>Enter each track on a new line.<br>Format: Track Title, Length (in seconds)</i></html>");
        trackFormatLabel.setFont(trackFormatLabel.getFont().deriveFont(Font.ITALIC, 10f));
        formPanel.add(trackFormatLabel, gbc);

        // Điều chỉnh kích thước dialog
        pack();
        setSize(getWidth(), getHeight() + 60); // Tăng chiều cao một chút cho JTextArea
        setLocationRelativeTo(owner);
    }

    @Override
    protected void addMediaItem() {
        // Lấy dữ liệu
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();
        String costStr = tfCost.getText().trim();
        String artist = tfArtist.getText().trim();
        String tracksInput = taTracks.getText().trim();

        // --- Validation (chung và riêng) ---
        if (title.isEmpty() || category.isEmpty() || artist.isEmpty() || costStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Title, Category, Artist, and Cost.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        float cost;
        try {
            cost = Float.parseFloat(costStr);
            if (cost < 0) {
                JOptionPane.showMessageDialog(this, "Cost cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Track> tracksList = new ArrayList<>();
        if (!tracksInput.isEmpty()) {
            String[] lines = tracksInput.split("\\r?\\n");
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;
                int lastCommaIndex = line.lastIndexOf(',');
                if (lastCommaIndex == -1 || lastCommaIndex == 0 || lastCommaIndex == line.length() - 1) {
                    JOptionPane.showMessageDialog(this, "Invalid format on track line " + (i + 1) + ": '" + line + "'.\nExpected format: Title,Length", "Track Format Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String trackTitle = line.substring(0, lastCommaIndex).trim();
                String lengthStr = line.substring(lastCommaIndex + 1).trim();
                if (trackTitle.isEmpty()) {
                     JOptionPane.showMessageDialog(this, "Track title cannot be empty on line " + (i + 1) + ": '" + line + "'", "Track Format Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    int trackLength = Integer.parseInt(lengthStr);
                    if (trackLength <= 0) {
                        JOptionPane.showMessageDialog(this, "Track length must be positive on line " + (i + 1) + ": '" + line + "'", "Track Format Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    tracksList.add(new Track(trackTitle, trackLength));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Invalid length format on track line " + (i + 1) + ": '" + line + "'. Length must be a number.", "Track Format Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        // --- Tạo và Thêm vào Store ---
         try {
            int newId = store.generateId();
            // Gọi đúng constructor CompactDisc, truyền null cho director
            CompactDisc newCd = new CompactDisc(newId, title, category, cost, null, artist);
            for (Track track : tracksList) {
                newCd.addTrack(track);
            }

            store.addMedia(newCd);
            JOptionPane.showMessageDialog(this, "CD added successfully!\nID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Xóa các trường và đóng dialog
            clearCommonFields();
            tfArtist.setText("");
            taTracks.setText("");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while adding the CD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}