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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.proglovving.cfviews.CTypefaceProvider;

import static ir.proglovving.simplecounter.CounterWidget.updateCounterWidget;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton increaseButton, decreaseButton;
    private TextView numTextView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        updateNumTextView();

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

    @Override
    protected void onResume() {
        super.onResume();
        updateNumTextView();
    }

    private void initViews() {
        numTextView = findViewById(R.id.num_tv);

        increaseButton = findViewById(R.id.increase_btn);
        decreaseButton = findViewById(R.id.decrease_btn);

        toolbar = findViewById(R.id.toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof TextView) {
                ((TextView) toolbar.getChildAt(i)).setTypeface(CTypefaceProvider.getVazirBold(this));
            }
        }
    }


    private void increaseNumber() {
        CounterPrefManager.increaseNumber(this);
        updateNumTextView();
        updateCounterWidget(this);
    }

    private void decreaseNumber() {
        CounterPrefManager.decreaseNumber(this);
        updateNumTextView();
        updateCounterWidget(this);
    }

    private void updateNumTextView() {
        numTextView.setText(String.valueOf(CounterPrefManager.getNumber(this)));
    }


}