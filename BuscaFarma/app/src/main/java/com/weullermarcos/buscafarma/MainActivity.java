package com.weullermarcos.buscafarma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText edtRemedy;
    ListView lstPharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtRemedy = (EditText) findViewById(R.id.edtRemedy);
        lstPharmacy = (ListView) findViewById(R.id.lstPharmacy);

    }
}
