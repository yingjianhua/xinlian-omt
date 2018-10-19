package com.irille.omt.entity.auth;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

public class Role extends Entity {
	public static final Table<Role> table = TableFactory.entity(Role.class).column(T.values()).index(true, T.name).create();

	public enum T implements IColumnField {
		pkey(ColumnTemplate.PKEY),
		name(ColumnTemplate.STR__100.showName("名字")),
		sort(ColumnTemplate.INT__11.showName("排序")),
		;
		private Column column;

		T(IColumnTemplate template) {
			this.column = template.builder().create(this);
		}

		T(ColumnBuilder builder) {
			this.column = builder.create(this);
		}

		@Override
		public Column column() {
			return column;
		}

	}

	public static final String admin = "admin";
	public static final String anonymous = "anonymous";

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // pkey INT(11)
	private String name; // 名字 VARCHAR(100)
	private Integer sort; // 排序 INT(11)

	@Override
	public Role init() {
		super.init();
		name = null; // 名字 VARCHAR(100)
		sort = null; // 排序 INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
