package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class Novel extends Buku{
    private String cover;

    public Novel(String cover, String id_buku, String judul, String jenis, int tahun_terbit) {
        super(id_buku, judul, "novel", tahun_terbit);
        this.cover = cover;
    }

    public Novel(String cover, String judul, String jenis, int tahun_terbit) {
        super(judul, "novel", tahun_terbit);
        this.cover = cover;
    }

    public Novel(String cover, String id_buku, String judul, String jenis) {
        super(id_buku, judul, "novel");
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getString()
    {
        return super.getString() + " | " + cover;
    }

    @Override
    public String getSpecial()
    {
        return cover;
    }
}
