package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class KomikE extends Model.BukuE {
    private String ilustrator;

    public KomikE(String id_buku, String judul, String jenis, int tahun_terbit, String ilustrator) {
        super(id_buku, judul, jenis, tahun_terbit);
        this.ilustrator = ilustrator;
    }

    public KomikE(String judul, String jenis, int tahun_terbit, String ilustrator) {
        super(judul, jenis, tahun_terbit);
        this.ilustrator = ilustrator;
    }

    public KomikE(String id_buku, String judul, String jenis, String ilustrator) {
        super(id_buku, judul, jenis);
        this.ilustrator = ilustrator;
    }

    public String getIlustratork() {
        return ilustrator;
    }

    @Override
    public String getSpecial() {
        return ilustrator;
    }
}
