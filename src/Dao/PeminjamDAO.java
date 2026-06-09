package Dao;
import Connection.DBConnection;
import InterfaceDao.IDAO;
import java.sql.*;
import java.util.*;
import Model.Peminjam;
/**
 *
 * @author Beda Arya Wimala
 */
public class PeminjamDAO implements IDAO<Peminjam, Integer>{
    protected DBConnection dbCon = new DBConnection();
    protected Connection con;

    @Override
    public void insert(Peminjam p) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO `peminjam` (`nama`, `umur`, `notelp`) " + "VALUES ('"
                + p.getNama() + "', '" + p.getUmur() + "', '" + p.getNotelp() + "')";

        System.out.println("Adding peminjam...");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            statement.close();
            System.out.println("Added " + result + " peminjam");
        } catch (Exception e) {
            System.out.println("Error adding peminjam...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public Peminjam search(Integer id_peminjam) {
        con = dbCon.makeConnection();

        String sql = "SELECT * FROM peminjam WHERE id_peminjam='" + id_peminjam + "'";
        System.out.println("Searching peminjam...");
        Peminjam p = null;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs!=null){
                while (rs.next()) {
                    p = new Peminjam(rs.getInt("id_peminjam")
                            , rs.getString("nama")
                            , rs.getInt("umur")
                            , rs.getString("notelp"));
                }
                rs.close();
                statement.close();
            }

        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return p;
    }

    @Override
    public List<Peminjam> showData(Integer data) {
        con = dbCon.makeConnection();

        String sql = "SELECT * FROM peminjam";
        System.out.println("Fetching Data...");
        List<Peminjam> p = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs!=null)
            {
                while (rs.next()) {
                    p.add(new Peminjam(rs.getInt("id_peminjam")
                            ,rs.getString("nama")
                            ,rs.getInt("umur")
                            , rs.getString("notelp")));
                }
                rs.close();
                statement.close();
            }

        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return p;
    }

    @Override
    public void update(Peminjam p, Integer id_peminjam) {
        con = dbCon.makeConnection();
        String sql = "UPDATE `peminjam` SET " + "`nama` = '" + p.getNama() + "',"+ "`umur` = '" + p.getUmur()
                + "',"+ "`notelp` = '" + p.getNotelp() + "' " + "WHERE `id_peminjam` = '" + id_peminjam + "'";

        System.out.println("Updating peminjam");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " peminjam " + id_peminjam);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating peminjam...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public void delete(Integer id_peminjam) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `peminjam` WHERE `id_peminjam` = " + id_peminjam + "";
        System.out.println("Deleting peminjam...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " peminjam " + id_peminjam);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating peminjam...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
}
