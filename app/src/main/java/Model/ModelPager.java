package Model;

import androidx.fragment.app.Fragment;

public class ModelPager {

    private String TabName;
    private Fragment fragment;

    public ModelPager(String tabName, Fragment fragment) {
        TabName = tabName;
        this.fragment = fragment;
    }

    public ModelPager(String tabName) {
        TabName = tabName;
    }

    public String getTabName() {
        return TabName;
    }

    public void setTabName(String tabName) {
        TabName = tabName;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

}
