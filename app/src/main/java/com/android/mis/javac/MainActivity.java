package com.android.mis.javac;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.Util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Callback{

    TextView tv;
    NetworkRequest nr;
    View progress,error;
    String url = "https://www.facebook.com/";
    Map<String,String> mp;
    Button refreshButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        progress = (View)findViewById(R.id.loader);
        error = (View)findViewById(R.id.err);
        refreshButton = (Button)error.findViewById(R.id.refresh_button);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Heyy","JHEEL");
                Util.viewSnackbar(findViewById(android.R.id.content),"Hey");

            }
        });

        nr = new NetworkRequest(MainActivity.this,progress,error,this,"get","http",url,null,false,true,0);
        nr.setSnackbar_message("Fuck off !!");
        nr.initiateRequest();
    }

    @Override
    public void performAction(String result,int tag) {
        tv.setText(result);
    }
}
