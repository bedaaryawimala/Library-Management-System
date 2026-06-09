package PanelView;

import Dao.PeminjamDAO;
import Model.Peminjam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PeminjamMainPanel extends JPanel {

    private JTextField inputSearch;
    private JTextField inputNama;
    private JTextField inputUmur;
    private JTextField inputNoTelp;

    private JButton btnCari;
    private JButton btnTambah;
    private JButton btnBarukan;
    private JButton btnHapus;
    private JButton btnBatalkan;
    private JButton btnSimpan;

    private JTable tablePeminjam;
    private DefaultTableModel tableModel;
    private PeminjamDAO peminjamDAO;
    private Integer selectedId;
    private boolean isUpdate;

    public PeminjamMainPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        peminjamDAO = new PeminjamDAO();
        initComponents();
        loadData("");
    }

    private void initComponents() {
        JLabel labelSearch = new JLabel("Pencarian Peminjam");
        labelSearch.setBounds(20, 20, 200, 20);
        labelSearch.setFont(new Font("Arial", Font.BOLD, 14));
        add(labelSearch);

        inputSearch = new JTextField();
        inputSearch.setBounds(20, 45, 520, 35);
        inputSearch.setBackground(new Color(65, 71, 71));
        inputSearch.setForeground(Color.WHITE);
        add(inputSearch);

        btnCari = new JButton("Cari");
        btnCari.setBounds(560, 45, 90, 35);
        btnCari.setBackground(new Color(25, 24, 83));
        btnCari.setForeground(Color.WHITE);
        btnCari.setFont(new Font("Arial", Font.BOLD, 13));
        add(btnCari);

        btnTambah = createButton("Tambah", new Color(34, 190, 84), 25, 110);
        btnBarukan = createButton("Barukan", new Color(255, 183, 0), 135, 110);
        btnHapus = createButton("Hapus", new Color(235, 20, 12), 245, 110);
        btnBatalkan = createButton("Batalkan", new Color(65, 71, 71), 355, 110);

        add(btnTambah);
        add(btnBarukan);
        add(btnHapus);
        add(btnBatalkan);

        JLabel labelNama = createLabel("Nama Peminjam", 25, 190);
        add(labelNama);

        inputNama = createTextField(25, 215, 280, 35);
        add(inputNama);

        JLabel labelUmur = createLabel("Umur", 350, 190);
        add(labelUmur);

        inputUmur = createTextField(350, 215, 260, 35);
        add(inputUmur);

        JLabel labelNoTelp = createLabel("Nomor Telepon", 25, 280);
        add(labelNoTelp);

        inputNoTelp = createTextField(25, 305, 280, 35);
        add(inputNoTelp);

        btnSimpan = createButton("Simpan", new Color(34, 190, 84), 900, 370);
        add(btnSimpan);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Peminjam");
        tableModel.addColumn("Nama Peminjam");
        tableModel.addColumn("Umur");
        tableModel.addColumn("No Telepon");

        tablePeminjam = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablePeminjam);
        scrollPane.setBounds(20, 420, 1000, 220);
        add(scrollPane);

        btnTambah.addActionListener(e -> saveData());
        btnSimpan.addActionListener(e -> saveData());
        btnBarukan.addActionListener(e -> updateData());
        btnHapus.addActionListener(e -> deleteData());
        btnBatalkan.addActionListener(e -> clearForm());
        btnCari.addActionListener(e -> loadData(inputSearch.getText()));
        tablePeminjam.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                setSelectedData();
            }
        });
    }

    private void loadData(String keyword) {
        tableModel.setRowCount(0);
        List<Peminjam> list = peminjamDAO.showData(0);

        for (Peminjam p : list) {
            if (keyword == null || keyword.trim().isEmpty()
                    || p.getNama().toLowerCase().contains(keyword.trim().toLowerCase())
                    || String.valueOf(p.getId_peminjam()).contains(keyword.trim())) {
                tableModel.addRow(new Object[]{
                        p.getId_peminjam(),
                        p.getNama(),
                        p.getUmur(),
                        p.getNotelp()
                });
            }
        }
    }

    private void saveData() {
        try {
            peminjamDAO.insert(getPeminjamFromInput());
            clearForm();
            loadData("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Umur harus berupa angka.");
        }
    }

    private void updateData() {
        if (selectedId == null) {
            setSelectedData();
        }

        if (selectedId == null) {
            JOptionPane.showMessageDialog(this, "Pilih data peminjam yang mau diubah.");
            return;
        }

        try {
            peminjamDAO.update(getPeminjamFromInput(), selectedId);
            clearForm();
            loadData("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Umur harus berupa angka.");
        }
    }

    private Peminjam getPeminjamFromInput() {
        return new Peminjam(
                inputNama.getText(),
                Integer.parseInt(inputUmur.getText()),
                inputNoTelp.getText()
        );
    }

    private void setSelectedData() {
        int selectedRow = tablePeminjam.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        selectedId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        inputNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
        inputUmur.setText(tableModel.getValueAt(selectedRow, 2).toString());
        inputNoTelp.setText(tableModel.getValueAt(selectedRow, 3).toString());
        isUpdate = true;
    }

    private void deleteData() {
        if (selectedId == null) {
            setSelectedData();
        }

        if (selectedId != null) {
            peminjamDAO.delete(selectedId);
            clearForm();
            loadData("");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data peminjam yang mau dihapus.");
        }
    }

    private void clearForm() {
        selectedId = null;
        isUpdate = false;
        inputNama.setText("");
        inputUmur.setText("");
        inputNoTelp.setText("");
        tablePeminjam.clearSelection();
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
