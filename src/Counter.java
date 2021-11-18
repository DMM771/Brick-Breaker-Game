//324680438

/**
 * a class for counting.
 */
public class Counter {
    //start count at 0
    private int count = 0;

    /**
     * increase the count.
     * @param number is the margin to increase
     */
    void increase(int number) {
        this.count += number;
    }
    /**
     * decrease the count.
     * @param number is the margin to decrease
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * accessor.
     * @return current count
     */
    int getValue() {
        return this.count;
    }
}
