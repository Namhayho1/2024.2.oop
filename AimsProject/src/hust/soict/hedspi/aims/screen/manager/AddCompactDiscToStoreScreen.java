package hust.soict.hedspi.aims.screen.manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import hust.soict.hedspi.aims.media.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = super.createCenter();

        JPanel artistPanel = new JPanel();
        artistPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblArtist = new JLabel("Artist: ");
        tfArtist = new JTextField(30);
        artistPanel.add(lblArtist);
        artistPanel.add(tfArtist);
        center.add(artistPanel);

        JPanel directorPanel = new JPanel();
        directorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDirector = new JLabel("Director: ");
        tfDirector = new JTextField(30);
        directorPanel.add(lblDirector);
        directorPanel.add(tfDirector);
        center.add(directorPanel);

        JPanel lengthPanel = new JPanel();
        lengthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblLength = new JLabel("Length: ");
        tfLength = new JTextField(10);
        lengthPanel.add(lblLength);
        lengthPanel.add(tfLength);
        center.add(lengthPanel);

        JPanel btnPanel = new JPanel();
        JButton addButton = new JButton("Add CD");
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
            String artist = tfArtist.getText();
            String director = tfDirector.getText();
            int length = 0;

            if (!tfLength.getText().isEmpty()) {
                length = Integer.parseInt(tfLength.getText());
            }

            // Tạo CD mới
            CompactDisc newCD = new CompactDisc(id, title, category, cost, director, artist);

            // Thêm CD vào store
            store.addMedia(newCD);

            JOptionPane.showMessageDialog(this, "CD added successfully!");

            // Xóa nội dung các trường nhập liệu
            tfId.setText("");
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfArtist.setText("");
            tfDirector.setText("");
            tfLength.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding CD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}