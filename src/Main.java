import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import utils.*;

public class Main {
    public static Integer[] stringToIntegerArray(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new Integer[0];
        }
        String[] partes = input.trim().split("\\s+");
        Integer[] resultado = new Integer[partes.length];
        for (int i = 0; i < partes.length; i++) {
            resultado[i] = Integer.valueOf(partes[i]);
        }
        return resultado;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (Exception ignored) { }

            JFrame frame = new JFrame("Simulador");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            Color fundoCampo = Color.WHITE;
            Color textoCampo = Color.BLACK;
            Font fonteCampos = new Font("SansSerif", Font.PLAIN, 14);

            JTextField paginasField = new JTextField(20);
            paginasField.setBackground(fundoCampo);
            paginasField.setForeground(textoCampo);
            paginasField.setFont(fonteCampos);
            paginasField.setCaretColor(textoCampo);

            JTextField quartosField = new JTextField(10);
            quartosField.setBackground(fundoCampo);
            quartosField.setForeground(textoCampo);
            quartosField.setFont(fonteCampos);
            quartosField.setCaretColor(textoCampo);

            JTextArea resultadoArea = new JTextArea(6, 30);
            resultadoArea.setEditable(false);
            resultadoArea.setBackground(fundoCampo);
            resultadoArea.setForeground(textoCampo);
            resultadoArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
            resultadoArea.setCaretColor(textoCampo);

            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            inputPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK),
                    "Entrada",
                    TitledBorder.LEFT,
                    TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 12),
                    Color.BLACK
            ));
            inputPanel.setBackground(Color.LIGHT_GRAY);

            JLabel label1 = new JLabel("Páginas (ex: 1 2 3 4):");
            label1.setFont(fonteCampos);
            label1.setForeground(Color.BLACK);
            inputPanel.add(label1);
            inputPanel.add(paginasField);

            JLabel label2 = new JLabel("Quartos (número inteiro):");
            label2.setFont(fonteCampos);
            label2.setForeground(Color.BLACK);
            inputPanel.add(label2);
            inputPanel.add(quartosField);

            JButton botao = new JButton("Processar");
            botao.setFont(new Font("SansSerif", Font.BOLD, 14));
            botao.setBackground(new Color(70, 130, 180));
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);
            botao.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

            botao.addActionListener(e -> {
                try {
                    String textoPaginas = paginasField.getText().trim();
                    System.out.println(textoPaginas);
                    Integer[] paginas = stringToIntegerArray(textoPaginas);
                    Integer quartos = Integer.parseInt(quartosField.getText().trim());

                    FIFO fifo = new FIFO(paginas, quartos);
                    LRU lru = new LRU(paginas, quartos);
                    NFU nfu = new NFU(paginas, quartos);
                    Optimal optimal = new Optimal(paginas, quartos);

                    Integer faltasFifo = fifo.simulate();
                    Integer faltasLru = lru.simulate();
                    Integer faltasNfu = nfu.simulate();
                    Integer faltasOptimal = optimal.simulate();

                    String s1 = "Páginas lidas: " + Arrays.toString(paginas);
                    String s2 = "Quartos informados: " + quartos;
                    String sFifo = "faltas FIFO: " + faltasFifo;
                    String sLru = "faltas LRU: " + faltasLru;
                    String sNfu = "faltas NFU: " + faltasNfu;
                    String sOptimal = "faltas Optimal: " + faltasOptimal;

                    resultadoArea.setText(s1 + "\n" + s2 + "\n" + sFifo + "\n" + sLru + "\n" + sNfu + "\n" + sOptimal);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Erro: verifique se os campos estão preenchidos corretamente.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                    resultadoArea.setText("");
                }
            });

            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(botao, BorderLayout.CENTER);
            frame.add(new JScrollPane(resultadoArea), BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}