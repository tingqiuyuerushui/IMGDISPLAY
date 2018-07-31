package com.mine.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by lun.zhang on 9/26/2017.
 */


public class SerActLinechart {
    LineChart lineChart;
    Context context;
    Handler handler;
    ArrayList<Entry> values1 = new ArrayList<>();
    ArrayList<Entry> values2 = new ArrayList<>();
    ArrayList<Entry> values3 = new ArrayList<>();
    //LineDataSet每一个对象就是一条连接线
    LineDataSet set1;
    LineDataSet set2;
    LineDataSet set3;
    int i = 0;
    private final int FirstLine = 0;
    private final int SecondLine = 1;

//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0x111:
//                    lineChart.invalidate();
//                    break;
//                case 0x112:
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
    public SerActLinechart(LineChart lineChart, Context context, Handler handler) {
        this.lineChart = lineChart;
        this.context = context;
        this.handler = handler;
        initLineChart();
        initData(0);
    }
    private void initLineChart(){
        Description description =new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
        lineChart.setScaleYEnabled(false);
        Matrix matrix = new Matrix();
        matrix.postScale(3f, 1f);
        lineChart.getViewPortHandler().refresh(matrix, lineChart, false);
//重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
        lineChart.fitScreen();
        //获取此图表的x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
        xAxis.setLabelCount(10);
        xAxis.setAvoidFirstLastClipping(true);//如果设置为true，则在绘制时会避免“剪掉”在x轴上的图表或屏幕边缘的第一个和最后一个坐标轴标签项。
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        IAxisValueFormatter mIA = new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return "时间";
//            }
//        };
//
//        xAxis.setValueFormatter(mIA);//格式化x轴标签显示字符
        /**
         * Y轴默认显示左右两个轴线
         */
        //获取右边的轴线
        YAxis rightAxis=lineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);
        leftAxis.setAxisMinValue(0f);
    }
    public  void initData(int i) {
        /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        if (values1.size() > 0) {
            values1.clear();
        }
        if (values2.size() > 0) {
            values2.clear();
        }
        if (values3.size() > 0) {
            values3.clear();
        }

        values1.add(new Entry(1 + i, 10));
        values1.add(new Entry(5 + i, 30));
        values1.add(new Entry(10 + i, 30));
        values1.add(new Entry(15 + i, 40));
        values1.add(new Entry(20 + i, 20));
        values1.add(new Entry(25 + i, 20));

        values2.add(new Entry(1 + i, 12));
        values2.add(new Entry(5 + i, 32));
        values2.add(new Entry(10 + i, 32));
        values2.add(new Entry(15 + i, 42));
        values2.add(new Entry(20 + i, 22));
        values2.add(new Entry(25 + i, 22));

        values3.add(new Entry(1 + i, 8));
        values3.add(new Entry(5 + i, 28));
        values3.add(new Entry(10 + i, 28));
        values3.add(new Entry(15 + i, 38));
        values3.add(new Entry(20 + i, 18));
        values3.add(new Entry(25 + i, 18));


        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2.setValues(values2);
            set3 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set3.setValues(values3);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "标准曲线");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                set1.setFillColor(Color.BLACK);
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                set1.setFillDrawable(drawable);//设置范围背景填充
            } else {
                set1.setFillColor(Color.BLACK);
            }

            //设置数据2
            set2 = new LineDataSet(values2, "实际曲线");
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(1f);
            set2.setCircleRadius(3f);
            set2.setValueTextSize(10f);
            //设置数据3
            set3 = new LineDataSet(values3, "实际曲线");
            set3.setColor(Color.RED);
            set3.setCircleColor(Color.RED);
            set3.setLineWidth(1f);
            set3.setCircleRadius(3f);
            set3.setValueTextSize(10f);

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
//            dataSets.add(set3);
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
//            handler.sendEmptyMessage(0x111);
        }
    }
    public void addEntry(){
        i = i+1;
        LineData data = lineChart.getData();
        set1 = (LineDataSet) data.getDataSetByIndex(FirstLine);
        float fh = (float) (30);
        Entry entryh = new Entry(25+i,fh);
        data.addEntry(entryh, FirstLine);
        set2 = (LineDataSet) data.getDataSetByIndex(SecondLine);
        float fl = (float) ( 15);
        Entry entryl = new Entry(25+i,fl);
        data.addEntry(entryl, SecondLine);
//        data.removeEntry(entryh,0);
//        data.removeEntry(entryl,0);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        //设置在曲线图中显示的最大数量
        lineChart.setVisibleXRangeMaximum(24);
        //移到某个位置
        lineChart.moveViewToX(i);
    }
    /**
     * 获取最后一个LineDataSet的索引
     */
    private int getLastDataSetIndex(LineData lineData) {
        int dataSetCount = lineData.getDataSetCount();
        return dataSetCount > 0 ? (dataSetCount - 1) : 0;
    }
}
