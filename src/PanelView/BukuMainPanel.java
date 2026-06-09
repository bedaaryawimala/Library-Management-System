package PanelView;

import Dao.BukuDAO;
import Dao.KomikDAO;
import Dao.NovelDAO;
import Model.Buku;
import Model.Komik;
import Model.Novel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BukuMainPanel extends JPanel {

    private JTextField inputSearch;
    private JTextField inputIdBuku;
    private JTextField inputJudul;
    private JTextField inputTahunTerbit;
    private JTextField inputCover;

    private JComboBox<String> comboJenisBuku;

    private JButton btnCari;
    private JButton btnTambah;
    private JButton btnBarukan;
    private JButton btnHapus;
    private JButton btnBatalkan;
    private JButton btnSimpan;

    private JTable tableNovel;
    private JTable tableKomik;
    private DefaultTableModel modelNovel;
    private DefaultTableModel modelKomik;
    private BukuDAO bukuDAO;
    private NovelDAO novelDAO;
    private KomikDAO komikDAO;
    private String selectedId;
    private boolean isUpdate;

    public BukuMainPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        bukuDAO = new BukuDAO();
        novelDAO = new NovelDAO();
        komikDAO = new KomikDAO();
        initComponents();
        loadData("");
    }

    private void initComponents() {
        JLabel labelSearch = createLabel("Pencarian Buku", 20, 20);
        add(labelSearch);

        inputSearch = createTextField(20, 45, 520, 35);
        add(inputSearch);

        btnCari = createButton("Cari", new Color(25, 24, 83), 560, 45);
        add(btnCari);

        btnTambah = createButton("Tambah", new Color(34, 190, 84), 25, 110);
        btnBarukan = createButton("Barukan", new Color(255, 183, 0), 135, 110);
        btnHapus = createButton("Hapus", new Color(235, 20, 12), 245, 110);
        btnBatalkan = createButton("Batalkan", new Color(65, 71, 71), 355, 110);

        add(btnTambah);
        add(btnBarukan);
        add(btnHapus);
        add(btnBatalkan);

        JLabel labelId = createLabel("ID Buku", 25, 190);
        add(labelId);

        inputIdBuku = createTextField(25, 215, 270, 35);
        add(inputIdBuku);

        JLabel labelTahun = createLabel("Tahun Terbit", 350, 190);
        add(labelTahun);

        inputTahunTerbit = createTextField(350, 215, 270, 35);
        add(inputTahunTerbit);

        JLabel labelCover = createLabel("Cover / Ilustrator", 720, 190);
        add(labelCover);

        inputCover = createTextField(720, 215, 270, 35);
        add(inputCover);

        JLabel labelJudul = createLabel("Judul", 25, 285);
        add(labelJudul);

        inputJudul = createTextField(25, 310, 270, 35);
        add(inputJudul);

        JLabel labelJenis = createLabel("Jenis Buku", 350, 285);
        add(labelJenis);

        comboJenisBuku = new JComboBox<>();
        comboJenisBuku.addItem("novel");
        comboJenisBuku.addItem("komik");
        comboJenisBuku.setBounds(350, 310, 130, 35);
        add(comboJenisBuku);

        btnSimpan = createButton("Simpan", new Color(34, 190, 84), 900, 370);
        add(btnSimpan);

        modelNovel = new DefaultTableModel();
        modelNovel.addColumn("ID Buku");
        modelNovel.addColumn("Judul Novel");
        modelNovel.addColumn("Tahun Terbit");
        modelNovel.addColumn("Cover");

        tableNovel = new JTable(modelNovel);
        JScrollPane scrollNovel = new JScrollPane(tableNovel);
        scrollNovel.setBounds(20, 420, 500, 220);
        add(scrollNovel);

        modelKomik = new DefaultTableModel();
        modelKomik.addColumn("ID Buku");
        modelKomik.addColumn("Judul Komik");
        modelKomik.addColumn("Tahun Terbit");
        modelKomik.addColumn("Ilustrator");

        tableKomik = new JTable(modelKomik);
        JScrollPane scrollKomik = new JScrollPane(tableKomik);
        scrollKomik.setBounds(540, 420, 500, 220);
        add(scrollKomik);

        btnTambah.addActionListener(e -> saveData());
        btnSimpan.addActionListener(e -> saveData());
        btnBarukan.addActionListener(e -> updateData());
        btnHapus.addActionListener(e -> deleteData());
        btnBatalkan.addActionListener(e -> clearForm());
        btnCari.addActionListener(e -> loadData(inputSearch.getText()));
        tableNovel.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                setSelectedDataFromTable(tableNovel, modelNovel, "novel");
            }
        });
        tableKomik.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                setSelectedDataFromTable(tableKomik, modelKomik, "komik");
            }
        });
    }

    private void loadData(String keyword) {
        modelNovel.setRowCount(0);
        modelKomik.setRowCount(0);
        addBukuRows(bukuDAO.showData("novel"), modelNovel, keyword);
        addBukuRows(bukuDAO.showData("komik"), modelKomik, keyword);
    }

    private void addBukuRows(List<Buku> list, DefaultTableModel model, String keyword) {
        for (Buku b : list) {
            if (keyword == null || keyword.trim().isEmpty()
                    || b.getJudul().toLowerCase().contains(keyword.trim().toLowerCase())
                    || b.getId_buku().toLowerCase().contains(keyword.trim().toLowerCase())) {
                model.addRow(new Object[]{
                        b.getId_buku(),
                        b.getJudul(),
                        b.getTahun_terbit(),
                        b.getSpecial()
                });
            }
        }
    }

    private void saveData() {
        try {
            String jenis = comboJenisBuku.getSelectedItem().toString();
            String id = inputIdBuku.getText().trim();
            if (id.isEmpty()) {
                id = "B" + bukuDAO.generateId();
            }

            int tahunTerbit = Integer.parseInt(inputTahunTerbit.getText());
            if (jenis.equals("novel")) {
                Novel novel = new Novel(inputCover.getText(), id, inputJudul.getText(), jenis, tahunTerbit);
                novelDAO.insert(novel);
            } else {
                Komik komik = new Komik(inputCover.getText(), id, inputJudul.getText(), jenis, tahunTerbit);
                komikDAO.insert(komik);
            }

            clearForm();
            loadData("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tahun terbit harus berupa angka.");
        }
    }

    private void updateData() {
        if (selectedId == null) {
            setSelectedData();
        }

        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data buku yang mau diubah.");
            return;
        }

        try {
            String jenis = comboJenisBuku.getSelectedItem().toString();
            int tahunTerbit = Integer.parseInt(inputTahunTerbit.getText());

            if (jenis.equals("novel")) {
                Novel novel = new Novel(inputCover.getText(), inputIdBuku.getText(), inputJudul.getText(), jenis, tahunTerbit);
                novelDAO.update(novel, selectedId, inputCover.getText());
            } else {
                Komik komik = new Komik(inputCover.getText(), inputIdBuku.getText(), inputJudul.getText(), jenis, tahunTerbit);
                komikDAO.update(komik, selectedId, inputCover.getText());
            }

            clearForm();
            loadData("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tahun terbit harus berupa angka.");
        }
    }

    private void setSelectedData() {
        if (tableNovel.getSelectedRow() >= 0) {
            setSelectedDataFromTable(tableNovel, modelNovel, "novel");
        } else if (tableKomik.getSelectedRow() >= 0) {
            setSelectedDataFromTable(tableKomik, modelKomik, "komik");
        }
    }

    private void setSelectedDataFromTable(JTable table, DefaultTableModel model, String jenis) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        selectedId = model.getValueAt(selectedRow, 0).toString();
        inputIdBuku.setText(selectedId);
        inputJudul.setText(model.getValueAt(selectedRow, 1).toString());
        inputTahunTerbit.setText(model.getValueAt(selectedRow, 2).toString());
        inputCover.setText(model.getValueAt(selectedRow, 3).toString());
        comboJenisBuku.setSelectedItem(jenis);
        isUpdate = true;

        if (jenis.equals("novel")) {
            tableKomik.clearSelection();
        } else {
            tableNovel.clearSelection();
        }
    }

    private void deleteData() {
        if (selectedId == null) {
            setSelectedData();
        }

        if (selectedId != null) {
            novelDAO.deleteoldRole(selectedId);
            komikDAO.deleteoldRole(selectedId);
            bukuDAO.delete(selectedId);
            clearForm();
            loadData("");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data buku yang mau dihapus.");
        }
    }

    private void clearForm() {
        selectedId = null;
        isUpdate = false;
        inputIdBuku.setText("");
        inputJudul.setText("");
        inputTahunTerbit.setText("");
        inputCover.setText("");
        tableNovel.clearSelection();
        tableKomik.clearSelection();
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(new Color(65, 71, 71));
        textField.setForeground(Color.WHITE);
        return textField;
    }

    private JButton createButton(String text, Color color, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 95, 35);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        return button;
    }
}
