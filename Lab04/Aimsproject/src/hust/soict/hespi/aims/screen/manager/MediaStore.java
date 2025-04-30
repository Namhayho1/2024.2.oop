package hust.soict.hespi.aims.screen.manager;
import hust.soict.hespi.aims.media.Media;
import hust.soict.hespi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Tạo và hiển thị JDialog khi nhấn nút Play
                    JDialog playDialog = new JDialog(
                        // Lấy JFrame cha gần nhất của panel này
                        (Frame) SwingUtilities.getWindowAncestor(MediaStore.this),
                        "Playing Media", // Tiêu đề Dialog
                        true // true = Modal (chặn tương tác với cửa sổ chính)
                    );
                    playDialog.setLayout(new BorderLayout(10, 10)); // Layout cho nội dung dialog
                    playDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Đóng dialog khi nhấn X

                    // Nội dung dialog
                    JPanel dialogPanel = new JPanel(new BorderLayout(10, 10));
                    dialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    // Thông báo đang phát
                    // Cần ép kiểu sang Playable để gọi play() hoặc hiển thị thông tin thêm
                    Playable playableMedia = (Playable) media;
                    // playableMedia.play(); // Có thể gọi phương thức play logic ở đây nếu có
                    JLabel playingLabel = new JLabel("<html>Playing:<br>" + media.getTitle() + "</html>", SwingConstants.CENTER);
                    dialogPanel.add(playingLabel, BorderLayout.CENTER);

                    // Nút đóng dialog
                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(evt -> playDialog.dispose()); // Lambda expression để đóng dialog
                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    buttonPanel.add(closeButton);
                    dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

                    playDialog.add(dialogPanel); // Thêm panel nội dung vào dialog
                    playDialog.pack(); // Tự động điều chỉnh kích thước dialog
                    playDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(MediaStore.this)); // Căn giữa dialog so với cửa sổ chính
                    playDialog.setVisible(true); // Hiển thị dialog
                }
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}