
package com.kushvatsa.android.stocker.rest;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kushvatsa.android.stocker.StockListActivity;
import com.kushvatsa.android.stocker.data.QuoteHistoricalDataColumns;
import com.kushvatsa.android.stocker.widget.helper.ItemTouchHelperCallback;
import com.kushvatsa.android.stocker.R;
import com.kushvatsa.android.stocker.data.QuoteColumns;
import com.kushvatsa.android.stocker.data.QuoteProvider;

public class QuoteCursorAdapter extends CursorRecyclerViewAdapter<QuoteCursorAdapter.ViewHolder>
        implements ItemTouchHelperCallback.SwipeListener {

    private static Context mContext;
    private int mChangeUnits;

    public QuoteCursorAdapter(Context context, Cursor cursor, int changeUnits) {
        super(cursor);
        mContext = context;
        mChangeUnits = changeUnits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_list_content, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final Cursor cursor) {
        viewHolder.mSymbol.setText(cursor.getString(cursor.getColumnIndex(QuoteColumns.SYMBOL)));
        viewHolder.mBidPrice.setText(cursor.getString(cursor.getColumnIndex(QuoteColumns.BIDPRICE)));
        if (cursor.getInt(cursor.getColumnIndex(QuoteColumns.ISUP)) == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.mChange.setBackground(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_green,
                                mContext.getTheme()));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.mChange.setBackground(
                        mContext.getResources().getDrawable(R.drawable.percent_change_pill_red,
                                mContext.getTheme()));
            }
        }
        if (mChangeUnits == StockListActivity.CHANGE_UNITS_PERCENTAGES) {
            viewHolder.mChange.setText(cursor.getString(cursor.getColumnIndex(QuoteColumns.PERCENT_CHANGE)));
        } else {
            viewHolder.mChange.setText(cursor.getString(cursor.getColumnIndex(QuoteColumns.CHANGE)));
        }
    }

    @Override
    public void onItemDismiss(int position) {
        String symbol = getSymbol(position);
        mContext.getContentResolver().delete(QuoteProvider.Quotes.withSymbol(symbol), null, null);
        mContext.getContentResolver().delete(QuoteProvider.QuotesHistoricData.CONTENT_URI,
                QuoteHistoricalDataColumns.SYMBOL + " = \"" + symbol + "\"", null);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public String getSymbol(int position) {
        Cursor c = getCursor();
        c.moveToPosition(position);
        return c.getString(c.getColumnIndex(QuoteColumns.SYMBOL));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements ItemTouchHelperCallback.ItemTouchHelperViewHolder, View.OnClickListener {
        public final TextView mSymbol;
        public final TextView mBidPrice;
        public final TextView mChange;

        public ViewHolder(View itemView) {
            super(itemView);
            mSymbol = (TextView) itemView.findViewById(R.id.stock_symbol);
            mBidPrice = (TextView) itemView.findViewById(R.id.bid_price);
            mChange = (TextView) itemView.findViewById(R.id.stock_change);
        }

        @Override
        public void onItemSelected() {
        }

        @Override
        public void onItemClear() {
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void setChangeUnits(int changeUnits) {
        this.mChangeUnits = changeUnits;
    }
}
