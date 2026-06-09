package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class PeminjamE extends Model.Peminjam{

    public PeminjamE(int id_peminjam, String nama, int umur, String notelp) {
        super(id_peminjam, nama, umur, notelp);
    }

    public PeminjamE(String nama, int umur, String notelp) {
        super(nama, umur, notelp);
    }

    @Override
    public String toString()
    {
        return getNama();
    }
}
