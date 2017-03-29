package com.android.mis.controllers.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.android.mis.R;
import com.android.mis.models.Home.Post;
import com.android.mis.models.Home.PostList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat on 8/3/17.
 */

public class PostAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<PostList> allData;

    public PostAdapter(List<PostList> data) {
        this.allData = data;
    }

    @Override
    public int getSectionCount() {
        return allData.size();
    }

    @Override
    public int getItemCount(int section) {
        return allData.get(section).getAllPostsOnGivenDate().size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        String sectionName = allData.get(section).getSectionTitle();
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.sectionTitle.setText(sectionName);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        ArrayList<Post> itemsInSection = allData.get(section).getAllPostsOnGivenDate();

        Post itemName = itemsInSection.get(relativePosition);

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.userOfPost.setText(itemName.getUserOfPost());
        itemViewHolder.identificationUserOfPost.setText(itemName.getTypeOfUserOfPost());
        itemViewHolder.postDetails.setText(itemName.getDetailsOfPost());
        itemViewHolder.timeOfPost.setText(itemName.getTimeOfPost());
        itemViewHolder.icon.setImageResource(itemName.getImageDrawable());
        itemViewHolder.multiPurposeButton.setText("Download Attachment");
        // Try to put a image . for sample i set background color in xml layout file
        // itemViewHolder.itemImage.setBackgroundColor(Color.parseColor("#01579b"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == VIEW_TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_home_section, parent, false);
            return new SectionViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_home_item, parent, false);
            return new ItemViewHolder(v);
        }
    }


    // SectionViewHolder Class for Sections
    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView sectionTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
            sectionTitle = (TextView) itemView.findViewById(R.id.section_button);
        }
    }

    // ItemViewHolder Class for Items in each Section
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView userOfPost,identificationUserOfPost,postDetails,timeOfPost;
        Button multiPurposeButton;
        ImageButton icon;


        public ItemViewHolder(View itemView) {
            super(itemView);
            userOfPost = (TextView) itemView.findViewById(R.id.user_of_post);
            timeOfPost = (TextView)itemView.findViewById(R.id.time_of_post);
            identificationUserOfPost = (TextView)itemView.findViewById(R.id.identification_user_of_post);
            postDetails = (TextView)itemView.findViewById(R.id.post_details);
            multiPurposeButton = (Button)itemView.findViewById(R.id.multi_purpose_button);
            icon = (ImageButton)itemView.findViewById(R.id.side_icon);
            multiPurposeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), userOfPost.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
