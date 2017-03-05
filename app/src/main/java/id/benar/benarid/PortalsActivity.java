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
import id.benar.benarid.network.APIService;
import id.benar.benarid.network.APIServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        mAdapter = new PortalsListAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter.getItemCount() == 0) {
            Log.d("PortalsActivity", "No portals. Loading...");
            loadPortals();
        }
    }

    private void loadPortals() {
        APIService service = APIServiceFactory.getInstance();
        service.getPortals().enqueue(new Callback<List<Portal>>() {
            @Override
            public void onResponse(Call<List<Portal>> call, Response<List<Portal>> response) {
                if (!response.isSuccessful()) {
                    Log.e("loadPortals", "Code: " + response.code() + ", Message: " + response.message());
                    return;
                }

                List<Portal> portals = response.body();
                Log.d("loadPortals", portals.toString());

                mAdapter = new PortalsListAdapter(portals);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Portal>> call, Throwable t) {
                Log.e("loadPortals", "Failed");
                t.printStackTrace();
            }
        });
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
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("PortalsActivity", "Clicked " +
                            ((TextView) v.findViewById(R.id.list_item_portal_title)).getText());
                }
            });
        }

        private void bindPortal(Portal portal) {
            mTextTitle.setText(portal.getName());
            mTextSubtitle.setText(portal.getSiteUrl());
        }
    }
}