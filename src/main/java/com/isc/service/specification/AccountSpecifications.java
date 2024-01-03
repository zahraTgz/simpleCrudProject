package com.isc.service.specification;

import com.isc.dto.AccountSearchCriteriaDto;
import com.isc.entity.Account;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecifications {

    public static Specification<Account> searchUsers(AccountSearchCriteriaDto searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(searchCriteria.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchCriteria.getName() + "%"));
            }

            if (StringUtils.hasText(searchCriteria.getEmail())) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + searchCriteria.getEmail() + "%"));
            }

            if (searchCriteria.getAge() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("age"), searchCriteria.getAge()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
