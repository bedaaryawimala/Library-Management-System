package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class Peminjaman {
    private int id_jadwal_bertugas;
    private int id_Peminjam;
    private String id_Buku;
    private String genre;
    private String wilayah;
    private String tanggal_meminjam;
    private String tanggal_mengembalikan;
    private PeminjamE PeminjamE;
    private BukuE BukuE;

    public Peminjaman(int id_jadwal_bertugas, int id_Peminjam, String id_Buku, String genre, String wilayah, String tanggal_meminjam, String tanggal_mengembalikan, PeminjamE PeminjamE, BukuE BukuE) {
        this.id_jadwal_bertugas = id_jadwal_bertugas;
        this.id_Peminjam = id_Peminjam;
        this.id_Buku = id_Buku;
        this.genre = genre;
        this.wilayah = wilayah;
        this.tanggal_meminjam = tanggal_meminjam;
        this.tanggal_mengembalikan = tanggal_mengembalikan;
        this.PeminjamE = PeminjamE;
        this.BukuE = BukuE;
    }

    public Peminjaman(int id_Peminjam, String id_Buku, String genre, String wilayah, String tanggal_meminjam, String tanggal_mengembalikan, PeminjamE PeminjamE, BukuE BukuE) {
        this.id_Peminjam = id_Peminjam;
        this.id_Buku = id_Buku;
        this.genre = genre;
        this.wilayah = wilayah;
        this.tanggal_meminjam = tanggal_meminjam;
        this.tanggal_mengembalikan = tanggal_mengembalikan;
        this.PeminjamE = PeminjamE;
        this.BukuE = BukuE;
    }

    public int getId_jadwal_bertugas() {
        return id_jadwal_bertugas;
    }

    public int getId_Peminjam() {
        return id_Peminjam;
    }

    public String getId_Buku() {
        return id_Buku;
    }

    public String getGenre() {
        return genre;
    }

    public String getWilayah() {
        return wilayah;
    }

    public String getTanggal_meminjam() {
        return tanggal_meminjam;
    }

    public String getTanggal_mengembalikan() {
        return tanggal_mengembalikan;
    }

    public PeminjamE getPeminjamE() {
        return PeminjamE;
    }

    public BukuE getBukuE() {
        return BukuE;
    }

    public void setId_jadwal_bertugas(int id_jadwal_bertugas) {
        this.id_jadwal_bertugas = id_jadwal_bertugas;
    }

    public void setId_Peminjam(int id_Peminjam) {
        this.id_Peminjam = id_Peminjam;
    }

    public void setId_Buku(String id_Buku) {
        this.id_Buku = id_Buku;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public void setTanggal_meminjam(String tanggal_meminjam) {
        this.tanggal_meminjam = tanggal_meminjam;
    }

    public void setTanggal_mengembalikan(String tanggal_mengembalikan) {
        this.tanggal_mengembalikan = tanggal_mengembalikan;
    }

    public void setPeminjamE(PeminjamE PeminjamE) {
        this.PeminjamE = PeminjamE;
    }

    public void setBukuE(BukuE BukuE) {
        this.BukuE = BukuE;
    }



}
