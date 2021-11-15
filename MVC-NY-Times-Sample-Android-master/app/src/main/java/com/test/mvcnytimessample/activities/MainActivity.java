package com.test.mvcnytimessample.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.mvcnytimessample.R;
import com.test.mvcnytimessample.adapter.MainRVAdapter;
import com.test.mvcnytimessample.api.ApiClient;
import com.test.mvcnytimessample.api.ApiInterface;
import com.test.mvcnytimessample.api.Constants;
import com.test.mvcnytimessample.model.Article;
import com.test.mvcnytimessample.model.ArticleResponse;
import com.test.mvcnytimessample.utils.InternetCheck;
import com.test.mvcnytimessample.utils.Progress;
import com.test.mvcnytimessample.utils.SnackAlert;
import java.net.HttpURLConnection;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainRVAdapter.onItemClick {
    //variables
    private static final String TAG = "MainActivity";
    private RecyclerView mainRv;
    private MainRVAdapter mainRVAdapter;
    private RelativeLayout noInternetConnectionLay;
    private Progress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new Progress(this);
        noInternetConnectionLay = findViewById(R.id.no_internet_connection_lay);

        //drawer is being used default without any custom modification to keep some demo.
        //toolbar and drawer init
        toolbarAndDrawerInit();

        //rv init
        init();

        //network request
        networkRequest();
    }

    //toolbar and drawer init
    private void toolbarAndDrawerInit() {
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // rv init
    private void init() {
        mainRv = (RecyclerView) findViewById(R.id.mainRV);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setHasFixedSize(true);
    }

    // network request to get data
    private void networkRequest() {
        // check internet and then send network request
        if (!InternetCheck.isNetworkAvailable(MainActivity.this)) {
            //no internet is available
            new SnackAlert(this, getString(R.string.no_internet_connection)).error();
            noInternetConnectionLay.setVisibility(View.VISIBLE);
        } else {
            //internet is available
            noInternetConnectionLay.setVisibility(View.GONE);
            progress.showProgressDialog();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ArticleResponse> call = apiService.getArticles("all-sections", 7, Constants.APIKey);
            call.enqueue(new Callback<ArticleResponse>() {
                @Override
                public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                    int statusCode = response.code();
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        List<Article> articles = response.body().getArticles();
                        Log.d("retrofittest", articles.get(0).getTitle());
                        mainRVAdapter = new MainRVAdapter(MainActivity.this, articles);
                        mainRVAdapter.setMainOnClickListener(MainActivity.this);
                        mainRv.setAdapter(mainRVAdapter);
                        progress.cancelProgressDialog();
                    }
                }

                @Override
                public void onFailure(Call<ArticleResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
    }


    //on backpress
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get id of menu item
        int id = item.getItemId();

        //check menu item id which is clicked
        if (id == R.id.action_github) {
            String url = getString(R.string.githublink);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
            return true;
        } else if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Main RV onclick
    @Override
    public void OnItemClick(View view, int pos, List<Article> articles) {
        String itemDetail = articles.get(pos).getAbstractText();
        String itemUrl = articles.get(pos).getUrl();
        if (!itemDetail.equals("")) {
            Intent i = new Intent(this, ArticleDetails.class);
            i.putExtra("detail", itemDetail);
            i.putExtra("url", itemUrl);
            startActivity(i);
        } else {
            new SnackAlert(this, getString(R.string.detail_empty)).error();
        }
    }

    //retry onclick
    public void retryOnClick(View view) {
        networkRequest();
    }
}
