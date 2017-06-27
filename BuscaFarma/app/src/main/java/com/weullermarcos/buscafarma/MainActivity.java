package com.weullermarcos.buscafarma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText edtRemedy;
    RecyclerView lstPharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtRemedy = (EditText) findViewById(R.id.edtRemedy);
        lstPharmacy = (RecyclerView) findViewById(R.id.lstPharmacy);
    }


}
