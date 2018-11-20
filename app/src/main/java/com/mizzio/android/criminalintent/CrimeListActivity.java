package com.mizzio.android.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }


}
