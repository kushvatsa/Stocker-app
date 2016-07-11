
package com.kushvatsa.android.stocker.network;

import com.google.gson.annotations.SerializedName;


public class StockQuote {

    @SerializedName("Change")
    private String mChange;

    @SerializedName("symbol")
    private String mSymbol;

    @SerializedName("Name")
    private String mName;

    @SerializedName("Bid")
    private String mBid;

    @SerializedName("ChangeinPercent")
    private String mChangeInPercent;

    public String getChange() {
        return mChange;
    }

    public String getBid() {
        return mBid;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getChangeInPercent() {
        return mChangeInPercent;
    }

    public String getName() {
        return mName;
    }
}
