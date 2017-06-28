package com.weullermarcos.buscafarma;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weullermarcos.buscafarma.domain.RemedioDomain;
import com.weullermarcos.buscafarma.interfaces.IRemedioCallback;
import com.weullermarcos.buscafarma.services.IRemedioService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends BaseActivity implements IRemedioCallback {
    @BindView(R2.id.rv_lista_farmacia)
    RecyclerView mListaFarmacia;
    @BindView(R2.id.bt_mostrar_busca)
    Button mMostrarBusca;
    @BindView(R2.id.ll_busca_container)
    LinearLayout mBuscaContainer;
    @BindView(R2.id.et_nome_remedio)
    EditText mNomeRemedio;
    @BindView(R2.id.et_preco_maximo)
    EditText mPrecoMaximo;
    @BindView(R2.id.et_quantidade_itens)
    EditText mQuantidadeItens;
    @BindView(R2.id.bt_buscar_remedio)
    Button mBuscarRemedio;

    private final static int INTERNET_PERMISSION_CODE = 21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        requestPermissions();
    }

    @OnClick(R2.id.bt_mostrar_busca)
    public void toggleContainerVisibility() {
        if(mBuscaContainer.getVisibility() == View.VISIBLE)
            mBuscaContainer.setVisibility(View.GONE);
        else
            mBuscaContainer.setVisibility(View.VISIBLE);
    }

    @OnClick(R2.id.bt_buscar_remedio)
    public void callService() {

        closeDialog();
        createProgressDialog(getString(R.string.alert_doing_request), getString(R.string.alert_loading), this);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(getString(R.string.url_base)).
                addConverterFactory(JacksonConverterFactory.create(new ObjectMapper())).
                build();

        IRemedioService remedioService = retrofit.create(IRemedioService.class);

        Map<String, String> urlParameters = new HashMap<>();

        if(!isEmpty(mNomeRemedio.getText()))
            urlParameters.put("produto", mNomeRemedio.getText().toString());

        if(!isEmpty(mQuantidadeItens.getText()))
            urlParameters.put("quantidade", mQuantidadeItens.getText().toString());

        Call<List<RemedioDomain>> call = remedioService.getRemedio(urlParameters);
        call.enqueue(this);
    }


    private void requestPermissions() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_CODE);
        }
    }

    @Override
    public void onResponse(Call<List<RemedioDomain>> call, Response<List<RemedioDomain>> response) {

        if(response.body() == null || response.body().isEmpty())
            return;

        List<RemedioDomain> data = response.body();

        if(!isEmpty(mPrecoMaximo.getText())) {

            Iterator<RemedioDomain> it = data.iterator();
            while (it.hasNext()) {
                if (it.next().getPf0() > Double.parseDouble(mPrecoMaximo.getText().toString())) {
                    it.remove();
                }
            }
        }

        RemedioAdapter adapter = new RemedioAdapter(data, this);
        mListaFarmacia.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mListaFarmacia.setLayoutManager(layout);

        closeDialog();
    }

    @Override
    public void onFailure(Call<List<RemedioDomain>> call, Throwable t) {
        closeDialog();
    }


    class RemedioAdapter extends RecyclerView.Adapter<RemedioAdapter.RemedioItemHolder> {

        private List<RemedioDomain> mSource;
        private Context mContext;

        public RemedioAdapter(List<RemedioDomain> source, Context context) {
            mSource = source;
            mContext = context;
        }

        @Override
        public RemedioItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_remedio, parent, false);
            RemedioItemHolder itemViewHolder = new RemedioItemHolder(view);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(RemedioItemHolder holder, int position) {
            if(position >= mSource.size())
                return;

            RemedioDomain data = mSource.get(position);

            Random r = new Random();
            int distancia = r.nextInt(80 - 65) + 30;

            holder.mNomeRemedio.setText(data.getProduto());
            holder.mPrecoRemedio.setText(String.valueOf(data.getPf0()));
            holder.mNomeFarmacia.setText(data.getLaboratorio());
            holder.mDistanciaFarmacia.setText(distancia + " Km");
        }

        @Override
        public int getItemCount() {
            return mSource.size();
        }

        class RemedioItemHolder extends RecyclerView.ViewHolder {

            public TextView mNomeRemedio;
            public TextView mPrecoRemedio;
            public TextView mNomeFarmacia;
            public TextView mDistanciaFarmacia;

            public RemedioItemHolder(View view) {
                super(view);

                mNomeFarmacia = (TextView) view.findViewById(R.id.tv_nome_farmacia);
                mNomeRemedio = (TextView) view.findViewById(R.id.tv_nome_remedio);
                mPrecoRemedio = (TextView) view.findViewById(R.id.tv_preco_remedio);
                mDistanciaFarmacia = (TextView) view.findViewById(R.id.tv_distancia_farmacia);
            }
        }
    }

    public boolean isEmpty(Editable text) {
        if(text == null)
            return true;

        if(text.toString().isEmpty())
            return true;

        return false;

    }

}
