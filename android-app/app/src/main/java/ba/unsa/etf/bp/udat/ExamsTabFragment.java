package ba.unsa.etf.bp.udat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class ExamsTabFragment extends Fragment implements HttpRequestTask.OnRequestDone {
    protected BarChart chartExamsByTurnout;
    protected BarChart chartExamsByPoints;
    //private int selectedValue = 0;
    private float[] xAxisData1 = new float[] {0, 1, 2, 3};
    private float[] xAxisData2 = new float[] {1, 2};
    private int selectedAY = 0;
    private int selectedDep = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_exams, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupCharts();
        loadChartsData();
        setupSpinners();
        loadSpinnerData();
    }

    @Override
    public void onDone(float[] response, ChartHelper.ChartType chartType) {
        if (chartType == ChartHelper.ChartType.EXAM_POINTS)
            ChartHelper.setChartData(chartExamsByPoints, new float[] {1}, response, "Izlaznost");
        else
            ChartHelper.setChartData(chartExamsByTurnout, new float[] {1}, response, "Prosjek bodova");


    }

    private void setupCharts() {
        chartExamsByTurnout = getView().findViewById(R.id.chart_exam_turnout);
        chartExamsByPoints = getView().findViewById(R.id.chart_exam_points);

        ChartHelper.setupBarChart(chartExamsByPoints, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        });

        ChartHelper.setupBarChart(chartExamsByTurnout, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        });


        YAxis yAxis = chartExamsByPoints.getAxisRight();
        yAxis.setEnabled(false);
        yAxis = chartExamsByTurnout.getAxisRight();
        yAxis.setEnabled(false);


        ChartHelper.setChartData(chartExamsByTurnout, new float[]{1}, new float[] {220}, "Izlaznost");
        ChartHelper.setChartData(chartExamsByPoints, new float[]{1}, new float[] {150}, "Prosjek bodova");

        chartExamsByPoints.invalidate();
        chartExamsByTurnout.invalidate();
    }

    private void loadChartsData() {
        Integer ay = 36 - selectedAY;
        Integer dep = 9 + selectedDep;
        String url = "/exams/turnout?ay=" + ay + "&dep=" + dep + "&course=-1";
        new HttpRequestTask(this, ChartHelper.ChartType.EXAM_TURNOUT).execute(url);
        url = "/exams/averagePoints?ay=" + ay + "&dep=" + dep + "&course=-1";
        new HttpRequestTask(this, ChartHelper.ChartType.EXAM_POINTS).execute(url);
    }

    private void setupSpinners() {
        setupAYSpinner();
        setupDepSpinner();
    }

    private void setupAYSpinner() {
        Spinner dropdown = getView().findViewById(R.id.spinner_ay);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedAY = position;
                loadChartsData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        String[] items = new String[]{"2017/18", "2016/17", "2015/16", "2014/15"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private void setupDepSpinner() {
        Spinner dropdown = getView().findViewById(R.id.spinner_dep);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedDep = position;
                loadChartsData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        String[] items = new String[]{"Racunarstvo i informatika", "Automatika i elektronika", "Telekomunikacije", "Energetika"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    private void loadSpinnerData() {

    }
}
