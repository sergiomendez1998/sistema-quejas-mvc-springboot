package com.example.sistemadequejas.service.quejas;

import com.example.sistemadequejas.IngresoQuejas;
import com.example.sistemadequejas.TipoQueja;
import com.example.sistemadequejas.Usuario;
import com.example.sistemadequejas.dao.IngresoQuejaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class IngresoQueja implements IngresoQuejaService {

    @Autowired
   private IngresoQuejaDAO ingresoQuejaDAO;

    @Autowired
    private EntityManager entityManager;



    @Override
    public List<IngresoQuejas> listaQuejas() {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<IngresoQuejas> query = builder.createQuery(IngresoQuejas.class);
        Root<IngresoQuejas> root = query.from(IngresoQuejas.class);

        query.where(
                builder.isNotNull(root.get("usuario"))
        );

        try {
            return  this.entityManager.createQuery(query).getResultList();
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<IngresoQuejas> listaQuejasCuentaHabiente() {


        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<IngresoQuejas> query = builder.createQuery(IngresoQuejas.class);
        Root<IngresoQuejas> root = query.from(IngresoQuejas.class);

        query.where(
                builder.isNull(root.get("usuario"))
        );

        try {
            return  this.entityManager.createQuery(query).getResultList();
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }


}


    @Override
    public void guardar(IngresoQuejas IngresoQuejas) {
         ingresoQuejaDAO.save(IngresoQuejas);
    }

    @Override
    public IngresoQuejas EncontrarPorCorrelativo(String correlativo) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<IngresoQuejas> query = builder.createQuery(IngresoQuejas.class);
        Root<IngresoQuejas> root = query.from(IngresoQuejas.class);
        query.where(
                builder.equal(root.get("correlativo"),correlativo)
        );
        try {
        return  this.entityManager.createQuery(query).getSingleResult();
        }
        catch (NoResultException e){
            e.printStackTrace();
                return null;
        }
    }
}
