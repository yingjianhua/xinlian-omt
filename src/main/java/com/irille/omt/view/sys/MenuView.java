package com.irille.omt.view.sys;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MenuView {

	private String name;
	@JsonIgnore
	private String fullName;
	@JsonIgnore
	private MenuView up;
	private boolean leaf;//是否为叶菜单:叶菜单下有ops,非叶菜单下有subs
	private List<MenuView> subs;
	private List<OperationView> ops;
	private int sort;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public MenuView getUp() {
		return up;
	}
	public void setUp(MenuView up) {
		this.up = up;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<MenuView> getSubs() {
		return subs;
	}
	public void setSubs(List<MenuView> subs) {
		this.subs = subs;
	}
	public List<OperationView> getOps() {
		return ops;
	}
	public void setOps(List<OperationView> ops) {
		this.ops = ops;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "MenuView [name=" + name + ", leaf=" + leaf + ", subs=" + subs + ", ops=" + ops + ", sort=" + sort + "]";
	}
	
}
