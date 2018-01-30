/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 * A demonstration application showing a time series chart where you can
 * dynamically add RANDOM) data by clicking on a button.
 *
 */
public class DynamicDataDemo extends JPanel implements ActionListener {

    /**
     * The time series data.
     */
    private TimeSeries series;
    private String strMIN;
    private String strHOR;
    private long watchStart, watchEnd;
    /**
     * The most recent value added.
     */
    private double lastValue = 100.0;
    public int mints = 1280;//

    /**
     * Constructs a new demonstration application.
     *
     * @param title the frame title.
     */
    public DynamicDataDemo(final String title) {

        //  super(title);
        this.series = new TimeSeries("", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
        //JFreeChart ch=
        this.setLayout(new BorderLayout());

        final ChartPanel chartPanel = new ChartPanel(chart);
        final JButton button = new JButton("Add New Data Item");
        button.setActionCommand("ADD_DATA");
        button.addActionListener(this);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        //   content.add(button, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 255));
        this.setBounds(0, 0, 825, 255);
        this.add(content, BorderLayout.CENTER);
        // setContentPane(content);
        watchStart = System.currentTimeMillis();
    }

    public JPanel getPanel() {
        return this;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "",
                "Time",
                "",
                dataset,
                true,
                true,
                false);
        final XYPlot plot = result.getXYPlot();
        plot.setBackgroundPaint(new Color(0xd3d3d3));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.WHITE);

        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(96000000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 20.0);
        //  axis.setf
        return result;
    }

   
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("ADD_DATA")) {
            final double factor = 0.90 + 0.2 * Math.random();
            this.lastValue = this.lastValue * factor;
            final Millisecond now = new Millisecond();
            System.out.println("Now = " + now.toString());
            this.series.add(new Millisecond(), this.lastValue);
        }
    }

    public void changechartLine(int s, double m, int h, double bsl) {
        final double factor = 0.90 + 0.2 * Math.random();
        this.lastValue = bsl;//this.lastValue * factor;

        int secondss = (int) ((int) (System.currentTimeMillis() - watchStart) / 50);
        // System.out.println("miliseconds"+System.currentTimeMillis());
        int dayss = secondss / 86400;

        int hours = (secondss / 3600) - (dayss * 24);
        int min = (secondss / 60) - (dayss * 1440) - (hours * 60);
        min = (int) m * 2;
        min = mints;
        hours = min / 60;
        min = mints % 60;
        int sec = secondss % 60;
        sec = 0;
        if (min < 10) {
            strMIN = "0" + min;
        } else {
            strMIN = "" + min;
        }

        if (hours < 10) {
            strHOR = "0" + hours;
        } else {
            strHOR = "" + hours;
        }


        mints += 20;
        String ss = new String(" " + strHOR + ":" + strMIN + ":" + sec + "");
        System.out.println("====" + ss);

        final Millisecond now = new Millisecond(0, sec, min, hours, 02, 6, 2014);





        //  final Millisecond now = new Millisecond();

        // System.out.println("Now = " + now.toString());
        // this.series.add(new Millisecond(), this.lastValue);
        this.series.addOrUpdate(now, this.lastValue);
        // this.series.addOrUpdate(now1, this.lastValue+23);


    }

    /**
     * Starting point for the demonstration application.
     *
     * @param ARGS ignored.
     */
    public static void main(final String[] args) {
//        final DynamicDataDemo demo = new DynamicDataDemo("Dynamic Data Demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
    }
}
