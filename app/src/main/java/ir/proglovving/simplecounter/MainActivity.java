package ir.proglovving.simplecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView numTextView;

    private int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numTextView = findViewById(R.id.num_tv);
        setNumber(CounterPrefManager.getNumber(this));

        ImageButton increaseButton = findViewById(R.id.increase_btn);
        ImageButton decreaseButton = findViewById(R.id.decrease_btn);

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

    private void setNumber(int x){
        numTextView.setText(String.valueOf(x));
    }

    private void increaseNumber(){
        x++;
        setNumber(x);
        CounterPrefManager.saveNumber(this,x);
    }

    private void decreaseNumber(){
        x--;
        setNumber(x);
        CounterPrefManager.saveNumber(this,x);
    }
}