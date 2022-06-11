package Program;

import jdk.jfr.Experimental;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private JTextField maxSizeTextField;
    private JButton startButton;
    private JScrollPane scrollChartArea;
    private JPanel chartArea;
    private XYSeriesCollection series = new XYSeriesCollection();

    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);

        ChartPanel panel = new ChartPanel(createChart(series));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.white);
        chartArea.setLayout(new FlowLayout());
        chartArea.add(panel);

        series.addSeries(new XYSeries("cmp_insertion"));
        series.addSeries(new XYSeries("chg_insertion"));
        series.addSeries(new XYSeries("cmp_binary_insertion"));
        series.addSeries(new XYSeries("chg_binary_insertion"));

        startButton.addActionListener(new ActionListener() { // кнопка старт
            @Override
            public void actionPerformed(ActionEvent e) {
                int size; // количество экспериментов
                try {
                    size = Integer.parseInt(maxSizeTextField.getText()); // получаем введённое число
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog( // если вдруг это не число, выведет ошибку
                            null,"Не число!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (size <= 0) {
                    JOptionPane.showMessageDialog( // у нас не может быть меньше 1 эксперимента
                            null,"Должно быть положительное",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ExperimentInfo result = null;
                try {
                    result = Experiment.experiment(size); // проводим эксперимент
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                assert result != null; // выведим ошибку если не получен рузльтат эксперимента
                // добавляем графики:

                // кол-ва сравнений в сорт-ке вставками
                Experiment.applyInsertionCmpLine(result, series.getSeries(0));
                // кол-ва обменов в сорт-ке вставками
                Experiment.applyInsertionChgLine(result, series.getSeries(1));
                // кол-ва сравнений в сорт-ке бинарными вставками
                Experiment.applyBinaryInsertionCmpLine(result, series.getSeries(2));
                // кол-ва обменов в сорт-ке бинарными вставками
                Experiment.applyBinaryInsertionChgLine(result, series.getSeries(3));
            }
        });
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.pack();
        mw.setVisible(true);
        System.exit(0);
    }

    private static JFreeChart createChart(XYSeriesCollection series) {
        JFreeChart jFreeChart = ChartFactory.createXYLineChart(
                "Сравнения vs обмены", "Размер массива", "Количество", series);
        XYPlot plot = jFreeChart.getXYPlot();
        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.MAGENTA);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.BLUE);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        return jFreeChart;
    }
}
