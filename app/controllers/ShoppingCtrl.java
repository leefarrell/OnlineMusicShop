package controllers;

import play.mvc.*;

import views.html.*;
import play.db.ebean.Transactional;

// Import models
import models.users.*;
import models.products.*;
import models.shopping.*;

// Import security controllers
import controllers.security.*;



public class ShoppingCtrl extends Controller {
    
    // Get a user - if logged in email will be set in the session
	private User getCurrentUser() {
		return (User )User.getLoggedIn(session().get("email"));
	}

    @Transactional
    public Result showBasket() {
        return ok(basket.render(getCurrentUser()));
    }
    
    // Add item to customer basket
    @Transactional
    public Result addToBasket(Long id) {
        
        // Find the product
        Product p = Product.find.byId(id);
        
        // Get basket for logged in customer
        User  user  = (User )User.getLoggedIn(session().get("email"));
        
        // Check if item in basket
        if (user.getBasket() == null) {
            // If no basket, create one
            user.setBasket(new Basket());
            user.getBasket().setUser(user);
            user.update();
        }
        // Add product to the basket and save
        user.getBasket().addProduct(p);
        user.update();
        
        // Show the basket contents     
        return ok(basket.render(user));
    }
    
    // Add an item to the basket
    @Transactional
    public Result addOne(Long itemId) {
        
        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Increment quantity
        item.increaseQty();
        // Save
        item.update();
        // Show updated basket
        return redirect(routes.ShoppingCtrl.showBasket());
    }

    @Transactional
    public Result removeOne(Long itemId) {
        
        // Get the order item
        OrderItem item = OrderItem.find.byId(itemId);
        // Get user
        User  u = getCurrentUser();
        // Call basket remove item method
        u.getBasket().removeItem(item);
        u.getBasket().update();
        // back to basket
        return ok(basket.render(u));
    }

    // Empty Basket
    @Transactional
    public Result emptyBasket() {
        
        User  u = getCurrentUser();
        u.getBasket().removeAllItems();
        u.getBasket().update();
        
        return ok(basket.render(u));
    }

    @Transactional
    public Result placeOrder() {
        User  u = getCurrentUser();
        
        // Create an order instance
        ShopOrder order = new ShopOrder();
        
        // Associate order with customer
        order.setUser (u);
        
        // Copy basket to order
        order.setItems(u.getBasket().getBasketItems());
        
        // Save the order now to generate a new id for this order
        order.save();
       
       // Move items from basket to order
        for (OrderItem i: order.getItems()) {
            // Associate with order
            i.setOrder(order);
            // Remove from basket
            i.setBasket(null);
            // update item
            i.update();
        }
        
        // Update the order
        order.update();
        
        // Clear and update the shopping basket
        u.getBasket().setBasketItems(null);
        u.getBasket().update();
        
        // Show order confirmed view
        return ok(orderConfirmed.render(u, order));
    }
    
    // View an individual order
    @Transactional
    public Result viewOrder(long id) {
        ShopOrder order = ShopOrder.find.byId(id);
        return ok(orderConfirmed.render(getCurrentUser(), order));
    }

}