package com.example.fragmentRss;

import android.R.fraction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentRendu  extends Fragment{

	Intent intent;
	TextView titreTv;
	TextView contenuTv;
	ImageView imageView;
		public FragmentRendu() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			View rootview=inflater.inflate(com.example.rss.R.layout.fragmentrendu, container, false);
			titreTv =(TextView)rootview.findViewById(com.example.rss.R.id.titreTv);
			contenuTv = (TextView)rootview.findViewById(com.example.rss.R.id.contenuTv);
			imageView =(ImageView)rootview.findViewById(com.example.rss.R.id.imageV);
		return rootview;
		}
}
