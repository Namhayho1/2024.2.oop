
package hust.soict.hespi.aims.media; // Nhất quán package

/**
 * Interface Playable định nghĩa một "hợp đồng" cho các đối tượng Media
 * có khả năng được "phát" (played).
 */
public interface Playable {
    /**
     * Phương thức trừu tượng định nghĩa hành động "phát" media.
     */
    public void play();
}