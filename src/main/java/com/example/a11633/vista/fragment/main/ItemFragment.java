package com.example.a11633.vista.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a11633.vista.MainActivity;
import com.example.a11633.vista.R;
import com.example.a11633.vista.cache.ExhibitsCache;
import com.example.a11633.vista.cache.MuseumCache;
import com.example.a11633.vista.cache.NewsCache;
import com.example.a11633.vista.fragment.main.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private final List<DummyItem> list = new ArrayList<DummyItem>();

    public static RecyclerView recyclerView;
    /**
     * 从from开始分页请求数据
     * @author followWindD
     * @date 2018/4/15 9:19
     * @param from 请求的开始位置
     * @return
     * @throws
     * @since
    */
    private void refreshList(int from){

    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment(){
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            if(MainActivity.fragKind== MainActivity.FragKind.MUSEUM)
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(MuseumCache.mCache, mListener));
            else if(MainActivity.fragKind==MainActivity.FragKind.NEWS)
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(NewsCache.news,mListener));
            else if(MainActivity.fragKind==MainActivity.FragKind.EXHIBIT){
                Log.d("get",""+MainActivity.museKind);
                recyclerView.setAdapter(new MyItemRecyclerViewAdapter(ExhibitsCache.map.get(MainActivity.museKind),mListener));
            }
        }
        return view;
    }


    public static void setRecyclerView(MyItemRecyclerViewAdapter myItemRecyclerViewAdapter){
        recyclerView.setAdapter(myItemRecyclerViewAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
