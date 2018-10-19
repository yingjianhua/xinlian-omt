package com.irille.omt.entity.sys;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

public class Access extends Entity {
	public static final Table<Access> table = TableFactory.entity(Access.class).column(T.values()).index(true, T.action).create();

	public enum T implements IColumnField {
		pkey(ColumnTemplate.PKEY),
		action(ColumnTemplate.STR__100.showName("action").comment("请求的名字如:sys_access_init")),
		module(ColumnTemplate.STR__100.showName("模块").comment("模块的平台显示名称如:系统模块")),
		controller(ColumnTemplate.STR__100.showName("控制器").comment("控制器的平台显示名称如:菜单管理")),
		method(ColumnTemplate.STR__100.showName("方法").comment("方法的平台显示名称如:查询")),
		description(ColumnTemplate.TEXT__20000_NULL.showName("描述")),
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

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private String action; // action VARCHAR(100)
	private String module; // 模块 VARCHAR(100)
	private String controller; // 控制器 VARCHAR(100)
	private String method; // 方法 VARCHAR(100)
	private String description; // 描述 TEXT(20000)<null>
	private Integer sort; // 排序 INT(11)

	@Override
	public Access init() {
		super.init();
		action = null; // action VARCHAR(100)
		module = null; // 模块 VARCHAR(100)
		controller = null; // 控制器 VARCHAR(100)
		method = null; // 方法 VARCHAR(100)
		description = null; // 描述 TEXT(20000)
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
