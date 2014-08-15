package com.example.customlist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private ListView mList;
	private MainAdapter mAdapter;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private Button mAdd;
	private Button mFlip;
	 
	public static boolean isFlip=false;
	public static boolean isAnimSet=false;
	public static boolean isAdd =false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mList = (ListView) findViewById(R.id.list);
		mAdd = (Button) findViewById(R.id.button1);
		mFlip = (Button) findViewById(R.id.button2);

		mAdd.setOnClickListener(this);
		mFlip.setOnClickListener(this);

		mAdapter = new MainAdapter(MainActivity.this);

//		list.add(1);

		mAdapter.setList(list);

		mList.setAdapter(mAdapter);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			isAnimSet=false;
			isAdd=true;
			list.add(list.size());
			mAdapter.notifyDataSetChanged();
		}else{
			isAdd=false;
			isAnimSet=true;
			if(isFlip){
				isFlip=false;
			}else{
				isFlip=true;
			}
			mAdapter.notifyDataSetChanged();
		}

	}
}
