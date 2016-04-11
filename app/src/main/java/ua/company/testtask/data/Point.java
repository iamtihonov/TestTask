package ua.company.testtask.data;

import java.io.Serializable;

public class Point implements Serializable {
    private float mXPosition;
    private float mYPosition;

    public Point(float mXPosition, float mYPosition) {
        this.mXPosition = mXPosition;
        this.mYPosition = mYPosition;
    }

    public float getYPosition() {
        return mYPosition;
    }

    public float getXPosition() {
        return mXPosition;
    }
}
