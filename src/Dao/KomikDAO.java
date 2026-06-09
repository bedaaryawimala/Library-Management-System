package Dao;
import InterfaceDao.IBukuDAO;
import java.sql.Statement;
import Model.Buku;
import Model.Komik;
/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class KomikDAO extends BukuDAO implements IBukuDAO{
    public void insert(Komik Ko) {
        super.insert(Ko);
        insertNewRole(Ko);
    }

    public void insertNewRole(Komik K) {
        con = dbCon.makeConnection();

        String sql = "INSERT INTO `komik`(`id_buku`, `ilustrator`) VALUES ('"
                + K.getId_buku()
                + "','"
                + K.getIlustrator()
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

    @Override
    public void deleteoldRole(String id_buku) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `komik` WHERE `id_buku` = '" + id_buku + "'";
        System.out.println("Deleting komik...");

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

    public void updateRole(Buku b, String id_buku) {
        con = dbCon.makeConnection();

        String sql = "UPDATE `"+ b.getJenis()
                + "` SET `ilustrator`='"
                + b.getSpecial()
                + "' WHERE `komik`.id_buku = '"
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

    public void update(Buku b, String id_buku, String ilustrator) {
        Komik k = new Komik(ilustrator, b.getId_buku(), b.getJudul(), b.getJenis(), b.getTahun_terbit());
        if(cekPerubahanJenis("komik", id_buku)) {
            deleteoldRole(id_buku);
            insertNewRole(k);
        } else {
            updateRole(k , id_buku);
        }
        super.update(k, id_buku);
    }
}
