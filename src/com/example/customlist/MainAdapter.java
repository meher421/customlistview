package com.example.customlist;

import java.util.ArrayList;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
	private Interpolator accelerator = new AccelerateInterpolator();
	private Interpolator decelerator = new AccelerateInterpolator();

	private Context mContext;
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();

	public MainAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder mHolder = null;
		if (convertView == null) {
			mHolder = new Holder();
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, null);

			mHolder.mTitle = (TextView) convertView.findViewById(R.id.textView1);
			mHolder.mImageview = (ImageView) convertView.findViewById(R.id.image);

			convertView.setTag(mHolder);
		} else {
			mHolder = (Holder) convertView.getTag();
		}

		mHolder.mTitle.setText("" + getItem(position));
		Log.v("123456", "pos:  " + position);
		mHolder.mImageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				arrayList.remove(position);
				MainActivity.isAnimSet =false;
				notifyDataSetChanged();
			}
		});

		
		if (MainActivity.isFlip && MainActivity.isAnimSet) {
			ObjectAnimator visToInvis = ObjectAnimator.ofFloat(convertView, "rotationY", 0f, 180f);
			visToInvis.setDuration(500);
			visToInvis.setInterpolator(accelerator);
			visToInvis.start();
		}
		else if(MainActivity.isAnimSet){
			final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(convertView, "rotationY",
					-180f, 0f);
			invisToVis.setDuration(500);
			invisToVis.setInterpolator(decelerator);
			invisToVis.start();
		}
		if((position+1) ==getCount() && MainActivity.isAdd && MainActivity.isFlip){
			ObjectAnimator visToInvis = ObjectAnimator.ofFloat(convertView, "rotationY", 0f, 180f);
			visToInvis.setDuration(50);
			visToInvis.setInterpolator(accelerator);
			visToInvis.start();
			MainActivity.isAnimSet=false;
		}
		return convertView;
	}

	public void setList(ArrayList<Integer> list) {
		arrayList = list;
	}

	static class Holder {
		TextView mTitle;
		ImageView mImageview;
	}

}
