package com.iseplutpi.invoiceapp.network;

import com.iseplutpi.invoiceapp.model.details.RequestDetailInvoice;
import com.iseplutpi.invoiceapp.model.list.RequestListInvoice;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @GET("module/list_invoice.php")
    Call<RequestListInvoice> req_listinvoice();

    @FormUrlEncoded
    @POST("module/invoice_details.php")
    Call<RequestDetailInvoice> reqDetailInvoice(
            @Field("no_invoice") String noInvoice
    );
}
