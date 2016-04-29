package ua.company.testtask.views.fragments;

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

import java.util.ArrayList;

import ua.company.testtask.data.Car;
import ua.company.testtask.R;
import ua.company.testtask.views.CarsLoaderRunningListener;
import ua.company.testtask.views.custom.SpaceItemDecoration;
import ua.company.testtask.views.adapters.CarsAdapter;
import ua.company.testtask.loaders.CarsLoader;

public class ListOfCarsFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<ArrayList<Car>>, CarsLoaderRunningListener {
    private static final String RECYCLE_VIEW_STATE_TAG = "recycle_view_state";

    private RecyclerView mRecycleView;
    private View mProgressBar;
    private AppCompatActivity mActivity;
    private Parcelable mSavedRecyclerLayoutState;
    private View mTextDataNotLoaded;

    @Override
    public void onAttach(Context context)  {
        super.onAttach(context);

        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_cars, container, false);
    }

    @Override
    public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);

        if(savedInstanceState != null) {
            mSavedRecyclerLayoutState = savedInstanceState.getParcelable(RECYCLE_VIEW_STATE_TAG);
        }

        initViews(fragmentView);
        mActivity.getSupportLoaderManager().initLoader(1, null, this);
    }

    public void initViews(View fragmentView) {
        mRecycleView = (RecyclerView)fragmentView.findViewById(R.id.recyclerViewCars);
        mProgressBar = fragmentView.findViewById(R.id.progressBar);
        mTextDataNotLoaded = fragmentView.findViewById(R.id.textDataNotLoaded);

        mRecycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addItemDecoration(new SpaceItemDecoration(mActivity));
    }

    @Override
    public Loader<ArrayList<Car>> onCreateLoader(int id, Bundle args) {
        return new CarsLoader(mActivity, ListOfCarsFragment.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Car>> loader, ArrayList<Car> cars) {
        if(cars != null) {
            changeVisibilityViews(true, false);
            mRecycleView.setAdapter(new CarsAdapter(mActivity, cars));

            if(mSavedRecyclerLayoutState != null) {
                mRecycleView.getLayoutManager().onRestoreInstanceState(mSavedRecyclerLayoutState);
            }
        } else {
            changeVisibilityViews(false, true);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Car>> loader) {}

    @Override
    public void carsLoaderRunning() {
        changeVisibilityViews(false, false);
    }

    public void changeVisibilityViews(boolean showList, boolean showTextDataNotLoaded) {
        mProgressBar.setVisibility(showList || showTextDataNotLoaded ? View.GONE : View.VISIBLE);
        mRecycleView.setVisibility(showList ? View.VISIBLE : View.GONE);
        mTextDataNotLoaded.setVisibility(showTextDataNotLoaded ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mRecycleView != null) {
            outState.putParcelable(RECYCLE_VIEW_STATE_TAG, mRecycleView.getLayoutManager().onSaveInstanceState());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}
