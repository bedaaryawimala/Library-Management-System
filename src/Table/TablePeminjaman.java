package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Peminjaman;

public class TablePeminjaman extends AbstractTableModel {

    private List<Peminjaman> list;

    public TablePeminjaman(List<Peminjaman> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Peminjaman p = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return p.getId_jadwal_bertugas();
            case 1:
                return p.getPeminjamE().getNama();
            case 2:
                return p.getBukuE().getJudul();
            case 3:
                return p.getGenre();
            case 4:
                return p.getWilayah();
            case 5:
                return p.getTanggal_meminjam();
            case 6:
                return p.getTanggal_mengembalikan();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No Peminjaman";
            case 1:
                return "Nama Peminjam";
            case 2:
                return "Judul Buku";
            case 3:
                return "Genre";
            case 4:
                return "Wilayah Buku";
            case 5:
                return "Tanggal Peminjaman";
            case 6:
                return "Tanggal Pengembalian";
            default:
                return null;
        }
    }
}
