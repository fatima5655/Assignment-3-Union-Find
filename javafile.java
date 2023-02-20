import java.util.Scanner;

enum State {
    NEW_YORK(0.08), CALIFORNIA(0.0725), TEXAS(0.0625);
    private double taxRate;
    State(double taxRate) {
        this.taxRate = taxRate;
    }
    public double getTaxRate() {
        return taxRate;
    }
}

class Laptop {
    private String brand;
    private String model;
    private double price;
    public Laptop(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
    public String toString() {
        return brand + " " + model + " $" + price;
    }
    public double getPrice() {
        return price;
    }
}

class RefurbUsedLaptopStore {
    private Laptop[] laptops;
    private Scanner scanner;
    private State state;
    public RefurbUsedLaptopStore() {
        laptops = new Laptop[] {
            new Laptop("Dell", "Latitude E7450", 350.00),
            new Laptop("HP", "Elitebook 840 G1", 300.00),
            new Laptop("Lenovo", "ThinkPad T450s", 325.00)
        };
        scanner = new Scanner(System.in);
        state = State.NEW_YORK;
    }
    public void displayLaptops() {
        System.out.println("Refurb Used Laptops:");
        for (int i = 0; i < laptops.length; i++) {
            System.out.println((i+1) + ") " + laptops[i]);
        }
    }
    public void selectLaptop() {
        System.out.print("Enter the number of the laptop you want: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > laptops.length) {
            System.out.println("Invalid choice. Please try again.");
            selectLaptop();
        } else {
            Laptop laptop = laptops[choice-1];
            System.out.println("You selected: " + laptop);
            System.out.println("Price: $" + laptop.getPrice());
        }
    }
    public void checkout() {
        System.out.println("Checkout:");
        System.out.println("State: " + state.name());
        double taxRate = state.getTaxRate();
        System.out.println("Tax Rate: " + (taxRate*100) + "%");
        System.out.print("Payment method (1=credit/debit card, 2=check, 3=cash): ");
        int paymentMethod = scanner.nextInt();
        if (paymentMethod == 1) {
            System.out.print("Enter credit/debit card number: ");
            long cardNumber = scanner.nextLong();
            System.out.print("Enter expiration date (MM/YY): ");
            String expirationDate = scanner.next();
            System.out.print("Enter CVV code: ");
            int cvv = scanner.nextInt();
            System.out.println("Payment successful!");
        } else if (paymentMethod == 2) {
            System.out.print("Enter check number: ");
            String checkNumber = scanner.next();
            System.out.println("Payment successful!");
        } else if (paymentMethod == 3) {
            System.out.print("Enter cash amount: ");
            double cashAmount = scanner.nextDouble();
            double totalAmount = laptops[0].getPrice() * (1 + taxRate);
            if (cashAmount < totalAmount) {
                System.out.println("Insufficient cash amount. Please try again.");
                checkout();
            } else {
                double change = cashAmount - totalAmount;
               
                System.out.println("Payment successful!");
                System.out.println("Change: $" + change);
            }
        } else {
            System.out.println("Invalid payment method. Please try again.");
            checkout();
        }
    }
    public void run() {
        while (true) {
            displayLaptops();
            selectLaptop();
            checkout();
            System.out.print("Do you want to continue shopping (y/n)? ");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
        }
    }
    public static void main(String[] args) {
        RefurbUsedLaptopStore store = new RefurbUsedLaptopStore();
        store.run();
    }
}
