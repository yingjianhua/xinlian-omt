package com.irille.omt.entity.sys;

import java.util.stream.Stream;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.pub.tb.TbBase;

public class Access extends BeanInt<Access> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(Access.class, "访问请求").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		ACTION(Sys.T.STR__100, "action"),//请求的名字如:sys_access_init
		MODULE(Sys.T.STR__100, "模块"),//模块的平台显示名称如:系统模块
		CONTROLLER(Sys.T.STR__100, "控制器"),//控制器的平台显示名称如:菜单管理
		METHOD(Sys.T.STR__100, "方法"),//方法的平台显示名称如:查询
		DESCRIPTION(Sys.T.TEXT__500_NULL, "描述"),
		SORT(Sys.T.SORT__INT, "sort"),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		public static final Index IDX_ACTION = TB.addIndex("action", true, ACTION);
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
  private String _action;	// action  STR(100)
  private String _module;	// 模块  STR(100)
  private String _controller;	// 控制器  STR(100)
  private String _method;	// 方法  STR(100)
  private String _description;	// 描述  TEXT(200)<null>
  private Integer _sort;	// sort  INT

	@Override
  public Access init(){
		super.init();
    _action=null;	// action  STR(100)
    _module=null;	// 模块  STR(100)
    _controller=null;	// 控制器  STR(100)
    _method=null;	// 方法  STR(100)
    _description=null;	// 描述  TEXT(200)
    _sort=0;	// sort  INT
    return this;
  }

  //方法----------------------------------------------
  public static Access loadUniqueAction(boolean lockFlag,String action) {
    return (Access)loadUnique(T.IDX_ACTION,lockFlag,action);
  }
  public static Access chkUniqueAction(boolean lockFlag,String action) {
    return (Access)chkUnique(T.IDX_ACTION,lockFlag,action);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getAction(){
    return _action;
  }
  public void setAction(String action){
    _action=action;
  }
  public String getModule(){
    return _module;
  }
  public void setModule(String module){
    _module=module;
  }
  public String getController(){
    return _controller;
  }
  public void setController(String controller){
    _controller=controller;
  }
  public String getMethod(){
    return _method;
  }
  public void setMethod(String method){
    _method=method;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
