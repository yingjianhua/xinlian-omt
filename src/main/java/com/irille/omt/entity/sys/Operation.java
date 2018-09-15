package com.irille.omt.entity.sys;

import java.util.stream.Stream;

import com.irille.omt.entity.auth.Role.T;

import irille.core.sys.Sys;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Operation extends BeanInt<Operation> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(Operation.class, "操作").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		NAME(Sys.T.STR__100, "名字"),
		MENU(Menu.fldOutKey("menu", "菜单")),
		ROUTE(Sys.T.STR__100, "路由"),
		ACTION(Sys.T.STR__100, "请求"),
		SORT(Sys.T.SORT__INT, "排序"),
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
  private String _name;	// 名字  STR(100)
  private Integer _menu;	// 菜单 <表主键:Menu>  INT
  private String _route;	// 路由  STR(100)
  private String _action;	// 请求  STR(100)
  private Integer _sort;	// 排序  INT

	@Override
  public Operation init(){
		super.init();
    _name=null;	// 名字  STR(100)
    _menu=null;	// 菜单 <表主键:Menu>  INT
    _route=null;	// 路由  STR(100)
    _action=null;	// 请求  STR(100)
    _sort=0;	// 排序  INT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Integer getMenu(){
    return _menu;
  }
  public void setMenu(Integer menu){
    _menu=menu;
  }
  public Menu gtMenu(){
    if(getMenu()==null)
      return null;
    return (Menu)get(Menu.class,getMenu());
  }
  public void stMenu(Menu menu){
    if(menu==null)
      setMenu(null);
    else
      setMenu(menu.getPkey());
  }
  public String getRoute(){
    return _route;
  }
  public void setRoute(String route){
    _route=route;
  }
  public String getAction(){
    return _action;
  }
  public void setAction(String action){
    _action=action;
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
