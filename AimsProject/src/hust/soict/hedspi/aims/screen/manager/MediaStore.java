package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Giá
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        // Container cho nút Play (nếu có)
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playMedia();
                }
            });
            container.add(playButton);
        }

        // Đẩy các thành phần lên theo trục Y
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        // Viền ngoài
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Play the media in a dialog window if it is playable
     */
    private void playMedia() {
        if (!(media instanceof Playable)) {
            return; // Không phải là media có thể chơi được
        }

        Playable playableMedia = (Playable) media;

        // Find the parent frame
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Create a dialog to show the play information
        JDialog playDialog = new JDialog(parentFrame, "Playing " + media.getTitle(), true);
        playDialog.setLayout(new BoxLayout(playDialog.getContentPane(), BoxLayout.Y_AXIS));

        // Create title label
        JLabel titleLabel = new JLabel("Now playing: " + media.getTitle());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Create a text area for media information
        JTextArea mediaInfo = new JTextArea();
        mediaInfo.setEditable(false);
        mediaInfo.setLineWrap(true);
        mediaInfo.setWrapStyleWord(true);
        mediaInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        mediaInfo.setBackground(playDialog.getBackground());

        // Call the play method and capture the output
        String playInfo = capturePlayInfo(playableMedia);
        mediaInfo.setText(playInfo);
        mediaInfo.setAlignmentX(CENTER_ALIGNMENT);

        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.setAlignmentX(CENTER_ALIGNMENT);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playDialog.dispose();
            }
        });

        // Add components to dialog
        playDialog.add(Box.createRigidArea(new Dimension(0, 10)));
        playDialog.add(titleLabel);
        playDialog.add(Box.createRigidArea(new Dimension(0, 10)));
        playDialog.add(mediaInfo);
        playDialog.add(Box.createRigidArea(new Dimension(0, 10)));
        playDialog.add(closeButton);
        playDialog.add(Box.createRigidArea(new Dimension(0, 10)));

        // Set dialog properties
        playDialog.setSize(400, 300);
        playDialog.setLocationRelativeTo(parentFrame);
        playDialog.setVisible(true);
    }

    /**
     * Capture the output of the play method
     * @param playableMedia the media to play
     * @return the captured output as a string
     */
    private String capturePlayInfo(Playable playableMedia) {
        // In a real implementation, you would capture the output from the play method
        // For now, we'll just return a description based on media type
        StringBuilder sb = new StringBuilder();

        sb.append("Playing media: ").append(media.getTitle()).append("\n\n");

        // Add details based on media type
        if (media.getCategory() != null && !media.getCategory().isEmpty()) {
            sb.append("Category: ").append(media.getCategory()).append("\n");
        }

        // Check for director field using reflection (since we don't have direct access to specific classes)
        try {
            java.lang.reflect.Method getDirectorMethod = media.getClass().getMethod("getDirector");
            if (getDirectorMethod != null) {
                String director = (String) getDirectorMethod.invoke(media);
                if (director != null && !director.isEmpty()) {
                    sb.append("Director: ").append(director).append("\n");
                }
            }
        } catch (Exception e) {
            // No director field, ignore
        }

        // Check for length field using reflection
        try {
            java.lang.reflect.Method getLengthMethod = media.getClass().getMethod("getLength");
            if (getLengthMethod != null) {
                int length = (Integer) getLengthMethod.invoke(media);
                if (length > 0) {
                    sb.append("Length: ").append(length).append(" minutes\n");
                }
            }
        } catch (Exception e) {
            // No length field, ignore
        }

        // Check for artist field (for CompactDisc)
        try {
            java.lang.reflect.Method getArtistMethod = media.getClass().getMethod("getArtist");
            if (getArtistMethod != null) {
                String artist = (String) getArtistMethod.invoke(media);
                if (artist != null && !artist.isEmpty()) {
                    sb.append("Artist: ").append(artist).append("\n");
                }
            }
        } catch (Exception e) {
            // No artist field, ignore
        }

        // Actually call the play method
        try {
            playableMedia.play();
            sb.append("\n--- Playing ---\n");
        } catch (Exception e) {
            sb.append("\nError playing media: ").append(e.getMessage());
        }

        return sb.toString();
    }
}