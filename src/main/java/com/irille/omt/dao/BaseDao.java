package com.irille.omt.dao;

import java.io.Serializable;
import java.util.List;

import irille.pub.bean.BeanMain;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.tb.IEnumFld;

public class BaseDao {
	
	protected static SqlQuery sql(String sql, Serializable... params) {
		return new SqlQuery(sql, params);
	}
	protected static SqlQuery sql(String sql, List<Serializable> params) {
		return new SqlQuery(sql, params.toArray(new Serializable[params.size()]));
	}
	protected static SqlQuery sql(SQL sql) {
		return new SqlQuery(sql.toString(), sql.PARAMS().toArray(new Serializable[sql.PARAMS().size()]));
	}
	
	protected static BeanQuery<?> SELECT(IEnumFld... flds) {
		BeanQuery<?> q = new BeanQuery<>();
		return q.SELECT(flds);
	}
	protected static <T extends BeanMain<?, ?>> BeanQuery<T> SELECT(Class<T> beanClass) {
		BeanQuery<?> q = new BeanQuery<>();
		return q.SELECT(beanClass).FROM(beanClass);
	}
	protected static <T extends BeanMain<?, ?>> T SELECT(Class<T> beanClass, Integer pkey) {
		BeanQuery<?> q = new BeanQuery<>();
		return q.SELECT(beanClass).FROM(beanClass).WHERE("pkey=?", pkey).query();
	}
	protected static <T extends BeanMain<?, ?>> BeanQuery<T> UPDATE(Class<T> beanClass) {
		BeanQuery<?> q = new BeanQuery<>();
		return q.UPDATE(beanClass);
	}
	
	protected static BeanQuery<?> SELECT(Language lang, IEnumFld... flds) {
		BeanQuery<?> q = new BeanQuery<>(lang);
		return q.SELECT(flds);
	}
	protected static <T extends BeanMain<?, ?>> BeanQuery<T> SELECT(Class<T> beanClass, Language lang) {
		BeanQuery<?> q = new BeanQuery<>(lang);
		return q.SELECT(beanClass).FROM(beanClass);
	}
	protected static <T extends BeanMain<?, ?>> T SELECT(Class<T> beanClass, Language lang, Integer pkey) {
		BeanQuery<?> q = new BeanQuery<>(lang);
		return q.SELECT(beanClass).FROM(beanClass).WHERE("pkey=?", pkey).query();
	}
	protected static <T extends BeanMain<?, ?>> BeanQuery<T> DELETE(Class<T> beanClass) {
		return new BeanQuery<>().DELETE(beanClass);
	}
}
