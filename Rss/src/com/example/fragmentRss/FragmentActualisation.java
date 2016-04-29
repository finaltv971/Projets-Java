package com.example.fragmentRss;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.example.rss.R;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentActualisation extends Fragment  {

    DocumentBuilder documentBuilder;
    DocumentBuilderFactory documentBuilderFactory;
    Document document;
    Element element;
    NodeList listdenoeud;
    HttpURLConnection httpURLConnection;
    private URL url;
    private URL urlbitmap;

    //string recup d'info html
    private int nbrArticle;
    private String strurl;
    private String titre="";
    private String description="";
    private String date="";
    private String resumé=date+"/n"+"";
    private String liens="";
    private String urlimage="";
    private Intent intent;
    
    ArrayList<HashMap<String, String>> menu;
    
    ListView listView;

	public FragmentActualisation() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView =inflater.inflate(R.layout.fragmentactualitation, container, false);
		intent=new Intent();
		strurl=intent.getStringExtra("stringFlux");
		return rootView;
	}
	public void erreurDeConnection(){
		Toast.makeText(getActivity(), "Connection impossible", Toast.LENGTH_LONG);
	}

	
	class LectureDuFlux extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
		try{
			// TODO Auto-generated method stubtry {
            url = new URL(strurl);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            if ( httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                documentBuilderFactory =DocumentBuilderFactory.newInstance();
                documentBuilder=documentBuilderFactory.newDocumentBuilder();
                document=documentBuilder.parse(new InputSource(url.openStream()));
                listdenoeud=document.getElementsByTagName("item");
                nbrArticle=listdenoeud.getLength();
                HashMap<String,String> hashMap = null;
                if (nbrArticle>0 || !(listdenoeud==null)){
                    for (int i =0;i<listdenoeud.getLength();i++){

                        Node node =listdenoeud.item(i);
                        if (node.getNodeType() ==Node.ELEMENT_NODE){

                            element=(Element) node;
                            //titre
                            NodeList subitem=element.getElementsByTagName("title");
                            hashMap.put(titre,subitem.item(0).getChildNodes().item(0).getNodeValue());
                            //description
                            subitem=element.getElementsByTagName("description");
                            hashMap.put(description,subitem.item(0).getChildNodes().item(0).getNodeValue());
                            //date
                            subitem=element.getElementsByTagName("pubDate");
                            hashMap.put(date,subitem.item(0).getChildNodes().item(0).getNodeValue());
                            //url
                            subitem=element.getElementsByTagName("link");
                            hashMap.put(liens,subitem.item(0).getChildNodes().item(0).getNodeValue());
                            //url images
                            //bug methode si-dessous
                           //subitem=element.getAttribute("url=");
                           hashMap.put(urlimage,subitem.item(0).getChildNodes().item(0).getNodeValue());

                            
                        }
                        menu.add(hashMap);
                        
                     
                    }

                }

                
            }

            if(!(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK)|| httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
            {
                erreurDeConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
            erreurDeConnection();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
			return null;
		}
		
	}
	
}
