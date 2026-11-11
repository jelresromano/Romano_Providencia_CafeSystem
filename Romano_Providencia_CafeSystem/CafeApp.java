/**
 * Main class of the Campus Cafe Ordering System. It builds customers, menu
 * items, and orders with hard-coded values, then prints the receipts.
 *
 * @author Jelres C. Romano, Ildefonso, Providencia Jr.
 * @version 1.0
 */
public class CafeApp {

    /**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===== Campus Cafe Ordering System =====");

        // Customers made with two different constructors
        Customer ana = new Customer("Ana Reyes", true); // name + student flag
        Customer ben = new Customer("Ben Cruz");        // name only

        // ---------- Order #1001 ----------
        // Items made with the parameterized constructors
        Beverage icedLatte = new Beverage("Iced Latte", 95.0, "B-01", "L");
        Pastry ensaymada = new Pastry("Ensaymada", 45.0, "P-01", true);
        Meal chickenAdobo = new Meal("Chicken Adobo", 110.0, "M-01", true, 1);
        Pastry cookie = new Pastry("Cookie", 35.0, "P-02", false);

        Order order1 = new Order("1001", ana);
        order1.addItem(icedLatte);
        order1.addItem(ensaymada);
        order1.addItem(chickenAdobo);
        order1.addItem(cookie); // fourth item: must be refused
        order1.printReceipt();

        // ---------- Order #1002 ----------
        // Items made with default constructors, then filled in with setters
        Beverage hotChocolate = new Beverage();
        hotChocolate.setName("Hot Chocolate");
        hotChocolate.setItemCode("B-02");
        hotChocolate.setBasePrice(-5.0); // invalid: setter rejects it
        hotChocolate.setBasePrice(80.0); // valid
        hotChocolate.setSize("M");

        Meal beefTapa = new Meal();
        beefTapa.setName("Beef Tapa");
        beefTapa.setItemCode("M-02");
        beefTapa.setBasePrice(130.0);

        Order order2 = new Order("1002", ben);
        order2.addItem(hotChocolate);
        order2.addItem(beefTapa);
        order2.printReceipt(20.0); // 20-peso voucher

        // ---------- Extra demonstrations ----------
        System.out.println();
        System.out.println("----- Extra checks -----");

        Customer walkIn = new Customer(); // default constructor
        System.out.println("Default customer: " + walkIn.getName()
                + ", student=" + walkIn.isStudent());
        walkIn.setLoyaltyPoints(-10);     // rejected
        walkIn.addPoints(120.0);          // double overload: 2 points
        walkIn.addPoints(3);              // int overload: +3 points
        System.out.println("Walk-in loyalty points: " + walkIn.getLoyaltyPoints());

        Pastry plainPastry = new Pastry(); // default constructor
        System.out.println("Default pastry: " + plainPastry.getDescription()
                + " = " + plainPastry.computePrice());
        icedLatte.setSize("XL");           // rejected, keeps L
        System.out.println("Latte size is still: " + icedLatte.getSize());
    }
}
