package com.irille.omt.entity.usr;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public class User extends Entity {
	public static final Table<User> table = TableFactory.entity(User.class).column(T.values()).create();
	
	public enum OSex implements IEnumOpt {
		UNKNOW(0,"未知"),
		MALE(1,"男"),
		FEMALE(2,"女");
		public static String NAME="性别";
		public static OSex DEFAULT = UNKNOW;
		private EnumLine _line;
		private OSex(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}

	public enum OType implements IEnumOpt {
		CUSTOMER(1,"客户"),PLATFORM(2,"平台");
		public static final String NAME="用户类型";
		public static final OType DEFAULT = CUSTOMER;
		private EnumLine _line;
		private OType(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}
	
	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		USERNAME(ColumnTemplate.STR__200.showName("用户名").comment("邮箱校验规则")),
		PASSWORD(ColumnTemplate.STR__50_NULL.showName("密码")),
		NAME(ColumnTemplate.STR__200_NULL.showName("名称")),
		SEX(ColumnFactory.opt(OSex.UNKNOW).showName("性别")),
		PHONE(ColumnTemplate.STR__20_NULL.showName("手机号码")),
		ICON(ColumnTemplate.IMG.showName("头像")),
		COMPANY(ColumnTemplate.INT__11_ZERO.showName("公司")),
		TYPE(ColumnFactory.opt(OType.CUSTOMER).showName("性别")),
		ROW_VERSION(ColumnTemplate.SHORT),
		
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
	private String username; // 用户名 VARCHAR(200)
	private String password; // 密码 VARCHAR(50)<null>
	private String name; // 名称 VARCHAR(200)<null>
	private Byte sex; // 性别<OSex> TINYINT(4)
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
	private String phone; // 手机号码 VARCHAR(20)<null>
	private String icon; // icon VARCHAR(200)<null>
	private Integer company; // company INT(11)
	private Byte type; // 性别<OType> TINYINT(4)
	// CUSTOMER:1,客户
	// PLATFORM:2,平台
	private Short rowVersion; // rowVersion SMALLINT(6)

	@Override
	public User init() {
		super.init();
		username = null; // 用户名 VARCHAR(200)
		password = null; // 密码 VARCHAR(50)
		name = null; // 名称 VARCHAR(200)
		sex = OSex.UNKNOW.getLine().getKey(); // 性别<OSex> TINYINT(4)
		phone = null; // 手机号码 VARCHAR(20)
		icon = null; // icon VARCHAR(200)
		company = 0; // company INT(11)
		type = OType.CUSTOMER.getLine().getKey(); // 性别<OType> TINYINT(4)
		rowVersion = null; // rowVersion SMALLINT(6)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public OSex gtSex() {
		return (OSex)(OSex.UNKNOW.getLine().get(sex));
	}
	public void stSex(OSex sex) {
		this.sex = sex.getLine().getKey();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public OType gtType() {
		return (OType)(OType.CUSTOMER.getLine().get(type));
	}
	public void stType(OType type) {
		this.type = type.getLine().getKey();
	}
	public Short getRowVersion() {
		return rowVersion;
	}
	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
