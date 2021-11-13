package com.example.sistemadequejas.service.usuario;

import com.example.sistemadequejas.Usuario;
import com.example.sistemadequejas.dao.UsuarioDAO;
import com.example.sistemadequejas.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Usuario> listaUsuarios() {

     CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
     CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
     Root<Usuario> root = query.from(Usuario.class);

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
    public void addUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public Usuario encontrarUsuarioPorDpi(String dpi) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);
        //hacemos una consulta y vemos si el usuario ingresado con el dpi ya existe
        /* select u from usuarios u  where u.dpi= dpi
         * */
        query.where(
                builder.equal(root.get("dpi"),dpi)
        );
        return  this.entityManager.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario econtrarUsuarioPorId(Usuario usuario) {
        return usuarioDAO.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional
    public void removeUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarioByRole(String role) {
     //create a criteria builder and get user where role is equal to ROLE_ADMIN
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);

        query.where(
                builder.equal(root.get("rolSet"),role)
        );


        return  this.entityManager.createQuery(query).getResultList();
    }
}
