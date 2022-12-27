package com.example.fistservice.repository;

import com.example.fistservice.repository.interfaces.LocationToRepository;
import com.example.fistservice.repository.utils.HibernateUtilsInterface;
import dto.LocationToDTOImpl;
import dto.annotations.SearchAttribute;
import dto.annotations.SearchAttributeType;
import model.LocationTo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class LocationToRepositoryImpl implements LocationToRepository, Serializable {
    @Inject
    private HibernateUtilsInterface hibernateUtils;

    @Override
    public void create(Object object) {

    }

    @Override
    public void update(Object object) {
    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public List<LocationTo> findBy(LocationToDTOImpl locationToDTO) throws RuntimeException {
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<LocationTo> cqr = cb.createQuery(LocationTo.class);
        Root<LocationTo> root = cqr.from(LocationTo.class);

        cqr.select(root);

        Class pbClass = locationToDTO.getClass();
        Field[] fields = pbClass.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields).filter(z -> {
            z.setAccessible(true);
            return z.getAnnotation(SearchAttribute.class) != null;
        }).collect(Collectors.toList());

        List<Predicate> predicateList = new ArrayList<>();
        for(Field currentField : fieldList) {
            try {
                if(currentField.get(locationToDTO) == null) continue;
                if(currentField.getAnnotation(SearchAttribute.class).value() == SearchAttributeType.FIELD) {
                    predicateList.add(cb.equal(root.get(currentField.getName()), currentField.get(locationToDTO)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        for(int i = 0; i < predicates.length; ++i) {
            predicates[i] = predicateList.get(0);
        }
        cqr.where(cb.and(predicates));

        Query query = session.createQuery(cqr);

        List<LocationTo> result = query.getResultList();
        return result;
    }

    @Override
    public void detach(LocationTo to) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.detach(to);
        transaction.commit();
    }
}
