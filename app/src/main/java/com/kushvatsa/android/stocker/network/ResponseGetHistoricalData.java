
package com.kushvatsa.android.stocker.network;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


public class ResponseGetHistoricalData {

    @SerializedName("query")
    private Results mResults;

    public List<Quote> getHistoricData() {
        List<Quote> result = new ArrayList<>();
        if (mResults.getQuote() != null) {
            List<Quote> quotes = mResults.getQuote().getStockQuotes();
            for (Quote quote : quotes) {
                result.add(quote);
            }
        }
        return result;
    }


    public class Results {

        @SerializedName("count")
        private String mCount;

        @SerializedName("results")
        private Quotes mQuote;

        public Quotes getQuote() {
            return mQuote;
        }
    }

    public class Quotes {

        @SerializedName("quote")
        private List<Quote> mStockQuotes = new ArrayList<>();

        public List<Quote> getStockQuotes() {
            return mStockQuotes;
        }
    }

    public class Quote {

        @SerializedName("Symbol")
        private String mSymbol;

        @SerializedName("Date")
        private String mDate;

        @SerializedName("Low")
        private String mLow;

        @SerializedName("High")
        private String mHigh;

        @SerializedName("Open")
        private String mOpen;

        public String getSymbol() {
            return mSymbol;
        }

        public String getDate() {
            return mDate;
        }

        public String getOpen() {
            return mOpen;
        }
    }
}
