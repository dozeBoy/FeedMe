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

import POJOs.Recipe;


public class RecipeRecycleViewAdapter extends RecyclerView.Adapter<RecipeRecycleViewAdapter.RecipeViewHolder> {

    private List<Recipe> recipeItemList;
    private Context context;
    private OnRecyclerItemClick mOnRecyclerItemClickListener;

    public RecipeRecycleViewAdapter(Context context, List<Recipe> recyclerItemList, OnRecyclerItemClick onRecyclerItemClick) {
        this.recipeItemList = recyclerItemList;
        this.context = context;
        this.mOnRecyclerItemClickListener = onRecyclerItemClick;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_layout_result, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {


        Recipe recipe = recipeItemList.get(position);

        holder.imageViewThumbnail.setImageResource(R.drawable.medium);
        holder.textView.setText(recipe.getName());


    }


    @Override
    public int getItemCount() {
        return recipeItemList.size();
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageViewThumbnail;
        protected TextView textView;


        public RecipeViewHolder(View view) {
            super(view);
            this.imageViewThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            imageViewThumbnail.setOnClickListener(this);

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
