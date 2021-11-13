package com.example.sistemadequejas.service.puntos;


import com.example.sistemadequejas.Punto;
import com.example.sistemadequejas.Usuario;
import com.example.sistemadequejas.dao.EstadoDAO;
import com.example.sistemadequejas.dao.PuntoDAO;
import com.example.sistemadequejas.dao.RegionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class PuntoServiceImpl implements PuntoService{

    @Autowired
    private PuntoDAO puntoDAO;

    @Autowired
    private RegionDAO regionDAO;

    @Autowired
    private EstadoDAO estadoDAO;

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Punto> listaPuntos() {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Punto> query = builder.createQuery(Punto.class);
        Root<Punto> root = query.from(Punto.class);
        //hacemos una consulta de los datos que estan activos
        /* select u from usuarios u  where u.id_estado = 1
         * */
        query.where(
                builder.equal(root.get("estado"),1L)

        );
        return  this.entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public void guardar(Punto punto) {
        puntoDAO.save(punto);
    }

    @Override
    @Transactional
    public void eliminar(Punto punto) {

        puntoDAO.save(punto);
    }

    @Override
    @Transactional(readOnly = true)
    public Punto encontrarPunto(Punto punto) {

        return puntoDAO.findById(punto.getIdPunto()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Punto> buscaPuntosPorRegion(Long id) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Punto> query = builder.createQuery(Punto.class);
         Root<Punto> root = query.from(Punto.class);

        //hacemos una consulta de los datos por region
        /* select u from usuarios u  where u.id_estado = 1
         * */
        query.where(
                builder.equal(root.get("region"),id)

        );
        return  this.entityManager.createQuery(query).getResultList();
    }
}
