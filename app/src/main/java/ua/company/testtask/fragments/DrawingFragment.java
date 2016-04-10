package ua.company.testtask.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

import ua.company.testtask.custom.DrawingView;
import ua.company.testtask.data.Point;

public class DrawingFragment extends Fragment {
    private static final String ALL_ACTIONS_TAG = "all_actions";

    private DrawingView mDrawingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDrawingView = new DrawingView(getActivity());
        return mDrawingView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ALL_ACTIONS_TAG, mDrawingView.getAllActions());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);

        if(savedInstanceState != null) {
            Serializable serializable = savedInstanceState.getSerializable(ALL_ACTIONS_TAG);
            ArrayList<ArrayList<Point>> allActions = (ArrayList<ArrayList<Point>>) serializable;
            mDrawingView.setAllActions(allActions);
        }
    }
}
