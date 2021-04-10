package com.iseplutpi.invoiceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.iseplutpi.invoiceapp.R;
import com.iseplutpi.invoiceapp.model.list.InvoicelistItem;
import com.iseplutpi.invoiceapp.views.DetailInvoiceActivity;

import java.util.List;

public class AdapterListInvoice extends RecyclerView.Adapter<AdapterListInvoice.MyViewHolder> {
    Context context;
    List<InvoicelistItem> invoiceListItems;

    public AdapterListInvoice(Context context, List<InvoicelistItem> invoiceListItems) {
        this.context = context;
        this.invoiceListItems = invoiceListItems;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listinvoice, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String noInvoice = invoiceListItems.get(position).getNoInvoice();
        String noStaff = invoiceListItems.get(position).getNoStaff();
        String namaStaff = invoiceListItems.get(position).getNamaStaff();
        holder.txtNoInvoice.setText("#" + noInvoice);
        holder.txtDetailInvoice.setText(noStaff + " - " + namaStaff);
        holder.txtStatusInvoice.setText("");

        // Onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "Nomor invoice yang dipilih adalah #" + noInvoice, Toast.LENGTH_SHORT).show();

                Intent kirimNoInvoice = new Intent(context, DetailInvoiceActivity.class);
                kirimNoInvoice.putExtra("NOINVOICE", noInvoice);
                context.startActivity(kirimNoInvoice);
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoiceListItems.size();
    }

    //    buat class view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Delklarasi variable
        TextView txtNoInvoice, txtDetailInvoice, txtStatusInvoice;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNoInvoice = (TextView) itemView.findViewById(R.id.list_noInvoice);
            txtDetailInvoice = (TextView) itemView.findViewById(R.id.list_noDetailPO);
            txtStatusInvoice = (TextView) itemView.findViewById(R.id.list_statusInvoice);
        }
    }
}
