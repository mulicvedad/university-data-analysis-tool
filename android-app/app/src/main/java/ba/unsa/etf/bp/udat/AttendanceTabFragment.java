package ba.unsa.etf.bp.udat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class AttendanceTabFragment extends Fragment implements HttpRequestTask.OnRequestDone {
    protected PieChart chartAttendance;
    private int selectedValue = 0;
    private float[] xAxisData1 = new float[] {0, 1, 2, 3};
    private float[] xAxisData2 = new float[] {1, 2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_attendance, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupCharts();
        Spinner dropdown = getView().findViewById(R.id.spinner_dep);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue = position;
                getChartData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        String[] items = new String[]{"Racunarstvo i informatika", "Automatika i elektronika", "Telekomunikacije", "Energetika"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        getChartData();
    }

    @Override
    public void onDone(float[] response, ChartHelper.ChartType chartType) {
        ChartHelper.setPieChartData(chartAttendance, xAxisData2, response, "Procenat prisutnih studenata");
    }

    private void setupCharts() {
        chartAttendance = getView().findViewById(R.id.attendance_percentage);

        ChartHelper.setupPieChart(chartAttendance);

        ChartHelper.setPieChartData(chartAttendance, new float[]{1, 2}, new float[] {0.22f, 0.78f}, "Procenat prisutnih studenata");

        chartAttendance.invalidate();
    }

    private void getChartData() {
        Integer tmp = selectedValue + 9;
        String url = "/attendancePercentage?dep=" + tmp.toString() + "&course=-1&lecturer=-1";
        new HttpRequestTask(this, ChartHelper.ChartType.ATTENDANCE_PERCENTAGE).execute(url);
    }
}
