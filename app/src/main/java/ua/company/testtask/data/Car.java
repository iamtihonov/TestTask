package ua.company.testtask.data;

public class Car {
    private String mModelName;
    private int mYearOfIssue;
    private String mColorText;
    private int mPriceInDollars;

    public Car(String mModelName, int mYearOfIssue, String mColorText, int mPriceInDollars) {
        this.mModelName = mModelName;
        this.mYearOfIssue = mYearOfIssue;
        this.mColorText = mColorText;
        this.mPriceInDollars = mPriceInDollars;
    }

    public int getPriceInDollars() {
        return mPriceInDollars;
    }

    public String getModelName() {
        return mModelName;
    }

    public int getYearOfIssue() {
        return mYearOfIssue;
    }

    public String getColorText() {
        return mColorText;
    }
}
