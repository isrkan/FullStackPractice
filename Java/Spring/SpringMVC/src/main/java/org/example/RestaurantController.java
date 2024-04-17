package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

// Marks the class as a Spring MVC controller. It indicates that this class defines methods to handle web requests.
@Controller
public class RestaurantController {

    // Map to store item details
    private static final Map<String, String> itemDetailsMap = new HashMap<>();
    private static final Map<String, String> itemPricesMap = new HashMap<>();
    // The static block ensures that these maps are populated with data before any method in the class is called
    static {
        // Populate item details map (item name as key, item details as value)
        itemDetailsMap.put("Pizza", "Delicious pizza made with fresh ingredients");
        itemDetailsMap.put("Burger", "Juicy burger with cheese and veggies");
        itemDetailsMap.put("Salad", "Fresh salad with mixed greens and dressing");
        // Populate item prices map (item name as key, item price as value)
        itemPricesMap.put("Pizza", "$10.99");
        itemPricesMap.put("Burger", "$8.49");
        itemPricesMap.put("Salad", "$6.99");
    }

    // Handler method for menu endpoint
    // GetMapping is used to map HTTP GET requests to specific handler methods.
    // It specifies that this method should be invoked when a GET request is made to the "/menu" endpoint.
    @GetMapping("/menu")
    // We use Model when we want to pass data to the view in a controller method.
    // It allows us to add attributes to the model, which can then be accessed in the view to render dynamic content.
    public String showMenu(Model model) {
        // Add menu items to the model
        model.addAttribute("item1", "Pizza");
        model.addAttribute("item2", "Burger");
        model.addAttribute("item3", "Salad");
        // Return the view name
        return "menu";
    }

    // Handler method for specials endpoint
    @GetMapping("/specials")
    // ModelAndView is used when we want to pass both the view name and model data together.
    // This is useful when we need more control over the view resolution or when we want to return different views dynamically based on certain conditions.
    public ModelAndView showSpecials(@RequestParam(required = false) String discount) {
        // Create a ModelAndView object with the view name
        ModelAndView modelAndView = new ModelAndView("specials");
        // Adjust special offers based on the provided discount parameter
        if ("student".equals(discount)) {
            modelAndView.addObject("special1", "20% off for students!");
            modelAndView.addObject("special2", "Free drink with every meal for students!");
            modelAndView.addObject("special3", "Discounted prices for student groups!");
        } else if ("senior".equals(discount)) {
            modelAndView.addObject("special1", "Senior citizen discount: 15% off!");
            modelAndView.addObject("special2", "Free dessert for seniors!");
            modelAndView.addObject("special3", "Special discounts for senior groups!");
        } else { // Default special offers
            modelAndView.addObject("special1", "20% off on all pizzas!");
            modelAndView.addObject("special2", "Buy one burger, get one free!");
            modelAndView.addObject("special3", "Free dessert with every salad order!");
        }
        // Return the ModelAndView object
        return modelAndView;
    }

    // Handler method for menu/itemName endpoint
    @GetMapping("/menu/{itemName}")
    public String showItemDetails(@PathVariable String itemName, Model model) {
        // Retrieve item details from the map based on the item name
        String itemDetails = itemDetailsMap.get(itemName);
        String itemPrices = itemPricesMap.get(itemName);
        // Add item details to the model
        model.addAttribute("itemName", itemName);
        model.addAttribute("itemDetails", itemDetailsMap.get(itemName));
        model.addAttribute("itemPrice", itemPricesMap.get(itemName));
        return "item-details";
    }

    // Handler method for order endpoint
    // RequestMapping is a general-purpose annotation for mapping HTTP requests to handler methods.
    // When we don't specify an HTTP method (like GET, POST), it will handle all types of HTTP requests for the given URL path.
    @RequestMapping("/order")
    public String placeOrder() {
        return "order";
    }
}