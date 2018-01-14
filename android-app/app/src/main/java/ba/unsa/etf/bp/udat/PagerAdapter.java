package ba.unsa.etf.bp.udat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                EnrollmentTabFragment enrollmentTabFragment = new EnrollmentTabFragment();
                return enrollmentTabFragment;
            case 1:
                AttendanceTabFragment attendanceTabFragment = new AttendanceTabFragment();
                return attendanceTabFragment;
            case 2:
                PredictionsTabFragment predictionsTabFragment = new PredictionsTabFragment();
                return predictionsTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
