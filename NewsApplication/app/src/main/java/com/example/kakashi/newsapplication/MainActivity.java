package com.example.kakashi.newsapplication;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final String GUARDIANURL = "https://content.guardianapis.com/search?api-key=b668cc83-f233-4a68-81fc-6da3709a5249";
    static NewsAdapter newsAdapter;
   @BindView(R.id.server_error) TextView emptyTextView;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);

        newsAdapter= new NewsAdapter(this, new ArrayList<News>());

        ListView newsListView= (ListView) findViewById(R.id.news_list_view);
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

        return new NewsLoader(this, GUARDIANURL);
    }



    @Override
    public void onLoadFinished(@NonNull  Loader<List<News>> loader, List<News> data) {
        Log.i("Earthquake", "onloadfinished");
       //clear the adapter
        newsAdapter.clear();
        //
          if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
        else{
             emptyTextView.setText(getString(R.string.no_internet));
          }
    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {
    newsAdapter.clear();
    }
}
