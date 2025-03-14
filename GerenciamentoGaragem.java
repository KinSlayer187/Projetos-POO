import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class GerenciamentoGaragem extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private Connection conexao;
    
    public GerenciamentoGaragem() {
        setTitle("Gerenciamento de Garagem");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        conectarBanco();
        
        modeloTabela = new DefaultTableModel();
        modeloTabela.setColumnIdentifiers(new String[]{"Placa", "Entrada", "Tempo (h)", "Taxa R$"});
        tabela = new JTable(modeloTabela);
        
        JButton atualizarBtn = new JButton("Atualizar Lista");
        atualizarBtn.addActionListener(event -> carregarVeiculos());
        
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painel.add(atualizarBtn, BorderLayout.SOUTH);
        
        add(painel);
        carregarVeiculos();
    }
    
    private void conectarBanco() {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:garagem.db");
            Statement stmt = conexao.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS veiculos (placa TEXT, entrada DATETIME, tempo INTEGER, taxa REAL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void carregarVeiculos() {
        modeloTabela.setRowCount(0);
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM veiculos");
            while (rs.next()) {
                Vector<Object> linha = new Vector<>();
                linha.add(rs.getString("placa"));
                linha.add(rs.getString("entrada"));
                linha.add(rs.getInt("tempo"));
                linha.add(rs.getDouble("taxa"));
                modeloTabela.addRow(linha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GerenciamentoGaragem().setVisible(true));
    }
}
