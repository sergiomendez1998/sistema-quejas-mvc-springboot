package com.example.sistemadequejas.service.tipoqueja;

import com.example.sistemadequejas.TipoQueja;
import com.example.sistemadequejas.Usuario;
import com.example.sistemadequejas.dao.TipoQuejaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class TipoQuejaImpl implements TIpoQuejaService{

    @Autowired
    private TipoQuejaDAO tipoQuejaDAO;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<TipoQueja> listaTiposQueja() {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoQueja> query = builder.createQuery(TipoQueja.class);
        Root<TipoQueja> root = query.from(TipoQueja.class);

        query.where(
                builder.equal(root.get("estado"),1L)
        );
        return  this.entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public void guardarTipoQueja(TipoQueja tipoQueja) {
        tipoQuejaDAO.save(tipoQueja);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoQueja buscarTipoQueja(TipoQueja tipoQueja) {
        return tipoQuejaDAO.findById(tipoQueja.getIdTipo()).orElse(null);
    }

    @Override
    public TipoQueja buscarTipoQuejaPorSigla(String sigla) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<TipoQueja> query = builder.createQuery(TipoQueja.class);
        Root<TipoQueja> root = query.from(TipoQueja.class);
        query.where(
                builder.equal(root.get("siglas"),sigla)
        );
        try {
            return  this.entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }


    }



}
