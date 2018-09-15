package com.irille.omt.entity.sys;

import java.util.stream.Stream;

import com.irille.omt.entity.auth.Role.T;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;

public class Menu extends BeanInt<Menu> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(Menu.class, "菜单").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		NAME(Sys.T.STR__100, "name"),
		UP(Menu.fldOutKey("up", "上级菜单")),
		LEAF(Sys.T.YN, "leaf"),
		SORT(Sys.T.SORT__INT, "sort"),
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
  private String _name;	// name  STR(100)
  private Integer _up;	// 上级菜单 <表主键:Menu>  INT
  private Byte _leaf;	// leaf <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _sort;	// sort  INT

	@Override
  public Menu init(){
		super.init();
    _name=null;	// name  STR(100)
    _up=null;	// 上级菜单 <表主键:Menu>  INT
    _leaf=OYn.DEFAULT.getLine().getKey();	// leaf <OYn>  BYTE
    _sort=0;	// sort  INT
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
  public Integer getUp(){
    return _up;
  }
  public void setUp(Integer up){
    _up=up;
  }
  public Menu gtUp(){
    if(getUp()==null)
      return null;
    return (Menu)get(Menu.class,getUp());
  }
  public void stUp(Menu up){
    if(up==null)
      setUp(null);
    else
      setUp(up.getPkey());
  }
  public Byte getLeaf(){
    return _leaf;
  }
  public void setLeaf(Byte leaf){
    _leaf=leaf;
  }
  public Boolean gtLeaf(){
    return byteToBoolean(_leaf);
  }
  public void stLeaf(Boolean leaf){
    _leaf=booleanToByte(leaf);
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
