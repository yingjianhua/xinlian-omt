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
import com.irille.omt.entity.sys.Operation;

public class RoleOperation extends Entity {

	public static final Table<RoleOperation> table = TableFactory.entity(RoleOperation.class).column(T.values()).index(true, T.role, T.operation).create();

	public enum T implements IColumnField {
		pkey(ColumnTemplate.PKEY),
		role(ColumnFactory.manyToOne(Role.class).showName("角色")),
		operation(ColumnFactory.manyToOne(Operation.class).showName("操作")),
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
	private Integer role; // 角色<表主键:Role> INT(11)
	private Integer operation; // 操作<表主键:Operation> INT(11)

	@Override
	public RoleOperation init() {
		super.init();
		role = null; // 角色 INT(11)
		operation = null; // 操作 INT(11)
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

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Operation gtOperation() {
		return selectFrom(Operation.class, getOperation());
	}

	public void stOperation(Operation operation) {
		this.operation = operation.getPkey();
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
