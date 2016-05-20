package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitlife.feedme.R;

import java.util.List;

import POJOs.RecyclerItem;


public class ResultRecycleViewAdapter extends RecyclerView.Adapter<ResultRecycleViewAdapter.ResultViewHolder> {

    private List<RecyclerItem> recyclerItemList;

    public ResultRecycleViewAdapter(List<RecyclerItem> recyclerItemList) {
        this.recyclerItemList = recyclerItemList;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_result, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {


        RecyclerItem recyclerItem = recyclerItemList.get(position);

        holder.imageViewThumbnail.setImageResource(recyclerItem.getThumbnail());
        holder.textView.setText(recyclerItem.getText());


    }

    @Override
    public int getItemCount() {
        return recyclerItemList.size();
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageViewThumbnail;
        protected TextView textView;


        public ResultViewHolder(View view) {
            super(view);
            this.imageViewThumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);

        }


    }

    public void clearRecycler() {
        int size = recyclerItemList.size();

        if (size > 0) {
            recyclerItemList.clear();
        }

    }
}
