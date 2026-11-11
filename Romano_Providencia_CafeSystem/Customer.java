/**
 * Represents the person placing an order at the campus cafe.
 * Students receive a discount, and every customer earns loyalty points.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class Customer {

    /** Pesos that must be spent to earn one loyalty point. */
    private static final double PESOS_PER_POINT = 50.0;

    /** Customer name. */
    private String name;

    /** True if the customer is a student (eligible for the discount). */
    private boolean student;

    /** Total loyalty points earned so far (never negative). */
    private int loyaltyPoints;

    /**
     * Default constructor. Creates a non-student customer named "Guest".
     */
    public Customer() {
        this.name = "Guest";
        this.student = false;
        this.loyaltyPoints = 0;
    }

    /**
     * Creates a non-student customer with the given name.
     *
     * @param name the customer name
     */
    public Customer(String name) {
        this(); // start from the defaults, then apply the validated name
        setName(name);
    }

    /**
     * Creates a customer with a name and student status.
     *
     * @param name    the customer name
     * @param student true if the customer is a student
     */
    public Customer(String name, boolean student) {
        this(name);
        this.student = student;
    }

    /**
     * Adds loyalty points directly. Zero or negative amounts are rejected.
     *
     * @param points the number of points to add
     */
    public void addPoints(int points) {
        // Only positive point values may be added
        if (points > 0) {
            loyaltyPoints += points;
        } else {
            System.out.println("Error: points to add must be positive.");
        }
    }

    /**
     * Adds loyalty points based on money spent: 1 point for every 50 pesos.
     * Any leftover amount below 50 pesos earns nothing.
     *
     * @param amountSpent the amount spent in pesos
     */
    public void addPoints(double amountSpent) {
        int earned = (int) (amountSpent / PESOS_PER_POINT);
        // Skip the call when the purchase is too small to earn a point
        if (earned > 0) {
            addPoints(earned);
        }
    }

    /**
     * Gets the customer name.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name. Blank names are rejected.
     *
     * @param name the new name
     */
    public void setName(String name) {
        // Accept only a real, non-blank name
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: customer name cannot be empty.");
        }
    }

    /**
     * Checks whether the customer is a student.
     *
     * @return true if the customer is a student
     */
    public boolean isStudent() {
        return student;
    }

    /**
     * Sets the student status.
     *
     * @param student true if the customer is a student
     */
    public void setStudent(boolean student) {
        this.student = student;
    }

    /**
     * Gets the loyalty points.
     *
     * @return the total loyalty points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Sets the loyalty points. Negative values are rejected.
     *
     * @param loyaltyPoints the new total of loyalty points
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        // Points can never go below zero
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        } else {
            System.out.println("Error: loyalty points cannot be negative.");
        }
    }
}
