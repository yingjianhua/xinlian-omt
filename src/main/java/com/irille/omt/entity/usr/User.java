package com.irille.omt.entity.usr;

import java.util.stream.Stream;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OSex;
import irille.pub.bean.BeanInt;
import irille.pub.tb.EnumLine;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class User extends BeanInt<User> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(User.class, "用户").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		USERNAME(Sys.T.STR__200, "用户名"),//邮箱校验规则
		PASSWORD(Sys.T.PASSWORD__NULL, "密码"),
		NAME(Sys.T.STR__200_NULL, "名称"),
		SEX(Sys.T.SEX, "性别"),
		PHONE(Sys.T.MOBILE__NULL, "手机号码"),
		ICON(Sys.T.IMG__200_NULL, "icon"),
		COMPANY(Sys.T.INT_PLUS_OR_ZERO,"company"),
		TYPE(Tb.crt(OType.customer)),
		ROWVERSION(Sys.T.ROW_VERSION)
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		private Fld<?> _fld;

		private T(Class<?> clazz, String name, boolean... isnull) {
			_fld = IEnumFld.crtFld(this, clazz, name, isnull);
		}

		private T(IEnumFld fld, boolean... isnull) {
			_fld = IEnumFld.crtFld(this, fld, null, isnull);
		}

		private T(IEnumFld fld, String name, boolean... null1) {
			_fld = IEnumFld.crtFld(this, fld, name, null1);
		}

		private T(IEnumFld fld, String name, int strLen) {
			_fld = IEnumFld.crtFld(this, fld, name, strLen);
		}

		private T(Fld<?> fld) {
			_fld = IEnumFld.crtFld(this, fld);
		}

		public Fld<?> getFld() {
			return _fld;
		}
	}

	static { // 在此可以加一些对FLD进行特殊设定的代码
		Stream.of(T.values()).forEach(f->TB.add(f.getFld()));
		TB.lockAllFlds();
	}

	public enum OType implements IEnumOpt {
		customer(1,"客户"),platform(2,"平台");
		public static final String NAME="用户类型";
		public static final OType DEFAULT = customer;
		private EnumLine _line;
		private OType(int key, String name) {_line=new EnumLine(this,key,name);	}
		public EnumLine getLine(){return _line;	}
	}
	
	public static Fld<?> fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld<?> fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name).setType(null);
	}

	// @formatter:on
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _username;	// 用户名  STR(200)
  private String _password;	// 密码  STR(40)<null>
  private String _name;	// 名称  STR(200)<null>
  private Byte _sex;	// 性别 <OSex>  BYTE
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
  private String _phone;	// 手机号码  STR(20)<null>
  private String _icon;	// icon  STR(200)<null>
  private Integer _company;	// company  INT
  private Byte _type;	// 用户类型 <OType>  BYTE
	// customer:1,客户
	// platform:2,平台
  private Short _rowversion;	// 版本  SHORT

	@Override
  public User init(){
		super.init();
    _username=null;	// 用户名  STR(200)
    _password=null;	// 密码  STR(40)
    _name=null;	// 名称  STR(200)
    _sex=OSex.DEFAULT.getLine().getKey();	// 性别 <OSex>  BYTE
    _phone=null;	// 手机号码  STR(20)
    _icon=null;	// icon  STR(200)
    _company=0;	// company  INT
    _type=OType.DEFAULT.getLine().getKey();	// 用户类型 <OType>  BYTE
    _rowversion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getUsername(){
    return _username;
  }
  public void setUsername(String username){
    _username=username;
  }
  public String getPassword(){
    return _password;
  }
  public void setPassword(String password){
    _password=password;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Byte getSex(){
    return _sex;
  }
  public void setSex(Byte sex){
    _sex=sex;
  }
  public OSex gtSex(){
    return (OSex)(OSex.UNKNOW.getLine().get(_sex));
  }
  public void stSex(OSex sex){
    _sex=sex.getLine().getKey();
  }
  public String getPhone(){
    return _phone;
  }
  public void setPhone(String phone){
    _phone=phone;
  }
  public String getIcon(){
    return _icon;
  }
  public void setIcon(String icon){
    _icon=icon;
  }
  public Integer getCompany(){
    return _company;
  }
  public void setCompany(Integer company){
    _company=company;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OType gtType(){
    return (OType)(OType.customer.getLine().get(_type));
  }
  public void stType(OType type){
    _type=type.getLine().getKey();
  }
  public Short getRowversion(){
    return _rowversion;
  }
  public void setRowversion(Short rowversion){
    _rowversion=rowversion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
