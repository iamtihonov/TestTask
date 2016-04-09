package ua.company.testtask.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ua.company.testtask.Car;
import ua.company.testtask.R;
import ua.company.testtask.SpaceItemDecoration;
import ua.company.testtask.adapters.CarsAdapter;
import ua.company.testtask.loaders.MachinesLoader;

public class ListOfMachinesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<ArrayList<Car>> {

    private static final String RECYCLE_VIEW_STATE_TAG = "recycle_view_state";

    private RecyclerView mRecycleView;
    private ProgressBar mProgressBar;
    private AppCompatActivity mActivity;
    private Parcelable mSavedRecyclerLayoutState;

    @Override
    public void onAttach(Context context)  {
        super.onAttach ( context );

        if (context instanceof AppCompatActivity ) {
            mActivity = (AppCompatActivity)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_machines, container, false);
    }

    @Override
    public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);

        if(savedInstanceState != null) {
            mSavedRecyclerLayoutState = savedInstanceState.getParcelable(RECYCLE_VIEW_STATE_TAG);
        }

        initViews(fragmentView);
        mActivity.getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    public void initViews(View fragmentView) {
        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar);
        mRecycleView = (RecyclerView)fragmentView.findViewById(R.id.recyclerView);

        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(mActivity));
    }

    @Override
    public Loader<ArrayList<Car>> onCreateLoader(int id, Bundle args) {
        changeVisibilityList(false);
        return new MachinesLoader(mActivity);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Car>> loader, ArrayList<Car> cars) {
        changeVisibilityList(true);
        mRecycleView.setAdapter(new CarsAdapter(mActivity, cars));

        if(mSavedRecyclerLayoutState != null) {
            mRecycleView.getLayoutManager().onRestoreInstanceState(mSavedRecyclerLayoutState);
            mSavedRecyclerLayoutState = null;
        }
    }

    public void changeVisibilityList(boolean isShow) {
        mProgressBar.setVisibility(isShow ? View.GONE : View.VISIBLE);
        mRecycleView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLE_VIEW_STATE_TAG, mRecycleView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Car>> loader) {}

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}
