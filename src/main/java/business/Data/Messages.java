package business.Data;

public class Messages {

    //error messages
    private static String EPIC = "Epic sadface: ";

    public static final String LOCKED_USER_ERROR_MESSAGE = EPIC + "Sorry, this user has been locked out.";

    public static final String USERNAME_REQUIRED_ERROR_MESSAGE = EPIC + "Username is required";

    public static final String FIRST_NAME_REQUIRED_ERROR_MESSAGE = "Error: First Name is required";

    //success messages
    public static final String ORDER_COMPLETE_SUCCESS_MESSAGE = "Thank you for your order!";

    public static final String ORDER_COMPLETE_SUCCESS_MESSAGE_DESC = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
}
