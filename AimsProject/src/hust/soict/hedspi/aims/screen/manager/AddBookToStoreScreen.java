package hust.soict.hedspi.aims.screen.manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.media.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = super.createCenter();

        JPanel authorsPanel = new JPanel();
        authorsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblAuthors = new JLabel("Authors (comma separated): ");
        tfAuthors = new JTextField(30);
        authorsPanel.add(lblAuthors);
        authorsPanel.add(tfAuthors);
        center.add(authorsPanel);

        JPanel btnPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToStore();
            }
        });
        btnPanel.add(addButton);
        center.add(btnPanel);

        return center;
    }

    @Override
    protected void addItemToStore() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());

            // Tạo sách mới
            Book newBook = new Book(id, title, category, cost);

            // Xử lý danh sách tác giả (nếu có)
            String authorsText = tfAuthors.getText().trim();
            if (!authorsText.isEmpty()) {
                String[] authors = authorsText.split(",");
                for (String author : authors) {
                    newBook.addAuthor(author.trim());
                }
            }

            // Thêm sách vào store
            store.addMedia(newBook);

            JOptionPane.showMessageDialog(this, "Book added successfully!");

            // Xóa nội dung các trường nhập liệu
            tfId.setText("");
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfAuthors.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}