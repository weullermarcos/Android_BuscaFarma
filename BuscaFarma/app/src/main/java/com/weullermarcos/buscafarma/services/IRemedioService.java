package com.weullermarcos.buscafarma.services;

import com.weullermarcos.buscafarma.domain.RemedioDomain;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Ismael on 24/03/2017.
 */

public interface IRemedioService {
    @GET("mapa-da-saude/rest/remedios")
    Call<List<RemedioDomain>> getRemedio(@QueryMap Map<String, String> options);
}
