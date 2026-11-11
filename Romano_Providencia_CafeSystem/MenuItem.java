/**
 * Abstract parent class of everything sold at the campus cafe.
 * It stores the data shared by all menu items and forces every
 * subclass to define its own pricing rule through {@link #computePrice()}.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public abstract class MenuItem {

    /** Display name of the item (visible to subclasses). */
    protected String name;

    /** Base price in pesos before any extras (visible to subclasses). */
    protected double basePrice;

    /** Unique menu code such as "B-01" (hidden from subclasses). */
    private String itemCode;

    /**
     * Default constructor. Creates an unnamed item with no price.
     */
    public MenuItem() {
        this.name = "Unnamed Item";
        this.basePrice = 0.0;
        this.itemCode = "N/A";
    }

    /**
     * Parameterized constructor. Values pass through the setters so that
     * invalid data is rejected and the defaults are kept instead.
     *
     * @param name      the item name
     * @param basePrice the base price in pesos (must not be negative)
     * @param itemCode  the menu code of the item
     */
    public MenuItem(String name, double basePrice, String itemCode) {
        this(); // start from the safe defaults, then apply validated values
        setName(name);
        setBasePrice(basePrice);
        setItemCode(itemCode);
    }

    /**
     * Computes the final price of this item. Each subclass has its own rule.
     *
     * @return the final price in pesos
     */
    public abstract double computePrice();

    /**
     * Describes this item. Subclasses override this to add their own details.
     *
     * @return the item name followed by its item code
     */
    public String getDescription() {
        return name + " [" + itemCode + "]";
    }

    /**
     * Gets the item name.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item name. Blank names are rejected.
     *
     * @param name the new name
     */
    public void setName(String name) {
        // Accept only a real, non-blank name
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: name cannot be empty.");
        }
    }

    /**
     * Gets the base price.
     *
     * @return the base price in pesos
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price. Negative values are rejected.
     *
     * @param basePrice the new base price in pesos
     */
    public void setBasePrice(double basePrice) {
        // A price below zero is invalid, so keep the old value and report it
        if (basePrice >= 0) {
            this.basePrice = basePrice;
        } else {
            System.out.println("Error: base price cannot be negative.");
        }
    }

    /**
     * Gets the item code.
     *
     * @return the menu code of the item
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code. Blank codes are rejected.
     *
     * @param itemCode the new menu code
     */
    public void setItemCode(String itemCode) {
        // Accept only a real, non-blank code
        if (itemCode != null && !itemCode.trim().isEmpty()) {
            this.itemCode = itemCode;
        } else {
            System.out.println("Error: item code cannot be empty.");
        }
    }
}
