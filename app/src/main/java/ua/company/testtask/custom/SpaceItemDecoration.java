package ua.company.testtask.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ua.company.testtask.R;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int dividerHeight;

    public SpaceItemDecoration(Context context) {
        Resources resources = context.getResources();

        dividerHeight = resources.getDimensionPixelSize(R.dimen.divider_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = dividerHeight;
    }
}
