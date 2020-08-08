package ir.proglovving.simplecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.proglovving.cfviews.CTypefaceProvider;

public class MainActivity extends AppCompatActivity {

    private ImageView increaseButton, decreaseButton;
    private TextView numTextView;
    Toolbar toolbar;

    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x = CounterPrefManager.getNumber(this);

        initViews();

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseNumber();
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseNumber();
            }
        });
    }

    private void initViews() {
        numTextView = findViewById(R.id.num_tv);
        setNumber(CounterPrefManager.getNumber(this));

        increaseButton = findViewById(R.id.increase_btn);
        decreaseButton = findViewById(R.id.decrease_btn);

        toolbar = findViewById(R.id.toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof TextView) {
                ((TextView) toolbar.getChildAt(i)).setTypeface(CTypefaceProvider.getVazirBold(this));
            }
        }
    }

    private void setNumber(int x) {
        numTextView.setText(String.valueOf(x));
    }

    private void increaseNumber() {
        x++;
        setNumber(x);
        CounterPrefManager.saveNumber(this, x);
        updateCounterWidget(this);
    }

    private void decreaseNumber() {
        x--;
        setNumber(x);
        CounterPrefManager.saveNumber(this, x);
        updateCounterWidget(this);
    }

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
}