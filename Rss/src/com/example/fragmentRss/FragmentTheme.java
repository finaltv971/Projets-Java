package com.example.fragmentRss;

import java.util.ArrayList;

import com.example.rss.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTheme extends Fragment{
	Intent intent ;
	ListView listView;
	ArrayList arraylist;
	
	public FragmentTheme() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragmenttheme, container, false);
		listView = (ListView)rootView.findViewById(com.example.rss.R.id.listview);
		{
		 //bug listview
		/* 	 HashMap<String,String> hashMap=(HashMap<String,String>) listView.getItemAtPosition(position);
	         arraylist=(ArrayList<String>)listView.getItemAtPosition(position);
	         intent=new Intent();
	         intent.putExtra("stringFlux", arraylist.get(position));
	         startActivity(intent);
		*/
		}
		return rootView;
	}

}
