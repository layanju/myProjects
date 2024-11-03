package pack305;

public class Session {

    private static String username;
    private static String Fname;
    private static String Lname;

    public static String getFname() {
        return Fname;
    }

    public static void setFname(String Fname) {
        Session.Fname = Fname;
    }

    public static String getLname() {
        return Lname;
    }

    public static void setLname(String Lname) {
        Session.Lname = Lname;
    }

    public static void setUsername(String user) {
        username = user;
    }

    public static String getUsername() {
        return username;
    }

}
