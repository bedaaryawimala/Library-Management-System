package Dao;
import Connection.DBConnection;
import InterfaceDao.IDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.*;
/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class PeminjamanDAO implements IDAO<Peminjaman, String>{
    protected DBConnection dbCon = new DBConnection();
    protected Connection con;


    @Override
    public void insert(Peminjaman p)
    {
        con = dbCon.makeConnection();

        String sql = "INSERT INTO `peminjaman` "
                + "(`id_peminjam`, `id_buku`, `tanggal_peminjaman`, `tanggal_pengembalian`, `wilayah`, `genre`)"
                + " VALUES "
                + "('" + p.getId_Peminjam() + "', '" + p.getId_Buku() + "', '" + p.getTanggal_meminjam() + "', '"
                + p.getTanggal_mengembalikan() + "', '" + p.getWilayah() + "', '" + p.getGenre() + "')";
        System.out.println("Adding Peminjaman...");

        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " Peminjaman");
            statement.close();
        }catch(Exception e){
            System.out.println("Error adding Peminjaman");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public List<Peminjaman> showData(String query){
        con = dbCon.makeConnection();

        String sql = "SELECT PJ.id_peminjaman, PJ.id_peminjam, PJ.id_buku, PJ.tanggal_peminjaman, "
                + "PJ.tanggal_pengembalian, PJ.wilayah, PJ.genre, "
                + "P.nama, P.umur, P.notelp, "
                + "B.judul, B.jenis, B.tahun_terbit, K.ilustrator, N.cover "
                + "FROM peminjaman PJ "
                + "JOIN peminjam P ON PJ.id_peminjam = P.id_peminjam "
                + "JOIN buku B ON PJ.id_buku = B.id_buku "
                + "LEFT JOIN komik K ON B.id_buku = K.id_buku "
                + "LEFT JOIN novel N ON B.id_buku = N.id_buku "
                + "WHERE (P.nama LIKE '%" + query + "%' "
                + "OR B.judul LIKE '%" + query + "%');";

        System.out.println("Mengambil data PeminjamanBuku...");
        List<Peminjaman> listPeminjaman= new ArrayList<>();
        BukuE be = null;

        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null){
                while(rs.next()){
                    PeminjamE p = new PeminjamE(
                            rs.getInt("id_peminjam"),
                            rs.getString("nama"),
                            rs.getInt("umur"),
                            rs.getString("notelp")
                    );

                    if(rs.getString("jenis").equals("novel")){
                        be = new NovelE(
                                rs.getString("cover"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    } else {
                        be = new KomikE(
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"),
                                rs.getString("ilustrator"));
                    };

                    Peminjaman PJ = new Peminjaman(
                            rs.getInt("id_peminjaman"),
                            rs.getInt("id_peminjam"),
                            rs.getString("id_buku"),
                            rs.getString("genre"),
                            rs.getString("wilayah"),
                            rs.getString("tanggal_peminjaman"),
                            rs.getString("tanggal_pengembalian"),
                            p,
                            be
                    );

                    listPeminjaman.add(PJ);
                }
                rs.close();
                statement.close();
            }

        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        System.out.println("Berhasil");
        dbCon.closeConnection();
        return listPeminjaman;
    }

    @Override
    public void update(Peminjaman pj, String id) {
        con = dbCon.makeConnection();

        String sql = "UPDATE `peminjaman` SET "
                + "`id_peminjam`= '" + pj.getId_Peminjam() + "', `id_buku`= '" + pj.getId_Buku() + "', `genre`= '" + pj.getGenre() + "', "
                + "`wilayah`= '" + pj.getWilayah() + "', `tanggal_peminjaman`= '" + pj.getTanggal_meminjam() + "', "
                + "`tanggal_pengembalian` = '" + pj.getTanggal_mengembalikan() + "' "
                + "WHERE `id_peminjaman` = '" + id + "'";

        System.out.println("Editing Peminjaman Buku...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " Peminjaman" + id);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating Peminjaman...");
            System.out.println(e);
        }

        dbCon.closeConnection();
    }

    @Override
    public void delete(String id){
        con = dbCon.makeConnection();

        String sql = "DELETE FROM peminjaman "
                + "WHERE id_peminjaman = " + id + "";

        System.out.println("Deleting PeminjamanBuku...");

        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Delete" + result + " Peminjaman" + id);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Deleting Peminjaman...");
            System.out.println(e);
        }

        dbCon.closeConnection();
    }

    @Override
    public Peminjaman search(String data){
        return null;
    }
}
