package models.users;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.*;
import models.shopping.*;

//https://www.playframework.com/documentation/2.2.x/JavaGuide4

// Product entity managed by Ebean
@Entity
// specify mapped table name
@Table(name = "user")
// Map inherited classes to a single table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
// Discriminator column used to define user type
@DiscriminatorColumn(name = "role")
// This user type is user
@DiscriminatorValue("customer") 

public class User extends Model {

    //@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 
	//public Long id;
	
	@Id
    @Constraints.Required
    private String email;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String address;

    @Constraints.Required
    private String password;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private Basket basket;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<ShopOrder> orders;


    // Default constructor
    public  User() {
    }
    // Constructor to initialise object
    public  User(String email, String name, String address, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
    }

	//Generic query helper for entity User with unique id String
    public static Finder<String,User> find = new Finder<String,User>(User.class);
    
    // Static method to authenticate based on username and password
    // Returns user object if found, otherwise NULL
    public	static User authenticate(String email, String password) {
        // If found return the user object with matching username and password
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    // Check if a user is logged in (by id - email address)
    public static User getLoggedIn(String id) {
        if (id == null)
                return null;
        else
            // Find user by id and return object
            return find.byId(id);
    }

   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public List<ShopOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShopOrder> orders) {
        this.orders = orders;
    }

}

