package com.iseplutpi.invoiceapp.model.details;

import com.google.gson.annotations.SerializedName;

public class ListpoProdukItem{

	@SerializedName("jumlah_rupiah")
	private String jumlahRupiah;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("produk")
	private String produk;

	@SerializedName("harga_satuan")
	private String hargaSatuan;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("jml_order")
	private String jmlOrder;

	@SerializedName("kode_produk")
	private String kodeProduk;

	@SerializedName("diskon")
	private String diskon;

	@SerializedName("minimum_request")
	private String minimumRequest;

	@SerializedName("potongan_rupiah")
	private String potonganRupiah;

	public void setJumlahRupiah(String jumlahRupiah){
		this.jumlahRupiah = jumlahRupiah;
	}

	public String getJumlahRupiah(){
		return jumlahRupiah;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setProduk(String produk){
		this.produk = produk;
	}

	public String getProduk(){
		return produk;
	}

	public void setHargaSatuan(String hargaSatuan){
		this.hargaSatuan = hargaSatuan;
	}

	public String getHargaSatuan(){
		return hargaSatuan;
	}

	public void setSatuan(String satuan){
		this.satuan = satuan;
	}

	public String getSatuan(){
		return satuan;
	}

	public void setTotalHarga(String totalHarga){
		this.totalHarga = totalHarga;
	}

	public String getTotalHarga(){
		return totalHarga;
	}

	public void setJmlOrder(String jmlOrder){
		this.jmlOrder = jmlOrder;
	}

	public String getJmlOrder(){
		return jmlOrder;
	}

	public void setKodeProduk(String kodeProduk){
		this.kodeProduk = kodeProduk;
	}

	public String getKodeProduk(){
		return kodeProduk;
	}

	public void setDiskon(String diskon){
		this.diskon = diskon;
	}

	public String getDiskon(){
		return diskon;
	}

	public void setMinimumRequest(String minimumRequest){
		this.minimumRequest = minimumRequest;
	}

	public String getMinimumRequest(){
		return minimumRequest;
	}

	public void setPotonganRupiah(String potonganRupiah){
		this.potonganRupiah = potonganRupiah;
	}

	public String getPotonganRupiah(){
		return potonganRupiah;
	}

	@Override
 	public String toString(){
		return 
			"ListpoProdukItem{" + 
			"jumlah_rupiah = '" + jumlahRupiah + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",produk = '" + produk + '\'' + 
			",harga_satuan = '" + hargaSatuan + '\'' + 
			",satuan = '" + satuan + '\'' + 
			",total_harga = '" + totalHarga + '\'' + 
			",jml_order = '" + jmlOrder + '\'' + 
			",kode_produk = '" + kodeProduk + '\'' + 
			",diskon = '" + diskon + '\'' + 
			",minimum_request = '" + minimumRequest + '\'' + 
			",potongan_rupiah = '" + potonganRupiah + '\'' + 
			"}";
		}
}