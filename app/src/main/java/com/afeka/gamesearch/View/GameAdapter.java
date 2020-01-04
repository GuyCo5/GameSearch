package com.afeka.gamesearch.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afeka.gamesearch.R;
import com.afeka.gamesearch.Model.VideoGame;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private ArrayList<VideoGame> videoGameList;
    private OnItemClickListener mListener;

    public GameAdapter (ArrayList<VideoGame> videoGameList){
        this.videoGameList = videoGameList;
    }



    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item,parent,false);
        GameViewHolder gvh = new GameViewHolder(v);
        return gvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        VideoGame currentItem = videoGameList.get(position);
        holder.imageView.setImageResource(R.drawable.ic_android);
        holder.textView1.setText(currentItem.getGameName());
        holder.textView2.setText(currentItem.getGenre().toString());
    }

    @Override
    public int getItemCount() {
        return videoGameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public ArrayList<VideoGame> getVideoGameList() {
        return videoGameList;
    }

    public void setVideoGameList(ArrayList<VideoGame> videoGameList) {
        this.videoGameList = videoGameList;
    }
}
