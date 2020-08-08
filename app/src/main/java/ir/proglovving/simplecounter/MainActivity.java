package ir.proglovving.simplecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import ir.proglovving.cfviews.CTypefaceProvider;

public class MainActivity extends AppCompatActivity {

    private ImageButton increaseButton, decreaseButton;
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
    }

    private void decreaseNumber() {
        x--;
        setNumber(x);
        CounterPrefManager.saveNumber(this, x);
    }
}