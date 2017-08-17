package com.epam.kgd.victory.bean;

/**
 * The Class Good.
 */
public class Good extends BaseBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7637131175622190957L;

	/** Identifier of good category */
	private int categoryId;
	/** Identifier of of good condition */
	private int conditionId;
	/** Good name */
	private String name;
	/** Good description */
	private String description;
	/** Starting price */
	private double startPrice;

	/** Constructor without parameters */
	public Good() {

	}

	/**
	 * Gets the category id.
	 *
	 * @return the category id
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the good's category id.
	 *
	 * @param categoryId
	 *            the new category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets the condition id.
	 *
	 * @return the condition id
	 */
	public int getConditionId() {
		return conditionId;
	}

	/**
	 * Sets the good's condition id.
	 *
	 * @param conditionId
	 *            the new condition id
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * Gets the good's name.
	 *
	 * @return the good's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the good's name.
	 *
	 * @param name
	 *            the new good's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the good's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the good's description.
	 *
	 * @param description
	 *            the new good's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the start price.
	 *
	 * @return the start price
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * Sets the start price.
	 *
	 * @param startPrice
	 *            the new starting price
	 */
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + categoryId;
		result = prime * result + conditionId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(startPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Good other = (Good) obj;
		if (categoryId != other.categoryId)
			return false;
		if (conditionId != other.conditionId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(startPrice) != Double.doubleToLongBits(other.startPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Good [categoryId=" + categoryId + ", conditionId=" + conditionId + ", name=" + name + ", description="
				+ description + ", startPrice=" + startPrice + "]";
	}

}
