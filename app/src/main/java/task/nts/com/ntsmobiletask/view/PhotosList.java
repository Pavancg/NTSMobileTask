package task.nts.com.ntsmobiletask.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;

import task.nts.com.ntsmobiletask.R;
import task.nts.com.ntsmobiletask.pojo.PhotosModel;
import task.nts.com.ntsmobiletask.viewmodel.PhotosViewModel;

public class PhotosList extends AppCompatActivity implements PhotoListAdapter.OnItemClickListener {

    private ProgressBar progress_bar;
    private PhotosViewModel viewModel;
    private RecyclerView photosView;
    private PhotoListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_list);

        photosView = findViewById(R.id.photo_list);
        photosView.setLayoutManager(new LinearLayoutManager(this));
        photosView.setHasFixedSize(true);

        adapter = new PhotoListAdapter(this,this);
        photosView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PhotosViewModel.class);

        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);


    }

    @Override
    public void onItemClick(String url, int position) {

        Intent intObj = new Intent(this,DetailsActivity.class);
        Bundle b = new Bundle();
        b.putString("imgUrl",url);
        intObj.putExtras(b);
        startActivity(intObj);
    }

    public void updatePhotosList(){
        viewModel.getPhotosList().observe(this, new Observer<List<PhotosModel>>() {
            @Override
            public void onChanged(@Nullable List<PhotosModel> photosModels) {
                progress_bar.setVisibility(View.GONE);
                adapter.setPhotos(photosModels);
            }
        });

    }
    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                    progress_bar.setVisibility(View.VISIBLE);
                    updatePhotosList();
                } else if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
                    Toast.makeText(getApplicationContext(),"Please check Internet connection",Toast.LENGTH_LONG).show();
                    progress_bar.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, intentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (networkReceiver != null)
            unregisterReceiver(networkReceiver);
    }
}
