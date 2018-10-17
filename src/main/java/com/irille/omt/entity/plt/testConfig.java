package com.irille.omt.entity.plt;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class testConfig {

	private EntityManager entityManager;
	
	private JPAQueryFactory queryFactory;
	
	void testLoad() {
		queryFactory = new JPAQueryFactory(entityManager);
		QConfig config = QConfig.config;
		queryFactory.update(null).execute();
	}
}
