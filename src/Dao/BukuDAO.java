package Dao;
import Connection.DBConnection;
import InterfaceDao.IDAO;
import java.sql.*;
import java.util.*;
import Model.Buku;
import Model.Komik;
import Model.Novel;
/**
 *
 * @author Beda Arya Wimala 230712345
 */
public class BukuDAO implements IDAO<Buku, String> {
    protected DBConnection dbCon = new DBConnection();
    protected Connection con;

    @Override
    public void insert(Buku b) {
        con = dbCon.makeConnection();

        String sql = "INSERT INTO `buku`(`id_buku`, `judul`, `jenis`, `tahun_terbit`) "
                + "VALUES ('"+b.getId_buku()+"', '"+b.getJudul()+"', '"+b.getJenis()+"', '"+b.getTahun_terbit()+"')";

        System.out.println("Adding buku...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " buku");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error adding buku...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public Buku search(String id_buku) {
        con = dbCon.makeConnection();

        String sql = "SELECT buku.*, komik.ilustrator, novel.cover FROM buku\n" +
                "LEFT JOIN komik ON buku.id_buku = komik.id_buku\n" +
                "LEFT JOIN novel ON buku.id_buku = novel.id_buku\n" +
                "WHERE buku.id_buku = '" + id_buku + "'";

        System.out.println("Searching Kendaraan...");
        Buku b = null;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null) {
                while(rs.next()) {
                    if(rs.getString("jenis").equals("komik")) {
                        b = new Komik(
                                rs.getString("ilustrator"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    } else {
                        b = new Novel(
                                rs.getString("cover"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    }
                }

            }
            rs.close();
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return b;
    }

    @Override
    public List<Buku> showData(String jenis) {
        con = dbCon.makeConnection();

        String sql = "SELECT buku.*, komik.ilustrator, novel.cover FROM buku\n" +
                "LEFT JOIN komik ON buku.id_buku = komik.id_buku\n" +
                "LEFT JOIN novel ON buku.id_buku = novel.id_buku\n" +
                "WHERE buku.jenis = '" + jenis + "';";

        System.out.println("Fetching Data...");
        List<Buku> list = new ArrayList();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Buku b = null;

            if(rs != null) {
                while(rs.next()) {
                    if(rs.getString("jenis").equals("komik")) {
                        b = new Komik(
                                rs.getString("ilustrator"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    } else {
                        b = new Novel(
                                rs.getString("cover"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    }
                    list.add(b);
                }
            }
            rs.close();
            statement.close();

        } catch(Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public void update(Buku b, String id_buku) {
        con = dbCon.makeConnection();

        String sql = "UPDATE `buku` SET " +
                "`judul`='" + b.getJudul() + "'," +
                "`jenis`='" + b.getJenis() + "'," +
                "`tahun_terbit`='" + b.getTahun_terbit() + "' " +
                "WHERE id_buku='" + id_buku + "'";

        System.out.println("Updating buku...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " Buku " + id_buku);
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Updating Buku...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public void delete(String id_buku) {
        con = dbCon.makeConnection();

        String sql = "DELETE FROM `buku` WHERE `id_buku` = '"+id_buku+"'";

        System.out.println("Deleting Buku...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " Buku " + id_buku);
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Updating Buku...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_buku, 2) AS SIGNED)) AS highest_number FROM buku WHERE id_buku LIKE 'B%';";

        System.out.println("Generating Id...");
        int id = 0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null && rs.next()) {
                if(!rs.wasNull()) {
                    id = rs.getInt("highest_number") + 1;
                }
            }
            rs.close();
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return id;
    }

    public boolean cekPerubahanJenis(String jenis, String id_buku) {
        con = dbCon.makeConnection();

        String sql = "SELECT jenis!='" + jenis + "'" + "as result " +
                "FROM `buku` " +
                "WHERE id_buku = '" + id_buku + "';";

        System.out.println(sql);
        System.out.println("Checking Result...");
        boolean result = false;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null) {
                while(rs.next()) {
                    result = rs.getBoolean("result");
                }
                rs.close();
                statement.close();
            }
        } catch(Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        System.out.println("The result is " + result);
        return result;
    }
}
