import java.util.List;
public class Main {
    public static void main(String[] args) {
        MenuItem pizza = new FoodItem("Pizza", 12.99, false);
        MenuItem burger = new FoodItem("Burger", 8.99, false);
        MenuItem fries = new FoodItem("Fries", 5.99, true);
        MenuItem pepsi = new FoodItem("Pepsi", 1.99, true);

        Order order1 = new Order(1, pizza, 2);
        Order order2 = new Order(2, pepsi, 3);
        Order order3 = new Order(3, burger, 4);

        Restaurant restaurant = new Restaurant ("Happy Meal");
        restaurant.addMenuItem(pizza);
        restaurant.addMenuItem(burger);
        restaurant.addMenuItem(fries);
        restaurant.addMenuItem(pepsi);

        restaurant.placeOrder(order1);
        restaurant.placeOrder(order2);
        restaurant.placeOrder(order3);

        restaurant.display();

        System.out.println("Vegetatian Items: ");
        List<MenuItem> vegetarianItems = restaurant.filterVegetarian();
        for (MenuItem item : vegetarianItems) {
            item.display();
        }

        System.out.println("Searching for 'Burger: ");
        MenuItem searchedItem = restaurant.searchMenuItemByName("Burger");
        if (searchedItem != null) {
            searchedItem.display();
        }
        else{
            System.out.println("No item found");
        }

        System.out.println("Sorting menu by price: ");
        restaurant.sortMenuByPrice();
        restaurant.display();
    }
}
