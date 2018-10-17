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

public class Menu extends Entity {

	public static final Table<Menu> table = TableFactory.entity(Menu.class).column(T.values()).index(true, T.UP, T.NAME).create();
	
	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		NAME(ColumnTemplate.STR__100.showName("名称").comment("菜单的平台显示名称如:机构信息")),
		ROUTE(ColumnTemplate.STR__100.showName("路由").comment("浏览器地址栏中显示的路径,唯一")),
		COMPONENT(ColumnTemplate.STR__100.showName("组件").comment("页面组件")),
		FULL_NAME(ColumnTemplate.STR__100.showName("完全限定名").comment("菜单的完全限定名如:系统管理_机构信息") ),
		UP(ColumnFactory.manyToOne(Menu.T.PKEY).nullable(true).showName("上级菜单")),
		LEAF(ColumnTemplate.BOOLEAN.showName("叶菜单").comment("是否为叶菜单")),
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
	private String name; // 名称 VARCHAR(100)
	private String route; // 路由 VARCHAR(100)
	private String component; // 组件 VARCHAR(100)
	private String fullName; // 完全限定名 VARCHAR(100)
	private Integer up; // 上级菜单<表主键:Menu> INT(11)<null>
	private Boolean leaf; // 叶菜单 TINYINT(1)
	private Integer sort; // 排序 INT(11)

	@Override
	public Menu init() {
		super.init();
		name = null; // 名称 VARCHAR(100)
		route = null; // 路由 VARCHAR(100)
		component = null; // 组件 VARCHAR(100)
		fullName = null; // 完全限定名 VARCHAR(100)
		up = null; // 上级菜单 INT(11)
		leaf = null; // 叶菜单 TINYINT(1)
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
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getUp() {
		return up;
	}
	public void setUp(Integer up) {
		this.up = up;
	}
	public Menu gtUp() {
		return selectFrom(Menu.class, getUp());
	}
	public void stUp(Menu up) {
		this.up = up.getPkey();
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
