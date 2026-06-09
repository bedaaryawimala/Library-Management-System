package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public abstract class BukuE extends Model.Buku{

    public BukuE(String id_buku, String judul, String jenis, int tahun_terbit) {
        super(id_buku, judul, jenis, tahun_terbit);
    }

    public BukuE(String judul, String jenis, int tahun_terbit) {
        super(judul, jenis, tahun_terbit);
    }

    public BukuE(String id_buku, String judul, String jenis) {
        super(id_buku, judul, jenis);
    }

    @Override
    public String toString()
    {
        return getId_buku() + " | " + getJudul() + " | " + getJenis() + " | " + getTahun_terbit();
    }

}
