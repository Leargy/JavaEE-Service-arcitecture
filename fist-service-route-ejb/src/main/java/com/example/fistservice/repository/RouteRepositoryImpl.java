package com.example.fistservice.repository;

import com.example.fistservice.repository.interfaces.RouteRepository;
import com.example.fistservice.repository.utils.HibernateUtilsInterface;
import dto.ParamBeanDTOImpl;
import dto.annotations.AttributeName;
import dto.annotations.OptionalAttribute;
import dto.annotations.SearchAttribute;
import dto.annotations.SearchAttributeType;
import model.LocationFrom;
import model.LocationTo;
import model.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class RouteRepositoryImpl implements RouteRepository, Serializable {

    @Inject
    private HibernateUtilsInterface hibernateUtils;

    @Override
    public void create(Route object) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        object.setFrom((LocationFrom) session.merge(object.getFrom()));
        object.setTo((LocationTo) session.merge(object.getTo()));
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Route object) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        object.setFrom((LocationFrom) session.merge(object.getFrom()));
        object.setTo((LocationTo) session.merge(object.getTo()));
        session.update(object);
        transaction.commit();
    }

    @Override
    public void delete(Route object) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
    }


    @Override
    public List<Route> findBy(ParamBeanDTOImpl paramBean) throws RuntimeException{
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Route> cqr = cb.createQuery(Route.class);
        Root<Route> root = cqr.from(Route.class);

        cqr.select(root);

        Class pbClass = paramBean.getClass();
        Field[] fields = pbClass.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields).filter(z -> {
            z.setAccessible(true);
            return z.getAnnotation(SearchAttribute.class) != null;
        }).collect(Collectors.toList());

        List<Predicate> predicateList = new ArrayList<>();
        for(Field currentField : fieldList) {
            try {
                if(currentField.get(paramBean) == null) continue;
                if(currentField.getAnnotation(SearchAttribute.class).value() == SearchAttributeType.FIELD) {
                    predicateList.add(cb.equal(root.get(currentField.getName()), currentField.get(paramBean)));
                }else if(currentField.getAnnotation(SearchAttribute.class).value() == SearchAttributeType.CLASS){
                    Object currentFieldObject = currentField.get(paramBean);
                    Field[] innerClassFields = currentFieldObject.getClass().getDeclaredFields();
                    for (Field currentInnerClassField : innerClassFields) {
                        currentInnerClassField.setAccessible(true);
                        if(currentInnerClassField.get(currentFieldObject) != null) {
                            predicateList.add(cb.equal(root.get(currentField.getName()).get(currentInnerClassField.getName()), currentInnerClassField.get(currentFieldObject)));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        for(int i = 0; i < predicates.length; ++i) {
            predicates[i] = predicateList.get(i);
        }
        cqr.where(cb.and(predicates));

        fieldList = Arrays.stream(fields).filter(z -> {
            if(z.getAnnotation(OptionalAttribute.class) != null) {
                z.setAccessible(true);
                return true;
            }else return false;
        }).collect(Collectors.toList());

        boolean isDecr = false;
        try {
            for (Field currentField : fieldList) {
                if(currentField.get(paramBean) == null) continue;
                if(currentField.getAnnotation(OptionalAttribute.class).value() == AttributeName.ISDECR) {
                    isDecr = (boolean)currentField.get(paramBean);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        for(Field currentField : fieldList) {
            try {
                if(currentField.get(paramBean) == null) continue;
                if(currentField.getAnnotation(OptionalAttribute.class).value() == AttributeName.ORDER) {
                    List<Order> orderList = new ArrayList();
                    String[] fieldArrayPath = ((String[]) currentField.get(paramBean));
                    for(String str : fieldArrayPath) {
                        String[] fieldPath = str.split("_");
                        Path<Route> path = root.get(fieldPath[0]);
                        for (int i = 1; i < fieldPath.length; ++i) {
                            path = path.get(fieldPath[i]);
                        }
                        if(isDecr) {
                            orderList.add(cb.desc(path));
                        }else {
                            orderList.add(cb.asc(path));
                        }

                    }
                    cqr.orderBy(orderList);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        Query query = session.createQuery(cqr);
        Integer pageSize = null;
        for(Field currentField : fieldList) {
            try {
                if(currentField.get(paramBean) == null) continue;
                if(currentField.getAnnotation(OptionalAttribute.class).value() == AttributeName.PAGESIZE) {
                    pageSize = ((Long)currentField.get(paramBean)).intValue();
                    query.setMaxResults(pageSize);
                }else if(currentField.getAnnotation(OptionalAttribute.class).value() == AttributeName.OFFSET) {
                    if(pageSize == null) throw new RuntimeException("Offset can't be used without pageSize");
                    query.setFirstResult(((Long)currentField.get(paramBean)).intValue() * pageSize);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        List result = query.getResultList();
        return result;
    }

    @Override
    public Float getRoutesDistanceSum() {
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Float> cqr = cb.createQuery(Float.class);

        cqr.select(cb.sum(cqr.from(Route.class).get("distance")));

        Query query = session.createQuery(cqr);

        return (Float) query.getSingleResult();
    }

    @Override
    public Route findRouteByCoordinatesId(Integer id) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Route> cqr = cb.createQuery(Route.class);
        Root<Route> root = cqr.from(Route.class);

        cqr.select(root);
        cqr.where(cb.equal(root.get("coordinates").get("id"),id));

        Query query = session.createQuery(cqr);

        return (Route) query.getSingleResult();
    }

    @Override
    public List<Route> getRoutesDistanceLess(Float distanceValue) {
        Session session = hibernateUtils.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Route> cqr = cb.createQuery(Route.class);
        Root<Route> root = cqr.from(Route.class);

        cqr.select(root);
        cqr.where(cb.lessThan(root.get("distance"), distanceValue));

        Query query = session.createQuery(cqr);

        return query.getResultList();
    }
}
