package com.iseplutpi.invoiceapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.iseplutpi.invoiceapp.R;
import com.iseplutpi.invoiceapp.adapter.AdapterListInvoice;
import com.iseplutpi.invoiceapp.model.list.InvoicelistItem;
import com.iseplutpi.invoiceapp.model.list.RequestListInvoice;
import com.iseplutpi.invoiceapp.network.ApiServices;
import com.iseplutpi.invoiceapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInvoiceActivity extends AppCompatActivity {
    // Deklarasi
    RecyclerView listInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listinvoice);
        getSupportActionBar().setTitle("List Invoice");

        // Instansiasi
        listInvoice = (RecyclerView) findViewById(R.id.rvListInvoice);

        // Inplementasi
        listInvoice.setHasFixedSize(true);
        listInvoice.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Module List Invoice
        tampilkanListInvoice();
    }

    private void tampilkanListInvoice() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<RequestListInvoice> requestListInvoiceCall = apiServices.req_listinvoice();
        requestListInvoiceCall.enqueue(new Callback<RequestListInvoice>() {
            @Override
            public void onResponse(Call<RequestListInvoice> call, Response<RequestListInvoice> response) {
                if (response.isSuccessful()) {
                    int codestatus = response.body().getCode();
                    if (codestatus == 200) {
                        List<InvoicelistItem> itemInvoice = response.body().getInvoicelist();
                        AdapterListInvoice adapterListInvoice = new AdapterListInvoice(ListInvoiceActivity.this, itemInvoice);
                        listInvoice.setAdapter(adapterListInvoice);
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestListInvoice> call, Throwable t) {

            }
        });
    }
}