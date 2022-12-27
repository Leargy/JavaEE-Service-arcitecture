package com.example.secondserrvicenavigatorejb.repository;

import com.example.secondserrvicenavigatorejb.repository.utils.HibernateUtilsInterface;
import model.SecretEntity;
import org.hibernate.Session;
import service.SecreteRepository;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ManagedBean
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
