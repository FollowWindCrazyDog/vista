package com.example.a11633.vista;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.a11633.vista.common.BaseActivity;
import com.example.a11633.vista.fragment.comment.CommentFragment;
import com.example.a11633.vista.fragment.comment.dummy.DummyContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity implements CommentFragment.OnListFragmentInteractionListener {

    @BindView(R.id.comment_edit_text)
    EditText editText;
    @BindView(R.id.comment_button)
    Button button;
    @BindView(R.id.rating)
    RatingBar ratingBar;

    public static void startActivity(Activity activity,int mId,int id){
        Intent intent = new Intent(activity, CommentActivity.class);
        intent.putExtra("mId",mId);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    private int mId = 0;
    private int id = 0;

    public void initData(){
        Intent intent = getIntent();
        mId = intent.getIntExtra("mId",0);
        id = intent.getIntExtra("id",0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                if("".equals(s)){
                    Toast.makeText(CommentActivity.this, "请输入评论", Toast.LENGTH_SHORT).show();
                }else{
                    DummyContent.addItem(new DummyContent.DummyItem("xxx",s,""));
                    editText.setText("");
                    replaceFragment(new CommentFragment());
                }
            }
        });
        replaceFragment(new CommentFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.comment_fragment, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
