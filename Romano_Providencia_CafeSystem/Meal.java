/**
 * Represents a rice meal at the campus cafe.
 * A meal can be upsized and can have extra sides.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class Meal extends MenuItem {

    /** Extra charge for upsizing a meal, in pesos. */
    private static final double UPSIZE_FEE = 20.0;

    /** Price of each extra side, in pesos. */
    private static final double EXTRA_SIDE_PRICE = 12.0;

    /** True if the meal is upsized. */
    private boolean upsized;

    /** Number of extra sides ordered (never negative). */
    private int extraSides;

    /**
     * Default constructor. Creates a regular meal with no extra sides.
     */
    public Meal() {
        super();
        this.upsized = false;
        this.extraSides = 0;
    }

    /**
     * Parameterized constructor.
     *
     * @param name       the meal name
     * @param basePrice  the regular price in pesos
     * @param itemCode   the menu code
     * @param upsized    true if the meal is upsized
     * @param extraSides the number of extra sides (negative values are rejected)
     */
    public Meal(String name, double basePrice, String itemCode, boolean upsized, int extraSides) {
        super(name, basePrice, itemCode);
        this.upsized = upsized;
        this.extraSides = 0; // safe default in case the given value is invalid
        setExtraSides(extraSides);
    }

    /**
     * Computes the final price: base price, plus the upsize fee, plus the
     * price of every extra side.
     *
     * @return the price of this meal in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Upsizing adds a flat fee
        if (upsized) {
            price += UPSIZE_FEE;
        }
        price += EXTRA_SIDE_PRICE * extraSides;
        return price;
    }

    /**
     * Describes this meal, including upsize status and number of extra sides.
     *
     * @return the parent description with the upsize status and side count
     */
    @Override
    public String getDescription() {
        String sizeLabel;
        // Show whether the meal is upsized or regular
        if (upsized) {
            sizeLabel = "Upsized";
        } else {
            sizeLabel = "Regular";
        }

        String sideLabel;
        // Use the singular word for exactly one side
        if (extraSides == 1) {
            sideLabel = "side";
        } else {
            sideLabel = "sides";
        }
        return super.getDescription() + " " + sizeLabel + ", " + extraSides + " " + sideLabel;
    }

    /**
     * Checks whether the meal is upsized.
     *
     * @return true if upsized
     */
    public boolean isUpsized() {
        return upsized;
    }

    /**
     * Sets whether the meal is upsized.
     *
     * @param upsized true to upsize the meal
     */
    public void setUpsized(boolean upsized) {
        this.upsized = upsized;
    }

    /**
     * Gets the number of extra sides.
     *
     * @return the number of extra sides
     */
    public int getExtraSides() {
        return extraSides;
    }

    /**
     * Sets the number of extra sides. Negative values are rejected.
     *
     * @param extraSides the new number of extra sides
     */
    public void setExtraSides(int extraSides) {
        // A negative number of sides makes no sense, so keep the old value
        if (extraSides >= 0) {
            this.extraSides = extraSides;
        } else {
            System.out.println("Error: extra sides cannot be negative.");
        }
    }
}
