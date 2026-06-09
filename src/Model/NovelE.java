package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class NovelE extends Model.BukuE{
    private String cover;

    public NovelE(String cover, String id_buku, String judul, String jenis, int tahun_terbit) {
        super(id_buku, judul, jenis, tahun_terbit);
        this.cover = cover;
    }

    public NovelE(String cover, String judul, String jenis, int tahun_terbit) {
        super(judul, jenis, tahun_terbit);
        this.cover = cover;
    }

    public NovelE(String cover, String id_buku, String judul, String jenis) {
        super(id_buku, judul, jenis);
        this.cover = cover;
    }

    @Override
    public String getSpecial() {
        return cover;
    }

}
