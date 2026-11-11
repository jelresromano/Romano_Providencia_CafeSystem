/**
 * Represents a baked item at the campus cafe.
 * A pastry can be warmed for an extra fee.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class Pastry extends MenuItem {

    /** Extra charge for warming the pastry, in pesos. */
    private static final double WARMING_FEE = 10.0;

    /** True if the customer wants the pastry warmed. */
    private boolean warmed;

    /**
     * Default constructor. Creates an unwarmed pastry with default item values.
     */
    public Pastry() {
        super();
        this.warmed = false;
    }

    /**
     * Parameterized constructor.
     *
     * @param name      the pastry name
     * @param basePrice the price in pesos
     * @param itemCode  the menu code
     * @param warmed    true if the pastry should be warmed
     */
    public Pastry(String name, double basePrice, String itemCode, boolean warmed) {
        super(name, basePrice, itemCode);
        this.warmed = warmed;
    }

    /**
     * Computes the final price, adding the warming fee when applicable.
     *
     * @return the price of this pastry in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Warming costs an extra fee
        if (warmed) {
            price += WARMING_FEE;
        }
        return price;
    }

    /**
     * Describes this pastry, marking it when it is warmed.
     *
     * @return the parent description, plus "(Warmed)" when applicable
     */
    @Override
    public String getDescription() {
        // Only warmed pastries get the extra label
        if (warmed) {
            return super.getDescription() + " (Warmed)";
        } else {
            return super.getDescription();
        }
    }

    /**
     * Checks whether the pastry is warmed.
     *
     * @return true if warmed
     */
    public boolean isWarmed() {
        return warmed;
    }

    /**
     * Sets whether the pastry is warmed.
     *
     * @param warmed true to warm the pastry
     */
    public void setWarmed(boolean warmed) {
        this.warmed = warmed;
    }
}
