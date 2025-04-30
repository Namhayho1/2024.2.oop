package hust.soict.hespi.aims.screen.manager;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.DigitalVideoDisc;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    private static final long serialVersionUID = 1L;
    private JTextField tfDirector; // Trường riêng
    private JTextField tfLength;   // Trường riêng

    public AddDigitalVideoDiscToStoreScreen(JFrame owner, Store store) {
        super(owner, store, "Add New DVD");

        // --- Thêm trường riêng cho DVD ---
        tfDirector = new JTextField(30);
        tfLength = new JTextField(10);

        // Thêm Director
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Director:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(tfDirector, gbc);

        // Thêm Length
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Length (minutes):"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(tfLength, gbc);

        // Điều chỉnh kích thước dialog
        pack();
        setLocationRelativeTo(owner);
    }

    @Override
    protected void addMediaItem() {
        // Lấy dữ liệu
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();
        String costStr = tfCost.getText().trim();
        String director = tfDirector.getText().trim();
        String lengthStr = tfLength.getText().trim();

        // --- Validation ---
        if (title.isEmpty() || category.isEmpty() || director.isEmpty() || lengthStr.isEmpty() || costStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        float cost;
        int length;
        try {
            cost = Float.parseFloat(costStr);
            length = Integer.parseInt(lengthStr);
            if (length <= 0 || cost < 0) {
                JOptionPane.showMessageDialog(this, "Length must be positive and Cost cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format for Length or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Tạo và Thêm vào Store ---
        try {
            int newId = store.generateId();
            DigitalVideoDisc newDvd = new DigitalVideoDisc(newId, title, category, director, length, cost);

            store.addMedia(newDvd);
            JOptionPane.showMessageDialog(this, "DVD added successfully!\nID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Xóa các trường và đóng dialog
            clearCommonFields();
            tfDirector.setText("");
            tfLength.setText("");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while adding the DVD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}