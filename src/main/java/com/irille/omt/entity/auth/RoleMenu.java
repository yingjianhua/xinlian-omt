package com.irille.omt.entity.auth;

import java.util.stream.Stream;

import com.irille.omt.entity.sys.Menu;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.pub.tb.Tb.Index;

public class RoleMenu extends BeanInt<RoleMenu> {
	private static final long serialVersionUID = 3685013477292302359L;
	public static final Tb<?> TB = new Tb<>(RoleMenu.class, "角色菜单关联表").setAutoIncrement();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TbBase.crtIntPkey()),
		ROLE(Role.fldOutKey()),
		MENU(Menu.fldOutKey()),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		public static final Index IDX_ROLE_MENU = TB.addIndex("role_menu", true, ROLE, MENU);
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
  private Integer _role;	// 角色 <表主键:Role>  INT
  private Integer _menu;	// 菜单 <表主键:Menu>  INT

	@Override
  public RoleMenu init(){
		super.init();
    _role=null;	// 角色 <表主键:Role>  INT
    _menu=null;	// 菜单 <表主键:Menu>  INT
    return this;
  }

  //方法----------------------------------------------
  public static RoleMenu loadUniqueRole_menu(boolean lockFlag,Integer role,Integer menu) {
    return (RoleMenu)loadUnique(T.IDX_ROLE_MENU,lockFlag,role,menu);
  }
  public static RoleMenu chkUniqueRole_menu(boolean lockFlag,Integer role,Integer menu) {
    return (RoleMenu)chkUnique(T.IDX_ROLE_MENU,lockFlag,role,menu);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getRole(){
    return _role;
  }
  public void setRole(Integer role){
    _role=role;
  }
  public Role gtRole(){
    if(getRole()==null)
      return null;
    return (Role)get(Role.class,getRole());
  }
  public void stRole(Role role){
    if(role==null)
      setRole(null);
    else
      setRole(role.getPkey());
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

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
