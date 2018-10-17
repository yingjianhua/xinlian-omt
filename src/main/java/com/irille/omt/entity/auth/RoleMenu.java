package com.irille.omt.entity.auth;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;
import com.irille.omt.entity.sys.Menu;

public class RoleMenu extends Entity {
	public static final Table<RoleMenu> table = TableFactory.entity(RoleMenu.class).column(T.values()).index(true, T.ROLE, T.MENU).create();
	
	public enum T implements IColumnField {		
		PKEY(ColumnTemplate.PKEY),
		ROLE(ColumnFactory.manyToOne(Role.class)),
		MENU(ColumnFactory.manyToOne(Menu.class)),
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
	private Integer pkey; // pkey INT(11)
	private Integer role; // role<表主键:Role> INT(11)
	private Integer menu; // menu<表主键:Menu> INT(11)

	@Override
	public RoleMenu init() {
		super.init();
		role = null; // role INT(11)
		menu = null; // menu INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Role gtRole() {
		return selectFrom(Role.class, getRole());
	}
	public void stRole(Role role) {
		this.role = role.getPkey();
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

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
