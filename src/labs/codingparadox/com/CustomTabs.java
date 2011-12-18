package labs.codingparadox.com;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class CustomTabs extends TabActivity{
	TabHost mTabHost;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		//setup TABS
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		setupTab(new TextView(this), "Quotes");
		setupTab(new TextView(this), "Favourites");
		
		//select the default opened tab (comming from intent raised via dashboard)
		Bundle extras = getIntent().getExtras(); 
		int selectedTab = extras.getInt("selectedTab");

		mTabHost.setCurrentTab(selectedTab);
		
		//add listener and customize
		setUIBySelectedTab(mTabHost);

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

	    	public void onTabChanged(String arg0) {				
	    		setUIBySelectedTab(mTabHost);
	    	}
	    });
		
		//add the share functionality
		ImageButton btn_Share = (ImageButton) findViewById(R.id.btnShare);
        btn_Share.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String quote = "";
				if(mTabHost.getCurrentTab()==0)
					quote = getQuoteFromQuoteTab();
					
				Intent msg = new Intent(Intent.ACTION_SEND);
				msg.setType("text/plain");
				msg.putExtra(Intent.EXTRA_TEXT, quote );
				startActivity(Intent.createChooser(msg, "Share Quote"));
			}
		});
        
        //add the "add to favorite' functionality
        ImageButton btnFav = (ImageButton) findViewById(R.id.btnFav);
        btnFav.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String quote = getQuoteFromQuoteTab();
					
				//display alert for now add code later
				Toast t = Toast.makeText(getApplicationContext(), "Added to favorites !!", Toast.LENGTH_LONG);
				t.setDuration(6000);
				t.show();
			}
		});
	}
	
	private void setupTab(final View view, final String tag) {
		View tabview = createTabView(mTabHost.getContext(), tag);
		
		Intent intent; 
		if(tag.equals("Quotes"))
			intent = new Intent().setClass(this, QuotesTab.class);
		else
			intent = new Intent().setClass(this, FavoritesTab.class);
		
		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(intent);
		mTabHost.addTab(setContent);
	}

	private static View createTabView(final Context context, final String text) 
	{
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		
		return view;
	}
	
	public void setUIBySelectedTab(TabHost tabHost)
	{
		//set color for the selected tab
		setTabColor(tabHost);
		
		ImageButton btn_fav = (ImageButton) findViewById(R.id.btnFav);
		
		if(tabHost.getCurrentTab()==1)
			btn_fav.setVisibility(View.GONE);
		else
			btn_fav.setVisibility(View.VISIBLE);
	}
	
	public void setTabColor(TabHost tabhost) 
	{
	    for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
	    {
	        tabhost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.UnSelectedTabColor)); //unselected
	    }
	    tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.SelectedTabColor)); // selected
	}
	
	public String getQuoteFromQuoteTab()
	{
		//get from outer layout to the displayed message, when in QUOTE Screen
		FrameLayout frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);
		View tempLinerView = frameLayout.getChildAt(0);
		LinearLayout linearLayout = (LinearLayout) tempLinerView.findViewById(R.id.quotes_root);
		View tempScrollView = linearLayout.getChildAt(0);
		ScrollView scrollView = (ScrollView) tempScrollView.findViewById(R.id.QuotesScrollView);
		View tempTextView = scrollView.getChildAt(0);

		TextView display_quote = (TextView) tempTextView.findViewById(R.id.displayquote);
		
		return display_quote.getText().toString();
	}
}
