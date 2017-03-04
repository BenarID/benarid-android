package id.benar.benarid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.benar.benarid.models.Portal;

public class PortalsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portals);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_portals);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Portal> dataset = new ArrayList<>();
        dataset.add(new Portal("", "Detik", "http://detik.com"));
        dataset.add(new Portal("", "Kompas", "http://kompas.com"));
        dataset.add(new Portal("", "CNN Indonesia", "http://cnnindonesia.com"));
        dataset.add(new Portal("", "Metrotvnews", "http://metrotvnews.com"));
        dataset.add(new Portal("", "BBC Indonesia", "http://bbc.com/indonesia"));

        mAdapter = new PortalsListAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter.getItemCount() == 0) {
            // TODO: init portals load
            Log.d("RecyclerView", "Should load.");
        }
    }

}

class PortalsListAdapter extends RecyclerView.Adapter<PortalsListAdapter.ViewHolder> {
    private List<Portal> mDataset;

    PortalsListAdapter(List<Portal> dataset) {
        mDataset = dataset;
    }

    @Override
    public PortalsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_portal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Portal portal = mDataset.get(position);
        holder.bindPortal(portal);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle;
        private TextView mTextSubtitle;

        private ViewHolder(View v) {
            super(v);
            mTextTitle = (TextView) v.findViewById(R.id.list_item_portal_title);
            mTextSubtitle = (TextView) v.findViewById(R.id.list_item_portal_subtitle);
        }

        private void bindPortal(Portal portal) {
            mTextTitle.setText(portal.getName());
            mTextSubtitle.setText(portal.getSiteUrl());
        }
    }
}