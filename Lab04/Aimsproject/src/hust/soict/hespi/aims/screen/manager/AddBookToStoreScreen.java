package hust.soict.hespi.aims.screen.manager;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.Book;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    private static final long serialVersionUID = 1L;
    private JTextField tfAuthors; // Trường riêng cho Book

    public AddBookToStoreScreen(JFrame owner, Store store) {
        super(owner, store, "Add New Book"); // Gọi constructor cha

        // --- Thêm trường riêng cho Book ---
        tfAuthors = new JTextField(30);
        gbc.gridx = 0; gbc.gridy = 3; // Hàng tiếp theo trong GridBagLayout
        formPanel.add(new JLabel("Authors (comma-separated):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(tfAuthors, gbc);

        // Điều chỉnh lại kích thước dialog nếu cần để chứa thêm trường
        pack(); // Tự động điều chỉnh kích thước dựa trên nội dung
        setLocationRelativeTo(owner); // Căn giữa lại sau khi pack()
    }

    @Override
    protected void addMediaItem() {
        // Lấy dữ liệu từ các trường (chung và riêng)
        String title = tfTitle.getText().trim();
        String category = tfCategory.getText().trim();
        String costStr = tfCost.getText().trim();
        String authorsStr = tfAuthors.getText().trim(); // Lấy authors

        // --- Validation ---
        if (title.isEmpty() || category.isEmpty() || costStr.isEmpty() || authorsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
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

        List<String> authors = new ArrayList<>();
        for (String author : authorsStr.split("\\s*,\\s*")) {
            if (!author.trim().isEmpty()) {
                authors.add(author.trim());
            }
        }
        if (authors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one valid author.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- Tạo và Thêm vào Store ---
        try {
            int newId = store.generateId();
            Book newBook = new Book(newId, title, category, cost);
            for (String author : authors) {
                newBook.addAuthor(author);
            }

            store.addMedia(newBook); // Thêm vào store
            JOptionPane.showMessageDialog(this, "Book added successfully!\nID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);

            // Xóa các trường và đóng dialog
            clearCommonFields();
            tfAuthors.setText("");
            dispose(); // Đóng dialog sau khi thêm thành công

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while adding the book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}