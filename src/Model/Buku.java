package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public abstract class Buku {
    private String id_buku;
    private String judul;
    private String jenis;
    private int tahun_terbit;

    public Buku(String id_buku, String judul, String jenis, int tahun_terbit) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.jenis = jenis;
        this.tahun_terbit = tahun_terbit;
    }

    public Buku(String judul, String jenis, int tahun_terbit) {
        this.judul = judul;
        this.jenis = jenis;
        this.tahun_terbit = tahun_terbit;
    }

    public Buku(String id_buku, String judul, String jenis) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.jenis = jenis;
    }

    public String getId_buku() {
        return id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public String getJenis() {
        return jenis;
    }

    public int getTahun_terbit() {
        return tahun_terbit;
    }

    public void setId_buku(String id_buku) {
        this.id_buku = id_buku;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setTahun_terbit(int tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getString() {
        return id_buku + " | " + judul + " | " + tahun_terbit;
    }

    public abstract String getSpecial();
}
