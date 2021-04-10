package com.iseplutpi.invoiceapp.model.details;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RequestDetailInvoice{

	@SerializedName("header_invoice")
	private HeaderInvoice headerInvoice;

	@SerializedName("datatotalpo")
	private Datatotalpo datatotalpo;

	@SerializedName("code")
	private int code;

	@SerializedName("jumlah_produk")
	private int jumlahProduk;

	@SerializedName("data_patner")
	private DataPatner dataPatner;

	@SerializedName("message")
	private String message;

	@SerializedName("listpo_produk")
	private List<ListpoProdukItem> listpoProduk;

	@SerializedName("status")
	private boolean status;

	public void setHeaderInvoice(HeaderInvoice headerInvoice){
		this.headerInvoice = headerInvoice;
	}

	public HeaderInvoice getHeaderInvoice(){
		return headerInvoice;
	}

	public void setDatatotalpo(Datatotalpo datatotalpo){
		this.datatotalpo = datatotalpo;
	}

	public Datatotalpo getDatatotalpo(){
		return datatotalpo;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setJumlahProduk(int jumlahProduk){
		this.jumlahProduk = jumlahProduk;
	}

	public int getJumlahProduk(){
		return jumlahProduk;
	}

	public void setDataPatner(DataPatner dataPatner){
		this.dataPatner = dataPatner;
	}

	public DataPatner getDataPatner(){
		return dataPatner;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setListpoProduk(List<ListpoProdukItem> listpoProduk){
		this.listpoProduk = listpoProduk;
	}

	public List<ListpoProdukItem> getListpoProduk(){
		return listpoProduk;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RequestDetailInvoice{" + 
			"header_invoice = '" + headerInvoice + '\'' + 
			",datatotalpo = '" + datatotalpo + '\'' + 
			",code = '" + code + '\'' + 
			",jumlah_produk = '" + jumlahProduk + '\'' + 
			",data_patner = '" + dataPatner + '\'' + 
			",message = '" + message + '\'' + 
			",listpo_produk = '" + listpoProduk + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}