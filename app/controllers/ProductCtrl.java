package controllers;

import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

// Import models
import models.users.*;
import models.products.*;
import models.*;


public class ProductCtrl extends Controller {

    /** Dependency Injection **/

    /** http://stackoverflow.com/questions/15600186/play-framework-dependency-injection **/
    private FormFactory formFactory;

    /** http://stackoverflow.com/a/37024198 **/
    private play.api.Environment env;

    /** http://stackoverflow.com/a/10159220/6322856 **/
    @Inject
    public ProductCtrl(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    // Get a user - if logged in email will be set in the session
    @Transactional
	public User getCurrentUser() {
		User u = User.getLoggedIn(session().get("email"));
		return u;
	}

    public Result index() {
         List<Product> products = new ArrayList<Product>();
		return ok(index.render(env, products,getCurrentUser()));
    }

    public Result about() {
		return ok(about.render(getCurrentUser()));
    }

	// Get a list of products
    // If cat parameter is 0 then return all products
    // Otherwise return products for a category (by id)
    // In both cases products will be searched using the fiter value
    @Transactional
    public Result listProducts(Long cat, String filter) {
        // Get list of all categories in ascending order
        List<Category> categories = Category.findAll();
        // Instantiate products, an Array list of products			
        List<Product> products = new ArrayList<Product>();
    
        if (cat == 0) {
            // Get the list of ALL products with filter
            products = Product.findAll(filter);
        }
        else {
            // Get products for the selected category and filter (search field)
            products = Product.findFilter(cat, filter);
        }
        // Render the list products view, passing parameters
        // categories and products lists
        // category id - used for filtering
        // the filter string - this will be displayed in the filter text input
        // current user - if one is logged in
        return ok(listProducts.render(env, categories, products, cat, filter, getCurrentUser()));
    }
    public Result charts() {

        List<Chart> chartsList = Chart.findAll();

        return ok(charts.render(env,chartsList, getCurrentUser()));
    }
     public Result contactUs() {
		return ok(contactUs.render(getCurrentUser()));
    }
    public Result editProfile() {

        return ok(editProfile.render(getCurrentUser()));
    }

    public Result signup() {
        Form<User> addUsersForm = formFactory.form(User.class);

        return ok(signup.render(addUsersForm));
    }

    @Transactional
    public Result signupSubmit() {

        Form<User> newUsersForm = formFactory.form(User.class).bindFromRequest();

        if (newUsersForm.hasErrors()) {
            return badRequest(signup.render(newUsersForm));
        }

        User newUser = newUsersForm.get();

        newUser.save();

        flash("success", "Account " + newUser.getName() + " has been created");

        return redirect(controllers.routes.ProductCtrl.signup());
    }

}