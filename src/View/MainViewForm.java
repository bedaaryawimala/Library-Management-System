package View;

import PanelView.BukuMainPanel;
import PanelView.PeminjamMainPanel;
import PanelView.PeminjamanMainPanel;

import javax.swing.*;
import java.awt.*;

public class MainViewForm extends JFrame {

    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;

    private JButton btnPeminjam;
    private JButton btnBuku;
    private JButton btnPeminjaman;

    public MainViewForm() {
        setTitle("W.Library");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());

        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, 720));
        sidePanel.setBackground(new Color(25, 24, 83));
        sidePanel.setLayout(null);

        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(75, 30, 50, 50);

        ImageIcon icon = new ImageIcon("src/icon/library.png");
        Image img = icon.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(img));
        sidePanel.add(logoLabel);

        JLabel titleLabel = new JLabel("W.Library");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(40, 90, 150, 35);
        sidePanel.add(titleLabel);

        btnPeminjam = createMenuButton("Peminjam", "src/Icon/customerIcon.png", 25, 170);
        btnBuku = createMenuButton("Buku", "src/Icon/book.png", 25, 240);
        btnPeminjaman = createMenuButton("Peminjaman", "src/Icon/open-book_2.png", 25, 310);

        sidePanel.add(btnPeminjam);
        sidePanel.add(btnBuku);
        sidePanel.add(btnPeminjaman);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(220, 224, 226));

        mainPanel.add(sidePanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        btnPeminjam.addActionListener(e -> setForm(new PeminjamMainPanel()));
        btnBuku.addActionListener(e -> setForm(new BukuMainPanel()));
        btnPeminjaman.addActionListener(e -> setForm(new PeminjamanMainPanel()));

        setForm(new PeminjamMainPanel());
    }

    private JButton createMenuButton(String text, String iconPath, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 160, 45);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setIconTextGap(10);

        return button;
    }

    public void setForm(JComponent component) {
        contentPanel.removeAll();
        contentPanel.add(component, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        new MainViewForm().setVisible(true);
    }
}
