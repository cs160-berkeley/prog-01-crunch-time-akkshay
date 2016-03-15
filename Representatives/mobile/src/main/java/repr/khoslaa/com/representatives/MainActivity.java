package repr.khoslaa.com.representatives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ioPsfwvAp9dJrqyf8jOKH47d9";
    private static final String TWITTER_SECRET = "HP4CltWNCQGB9cOz9jaoNgXrM58FDE2wUHkVhuuUCly8LfFBb2";


    Button findRepsButton;
    EditText editText;
    EditText editText2;
    private GoogleApiClient mGoogleApiClient;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        editText = (EditText)findViewById(R.id.editText);



        findRepsButton = (Button) findViewById(R.id.findRepsButton);
        if (findRepsButton == null) {
            Log.d("T", "so findreps is null");
        }
        findRepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isZip = false;
                String zipCode = "";
                if (editText.getText().toString() != null && editText.getText().toString() != "") {
                    zipCode = editText.getText().toString();
                    isZip = true;
                }

//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Fred");
//                if (sendIntent != null) {
//                    startService(sendIntent);
//                }

                Intent intent = new Intent(getApplicationContext(), CongressionalActivity.class);
                intent.putExtra("IS_ZIPCODE", isZip);
                intent.putExtra("VALUE", zipCode);
                MainActivity.this.startActivity(intent);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

}
