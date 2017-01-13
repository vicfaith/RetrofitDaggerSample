package au.com.vicfaith.android.retrofitdaggersample.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import au.com.vicfaith.android.retrofitdaggersample.R;
import au.com.vicfaith.android.retrofitdaggersample.models.CityListData;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<CityListData> data;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(CityListData Item);
    }

    public HomeAdapter(Context context, List<CityListData> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.tvCity.setText(data.get(position).getName());
        holder.tvDesc.setText(data.get(position).getDescription());

        String images = data.get(position).getBackground();

        Glide.with(context)
                .load(images)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.background);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.city)
        TextView tvCity;
        @Bind(R.id.hotel)
        TextView tvDesc;
        @Bind(R.id.image)
        ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void click(final CityListData cityListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(cityListData);
                }
            });
        }
    }
}