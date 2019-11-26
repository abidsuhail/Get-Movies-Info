package com.dragontelnet.tmdbmovies.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.dragontelnet.tmdbmovies.R;
import com.dragontelnet.tmdbmovies.adapter.MoviesListAdapter;
import com.dragontelnet.tmdbmovies.databinding.ActivityMainBinding;
import com.dragontelnet.tmdbmovies.di.MyDaggerInjection;
import com.dragontelnet.tmdbmovies.model.Result;
import com.dragontelnet.tmdbmovies.viewmodel.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String MOVIE_TYPE = UPCOMING;
    private static final String TAG = "MainActivity";
    private Observer<List<Result>> moviesListObserver;
    public static String UPCOMING = "upcoming";
    public static String POPULAR = "popular";
    public static String NOW_PLAYING = "nowplaying";
    public static String TOP_RATED = "toprated";
    public static final String MOVIE_OBJ = "movieObj";

    @Inject
    MoviesListAdapter adapter;

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();

        activityMainBinding= DataBindingUtil
                .setContentView(this,R.layout.activity_main);
        activityMainBinding
                .setRefreshHandler(new RefreshHandler());

        initUi();

        setUpNavView();

        setRecyclerView();

        getUpcomingMoviesList();


    }

    private void initUi() {
        toolbar = activityMainBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        swipeRefreshLayout = activityMainBinding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
    }

    private void inject() {
        MyDaggerInjection
                .getMoviesListAdpterComponent()
                .inject(this);
    }

    private MainActivityViewModel getViewModel()
    {
        return ViewModelProviders.of(this)
                .get(MainActivityViewModel.class);
    }

    private void setObserver() {
        moviesListObserver=new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                adapter.setMoviesList(results);
            }
        };

    }

    private void getUpcomingMoviesList() {
        getViewModel()
                .getUpcomingMovies()
                .observe(this, moviesListObserver);
    }

    private void getNowPlaying() {
        getViewModel()
                .getNowPlayingMovies()
                .observe(this, moviesListObserver);
    }

    private void getPopularMoviesList() {
        getViewModel()
                .getPopularMovies()
                .observe(this, moviesListObserver);
    }

    private void getTopRatedMovies() {
        getViewModel()
                .getTopRatedMovies()
                .observe(this, moviesListObserver);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.nav_upcoming:
                getUpcomingMoviesList();
                setTitle("Upcoming Movies");
                MOVIE_TYPE = UPCOMING;
                break;
            case R.id.nav_popular:
                getPopularMoviesList();
                setTitle("Popular Movies");
                MOVIE_TYPE = POPULAR;
                break;
            case R.id.nav_now_playing:
                getNowPlaying();
                setTitle("Now Playing Movies");
                MOVIE_TYPE = NOW_PLAYING;
                break;
            case R.id.nav_top_rated:
                getTopRatedMovies();
                setTitle("Top Rated Movies");
                MOVIE_TYPE = TOP_RATED;
                break;
            case R.id.nav_exit:
                finish();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpNavView() {
        NavigationView navigationView = activityMainBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = activityMainBinding.moviesListRv;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        setObserver();
    }

    public class RefreshHandler {
        public void onRefresh() {
            if (MOVIE_TYPE.equals(UPCOMING)) {
                Log.d(TAG, "onRefresh: in");
                getUpcomingMoviesList();
                swipeRefreshLayout.setRefreshing(false);
            } else if (MOVIE_TYPE.equals(POPULAR)) {
                getPopularMoviesList();
                swipeRefreshLayout.setRefreshing(false);
            } else if (MOVIE_TYPE.equals(NOW_PLAYING)) {
                getNowPlaying();
                swipeRefreshLayout.setRefreshing(false);

            } else if (MOVIE_TYPE.equals(TOP_RATED)) {
                getTopRatedMovies();
                swipeRefreshLayout.setRefreshing(false);

            }

        }
    }

}
