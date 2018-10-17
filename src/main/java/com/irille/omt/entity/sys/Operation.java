package com.irille.omt.entity.sys;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

public class Operation extends Entity {

	public static final Table<Operation> table = TableFactory.entity(Operation.class).column(T.values()).create();
	
	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		NAME(ColumnTemplate.STR__100.showName("名字")),
		MENU(ColumnFactory.manyToOne(Menu.class).showName("菜单")),
		ACTION(ColumnTemplate.STR__100.showName("请求")),
		SORT(ColumnTemplate.INT__11.showName("排序")),
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

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private String name; // 名字 VARCHAR(100)
	private Integer menu; // 菜单<表主键:Menu> INT(11)
	private String action; // 请求 VARCHAR(100)
	private Integer sort; // 排序 INT(11)

	@Override
	public Operation init() {
		super.init();
		name = null; // 名字 VARCHAR(100)
		menu = null; // 菜单 INT(11)
		action = null; // 请求 VARCHAR(100)
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
	public Integer getMenu() {
		return menu;
	}
	public void setMenu(Integer menu) {
		this.menu = menu;
	}
	public Menu gtMenu() {
		return selectFrom(Menu.class, getMenu());
	}
	public void stMenu(Menu menu) {
		this.menu = menu.getPkey();
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
