package ir.proglovving.simplecounter;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class CounterWidget extends AppWidgetProvider {

    public static String WIDGET_INCREASE_BUTTON = "ir.proglovving.simplecounter.widget_increase_button";
    public static String WIDGET_DECREASE_BUTTON = "ir.proglovving.simplecounter.widget_decrease_button";

    public static void updateCounterWidget(Context context) {
        Intent intent = new Intent(context, CounterWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, CounterWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }


    static void updateAppWidget(RemoteViews views, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.counter_widget);
            remoteViews.setTextViewText(R.id.num_tv, String.valueOf(CounterPrefManager.getNumber(context)));
            remoteViews.setOnClickPendingIntent(R.id.increase_btn, getPendingSelfIntent(context, WIDGET_INCREASE_BUTTON));
            remoteViews.setOnClickPendingIntent(R.id.decrease_btn, getPendingSelfIntent(context, WIDGET_DECREASE_BUTTON));
            updateAppWidget(remoteViews, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WIDGET_INCREASE_BUTTON.equals(intent.getAction())) {
            CounterPrefManager.increaseNumber(context);
            updateCounterWidget(context);
        }else if(WIDGET_DECREASE_BUTTON.equals(intent.getAction())){
            CounterPrefManager.decreaseNumber(context);
            updateCounterWidget(context);
        }
        super.onReceive(context, intent);
    }
}

