package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";//model
	private LonelyTwitterActivity activity = this;//copy to use later for tests

	public Button getSaveButton() {
		return saveButton;
	}

	private Button saveButton;

	public LonelyTwitterActivity() {
	}


	public EditText getBodyText() {
		return bodyText;
	}

	private EditText bodyText;//controller

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	private ListView oldTweetsList;//View
    private ArrayAdapter<Tweet> adapter;//model

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();//model

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {//model

		super.onCreate(savedInstanceState);//model
		setContentView(R.layout.main);//view
		saveButton = (Button) findViewById(R.id.save);

		bodyText = (EditText) findViewById(R.id.body);//controller

		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);//model

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);//controller
				String text = bodyText.getText().toString();//view
				tweets.add(new NormalTweet(text));//controller
				saveInFile();//controller
				adapter.notifyDataSetChanged();//controller
			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();//view
		//ArrayList<Tweet> tweets = loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);//view
		oldTweetsList.setAdapter(adapter);//controller
        adapter.notifyDataSetChanged();//controller
	}

	private void loadFromFile() {//controller
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();//model
		try {
			FileInputStream fis = openFileInput(FILENAME);//model
            Gson gson = new Gson();//model
            Type arrayListType = new TypeToken<ArrayList<Tweet>>() {}.getType();//model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));//model
            tweets = gson.fromJson(in, arrayListType);//model
			String line = in.readLine();//mdel
			while (line != null) {
				tweets.add(new NormalTweet(line));//model
				line = in.readLine();//model
			}

		} catch (FileNotFoundException e) {
			tweets = new ArrayList<Tweet>();//model
		} catch (IOException e) {
			throw new RuntimeException(e);//model
		}
	}
	
	private void saveInFile() {//controller
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);//model
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));//model
            Gson gson = new Gson();//model
            gson.toJson(tweets, out);//model
			fos.flush();//model
            fos.close();//model
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);//model
		} catch (IOException e) {
			throw new RuntimeException(e);//model
		}
	}

    public void clearTweets(View view){//controller
        tweets.clear();
        saveInFile();//controller
        adapter.notifyDataSetChanged();//controller
    }
}