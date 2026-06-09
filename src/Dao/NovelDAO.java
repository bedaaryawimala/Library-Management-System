package Dao;
import InterfaceDao.IBukuDAO;
import java.sql.Statement;
import Model.Buku;
import Model.Novel;
/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class NovelDAO extends BukuDAO implements IBukuDAO{

    public void insert(Novel nvl) {
        super.insert(nvl);
        insertNewRole(nvl);
    }

    public void insertNewRole(Novel nvl) {
        con = dbCon.makeConnection();

        String sql = "INSERT INTO `novel`(`id_buku`, `cover`) VALUES ('"
                + nvl.getId_buku()
                + "','"
                + nvl.getCover()
                + "')";

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

    public void updateRole(Buku b, String id_buku) {
        con = dbCon.makeConnection();

        String sql = "UPDATE `" + b.getJenis()
                + "` SET `cover`='"
                + b.getSpecial()
                + "' WHERE `novel`.id_buku = '"
                + id_buku
                + "'";

        System.out.println("Updating Jenis buku...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " buku " + id_buku);
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Updating buku...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public void deleteoldRole(String id_buku) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `novel` WHERE `id_buku` = '"+id_buku+"'";
        System.out.println("Deleting novel...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " buku " + id_buku);
            statement.close();
        } catch(Exception e) {
            System.out.println("Error Updating buku...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    public void update(Buku b, String id_buku, String cover) {
        Novel n = new Novel(cover, b.getId_buku(), b.getJudul(), b.getJenis(), b.getTahun_terbit());
        if(cekPerubahanJenis("novel", id_buku)) {
            deleteoldRole(id_buku);
            insertNewRole(n);
        } else {
            updateRole(b, id_buku);
        }
        super.update(b, id_buku);
    }
}
