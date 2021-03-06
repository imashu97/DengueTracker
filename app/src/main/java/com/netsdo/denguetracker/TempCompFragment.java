package com.netsdo.denguetracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netsdo.swipe4d.EventBus;
import com.netsdo.swipe4d.FragmentsClassesPagerAdapter;
import com.netsdo.swipe4d.events.HorizontalPagerSwitchedEvent;

import java.util.ArrayList;

public class TempCompFragment extends Fragment {
    private static String TAG = "TempCompFragment";

	private ViewPager mHorizontalPager;
	private int mCentralPageIndex = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_temp_comp, container, false);
        mHorizontalPager = (ViewPager) fragmentView.findViewById(R.id.fragment_composite_temp_pager);

        initViews();

		return fragmentView;
	}

	private void initViews() {
		populateHorizontalPager();
		mHorizontalPager.setCurrentItem(mCentralPageIndex);
		mHorizontalPager.setOnPageChangeListener(new PageChangeListener());
	}

	private void populateHorizontalPager() {
		ArrayList<Class<? extends Fragment>> pages = new ArrayList<Class<? extends Fragment>>();
		pages.add(TempRecFragment.class);
		pages.add(TempListFragment.class);
		mCentralPageIndex = pages.indexOf(TempRecFragment.class);
		mHorizontalPager.setAdapter(new FragmentsClassesPagerAdapter(getChildFragmentManager(), getActivity(), pages));
	}

	private class PageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			EventBus.getInstance().post(new HorizontalPagerSwitchedEvent(mCentralPageIndex == position));
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}
}
