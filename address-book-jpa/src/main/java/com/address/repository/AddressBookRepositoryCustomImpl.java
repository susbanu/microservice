package com.address.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.address.repository.entity.AddressEntity;

@Repository
public class AddressBookRepositoryCustomImpl implements AddressBookRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AddressEntity> findAllByAny(String searchField) {

		var cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AddressEntity> criteriaQuery = cb.createQuery(AddressEntity.class);
		Root<AddressEntity> root = criteriaQuery.from(AddressEntity.class);

		Predicate firstName = cb.like(cb.upper(root.get("firstName")), "%" + searchField.toUpperCase() + "%");
		Predicate lastName = cb.like(cb.upper(root.get("lastName")), "%" + searchField.toUpperCase() + "%");
		Predicate street = cb.like(cb.upper(root.get("street")), "%" + searchField.toUpperCase() + "%");
		Predicate city = cb.like(cb.upper(root.get("city")), "%" + searchField.toUpperCase() + "%");
		Predicate state = cb.like(cb.upper(root.get("state")), "%" + searchField.toUpperCase() + "%");
		Predicate zip = cb.equal(root.get("zip"), searchField);

		criteriaQuery.select(root).where(cb.or(firstName, lastName, street, city, state, zip));
		TypedQuery<AddressEntity> query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}
}
