package ir.proglovving.simplecounter;

import android.content.Context;

public class CounterPrefManager {
    private static final String COUNTER_SHARED_PREF_NAME = "counter_shared_pref";
    private static final String KEY_NUMBER = "number";

    public static void saveNumber(Context context, int number) {
        context.getSharedPreferences(COUNTER_SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit().putInt(KEY_NUMBER, number)
                .commit();
    }

    public static int getNumber(Context context){
        return context.getSharedPreferences(COUNTER_SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getInt(KEY_NUMBER, 0);
    }
}
