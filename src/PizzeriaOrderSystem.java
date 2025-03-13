public class PizzeriaOrderSystem {
    enum PizzaSelection {
        PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
        HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
        VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
        BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
        EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

        private String pizzaName;
        private String pizzaToppings;
        private int price;

        PizzaSelection(String pizzaName, String pizzaToppings, int price) {
            this.pizzaName = pizzaName;
            this.pizzaToppings = pizzaToppings;
            this.price = price;
        }

        public String getPizzaName() {
            return pizzaName;
        }

        public String getPizzaToppings() {
            return pizzaToppings;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return getPizzaName() + " Pizza with " + getPizzaToppings() + ", for €" + getPrice();
        }
    }

    enum PizzaToppings {
        HAM("Ham", 2),
        PEPPERONI("Pepperoni", 2),
        BEEF("Beef", 2),
        CHICKEN("Chicken", 2),
        SAUSAGE("Sausage", 2),
        PINEAPPLE("Pineapple", 1),
        ONION("Onion", 0.5),
        TOMATOES("Tomatoes", 0.4),
        GREEN_PEPPER("Green Pepper", 0.5),
        BLACK_OLIVES("Black Olives", 0.5),
        SPINACH("Spinach", 0.5),
        CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
        MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
        FETA_CHEESE("Feta Cheese", 1),
        PARMESAN_CHEESE("Parmesan Cheese", 1);

        private String topping;
        private double toppingPrice;

        PizzaToppings(String topping, double toppingPrice) {
            this.topping = topping;
            this.toppingPrice = toppingPrice;
        }

        public String getTopping() {
            return topping;
        }

        public double getToppingPrice() {
            return toppingPrice;
        }

        @Override
        public String toString() {
            return getTopping();
        }
    }

    enum PizzaSize {
        LARGE("Large", 10),
        MEDIUM("Medium", 5),
        SMALL("Small", 0);

        private String pizzaSize;
        private int addToPizzaPrice;

        PizzaSize(String pizzaSize, int addToPizzaPrice) {
            this.pizzaSize = pizzaSize;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getPizzaSize() {
            return pizzaSize;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return getPizzaSize() + ": €" + getAddToPizzaPrice();
        }
    }

    enum SideDish {
        CALZONE("Calzone", 15),
        CHICKEN_PUFF("Chicken Puff", 20),
        MUFFIN("Muffin", 12),
        NOTHING("No side dish", 0);

        private String sideDishName;
        private int addToPizzaPrice;

        SideDish(String sideDishName, int addToPizzaPrice) {
            this.sideDishName = sideDishName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getSideDishName() {
            return sideDishName;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return getSideDishName() + ": €" + getAddToPizzaPrice();
        }
    }

    enum Drinks {
        COCA_COLA("Coca Cola", 8),
        COCOA_DRINK("Cocoa Drink", 10),
        NOTHING("No drinks", 0);

        private String drinkName;
        private int addToPizzaPrice;

        Drinks(String drinkName, int addToPizzaPrice) {
            this.drinkName = drinkName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getDrinkName() {
            return drinkName;
        }

        public int getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return getDrinkName() + ": €" + getAddToPizzaPrice();
        }
    }

    public static final double PIZZA_BASE_PRICE = 10.0;

    public static String[] pizzasOrdered = new String[10];
    public static String[] pizzaSizesOrdered = new String[10];
    public static String[] sideDishesOrdered = new String[20];
    public static String[] drinksOrdered = new String[20];
    public static int orderIndex = 0;
    public static double totalOrderPrice = 0;

    public static void takeOrder() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
            int option = 1;
            for (PizzaSelection pizza : PizzaSelection.values()) {
                System.out.println(option + ". " + pizza);
                option++;
            }
            System.out.println(option + ". Custom Pizza with a maximum of 10 toppings that you choose");
            System.out.print("Please enter your choice (1 - " + option + "): ");
            int pizzaChoice = scanner.nextInt();

            String pizzaDetails;
            if (pizzaChoice <= PizzaSelection.values().length) {
                PizzaSelection selectedPizza = PizzaSelection.values()[pizzaChoice - 1];
                pizzaDetails = selectedPizza.toString();
                totalOrderPrice += selectedPizza.getPrice();
            } else {
                System.out.println("Available toppings:");
                int toppingOption = 1;
                for (PizzaToppings topping : PizzaToppings.values()) {
                    System.out.println(toppingOption + ". " + topping);
                    toppingOption++;
                }
                double customPizzaPrice = PIZZA_BASE_PRICE;
                StringBuilder customPizzaToppings = new StringBuilder();
                System.out.print("Please enter a maximum of 10 choices (separated by commas): ");
                scanner.nextLine(); 
                String toppingInputs = scanner.nextLine();
                String[] toppingArray = toppingInputs.split(",");
                for (String input : toppingArray) {
                    int toppingIndex = Integer.parseInt(input.trim()) - 1;
                    PizzaToppings selectedTopping = PizzaToppings.values()[toppingIndex];
                    customPizzaPrice += selectedTopping.getToppingPrice();
                    customPizzaToppings.append(selectedTopping.getTopping()).append(", ");
                }
                if (customPizzaToppings.length() > 2) {
                    customPizzaToppings.setLength(customPizzaToppings.length() - 2); 
                }
                pizzaDetails = "Custom Pizza with " + customPizzaToppings + ", for €" + customPizzaPrice;
                totalOrderPrice += customPizzaPrice;
            }
            pizzasOrdered[orderIndex] = pizzaDetails;

            System.out.println("Please choose a pizza size:");
            for (int i = 0; i < PizzaSize.values().length; i++) {
                System.out.println((i + 1) + ". " + PizzaSize.values()[i]);
            }
            int sizeChoice = scanner.nextInt();
            PizzaSize selectedSize = PizzaSize.values()[sizeChoice - 1];
            pizzaSizesOrdered[orderIndex] = selectedSize.toString();
            totalOrderPrice += selectedSize.getAddToPizzaPrice();

            System.out.println("Please choose a side dish:");
            for (int i = 0; i < SideDish.values().length; i++) {
                System.out.println((i + 1) + ". " + SideDish.values()[i]);
            }
            int sideDishChoice = scanner.nextInt();
            SideDish selectedSideDish = SideDish.values()[sideDishChoice - 1];
            sideDishesOrdered[orderIndex] = selectedSideDish.toString();
            totalOrderPrice += selectedSideDish.getAddToPizzaPrice();

            System.out.println("Please choose a drink:");
            for (int i = 0; i < Drinks.values().length; i++) {
                System.out.println((i + 1) + ". " + Drinks.values()[i]);
            }
            int drinkChoice = scanner.nextInt();
            Drinks selectedDrink = Drinks.values()[drinkChoice - 1];
            drinksOrdered[orderIndex] = selectedDrink.toString();
            totalOrderPrice += selectedDrink.getAddToPizzaPrice();

            orderIndex++;

            System.out.print("Do you want to order more? (Y/N): ");
            String moreOrders = scanner.next();
            if (!moreOrders.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Thank you for dining with Slice-o-Heaven Pizzeria. Your order details are as follows:\n");
        for (int i = 0; i < orderIndex; i++) {
            orderDetails.append((i + 1) + ". ").append(pizzasOrdered[i]).append("\n");
            orderDetails.append(pizzaSizesOrdered[i]).append("\n");
            orderDetails.append(sideDishesOrdered[i]).append("\n");
            orderDetails.append(drinksOrdered[i]).append("\n");
        }
        orderDetails.append("ORDER TOTAL: €").append(totalOrderPrice);
        return orderDetails.toString();
    }

    public static void main(String[] args) {
        takeOrder();
        PizzeriaOrderSystem orderSystem = new PizzeriaOrderSystem();
        System.out.println(orderSystem);
    }
}