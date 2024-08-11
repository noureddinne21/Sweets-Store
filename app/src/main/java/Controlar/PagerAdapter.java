package Controlar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import Model.ModelPager;

public class PagerAdapter extends FragmentStateAdapter {

    ArrayList<ModelPager> list = new ArrayList<ModelPager>();

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addTab(ModelPager model) {
        list.add(model);
        notifyDataSetChanged();
    }

    public String getTabName(int position){
        return list.get(position).getTabName();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position).getFragment();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
























}
