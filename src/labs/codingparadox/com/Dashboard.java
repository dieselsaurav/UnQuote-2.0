package labs.codingparadox.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardlayout);
        
        Button btn_get_quotes = (Button) findViewById(R.id.btn_get_quotes);
        Button btn_get_favorites = (Button) findViewById(R.id.btn_favorites);
        
        btn_get_quotes.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), CustomTabs.class);
                i.putExtra("selectedTab", 0);
                startActivity(i);
            }
        });
        
        btn_get_favorites.setOnClickListener(new View.OnClickListener() {
       	 public void onClick(View view) {
               // Launching News Feed Screen
               Intent i = new Intent(getApplicationContext(), CustomTabs.class);
               i.putExtra("selectedTab", 1);
               startActivity(i);
           }
       });
    }
}