package Dao;

import InterfaceDao.IShowForDropdown;
import Model.BukuE;
import Model.KomikE;
import Model.NovelE;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class BukuDAOE extends Dao.BukuDAO implements IShowForDropdown<BukuE>{

    @Override
    public List<BukuE> IShowForDropdown()
    {
        con = dbCon.makeConnection();

        String sql = "SELECT buku.*, komik.ilustrator, novel.cover FROM buku\n" +
                "LEFT JOIN komik ON buku.id_buku = komik.id_buku\n" +
                "LEFT JOIN novel ON buku.id_buku = novel.id_buku;";
        System.out.println("Fetching Data...");

        List<BukuE> list = new ArrayList();

        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            BukuE b = null;

            if(rs!=null)
            {
                while(rs.next())
                {
                    if(rs.getString("jenis").equals("novel"))
                    {
                        b = new NovelE(
                                rs.getString("cover"),
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"));
                    }else{
                        b = new KomikE(
                                rs.getString("id_buku"),
                                rs.getString("judul"),
                                rs.getString("jenis"),
                                rs.getInt("tahun_terbit"),
                                rs.getString("ilustrator"));
                    }
                    list.add(b);
                }
                rs.close();
                statement.close();
            }

        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return list;
    }
}
