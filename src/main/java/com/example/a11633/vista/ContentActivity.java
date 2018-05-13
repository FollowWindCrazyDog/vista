package com.example.a11633.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a11633.vista.cache.ExhibitsCache;
import com.example.a11633.vista.cache.MuseumCache;
import com.example.a11633.vista.common.BaseActivity;
import com.fasterxml.jackson.databind.ser.Serializers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.content_text)
    TextView textView;

    /**
     * @author followWindD
     * @date 2018/4/19 8:45
     * @param activity 启动此活动的活动
     * @param id 内容的id
     * @param imgId 使用图片的资源id
     * @param title 内容标题
     * @param kind 内容类型
     * @return
     * @throws
     * @since
    */
    public static void startActivity(Activity activity,int id,int imgId,String title,String kind){
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("imgId",imgId);
        intent.putExtra("title",title);
        intent.putExtra("kind",kind);
        activity.startActivity(intent);
    }
    public static void startActivity(Activity activity,int id,int imgId,String title,String kind,int mId){
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("imgId",imgId);
        intent.putExtra("title",title);
        intent.putExtra("kind",kind);
        intent.putExtra("mId",mId);
        activity.startActivity(intent);
    }

    private int id;
    private int imgId;
    private String title;
    private String kind;
    private int mId;

    private void initData(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        imgId = intent.getIntExtra("imgId",-1);
        title = intent.getStringExtra("title");
        kind = intent.getStringExtra("kind");
        collapsingToolbarLayout.setTitle(title);
        Glide.with(this).load(imgId).into(imageView);
        if("museum".equals(kind)){
            textView.setText(MuseumCache.introduces.get(id));
        }else if("exhibit".equals(kind)){
            mId = intent.getIntExtra("mId",-1);
            ExhibitsCache.EId eId = new ExhibitsCache.EId(mId, id);
            textView.setText(ExhibitsCache.introduction.get(eId));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if(MainActivity.fragKind== MainActivity.FragKind.MUSEUM)
                    CommentActivity.startActivity(ContentActivity.this,id,-1);
                else if(MainActivity.fragKind== MainActivity.FragKind.EXHIBIT){
                    CommentActivity.startActivity(ContentActivity.this,mId,id);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
