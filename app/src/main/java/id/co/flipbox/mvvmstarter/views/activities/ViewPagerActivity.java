package id.co.flipbox.mvvmstarter.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.views.adapters.ViewPagerAdapter;
import id.co.flipbox.mvvmstarter.views.fragments.BlankFragment;
import id.co.flipbox.mvvmstarter.views.fragments.ListFragment;


public class ViewPagerActivity extends BaseActivity
{

    List<Fragment> mFragments;
    TabLayout      mTabLayout;
    ViewPager      mViewPager;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        mFragments = new ArrayList<>();
        mFragments.add(new BlankFragment());
        mFragments.add(new ListFragment());

        // do we need to implement databinding on each layout? I don't think so. but feel free to bind the layout if you want to.
        mViewPager = (ViewPager) findViewById(R.id.vp_fragments_container);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragments));

        mTabLayout = (TabLayout) findViewById(R.id.tl_tabs_container);
        mTabLayout.setupWithViewPager(mViewPager);

        //proof of concept that location detection work
        if (app.mLastLocation != null)
        {
            Toast.makeText(this, "lat : " + app.mLastLocation.getLatitude(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "long : " + app.mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater lInflater = getMenuInflater();
        lInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_logout:
                startActivity(new Intent(ViewPagerActivity.this, AuthActivity.class));
                finish();
                break;
            case R.id.action_about:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
