package Model;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class Peminjam {
    private int id_peminjam;
    private String nama;
    private int umur;
    private String notelp;

    public Peminjam(int id_peminjam, String nama, int umur, String notelp) {
        this.id_peminjam = id_peminjam;
        this.nama = nama;
        this.umur = umur;
        this.notelp = notelp;
    }

    public Peminjam(String nama, int umur, String notelp) {
        this.nama = nama;
        this.umur = umur;
        this.notelp = notelp;
    }

    public int getId_peminjam() {
        return id_peminjam;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setId_peminjam(int id_peminjam) {
        this.id_peminjam = id_peminjam;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }


}
