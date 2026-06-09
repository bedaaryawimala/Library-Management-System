package Exception;

public class InputSpecialAtributeException extends Exception{
    public String message(String jenis)
    {
        return "Atribut Tidak Sesuai untuk jenis " + jenis;
    }
}
