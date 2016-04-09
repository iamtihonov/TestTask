package ua.company.testtask.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.company.testtask.Car;
import ua.company.testtask.R;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ItemViewHolder> {
    private ArrayList<Car> mCars;
    private Resources mResources;

    public CarsAdapter(Context context, ArrayList<Car> cars) {
        mResources = context.getResources();
        mCars = cars;
    }

    @Override
    public CarsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarsAdapter.ItemViewHolder holder, int position) {
        Car currentCar = mCars.get(position);
        int price = currentCar.getPriceInDollars();

        holder.vTextModelName.setText(getString(R.string.car_model, currentCar.getModelName()));
        holder.vTextYear.setText(getString(R.string.car_year, currentCar.getYearOfIssue()));
        holder.vTextColor.setText(getString(R.string.car_color, currentCar.getColorText()));
        holder.vTextPrice.setText(getQuantityString(R.plurals.car_price, price));
    }

    public String getString(@StringRes int stringId, Object... args) {
        return mResources.getString(stringId, args);
    }

    public String getQuantityString(@PluralsRes int stringId, int quantity) {
        return mResources.getQuantityString(stringId, quantity, quantity);
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTextModelName;
        protected TextView vTextYear;
        protected TextView vTextColor;
        protected TextView vTextPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);

            vTextModelName = (TextView)itemView.findViewById(R.id.textModelName);
            vTextYear = (TextView)itemView.findViewById(R.id.textYear);
            vTextColor = (TextView)itemView.findViewById(R.id.textColor);
            vTextPrice = (TextView)itemView.findViewById(R.id.textPrice);
        }
    }
}
