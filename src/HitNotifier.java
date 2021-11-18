//324680438
/**
 * this is the hit notifier interface.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * add new hit listener.
     * @param hl is the new listener
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.

    /**
     * remove a hit listener.
     * @param hl is the listener to remove
     */
    void removeHitListener(HitListener hl);
}