package com.weullermarcos.buscafarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weullermarcos.buscafarma.domain.RemedioDomain;
import com.weullermarcos.buscafarma.models.RemedioRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R2.id.detalhe_nome_remedio)
    public TextView mNomeRemedio;

    @BindView(R2.id.detalhe_principio_ativo)
    public TextView mPrincipioAtivo;

    @BindView(R2.id.detalhe_apresentacao)
    public TextView mApresentacao;

    @BindView(R2.id.detalhe_classe_terapeutica)
    public TextView mClasseTerapeutica;

    @BindView(R2.id.detalhe_farmacia)
    public TextView mFarmacia;

    @BindView(R2.id.detalhe_distancia)
    public TextView mDistancia;

    @BindView(R2.id.detalhe_preco_medicamento)
    public TextView mPrecoMedicamento;

    @BindView(R2.id.detalhe_localizacao)
    public LinearLayout mLocalizacao;

    @BindView(R2.id.detalhe_pagamento)
    public LinearLayout mPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        final Bundle extras = getIntent().getExtras();
        RemedioDomain data = (RemedioDomain) extras.get("data");
        int distancia = (int) extras.get("distancia");

        if(data == null) return;

        mNomeRemedio.setText(data.getProduto());
        mPrincipioAtivo.setText(data.getPrincipioAtivo());
        mApresentacao.setText(data.getApresentacao());
        mClasseTerapeutica.setText(data.getClasseTerapeutica());
        mFarmacia.setText(data.getLaboratorio());
        mDistancia.setText(String.valueOf(distancia));
        mPrecoMedicamento.setText(String.valueOf(data.getPf0()));
    }
}
