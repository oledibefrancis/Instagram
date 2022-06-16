package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;


import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    Context context;
    List<Post> posts;

    //Pass in the context and the list of posts
    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    //For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }
    // Bind the values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data
        Post post = posts.get(position);
        //bind the data
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvUsername;
        ImageView ivImage;
        TextView tvDescription;
        private Context context;


        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            this.context = itemView.getContext();
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {

            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            ParseFile image = post.getImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Post post = posts.get(position);
                // We can access the data within the views
                Intent intent = new Intent(context, DetailActivity.class);
                //intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(posts));
                intent.putExtra("posts", post);
                context.startActivity(intent);
            }
        }
        }


//    int postion = getAdapterPosition();
//            if (postion != RecyclerView.NO_POSITION){
//                    Tweet tweet = tweets.get(postion);
//                    Intent intent = new Intent(context, TweetDetail.class);
//        intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
//        context.startActivity(intent);

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
}
