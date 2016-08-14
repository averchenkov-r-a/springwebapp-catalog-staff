package ru.devjava.averchenkov.staff.model.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.devjava.averchenkov.staff.model.entity.Staff;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Детализация для выборки данных Staff
 *
 * @author Averchenkov R.A.
 */
public class StaffSpecification implements Specification<Staff> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if ((criteria.getOperation() == null) ||
            (criteria.getKey() == null)){
            return null;
        }

        if(criteria.getOperation().equals(">")){
            return criteriaBuilder.greaterThan(
                    root.get(criteria.getKey()),
                    criteria.getValue().toString()
            );
        } else if (criteria.getOperation().equals("<")){
            return criteriaBuilder.lessThan(
                    root.get(criteria.getKey()),
                    criteria.getValue().toString()
            );
        } else if (criteria.getOperation().equals("=")) {
            return criteriaBuilder.equal(
                    root.get(criteria.getKey()),
                    criteria.getValue()
            );
        } else if (criteria.getOperation().equals("like")){
            return criteriaBuilder.equal(
                    root.get(criteria.getKey()),
                    "%" + criteria.getValue() + "%"
            );
        }

        return null;
    }

    public StaffSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
