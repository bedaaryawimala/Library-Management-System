package Exception;

public class NoTeleponException extends Exception {
    public String message()
    {
        return "No Telpon Hanya Boleh Terdiri dari angka / diawali +";
    }
}