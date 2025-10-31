import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import utils.*;

public class Main {

    static class BarChartPanel extends JPanel {
        private int[] values = {0, 0, 0, 0}; // FIFO, LRU, NFU, Optimal
        private String[] labels = {"FIFO", "LRU", "NFU", "Ótimo"};
        private Color[] colors = {
                new Color(70, 130, 180),   // SteelBlue
                new Color(50, 205, 50),    // LimeGreen
                new Color(255, 140, 0),    // DarkOrange
                new Color(139, 0, 139)     // DarkMagenta
        };

        public void setValues(int fifo, int lru, int nfu, int optimal) {
            this.values[0] = fifo;
            this.values[1] = lru;
            this.values[2] = nfu;
            this.values[3] = optimal;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!(g instanceof Graphics2D)) return;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int topPadding = 40;
            int bottomPadding = 50;
            int leftPadding = 60;
            int rightPadding = 40;
            int barWidth = 60;
            int spacing = 30;

            // Evitar divisão por zero
            int max = 1;
            for (int v : values) {
                if (v > max) max = v;
            }

            // Fundo branco
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // Linhas de grade horizontais
            g2d.setColor(new Color(240, 240, 240));
            int steps = Math.min(max, 10);
            for (int i = 0; i <= steps; i++) {
                int y = height - bottomPadding - (i * (height - topPadding - bottomPadding)) / Math.max(steps, 1);
                g2d.drawLine(leftPadding, y, width - rightPadding, y);
            }

            // Desenhar barras
            for (int i = 0; i < values.length; i++) {
                int x = leftPadding + i * (barWidth + spacing);
                int barHeight = (values[i] * (height - topPadding - bottomPadding)) / max;
                int y = height - bottomPadding - barHeight;

                g2d.setColor(colors[i]);
                g2d.fillRect(x, y, barWidth, barHeight);

                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, barWidth, barHeight);

                // Rótulo do algoritmo
                g2d.setColor(Color.BLACK);
                FontMetrics fm = g2d.getFontMetrics();
                String label = labels[i];
                Rectangle2D bounds = fm.getStringBounds(label, g2d);
                int labelX = x + (barWidth - (int) bounds.getWidth()) / 2;
                g2d.drawString(label, labelX, height - 15);

                // Valor numérico acima da barra
                String valStr = String.valueOf(values[i]);
                bounds = fm.getStringBounds(valStr, g2d);
                int valX = x + (barWidth - (int) bounds.getWidth()) / 2;
                g2d.drawString(valStr, valX, y - 8);
            }

            // Título
            g2d.setFont(new Font("SansSerif", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            String title = "Faltas por Algoritmo";
            Rectangle2D titleBounds = fm.getStringBounds(title, g2d);
            int titleX = (width - (int) titleBounds.getWidth()) / 2;
            g2d.drawString(title, titleX, 25);
        }

        @Override
        public Dimension getPreferredSize() {
            // Aumentado para dar mais espaço ao gráfico
            return new Dimension(380, 320);
        }
    }

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

            JFrame frame = new JFrame("Simulador de Algoritmos de Substituição");
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

            // Criar painel de gráfico
            BarChartPanel chartPanel = new BarChartPanel();

            botao.addActionListener(e -> {
                try {
                    String textoPaginas = paginasField.getText().trim();
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

                    // Atualizar gráfico
                    chartPanel.setValues(faltasFifo, faltasLru, faltasNfu, faltasOptimal);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Erro: verifique se os campos estão preenchidos corretamente.",
                            "Entrada Inválida",
                            JOptionPane.ERROR_MESSAGE);
                    resultadoArea.setText("");
                    chartPanel.setValues(0, 0, 0, 0);
                }
            });

            // Layout final
            frame.add(inputPanel, BorderLayout.NORTH);
            frame.add(botao, BorderLayout.CENTER);

            // Painel inferior: resultado + gráfico lado a lado
            JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 15, 15));
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            bottomPanel.add(new JScrollPane(resultadoArea));
            bottomPanel.add(chartPanel);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}