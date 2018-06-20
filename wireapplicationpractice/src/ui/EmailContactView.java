package ui;

public class EmailContactView {

    //By adding this property we are stating
    //that the view layer is dependent on the io
    private UserIO Io;

    // This Constructor allows any class that creates an instance of the view
    // to pass in the values for the view dependencies
    // This allows for dependency injection
    public EmailContactView (UserIO Io) {
        this.Io = Io;
    }

}
