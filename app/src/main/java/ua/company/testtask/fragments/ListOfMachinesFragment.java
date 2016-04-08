package ua.company.testtask.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ua.company.testtask.AppLog;
import ua.company.testtask.Car;
import ua.company.testtask.R;
import ua.company.testtask.loaders.MachinesLoader;

public class ListOfMachinesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Car>> {

    private ProgressBar mProgressBar;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_machines, container, false);
    }

    @Override
    public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);

        initViews(fragmentView);
        AppLog.d("ListOfMachinesFragment, initLoader");
        getActivity().getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    public void initViews(View fragmentView) {
        mProgressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar);
        mTextView = (TextView)fragmentView.findViewById(R.id.textName);
    }

    @Override
    public Loader<ArrayList<Car>> onCreateLoader(int id, Bundle args) {
        AppLog.d("ListOfMachinesFragment, onCreateLoader");
        mProgressBar.setVisibility(View.VISIBLE);
        return new MachinesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Car>> loader, ArrayList<Car> data) {
        AppLog.d("ListOfMachinesFragment, onLoadFinished");
        mProgressBar.setVisibility(View.GONE);
        mTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Car>> loader) {
        AppLog.d("ListOfMachinesFragment, onLoaderReset");
    }
}
