package repr.khoslaa.com.representatives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailedViewActivity extends AppCompatActivity {

    TextView repName;
    TextView partyLabel;
    TextView email;
    TextView website;
    TextView termEnds;
    TextView currentComm;
    TextView bills;

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
        


    }

}
