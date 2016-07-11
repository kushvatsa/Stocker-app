
package com.kushvatsa.android.stocker.appwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class StockWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockWidgetFactory(getApplicationContext(), intent);
    }
}
