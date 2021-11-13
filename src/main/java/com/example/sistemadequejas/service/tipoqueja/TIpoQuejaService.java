package com.example.sistemadequejas.service.tipoqueja;

import com.example.sistemadequejas.TipoQueja;

import java.util.List;

public interface TIpoQuejaService {

    public List<TipoQueja> listaTiposQueja();
    public void guardarTipoQueja(TipoQueja tipoQueja);
    TipoQueja buscarTipoQueja(TipoQueja tipoQueja);
    TipoQueja  buscarTipoQuejaPorSigla(String sigla);

}
