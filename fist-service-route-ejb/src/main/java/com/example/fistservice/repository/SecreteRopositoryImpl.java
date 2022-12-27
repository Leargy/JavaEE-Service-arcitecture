package com.example.fistservice.repository;

import com.example.fistservice.repository.utils.HibernateUtilsInterface;
import model.SecretEntity;
import org.hibernate.Session;
import org.jboss.ejb3.annotation.Pool;
import service.SecreteRepository;

import javax.annotation.ManagedBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;


public class SecreteRopositoryImpl implements SecreteRepository {

    @Inject
    private HibernateUtilsInterface hibernateUtils;

    @Override
    public SecretEntity getSecrete() {
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SecretEntity> cqr = cb.createQuery(SecretEntity.class);
        Root<SecretEntity> root = cqr.from(SecretEntity.class);

        cqr.select(root);

        Query query = session.createQuery(cqr);
        return (SecretEntity) query.getSingleResult();
    }
}
