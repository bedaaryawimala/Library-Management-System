package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class Komik extends Buku{
    private String ilustrator;

    public Komik(String ilustrator, String id_buku, String judul, String jenis, int tahun_terbit) {
        super(id_buku, judul, "komik", tahun_terbit);
        this.ilustrator = ilustrator;
    }

    public Komik(String ilustrator, String judul, String jenis, int tahun_terbit) {
        super(judul, "komik", tahun_terbit);
        this.ilustrator = ilustrator;
    }

    public Komik(String ilustrator, String id_buku, String judul, String jenis) {
        super(id_buku, judul, "komik");
        this.ilustrator = ilustrator;
    }

    public String getIlustrator() {
        return ilustrator;
    }

    public void setIlustrator(String ilustrator) {
        this.ilustrator = ilustrator;
    }

    public String getString()
    {
        return super.getString() + " | " + ilustrator;
    }

    @Override
    public String getSpecial()
    {
        return ilustrator;
    }
}
