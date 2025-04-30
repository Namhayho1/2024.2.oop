package hust.soict.hespi.aims.screen.manager;

import hust.soict.hespi.aims.store.Store;
import hust.soict.hespi.aims.media.Media; // Cần thiết cho việc thêm

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lớp cha trừu tượng cho các màn hình thêm Media vào Store.
 * Chứa các trường và thành phần giao diện chung (Title, Category, Cost)
 * và nút Add. Sử dụng JDialog.
 */
public abstract class AddItemToStoreScreen extends JDialog {

    private static final long serialVersionUID = 1L; // Cho JDialog

    protected Store store; // Store để thêm media vào
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;
    protected JButton btnAdd;
    protected JButton btnCancel; // Nút hủy

    protected JPanel formPanel; // Panel chứa các trường nhập liệu
    protected GridBagConstraints gbc; // Để các lớp con có thể dùng

    /**
     * Constructor chung.
     * @param owner Frame cha (StoreManagerScreen)
     * @param store Đối tượng Store
     * @param dialogTitle Tiêu đề của Dialog
     */
    public AddItemToStoreScreen(JFrame owner, Store store, String dialogTitle) {
        super(owner, dialogTitle, true); // Gọi constructor JDialog (modal)
        this.store = store;

        // --- Setup Dialog ---
        setSize(500, 400); // Kích thước có thể điều chỉnh
        setLocationRelativeTo(owner); // Hiện giữa màn hình cha
        // setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Đóng dialog khi nhấn nút X

        // --- Tạo Components chung ---
        tfTitle = new JTextField(30);
        tfCategory = new JTextField(30);
        tfCost = new JTextField(10);

        // --- Layout ---
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(10, 10)); // Layout chính: BorderLayout

        formPanel = new JPanel(new GridBagLayout()); // Panel cho form ở giữa
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các component
        gbc.fill = GridBagConstraints.HORIZONTAL; // Giãn ngang

        // Thêm các trường chung vào formPanel
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; formPanel.add(tfTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(tfCategory, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Cost ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; formPanel.add(tfCost, gbc);

        // --- Panel chứa các nút ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAdd = new JButton("Add to Store");
        btnCancel = new JButton("Cancel");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnCancel);

        // --- Thêm form và nút vào Content Pane ---
        cp.add(formPanel, BorderLayout.CENTER);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức trừu tượng để lớp con xử lý việc thêm
                addMediaItem();
            }
        });

        btnCancel.addActionListener(e -> dispose()); // Đóng dialog khi nhấn Cancel

    } // Kết thúc constructor

    /**
     * Phương thức trừu tượng mà các lớp con phải triển khai.
     * Chịu trách nhiệm lấy dữ liệu cụ thể, tạo đối tượng Media,
     * thêm vào store, thông báo và dọn dẹp.
     */
    protected abstract void addMediaItem();

    /**
     * Phương thức tiện ích để xóa nội dung các trường chung.
     * Lớp con có thể gọi sau khi thêm thành công.
     */
    protected void clearCommonFields() {
        tfTitle.setText("");
        tfCategory.setText("");
        tfCost.setText("");
    }

} // Kết thúc lớp AddItemToStoreScreen