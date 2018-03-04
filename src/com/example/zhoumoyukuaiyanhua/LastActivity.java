package com.example.zhoumoyukuaiyanhua;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
public class LastActivity  extends Activity  {

	TransformerItem tf;
	private ViewPager mPager;
	private PageAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tf=new TransformerItem(StackTransformer.class);
		int selectedPage = 0;
		setContentView(R.layout.activity_last);

		mAdapter = new PageAdapter(getFragmentManager());

		mPager = (ViewPager) findViewById(R.id.container);
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(selectedPage);
		try {
			mPager.setPageTransformer(true, tf.clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public static class PlaceholderFragment extends Fragment {

		private static final String EXTRA_POSITION = "EXTRA_POSITION";
		private static final int[] COLORS = new int[] { 0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444 };

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final int position = getArguments().getInt(EXTRA_POSITION);
			View view=null;
			switch(position){
			
			case 1:
				view=inflater.inflate(R.layout.g1, container, false);
				break;
			case 2:
				view=inflater.inflate(R.layout.g2, container, false);
				break;
			case 3:
				view=inflater.inflate(R.layout.g3, container, false);
				break;
			case 4:
				view=inflater.inflate(R.layout.g4, container, false);
				break;
			case 5:
				view=inflater.inflate(R.layout.g5, container, false);
				break;
			case 6:
				view=inflater.inflate(R.layout.g6, container, false);
				break;
			case 7:
				view=inflater.inflate(R.layout.g7, container, false);
				break;
		
			}
			

			return view;
		}

	}

	private static final class PageAdapter extends FragmentStatePagerAdapter {

		public PageAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			final Bundle bundle = new Bundle();
			bundle.putInt(PlaceholderFragment.EXTRA_POSITION, position + 1);

			final PlaceholderFragment fragment = new PlaceholderFragment();
			fragment.setArguments(bundle);

			return fragment;
		}

		@Override
		public int getCount() {
			return 7;
		}

	}

	private static final class TransformerItem {

		final String title;
		final Class<? extends PageTransformer> clazz;

		public TransformerItem(Class<? extends PageTransformer> clazz) {
			this.clazz = clazz;
			title = clazz.getSimpleName();
		}

		@Override
		public String toString() {
			return title;
		}

	}

}


