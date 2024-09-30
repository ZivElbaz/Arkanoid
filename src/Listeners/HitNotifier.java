/**
 * @author Ziv Elbaz
 * @version 1
 * @since 05/06/2023
 */
package Listeners;

/**
 * A interface representing an object that can notify listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hitlistener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hitlistener
     */
    void removeHitListener(HitListener hl);
}
