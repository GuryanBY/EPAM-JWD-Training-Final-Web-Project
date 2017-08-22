package com.epam.kgd.victory.bean;

import java.io.Serializable;
/**
 * The Class BaseBean.
 */
public class BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1719271767473431788L;

	/** Identifier for all type of beans*/
	private int id;

	
	/** Constructor without parameters */
	public BaseBean() {

	}
	/**
	 * Gets the entity id.
	 *
	 * @return the entity id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the entity id.
	 *
	 * @param id the new entity id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseBean other = (BaseBean) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseBean [id=" + id + "]";
	}

}
