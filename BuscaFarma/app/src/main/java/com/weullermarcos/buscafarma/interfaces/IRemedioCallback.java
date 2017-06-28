package com.weullermarcos.buscafarma.interfaces;

import com.weullermarcos.buscafarma.domain.RemedioDomain;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by allanalves on 27/06/17.
 */

public interface IRemedioCallback extends Callback<List<RemedioDomain>> {
}
