/**
 * Represents a drink sold at the campus cafe.
 * The price depends on the selected size.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class Beverage extends MenuItem {

    /** Extra charge for a medium drink, in pesos. */
    private static final double MEDIUM_SURCHARGE = 15.0;

    /** Extra charge for a large drink, in pesos. */
    private static final double LARGE_SURCHARGE = 25.0;

    /** Cup size: "S", "M", or "L". */
    private String size;

    /**
     * Default constructor. Creates a small drink with the default item values.
     */
    public Beverage() {
        super();
        this.size = "S";
    }

    /**
     * Parameterized constructor.
     *
     * @param name      the drink name
     * @param basePrice the price of a small cup in pesos
     * @param itemCode  the menu code
     * @param size      the cup size ("S", "M", or "L")
     */
    public Beverage(String name, double basePrice, String itemCode, String size) {
        super(name, basePrice, itemCode);
        this.size = "S"; // safe default in case the given size is invalid
        setSize(size);
    }

    /**
     * Computes the final price based on size.
     *
     * @return the price of this beverage in pesos
     */
    @Override
    public double computePrice() {
        // Large adds 25, Medium adds 15, Small uses the base price
        if (size.equals("L")) {
            return basePrice + LARGE_SURCHARGE;
        } else if (size.equals("M")) {
            return basePrice + MEDIUM_SURCHARGE;
        } else {
            return basePrice;
        }
    }

    /**
     * Describes this beverage, including its size.
     *
     * @return the parent description followed by the size
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " Size: " + size;
    }

    /**
     * Gets the cup size.
     *
     * @return "S", "M", or "L"
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the cup size. Only "S", "M", or "L" are accepted; anything else
     * keeps the old value.
     *
     * @param size the new size
     */
    public void setSize(String size) {
        // Only the three valid sizes may replace the current size
        if (size != null && (size.equals("S") || size.equals("M") || size.equals("L"))) {
            this.size = size;
        } else {
            System.out.println("Error: size must be S, M, or L. Keeping size " + this.size + ".");
        }
    }
}
