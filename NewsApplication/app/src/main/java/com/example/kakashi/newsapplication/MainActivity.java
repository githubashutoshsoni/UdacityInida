package com.example.kakashi.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    private final static String GUARDIANURL = "https://content.guardianapis.com/search?api-key=b668cc83-f233-4a68-81fc-6da3709a5249&show-tags=contributor";

    static NewsAdapter newsAdapter;
    //to see if internet is there, then 1  or else it is 0
    int internet=1;
   @BindView(R.id.server_error)
    TextView emptyTextView;
   @ BindView(R.id.progress)
    ProgressBar progressBar;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
        //starting to use Timber for logging. still learning. will update in next submission.
        Timber.plant();
        newsAdapter= new NewsAdapter(this, new ArrayList<News>());

        ListView newsListView= findViewById(R.id.news_list_view);
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = newsAdapter.getItem(i);
                String  stringUrl = news.getWebURL();
                Uri myUri = Uri.parse(stringUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        newsListView.setEmptyView(emptyTextView);
        getSupportLoaderManager().initLoader(1,null,this).forceLoad();
    ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {

      //  progressBar.setVisibility(View.VISIBLE);
       if(isConnectedToInternet(this))
       {
            internet=1;
           return new NewsLoader(this, GUARDIANURL);
       }
       else{
           internet=0;
           return new NewsLoader(this, "");
       }
    }



    @Override
    public void onLoadFinished(@NonNull  Loader<List<News>> loader, List<News> data) {
        Log.i("Earthquake", "onloadfinished");
       //clear the adapter
        newsAdapter.clear();
          if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
        else{
             if(internet==0){
                 emptyTextView.setText(getString(R.string.no_internet));
             }
             else
              emptyTextView.setText(getString(R.string.no_news));

          }
    progressBar.setVisibility(View.GONE);
   }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {
    newsAdapter.clear();
    }
    public static boolean  isConnectedToInternet(Context context){
        boolean isConnected= false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isConnected= (wifi.isAvailable() && wifi.isConnectedOrConnecting() || (mobile.isAvailable() && mobile.isConnectedOrConnecting()));
        return isConnected;
    }
}
