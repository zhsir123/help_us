package stack.birds.helpus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stack.birds.helpus.FCM.FirebaseInstanceIDService;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    Button btn1;

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView) findViewById(R.id.text_result);
        btn1 = (Button) findViewById(R.id.btn_refresh);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInstanceIDService fb = new FirebaseInstanceIDService(getApplicationContext());
            }
        });

    }
}