package com.iseplutpi.invoiceapp.model.details;

import com.google.gson.annotations.SerializedName;

public class Datatotalpo{

	@SerializedName("ongkir")
	private int ongkir;

	@SerializedName("subtotal")
	private int subtotal;

	@SerializedName("ppn")
	private int ppn;

	public void setOngkir(int ongkir){
		this.ongkir = ongkir;
	}

	public int getOngkir(){
		return ongkir;
	}

	public void setSubtotal(int subtotal){
		this.subtotal = subtotal;
	}

	public int getSubtotal(){
		return subtotal;
	}

	public void setPpn(int ppn){
		this.ppn = ppn;
	}

	public int getPpn(){
		return ppn;
	}

	@Override
 	public String toString(){
		return 
			"Datatotalpo{" + 
			"ongkir = '" + ongkir + '\'' + 
			",subtotal = '" + subtotal + '\'' + 
			",ppn = '" + ppn + '\'' + 
			"}";
		}
}