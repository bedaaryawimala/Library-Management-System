package Controller;
import Model.Buku;
import Dao.BukuDAO;
/**
 *
 * @author Beda Arya Wimala - 230712345
 */
public class BukuController {
    BukuDAO bDao = new BukuDAO();

    public String generateId() {
        return "B" + bDao.generateId();
    }

    public Buku searchDataBuku(String id) {
        return bDao.search(id);
    }

    public void deleteDataBuku(String id) {
        bDao.delete(id);
    }
}
