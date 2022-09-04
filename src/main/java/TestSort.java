import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;
import java.util.*;

public class TestSort {

    public static void main(String[] args) {
        int runCount=500;
        int size=50000;
        long sum=0;
        long average=0;
        int[] times = new int[runCount];
        for(int i = 0; i < runCount; i++){
            int[] array=getArray(size);
            array=shuffle(array);
            long before = System.currentTimeMillis();
            MergeSort.mergeSort(array);
            long after= System.currentTimeMillis();
            long diff =after-before;
            sum+=diff;
            times[i]=(int)diff;
        }
        //printArray(out);
        JFreeChart barChart = ChartFactory.createBarChart(
                "Merge Sort Times",
                "",
                "Count",
                createDataSet(times),
                PlotOrientation.VERTICAL,
                false, false, false);
        showChart(barChart);
        average=sum/runCount;
        System.out.println("Took "+average+"ms");
    }
    static void showChart(JFreeChart chart){
        JFrame frame = new JFrame();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        frame.add(chartPanel);

        frame.pack();
        frame.setTitle("Bar chart");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    static DefaultCategoryDataset createDataSet(int[] times){
        Map<Double,Double> timeCounter = new HashMap<>();
        times=MergeSort.mergeSort(times);
        for(int num :times){
            double cur = timeCounter.getOrDefault(num,0.0)+1;
            timeCounter.put((double)num,cur);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Double key:timeCounter.keySet()){
            Double value = timeCounter.get(key);
            dataset.addValue(value,"Count",key.toString());
        }
        return dataset;
    }
    public static void printArray(int[] array){
        String out="";
        for(int i = 0; i< array.length;i++){
            out+=array[i]+((i== array.length-1)?"":",");
        }
        System.out.println(out);
    }
    public static int[] shuffle(int[] array){
        Random rnd =new Random();
        for(int i = 0; i<array.length;i++){
            int firstIndex = rnd.nextInt(array.length);
            int secondIndex = rnd.nextInt(array.length);
            int fT=array[firstIndex];
            array[firstIndex]=array[secondIndex];
            array[secondIndex]=fT;
        }
        return array;
    }
    public static int[] getArray(int max){
        int[] out = new int[max];
        for(int i =1;i<=max;i++){
            out[i-1]=i;
        }
        return out;
    }
}
