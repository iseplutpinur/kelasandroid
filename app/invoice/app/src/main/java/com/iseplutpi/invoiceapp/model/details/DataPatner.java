package com.iseplutpi.invoiceapp.model.details;

import com.google.gson.annotations.SerializedName;

public class DataPatner{

	@SerializedName("alamat_partner")
	private String alamatPartner;

	@SerializedName("kode_partner")
	private String kodePartner;

	@SerializedName("kodepos_kota")
	private String kodeposKota;

	@SerializedName("nama_partner")
	private String namaPartner;

	public void setAlamatPartner(String alamatPartner){
		this.alamatPartner = alamatPartner;
	}

	public String getAlamatPartner(){
		return alamatPartner;
	}

	public void setKodePartner(String kodePartner){
		this.kodePartner = kodePartner;
	}

	public String getKodePartner(){
		return kodePartner;
	}

	public void setKodeposKota(String kodeposKota){
		this.kodeposKota = kodeposKota;
	}

	public String getKodeposKota(){
		return kodeposKota;
	}

	public void setNamaPartner(String namaPartner){
		this.namaPartner = namaPartner;
	}

	public String getNamaPartner(){
		return namaPartner;
	}

	@Override
 	public String toString(){
		return 
			"DataPatner{" + 
			"alamat_partner = '" + alamatPartner + '\'' + 
			",kode_partner = '" + kodePartner + '\'' + 
			",kodepos_kota = '" + kodeposKota + '\'' + 
			",nama_partner = '" + namaPartner + '\'' + 
			"}";
		}
}