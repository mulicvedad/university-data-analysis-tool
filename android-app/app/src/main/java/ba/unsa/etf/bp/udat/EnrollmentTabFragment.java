package ba.unsa.etf.bp.udat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class EnrollmentTabFragment extends Fragment implements HttpRequestTask.OnRequestDone {
    protected BarChart chartEnrollmentByYear;
    protected BarChart chartEnrollmentByDep;
    protected BarChart chartEnrollmentByBudget;
    protected BarChart chartEnrollmentByRepeating;
    private float[] xAxisData1 = new float[] {0, 1, 2, 3};
    private float[] xAxisData2 = new float[] {1, 2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_enrollment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupCharts();
        getChartsData();
    }

    @Override
    public void onDone(float[] response, ChartHelper.ChartType chartType) {
        switch (chartType) {
            case ENROLLMENT_BY_YEAR:
                ChartHelper.setChartData(chartEnrollmentByYear, xAxisData1, response, "Godine");
                chartEnrollmentByYear.invalidate();
                break;
            case ENROLLMENT_BY_DEP:
                ChartHelper.setChartData(chartEnrollmentByDep, xAxisData1, response, "Odsjeci");
                chartEnrollmentByDep.invalidate();
                break;
            case ENROLLMENT_BY_REPEATING:
                ChartHelper.setChartData(chartEnrollmentByRepeating, xAxisData2, response, "Tip");
                chartEnrollmentByRepeating.invalidate();
                break;
            case ENROLLMENT_BY_BUDGET:
                ChartHelper.setChartData(chartEnrollmentByBudget, xAxisData2, response, "Finansiranje");
                chartEnrollmentByBudget.invalidate();
                break;
        }

    }

    private void setupCharts() {
        chartEnrollmentByYear = getView().findViewById(R.id.chart_enrollment_by_year);
        chartEnrollmentByDep = getView().findViewById(R.id.chart_enrollment_by_department);
        chartEnrollmentByBudget = getView().findViewById(R.id.chart_enrollment_by_budget);
        chartEnrollmentByRepeating = getView().findViewById(R.id.chart_enrollment_by_repeating);

        ChartHelper.setupBarChart(chartEnrollmentByYear, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                final ArrayList<String> xLabel = new ArrayList<>();
                xLabel.add("2013/14");
                xLabel.add("2014/15");
                xLabel.add("2015/16");
                xLabel.add("2017/18");
                return xLabel.get((int)value);
            }
        });

        ChartHelper.setupBarChart(chartEnrollmentByDep, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                final ArrayList<String> xLabel = new ArrayList<>();
                xLabel.add("RI");
                xLabel.add("AiE");
                xLabel.add("TK");
                xLabel.add("EE");
                return xLabel.get((int)value);
            }
        });

        ChartHelper.setupBarChart(chartEnrollmentByBudget, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value == 1f ? "Redovni" : "Samofinansirajuci";
            }
        });

        ChartHelper.setupBarChart(chartEnrollmentByRepeating, new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value == 1f ? "Redovni" : "Ponovci";
            }
        });

        ChartHelper.setChartData(chartEnrollmentByDep,  new float[]{0, 1, 2, 3}, new float[] {50, 100, 150, 120},
                "Odsjeci");
        ChartHelper.setChartData(chartEnrollmentByYear, new float[]{0, 1, 2, 3}, new float[] {50, 100, 150, 120}, "Godine");
        ChartHelper.setChartData(chartEnrollmentByBudget, new float[]{1, 2}, new float[] {0.45f, 0.55f}, "Finansiranje");
        ChartHelper.setChartData(chartEnrollmentByRepeating, new float[]{1, 2}, new float[] {0.45f, 0.55f}, "Tip");

        chartEnrollmentByYear.invalidate();
        chartEnrollmentByDep.invalidate();
        chartEnrollmentByBudget.invalidate();
        chartEnrollmentByRepeating.invalidate();
    }

    private void getChartsData() {
        String url = "/enrollment/academic_year?ay=";
        new HttpRequestTask(this, ChartHelper.ChartType.ENROLLMENT_BY_YEAR).execute(url + "2014", url + "2015", url + "2016", url + "2017");
        url = "/enrollment/department?dep=";
        new HttpRequestTask(this, ChartHelper.ChartType.ENROLLMENT_BY_DEP).execute(url + "9", url + "10", url + "11", url + "12");
        url = "/enrollment/isRepeating?isRepeating=";
        new HttpRequestTask(this, ChartHelper.ChartType.ENROLLMENT_BY_REPEATING).execute(url + "0", url + "1");
        url = "/enrollment/budget?budget=";
        new HttpRequestTask(this, ChartHelper.ChartType.ENROLLMENT_BY_BUDGET).execute(url + "1", url + "0");
    }
}
