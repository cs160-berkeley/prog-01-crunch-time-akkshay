package repr.khoslaa.com.representatives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Iterator;

public class DetailedViewActivity extends AppCompatActivity {

    TextView repName;
    TextView partyLabel;
    TextView email;
    TextView website;
    TextView termEnds;
    TextView currentComm;
    TextView bills;
    private String repId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        repName = (TextView) findViewById(R.id.repName);
        partyLabel = (TextView) findViewById(R.id.partyLabel);
        email = (TextView) findViewById(R.id.email);
        website = (TextView) findViewById(R.id.website);
        termEnds = (TextView) findViewById(R.id.termEnds);
        currentComm = (TextView) findViewById(R.id.currentComm);
        bills = (TextView) findViewById(R.id.bills);

        Intent currIntent = getIntent();
        repName.setText(currIntent.getStringExtra("FULL_NAME"));
        partyLabel.setText("PARTY AFFILIATION: " + currIntent.getStringExtra("PARTY"));
        email.setText("EMAIL: " + currIntent.getStringExtra("EMAIL"));
        website.setText("WEBSITE: " + currIntent.getStringExtra("WEBSITE"));
        termEnds.setText("TERM ENDS: " + currIntent.getStringExtra("TERM_ENDS"));
        repId = currIntent.getStringExtra("REP_ID");

        getBills();
        getComms();


    }

    public void getBills() {
        String urlString = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=";
        urlString += repId;
        urlString += "&apikey=1346c165073d46dc8badcb108486150b";


        Ion.with(getApplicationContext())
                .load(urlString)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        JsonArray results = result.get("results").getAsJsonArray();
                        if (result == null) {
                            Log.d("T", " aresult is null");
                            Log.d("T", "aerror is " + e);
                        } else {
                            Log.d("T", "aresult is not null in this case");
                            Log.d("T", result.toString());
                            Log.d("T", "1 down aresult is not null in this case");
                            Log.d("T", result.get("results").toString());
                        }

                        if (result.get("results") == null) {
                            Log.d("T", "aresults get is null");
                        }

                        Iterator<JsonElement> resultIter = results.iterator();

                        String recentBills = "";

                        while (resultIter.hasNext()) {
                            JsonObject currResult = (JsonObject) resultIter.next();
                            Log.d("T", "2 down aresult is not null in this case");
                            Log.d("T", currResult.toString());
                            if (currResult.get("short_title") != null || currResult.get("official_title") != null) {
                                Log.d("T", "3 down aresult is not null in this case");




                                if (currResult.get("short_title") != null) {
                                    if (currResult.get("short_title").toString() != null && !currResult.get("short_title").toString().equals("null")) {
                                        recentBills += currResult.get("introduced_on").getAsString();
                                        recentBills += ": ";
                                        recentBills += currResult.get("short_title").toString();
                                    }

//                                    Log.d("T", currResult.get("short_title").getAsString());
                                    Log.d("T", "short title is not null 1");
                                    Log.d("T", "short title as string is " + currResult.get("short_title").toString());
                                } else if (currResult.get("official_title") != null) {
                                    if (currResult.get("official_title").toString() != null && !currResult.get("official_title").toString().equals("null")) {
                                        recentBills += currResult.get("introduced_on").getAsString();
                                        recentBills += ": ";
                                        recentBills += currResult.get("official_title").toString();
                                    }

//                                    Log.d("T", currResult.get("official_title").getAsString());
                                    Log.d("T", "official title is not null 2");
                                    Log.d("T", "official title as string is" + currResult.get("official_title").toString());
                                }


                                if (resultIter.hasNext()) {
                                    recentBills += ", ";
                                }
                            }

                        }

                        bills.setText(recentBills);


                    }
                });
    }

    public void getComms() {
        String urlString = "https://congress.api.sunlightfoundation.com/committees?subcommittee=false&member_ids=";
        urlString += repId;
        urlString += "&apikey=1346c165073d46dc8badcb108486150b";

        Ion.with(getApplicationContext())
                .load(urlString)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (result == null) {
                            Log.d("T", "result is null");
                            Log.d("T", "error is " + e);
                        } else {
                            Log.d("T", "result is not null in this case");
                            Log.d("T", result.toString());
                        }

                        if (result.get("results") == null) {
                            Log.d("T", "results get is null");
                        }

                        JsonArray results = result.get("results").getAsJsonArray();

                        Log.d("T", "the results for comms are" + results.toString());

                        Iterator<JsonElement> resultIter = results.iterator();

                        String comms = "";

                        while (resultIter.hasNext()) {
                            JsonObject currResult = (JsonObject) resultIter.next();
                            comms += currResult.get("name").getAsString();
                            if (resultIter.hasNext()) {
                                comms += ", ";
                            }
                        }

                        currentComm.setText(comms);


                    }
                });

    }

}
