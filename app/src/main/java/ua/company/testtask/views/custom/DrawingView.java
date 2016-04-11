package ua.company.testtask.views.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import ua.company.testtask.R;
import ua.company.testtask.data.Point;

public class DrawingView extends View {
    private ArrayList<ArrayList<Point>> mAllActions;
    private Paint mDrawingPain;
    private final int mDrawingRadius;

    public DrawingView(Context context) {
        super(context);
        mAllActions = new ArrayList<>();

        mDrawingPain = new Paint();
        mDrawingPain.setColor(Color.RED);
        mDrawingRadius = context.getResources().getDimensionPixelSize(R.dimen.drawing_paint_radius);
        mDrawingPain.setStrokeWidth(2 * mDrawingRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ArrayList<Point> currentActions : mAllActions) {
            Point previousPoint = null;
            int size = currentActions.size();
            for (int i = 0; i < size; i++) {
                Point currentPoint = currentActions.get(i);
                float currActionX = currentPoint.getXPosition();
                float currActionY = currentPoint.getYPosition();

                canvas.drawCircle(currActionX, currActionY, mDrawingRadius, mDrawingPain);

                if (previousPoint != null) {
                    float prevActionX = previousPoint.getXPosition();
                    float prevActionY = previousPoint.getYPosition();

                    canvas.drawLine(prevActionX, prevActionY, currActionX, currActionY, mDrawingPain);
                }

                previousPoint = currentPoint;
            }
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int actionId = event.getAction();
        Point newPaint = new Point(event.getX(), event.getY());

        switch (actionId) {
            case MotionEvent.ACTION_DOWN:
                ArrayList<Point> newPoints = new ArrayList<>();
                newPoints.add(newPaint);
                mAllActions.add(newPoints);
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mAllActions.get(mAllActions.size() - 1).add(newPaint);
                break;
        }

        invalidate();
        return true;
    }

    public ArrayList<ArrayList<Point>> getAllActions() {
        return mAllActions;
    }

    public void setAllActions(ArrayList<ArrayList<Point>> allActions) {
        mAllActions = allActions;
        invalidate();
    }
}
