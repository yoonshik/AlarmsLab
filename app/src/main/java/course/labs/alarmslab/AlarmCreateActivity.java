package course.labs.alarmslab;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AlarmCreateActivity extends Activity {

	public static final String TWEET_STRING = "TWEET";
	private AlarmManager mAlarmManager;

	private static final String TAG = "AlarmCreateActivity";
	private EditText mTweetTextView, mDelayTextView;
	private int mID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//DONE - Set up an AlarmManager
		mAlarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

		mTweetTextView = (EditText) findViewById(R.id.text);
		mDelayTextView = (EditText) findViewById(R.id.time);

	}

	public void set(View v) {
		String tweetText = mTweetTextView.getText().toString();
		Long delay = Integer.parseInt(mDelayTextView.getText().toString()) * 1000L;

		//DONE - Create an Intent to start the AlarmTweetService
		Intent startAlarmTweetService = new Intent(this, AlarmTweetService.class); // TODO


		//DONE - Add the tweet as an extra to the Intent
		startAlarmTweetService.putExtra(TWEET_STRING, tweetText);


		//DONE - Create a PendingIntent that will use the Intent above to start the
		// AlarmTweetService. Use PendingIntent.getService().

		// Pass in a unique value for the request code. Otherwise, your
		// pendingIntent will be overridden if there are two or more at any one time.

		// Pass in PendingIntent.FLAG_ONE_SHOT as a flag, which will make
		// sure that your PendingIntent is only used once.
		PendingIntent pendingStartAlarmTweetService = PendingIntent.getService(this, mID, startAlarmTweetService, PendingIntent.FLAG_ONE_SHOT);


		Log.d(TAG, "Tweet sent to AlarmTweetService");

		
		//DONE - Use the AlarmManager set method to set the Alarm
		long alarmTime = System.currentTimeMillis() + delay;
		mAlarmManager.set(AlarmManager.RTC, alarmTime + delay, pendingStartAlarmTweetService);

	}

	public void clear(View v) {
		mTweetTextView.setText("");
		mDelayTextView.setText("");

	}
}
