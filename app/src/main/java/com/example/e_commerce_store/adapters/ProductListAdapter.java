package com.example.e_commerce_store.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_store.ProductDetialsActivity;
import com.example.e_commerce_store.R;

import com.example.e_commerce_store.models.Product;
import com.example.e_commerce_store.utils.NetworkState;

public class ProductListAdapter extends PagedListAdapter<Product, RecyclerView.ViewHolder> {



    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;
    public ProductListAdapter(Context context) {
        super(Product.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_PROGRESS) {
            View view=layoutInflater.inflate(R.layout.item_network_state,parent,false);

            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(view);
            return viewHolder;

        } else {
            View viewItem=layoutInflater.inflate(R.layout.product_row,parent,false);

            ProductItemViewHolder viewHolder = new ProductItemViewHolder(viewItem);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProductItemViewHolder) {
            ((ProductItemViewHolder)holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    public class ProductItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


       private View viewItem;

       TextView productPrice,productName;

       ImageView productImage;
       int product_id;
       public ProductItemViewHolder(View viewItem) {
           super(viewItem);
           viewItem.setOnClickListener(this);

           this.viewItem=viewItem;
           productImage=viewItem.findViewById(R.id.product_image);
           productPrice=viewItem.findViewById(R.id.product_price);
           productName=viewItem.findViewById(R.id.child_item_title);


       }
        public void bindTo(Product product) {
            this.product_id=product.getId();
            productName.setText(product.getName());
            productName.setSelected(true);
            productPrice.setText("$."+product.getPrice());
            Glide.with(context)
                    .load(product.getImage())
                    .placeholder(R.drawable.placeholder)
                    .fitCenter()
                    .into(productImage);

        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ProductDetialsActivity.class);
            intent.putExtra("pass_product_id",product_id);
            view.getContext().startActivity(intent);
        }


    }
    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {




       private View view;
        private TextView error_msg;
        private ProgressBar progressBar;
        public NetworkStateItemViewHolder(View view) {
            super(view);
            this.view = view;
            error_msg=view.findViewById(R.id.error_msg);
            progressBar=view.findViewById(R.id.progress_bar);
        }
        public void bindView(NetworkState networkState) {
            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
               error_msg.setVisibility(View.VISIBLE);
                error_msg.setText(networkState.getMsg());
            } else {
                error_msg.setVisibility(View.GONE);
            }
        }
    }
}
