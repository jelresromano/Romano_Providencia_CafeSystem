/**
 * Represents one order. It connects a {@link Customer} to up to three
 * {@link MenuItem} objects. Items are stored in separate fields because
 * arrays and collections are not allowed in this exercise.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class Order {

    /** Maximum number of items allowed in one order. */
    private static final int MAX_ITEMS = 3;

    /** Discount rate given to students (10%). */
    private static final double STUDENT_DISCOUNT_RATE = 0.10;

    /** Width of the item column on the receipt. */
    private static final int RECEIPT_WIDTH = 52;

    /** The customer who placed the order. */
    private Customer customer;

    /** First item slot. */
    private MenuItem item1;

    /** Second item slot. */
    private MenuItem item2;

    /** Third item slot. */
    private MenuItem item3;

    /** How many slots are currently filled. */
    private int itemCount;

    /** Order number shown on the receipt. */
    private String orderId;

    /**
     * Creates an empty order for a customer.
     *
     * @param orderId  the order number
     * @param customer the customer placing the order
     */
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        // Fall back to a default customer so the order never holds null
        if (customer != null) {
            this.customer = customer;
        } else {
            this.customer = new Customer();
        }
        this.itemCount = 0;
    }

    /**
     * Adds an item to the first empty slot.
     *
     * @param item the menu item to add
     * @return true if the item was added, false if the order is full or the item is null
     */
    public boolean addItem(MenuItem item) {
        // Reject a missing item
        if (item == null) {
            System.out.println("Error: cannot add an empty item.");
            return false;
        }

        // The order can hold at most three items
        if (itemCount >= MAX_ITEMS) {
            System.out.println("Order is full. Cannot add " + item.getDescription() + ".");
            return false;
        } else {
            // Use the first empty slot
            if (item1 == null) {
                item1 = item;
            } else if (item2 == null) {
                item2 = item;
            } else {
                item3 = item;
            }
            itemCount++;
            return true;
        }
    }

    /**
     * Adds up the price of every item. Each call to computePrice() runs the
     * version that belongs to the item's real class (polymorphism).
     *
     * @return the subtotal in pesos
     */
    public double computeSubtotal() {
        double subtotal = 0.0;
        // Only filled slots count toward the subtotal
        if (item1 != null) {
            subtotal += item1.computePrice();
        }
        if (item2 != null) {
            subtotal += item2.computePrice();
        }
        if (item3 != null) {
            subtotal += item3.computePrice();
        }
        return subtotal;
    }

    /**
     * Computes the student discount amount for this order.
     *
     * @return the discount in pesos (0 for non-students)
     */
    public double computeDiscount() {
        // Only students receive the 10% discount
        if (customer.isStudent()) {
            return computeSubtotal() * STUDENT_DISCOUNT_RATE;
        } else {
            return 0.0;
        }
    }

    /**
     * Computes the total after the student discount (if any).
     *
     * @return the total in pesos
     */
    public double computeTotal() {
        return computeSubtotal() - computeDiscount();
    }

    /**
     * Computes the total after the student discount and a voucher.
     * The total never goes below zero.
     *
     * @param voucher the voucher amount in pesos
     * @return the total in pesos, never below 0
     */
    public double computeTotal(double voucher) {
        double total = computeTotal();
        // Ignore a negative voucher so it cannot increase the bill
        if (voucher > 0) {
            total -= voucher;
        }
        // The total may never be negative
        if (total < 0) {
            return 0.0;
        } else {
            return total;
        }
    }

    /**
     * Prints the receipt without a voucher.
     */
    public void printReceipt() {
        printReceipt(0.0);
    }

    /**
     * Prints the receipt with a voucher applied, then adds loyalty points to
     * the customer based on the amount paid (1 point per 50 pesos).
     *
     * @param voucher the voucher amount in pesos (0 for none)
     */
    public void printReceipt(double voucher) {
        String status;
        // Mark student customers in the header
        if (customer.isStudent()) {
            status = " (Student)";
        } else {
            status = "";
        }
        System.out.println();
        System.out.println("Order #" + orderId + " - " + customer.getName() + status);

        printItemLine(item1);
        printItemLine(item2);
        printItemLine(item3);

        System.out.println(String.format("  Subtotal: %.2f", computeSubtotal()));

        // Show the discount line only when a discount applies
        if (customer.isStudent()) {
            System.out.println(String.format("  Student discount (10%%): -%.2f", computeDiscount()));
        }
        // Show the voucher line only when a voucher is used
        if (voucher > 0) {
            System.out.println(String.format("  Voucher: -%.2f", voucher));
        }

        double total = computeTotal(voucher);
        System.out.println(String.format("  TOTAL: %.2f", total));

        // Object interaction: the order updates the customer's points
        customer.addPoints(total);
        System.out.println("  Loyalty points: " + customer.getLoyaltyPoints());
    }

    /**
     * Prints one receipt line (description, dots, price) for an item slot.
     *
     * @param item the item in the slot, or null if the slot is empty
     */
    private void printItemLine(MenuItem item) {
        // Skip empty slots
        if (item != null) {
            StringBuilder line = new StringBuilder("  " + item.getDescription() + " ");
            // Pad with dots so the prices line up
            while (line.length() < RECEIPT_WIDTH) {
                line.append('.');
            }
            line.append(String.format(" %7.2f", item.computePrice()));
            System.out.println(line.toString());
        }
    }

    /**
     * Gets the customer of this order.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the number of items in the order.
     *
     * @return the item count (0 to 3)
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Gets the order number.
     *
     * @return the order id
     */
    public String getOrderId() {
        return orderId;
    }
}
