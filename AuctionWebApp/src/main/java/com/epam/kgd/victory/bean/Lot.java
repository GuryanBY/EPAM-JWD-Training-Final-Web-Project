package com.epam.kgd.victory.bean;

import java.time.LocalDateTime;
import static com.epam.kgd.victory.util.CurrencyConverter.convertToCurrentCurrency;
import static com.epam.kgd.victory.util.DateConverter.convertDateToString;

public class Lot extends BaseBean {

	private static final long serialVersionUID = 1478055251392587931L;

	private int buyerId;
	private int sellerId;
	private int goodId;
	private String auctionTypeId;
	private int statusId;
	private String lotName;
	private int goodAmount;
	private LocalDateTime startSellingDate;
	private LocalDateTime endSellingDate;
	private LocalDateTime buyingDate;
	private double endPrice;

	public Lot() {
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public String getAuctionTypeId() {
		return auctionTypeId;
	}

	public void setAuctionTypeId(String auctionTypeId) {
		this.auctionTypeId = auctionTypeId;
	}

	public String getLotName() {
		return lotName;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public int getGoodAmount() {
		return goodAmount;
	}

	public void setGoodAmount(int goodAmount) {
		this.goodAmount = goodAmount;
	}


	public double getEndPrice() {
		return endPrice;
	}
	public double getEndPrice(String locale){
		 return convertToCurrentCurrency(endPrice, locale);
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public LocalDateTime getStartSellingDate() {
		return startSellingDate;
	}
	public String getConvertedStartSellingDate(){
		return convertDateToString(startSellingDate);
	}

	public void setStartSellingDate(LocalDateTime startSellingDate) {
		this.startSellingDate = startSellingDate;
	}

	public LocalDateTime getEndSellingDate() {
		return endSellingDate;
	}

	public void setEndSellingDate(LocalDateTime endSellingDate) {
		this.endSellingDate = endSellingDate;
	}

	public LocalDateTime getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(LocalDateTime buyingDate) {
		this.buyingDate = buyingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((auctionTypeId == null) ? 0 : auctionTypeId.hashCode());
		result = prime * result + buyerId;
		result = prime * result + ((buyingDate == null) ? 0 : buyingDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(endPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((endSellingDate == null) ? 0 : endSellingDate.hashCode());
		result = prime * result + goodAmount;
		result = prime * result + goodId;
		result = prime * result + ((lotName == null) ? 0 : lotName.hashCode());
		result = prime * result + sellerId;
		result = prime * result + ((startSellingDate == null) ? 0 : startSellingDate.hashCode());
		result = prime * result + statusId;
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
		Lot other = (Lot) obj;
		if (auctionTypeId == null) {
			if (other.auctionTypeId != null)
				return false;
		} else if (!auctionTypeId.equals(other.auctionTypeId))
			return false;
		if (buyerId != other.buyerId)
			return false;
		if (buyingDate == null) {
			if (other.buyingDate != null)
				return false;
		} else if (!buyingDate.equals(other.buyingDate))
			return false;
		if (Double.doubleToLongBits(endPrice) != Double.doubleToLongBits(other.endPrice))
			return false;
		if (endSellingDate == null) {
			if (other.endSellingDate != null)
				return false;
		} else if (!endSellingDate.equals(other.endSellingDate))
			return false;
		if (goodAmount != other.goodAmount)
			return false;
		if (goodId != other.goodId)
			return false;
		if (lotName == null) {
			if (other.lotName != null)
				return false;
		} else if (!lotName.equals(other.lotName))
			return false;
		if (sellerId != other.sellerId)
			return false;
		if (startSellingDate == null) {
			if (other.startSellingDate != null)
				return false;
		} else if (!startSellingDate.equals(other.startSellingDate))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lot [buyerId=" + buyerId + ", sellerId=" + sellerId + ", goodId=" + goodId + ", auctionTypeId="
				+ auctionTypeId + ", statusId=" + statusId + ", lotName=" + lotName + ", goodAmount=" + goodAmount
				+ ", startSellingDate=" + startSellingDate + ", endSellingDate=" + endSellingDate + ", buyingDate="
				+ buyingDate + ", endPrice=" + endPrice + "]";
	}


	

}
