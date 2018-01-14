package ba.unsa.etf.bp.udat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

public class EnrollmentTabFragment extends Fragment {
    protected BarChart chartEnrollmentByYear;
    protected BarChart chartEnrollmentByDep;
    protected BarChart chartEnrollmentByBudget;
    protected BarChart chartEnrollmentByRepeating;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_enrollment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        chartEnrollmentByYear = (BarChart) getView().findViewById(R.id.chart_enrollment_by_year);
        chartEnrollmentByDep = (BarChart) getView().findViewById(R.id.chart_enrollment_by_department);
        chartEnrollmentByBudget = (BarChart) getView().findViewById(R.id.chart_enrollment_by_budget);
        chartEnrollmentByRepeating = (BarChart) getView().findViewById(R.id.chart_enrollment_by_repeating);

        ChartHelper.setupBarChart(chartEnrollmentByYear);
        ChartHelper.setupBarChart(chartEnrollmentByDep);
        ChartHelper.setupBarChart(chartEnrollmentByBudget);
        ChartHelper.setupBarChart(chartEnrollmentByRepeating);

        ChartHelper.setBarChartData(chartEnrollmentByDep,  new float[]{2013, 2014, 2015, 2016}, new float[] {50, 100, 150, 120},
                "Departments (RI, AIE, TK, EE)");
        ChartHelper.setBarChartData(chartEnrollmentByYear, new float[]{2013, 2014, 2015, 2016}, new float[] {50, 100, 150, 120}, "Years");
        ChartHelper.setBarChartData(chartEnrollmentByBudget, new float[]{0, 1}, new float[] {0.45f, 0.55f}, "Redovni, Samofinansirajuci");
        ChartHelper.setBarChartData(chartEnrollmentByRepeating, new float[]{0, 1}, new float[] {0.45f, 0.55f}, "Redovni, Ponovci");

        chartEnrollmentByYear.invalidate();
        chartEnrollmentByDep.invalidate();
        chartEnrollmentByBudget.invalidate();
        chartEnrollmentByRepeating.invalidate();
    }

}
