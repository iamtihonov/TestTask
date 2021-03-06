package ua.company.testtask.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ua.company.testtask.utils.ViewUtil;
import ua.company.testtask.R;

import ua.company.testtask.views.adapters.GalleryAdapter;

public class GalleryFragment extends Fragment {
    private String mImagesFolderAbsolutePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View fragmentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(fragmentView, savedInstanceState);
        mImagesFolderAbsolutePath = ViewUtil.getTestImagesFolderPath();

        initViews(fragmentView);
    }

    public void initViews(View fragmentView) {
        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerViewImages);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new GalleryAdapter(getActivity(), ViewUtil.getTestImagesName()));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Toast.makeText(getActivity(), mImagesFolderAbsolutePath, Toast.LENGTH_LONG).show();
        }
    }
}
