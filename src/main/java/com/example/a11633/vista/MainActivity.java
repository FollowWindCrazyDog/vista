package com.example.a11633.vista;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a11633.vista.cache.ExhibitsCache;
import com.example.a11633.vista.cache.MuseumCache;
import com.example.a11633.vista.cache.NewsCache;
import com.example.a11633.vista.common.ActivityCollector;
import com.example.a11633.vista.common.BaseActivity;
import com.example.a11633.vista.common.WebActivity;
import com.example.a11633.vista.fragment.main.ItemFragment;
import com.example.a11633.vista.fragment.main.dummy.DummyContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemFragment.OnListFragmentInteractionListener {
    public static FragKind fragKind = FragKind.MUSEUM;
    public static int museKind = 0;
    private AlertDialog dialog;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sp_btn)
    AppCompatSpinner spinner;

    public FragKind getInteractionKind() {
        return fragKind;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner.setVisibility(View.GONE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                museKind = i;
                Log.d("musekind", "onItemSelected: " + i);
                replaceFragment(new ItemFragment());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initSearchDialog();
        replaceFragment(new ItemFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            ActivityCollector.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initSearchDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("搜索");    //设置对话框标题
        final EditText edit = new EditText(this);
        builder.setView(edit);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你输入的是: " + edit.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        } else if (id == R.id.action_search) {
            dialog.show();
        } else if (id == R.id.action_sort_by_fav) {
            if (fragKind==FragKind.MUSEUM)
                MuseumCache.sortByFav();
            else if(fragKind== FragKind.EXHIBIT)
                ExhibitsCache.sortByFav(museKind);
            else if(fragKind==FragKind.NEWS)
                NewsCache.sortByFav();
            replaceFragment(new ItemFragment());
        } else if (id == R.id.action_sort_by_heat) {
            if (fragKind==FragKind.MUSEUM)
                MuseumCache.sortByHeat();
            else if(fragKind== FragKind.EXHIBIT)
                ExhibitsCache.sortByHeat(museKind);
            else if(fragKind==FragKind.NEWS)
                NewsCache.sortByHeat();
            replaceFragment(new ItemFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            fragKind = FragKind.MUSEUM;
            replaceFragment(new ItemFragment());
        } else if (id == R.id.nav_gallery) {
            fragKind = FragKind.EXHIBIT;
            spinner.setVisibility(View.VISIBLE);
            replaceFragment(new ItemFragment());
        } else if (id == R.id.nav_slideshow) {
            spinner.setVisibility(View.GONE);
            fragKind = FragKind.NEWS;
            replaceFragment(new ItemFragment());
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        switch (fragKind) {
            case MUSEUM:
                ContentActivity.startActivity(this, item.id, item.content, item.details, "museum");
                break;
            case EXHIBIT:
                ContentActivity.startActivity(this, item.id, item.content, item.details, "exhibit", (Integer) item.val);
                break;
            case NEWS:
                String url = (String) item.val;
                WebActivity.startActivity(this, url);
                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
            default:
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public enum FragKind {
        MUSEUM, NEWS, EXHIBIT
    }
}
