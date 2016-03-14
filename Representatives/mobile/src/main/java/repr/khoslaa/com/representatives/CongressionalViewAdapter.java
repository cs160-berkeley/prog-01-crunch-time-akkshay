package repr.khoslaa.com.representatives;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by akkshay on 3/14/16.
 */

public class CongressionalViewAdapter extends RecyclerView.Adapter<CongressionalViewAdapter.CongressionalViewHolder> {

    private List<RepInfo> repInfoList;

    public CongressionalViewAdapter(List<RepInfo> contactList) {
        this.repInfoList = contactList;
    }


    @Override
    public int getItemCount() {
        return repInfoList.size();
    }

    @Override
    public void onBindViewHolder(CongressionalViewHolder congressionalViewHolder, int i) {
        RepInfo repInfo = repInfoList.get(i);
        congressionalViewHolder.fullName.setText(repInfo.getFullName() + " " + repInfo.getParty());
        congressionalViewHolder.email.setText(repInfo.getEmail());
        congressionalViewHolder.website.setText(repInfo.getWebsite());
        congressionalViewHolder.tweet.setText(repInfo.getTweet());
        congressionalViewHolder.repInfo = repInfo;
    }

    @Override
    public CongressionalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new CongressionalViewHolder(itemView);
    }

    public static class CongressionalViewHolder extends RecyclerView.ViewHolder {

        protected TextView fullName;
        protected TextView email;
        protected TextView website;
        protected TextView tweet;
        protected Button moreButton;
        protected RepInfo repInfo;

        public CongressionalViewHolder(View v) {
            super(v);
            fullName =  (TextView) v.findViewById(R.id.repName);
            email = (TextView)  v.findViewById(R.id.repEmail);
            website = (TextView) v.findViewById(R.id.repWebsite);
            tweet = (TextView) v.findViewById(R.id.tweet);
            moreButton = (Button) v.findViewById(R.id.moreButton);

            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), CongressionalActivity.class);
                    intent.putExtra("FULL_NAME", repInfo.getFullName());
                    intent.putExtra("EMAIL", repInfo.getEmail());
                    intent.putExtra("WEBSITE", repInfo.getWebsite());
                    intent.putExtra("TWEET", repInfo.getTweet());
                    intent.putExtra("PARTY", repInfo.getParty());
                    intent.putExtra("TERM_ENDS", repInfo.getTermEnds());
                    view.getContext().startActivity(intent);

                }
            });
        }

    }


}

