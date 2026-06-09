package Dao;
import Model.PeminjamE;
import InterfaceDao.IShowForDropdown;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class PeminjamDAOE extends Dao.PeminjamDAO implements IShowForDropdown<PeminjamE> {

    @Override
    public List<PeminjamE> IShowForDropdown()
    {
        con = dbCon.makeConnection();

        String sql = "SELECT * FROM peminjam";
        System.out.println("Fetching Data...");
        List<PeminjamE> c =new ArrayList();

        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs != null)
            {
                while(rs.next())
                {
                    c.add(new PeminjamE(
                            rs.getInt("id_peminjam"),
                            rs.getString("nama"),
                            rs.getInt("umur"),
                            rs.getString("notelp")));
                }
                rs.close();
                statement.close();
            }
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
}
