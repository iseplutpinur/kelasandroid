package com.iseplutpi.invoiceapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iseplutpi.invoiceapp.R;
import com.iseplutpi.invoiceapp.adapter.AdapterListProduct;
import com.iseplutpi.invoiceapp.model.details.ListpoProdukItem;
import com.iseplutpi.invoiceapp.model.details.RequestDetailInvoice;
import com.iseplutpi.invoiceapp.network.ApiServices;
import com.iseplutpi.invoiceapp.network.RetrofitClient;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInvoiceActivity extends AppCompatActivity {
    // Deklarasi variable
    TextView txgetNoInvoice, txtgetTglBuatInvoice, txtStaff, txtgetNamaPerusahaan, txtgetAlamatCompany, txtgetKodeCompany, txtgetTotalDataProduct;

    RecyclerView rvListProductfromFactur;
    String no_invoice = "";

    TextView txtPajak, txtOngir, txtSubTotal, txtTotalBayar;
    Button btTotalBayar, btCancelOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_invoice);
        getSupportActionBar().setTitle("Detail Item Invoice");

        Bundle getDataIntent = getIntent().getExtras();

        String invoiceGet = getDataIntent.getString("NOINVOICE");
        Log.d("Log", "NOINVOICE " + invoiceGet);
        txgetNoInvoice = (TextView) findViewById(R.id.getNoInvoice);
        txtgetTglBuatInvoice = (TextView) findViewById(R.id.getTglBuatInvoice);
        txtStaff = (TextView) findViewById(R.id.getStaff);
        txtgetNamaPerusahaan = (TextView) findViewById(R.id.getNamaPerusahaan);
        txtgetAlamatCompany = (TextView) findViewById(R.id.getAlamatPerusahaan);
        txtgetKodeCompany = (TextView) findViewById(R.id.getKodePerusahaan);
        txtgetTotalDataProduct = (TextView) findViewById(R.id.totalDataProductfromFactur);
        rvListProductfromFactur = (RecyclerView) findViewById(R.id.rlaListPoProduct);

        txtPajak = (TextView) findViewById(R.id.txPajak);
        txtOngir = (TextView) findViewById(R.id.txOngkir);
        txtSubTotal = (TextView) findViewById(R.id.txSubtotal);
        txtTotalBayar = (TextView) findViewById(R.id.txTotalBayar);

        btTotalBayar = (Button) findViewById(R.id.btTotalBayar);
        btCancelOrder = (Button) findViewById(R.id.btCancelOrder);

        btTotalBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        btCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        no_invoice = invoiceGet;
        rvListProductfromFactur.setHasFixedSize(true);
        rvListProductfromFactur.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        moduleRequestDetailInvoice(no_invoice);
        moduleListProductfromPO(no_invoice);

    }

    private void moduleListProductfromPO(String no_invoice) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<RequestDetailInvoice> reqListProduct = apiServices.reqDetailInvoice(no_invoice);

        reqListProduct.enqueue(new Callback<RequestDetailInvoice>() {
            @Override
            public void onResponse(Call<RequestDetailInvoice> call, Response<RequestDetailInvoice> response) {
                if (response.isSuccessful()) {
                    int statuscode = response.body().getCode();

                    if (statuscode == 200) {
                        int totalDataProduct = response.body().getJumlahProduk();

                        txtgetTotalDataProduct.setText(totalDataProduct + " Ordered Product");
                        List<ListpoProdukItem> poItem = response.body().getListpoProduk();
                        AdapterListProduct adpListProductfromPO = new AdapterListProduct(DetailInvoiceActivity.this, poItem);
                        rvListProductfromFactur.setAdapter(adpListProductfromPO);
                    } else {
                        Toast.makeText(DetailInvoiceActivity.this, "Data tidak ditemukan.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestDetailInvoice> call, Throwable t) {

            }
        });
    }

    public void moduleRequestDetailInvoice(String no_invoice) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<RequestDetailInvoice> requestListInvoiceCall = apiServices.reqDetailInvoice(no_invoice);
        requestListInvoiceCall.enqueue(new Callback<RequestDetailInvoice>() {
            @Override
            public void onResponse(Call<RequestDetailInvoice> call, Response<RequestDetailInvoice> response) {
                if (response.isSuccessful()) {
                    int coderespone = response.body().getCode();

                    switch (coderespone) {
                        case 200:
                            String tglBuatInvoice = response.body().getHeaderInvoice().getTanggalDibuat();
                            String nama_kodestaff = response.body().getHeaderInvoice().getNoStaff() + " - " + response.body().getHeaderInvoice().getNamaStaff();

                            String namaPT = response.body().getDataPatner().getNamaPartner();
                            String alamatPT = response.body().getDataPatner().getAlamatPartner() + " " + response.body().getDataPatner().getKodeposKota();
                            String kodePT = response.body().getDataPatner().getKodePartner();

                            String subTotal = String.valueOf(response.body().getDatatotalpo().getSubtotal());
                            String pajak = String.valueOf(response.body().getDatatotalpo().getPpn());
                            String ongkir = String.valueOf(response.body().getDatatotalpo().getOngkir());

                            String totalBayar = String.valueOf(response.body().getDatatotalpo().getSubtotal() - response.body().getDatatotalpo().getPpn() + response.body().getDatatotalpo().getOngkir());

                            txtgetTglBuatInvoice.setText(tglBuatInvoice);
                            txtStaff.setText(nama_kodestaff);

                            txtgetNamaPerusahaan.setText(namaPT);
                            txtgetAlamatCompany.setText(alamatPT);
                            txtgetKodeCompany.setText(kodePT);

                            txtOngir.setText("IDR. " + ongkir);
                            txtSubTotal.setText("IDR. " + subTotal);
                            txtPajak.setText("IDR. " + pajak);
                            txtTotalBayar.setText("IDR. " + totalBayar);


                            break;
                        case 404:

                            break;
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<RequestDetailInvoice> call, Throwable t) {

            }
        });
    }
}