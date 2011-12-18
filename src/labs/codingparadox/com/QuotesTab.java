package labs.codingparadox.com;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QuotesTab extends Activity implements OnGestureListener {
	public ArrayList<String> quotes;
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    TextView display_quote;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        View view = LayoutInflater.from(this).inflate(R.layout.quotes, null);
        
        setContentView(view);
        
        /*******************************Setup View*******************************************************/
        ScrollView sc = (ScrollView) view.findViewById(R.id.QuotesScrollView);
        display_quote = (TextView) view.findViewById(R.id.displayquote);
        
        
        /************************************************************************************************/
        /*******************************Add Gestures****************************************************/
        Toast t = Toast.makeText(this, "Slide the text to display another Quote", Toast.LENGTH_LONG);
		t.setDuration(6000);
		t.show();		
		
		// Gesture detection
		final GestureDetector gestureDetector = new GestureDetector(this);
		View.OnTouchListener gestureListener = new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				 if (gestureDetector.onTouchEvent(event)) {
                     return true;
                 }
                 return true;

			}
		};
				
        display_quote.setOnTouchListener(gestureListener);
        sc.setOnTouchListener(gestureListener);
        
        //Typeface myTypeface = Typeface.createFromAsset(this.getAssets(),"PRISTINA.TTF");
        //display_quote.setTypeface(myTypeface);
        /************************************************************************************************/
    }

    private void getQuotes(String type) {
		
//		DBHelper dbhelper = new DBHelper(this);
//		quotes = new ArrayList<String>();
//		
//		quotes = dbhelper.getQuotes(type);
//		
//		TextView display_quote = (TextView) findViewById(R.id.displayquote);
//		TextView category = (TextView) findViewById(R.id.displaycategory);
//		
//		category.setText("Current Category - '" + ((RandomQuotesApplication)(getApplication())).getType() + "'");
//		if(quotes.size() >0) {
//			display_quote.setText(quotes.get(0));
//		}
//		else {
//			display_quote.setText("No Quotes Found");
//		}
		
	}
	
	public void getQuote() {
		
//		Calendar cal = Calendar.getInstance();
//		Random r = new Random(cal.getTimeInMillis());
//		int no = r.nextInt(quotes.size());
		display_quote.setText("changed");
		RunAnimations();		
	}
	private void RunAnimations()
	{
		Animation a;
		a = AnimationUtils.loadAnimation(this, R.anim.alpha);
		a.reset();
		display_quote.clearAnimation();		
		display_quote.startAnimation(a);		
	}
    
    /****************************Gesture Controls*************************************************/
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) 
	{
		try {
			
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
               getQuote();
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                getQuote();
            }
        } catch (Exception e) {
            // nothing
        }
        return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	/*******************************************************************************************************************/
}