package com.jbk.entity;

public class ProductModel {

	private Object productId;
	private Object productName;
	private Object productPrice;

	public ProductModel() {
		// TODO Auto-generated constructor stub
	}

	

	public Object getProductId() {
		return productId;
	}



	public void setProductId(Object productId) {
		this.productId = productId;
	}



	public Object getProductName() {
		return productName;
	}



	public void setProductName(Object productName) {
		this.productName = productName;
	}



	public Object getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(Object productPrice) {
		this.productPrice = productPrice;
	}



	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}

}
