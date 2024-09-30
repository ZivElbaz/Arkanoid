/**
 * @author Ziv Elbaz
 * @version 1
 * @since 05/06/2023
 */
package Logic;

/**
 * The Counter class represents a simple counter that can be increased or decreased.
 * It keeps track of an integer value and provides methods to modify and retrieve that value.
 */
public class Counter {

    private int value;

    /**
     * Constructor.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Constructor.
     *
     * @param value the value of the counter
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases the value of the counter.
     *
     * @param number the number to add to the value
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * Decrease the balue of the counter.
     *
     * @param number the number to decrease from the value
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * Returns the current value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}