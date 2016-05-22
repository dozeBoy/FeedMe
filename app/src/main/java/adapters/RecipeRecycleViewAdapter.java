package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitlife.feedme.R;

import java.util.List;

import POJOs.RecipeCardView;


public class RecipeRecycleViewAdapter extends RecyclerView.Adapter<RecipeRecycleViewAdapter.RecipeViewHolder> {

    private List<RecipeCardView> recipeItemList;
    private OnRecyclerItemClick mOnRecyclerItemClickListener;

    public RecipeRecycleViewAdapter(List<RecipeCardView> recyclerItemList, OnRecyclerItemClick onRecyclerItemClick) {
        this.recipeItemList = recyclerItemList;
        this.mOnRecyclerItemClickListener = onRecyclerItemClick;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_result, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {


        RecipeCardView recipe = recipeItemList.get(position);
        holder.textView.setText(recipe.getName());
        holder.imageView.setImageResource(R.drawable.burger);


    }


    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView textView;
        protected ImageView imageView;

        public RecipeViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.imageView = (ImageView) view.findViewById(R.id.food);
            textView.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mOnRecyclerItemClickListener != null) {
                mOnRecyclerItemClickListener.onItemClick(v);
            }
        }

    }

    public void clearRecycler() {
        int size = recipeItemList.size();

        if (size > 0) {
            recipeItemList.clear();
        }

    }

    public interface OnRecyclerItemClick {
        void onItemClick(View view);
    }
}
