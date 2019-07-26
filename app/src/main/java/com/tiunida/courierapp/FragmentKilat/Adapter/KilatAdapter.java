package com.tiunida.courierapp.FragmentKilat.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.tiunida.courierapp.FragmentExpress.Adapter.ExpressAdapter;
import com.tiunida.courierapp.FragmentKilat.Model.KilatModel;
import com.tiunida.courierapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KilatAdapter extends FirestoreRecyclerAdapter<KilatModel, KilatAdapter.Holder> {

    private OnItemClickListener listener;

    public KilatAdapter(@NonNull FirestoreRecyclerOptions<KilatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int i, @NonNull KilatModel kilatModel) {
        holder.mNameTxt.setText(kilatModel.getA_name());
        holder.mDormitoryTxt.setText(kilatModel.getA_dormitory());
        holder.mRoomTxt.setText(kilatModel.getA_room());
        holder.mDoneTimeTxt.setText(kilatModel.getA_waktu_selesai());

        //orderHolder.mjenis.setText(model.getA_jenis());
        if (kilatModel.getA_price2() != null) {
            Log.d("tidak ", "null");
            int priceInt = Integer.valueOf(kilatModel.getA_price2());
            int diskonInt = Integer.valueOf(kilatModel.getA_diskon());
            int hasil = priceInt * diskonInt / 100;
            int totalPrice = priceInt - hasil;
            holder.mPrice.setText(String.valueOf(totalPrice));
        }

        //orderHolder.mPrice.setText(model.getA_price2());

        String string1 = "1";
        if (kilatModel.getH_accepted2().equals(string1)) {
            holder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_background);
            holder.mAcceptedLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            holder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_border);
            holder.mAcceptedLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (kilatModel.getH_on_proses2().equals(string1)) {
            holder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_background);
            holder.mProsesLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            holder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_border);
            holder.mProsesLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (kilatModel.getH_done2().equals(string1)) {
            holder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_background);
            holder.mDoneLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            holder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_border);
            holder.mDoneLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (kilatModel.getH_paid2().equals(string1) && kilatModel.getH_paid2Confirm().equals(string1)) {
            holder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_background);
            holder.mPaidLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            holder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_border);
            holder.mPaidLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (kilatModel.getH_delivered2().equals(string1) && kilatModel.getH_delivered2Confirm().equals(string1)) {
            holder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_background);
        } else {
            holder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_border);
        }
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_order, parent, false);
        return new Holder(view);
    }

    class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.doneTime)
        TextView mDoneTimeTxt;
        @BindView(R.id.nameTxt)
        TextView mNameTxt;
        @BindView(R.id.dormitoryTxt)
        TextView mDormitoryTxt;
        @BindView(R.id.roomTxt)
        TextView mRoomTxt;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.acceptIndicator)
        View mAcceptIndicator;
        @BindView(R.id.acceptLine)
        View mAcceptedLine;
        @BindView(R.id.prosesIndicator)
        View mProsesIndicator;
        @BindView(R.id.prosesLine)
        View mProsesLine;
        @BindView(R.id.doneIndicator)
        View mDoneIndicator;
        @BindView(R.id.doneLine)
        View mDoneLine;
        @BindView(R.id.paidIndicator)
        View mPaindIndicator;
        @BindView(R.id.paidLine)
        View mPaidLine;
        @BindView(R.id.deliveredIndicator)
        View mDeliveredIndicator;

        public Holder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posision = getAdapterPosition();
                    if (posision != RecyclerView.NO_POSITION && listener != null) {
                        listener.onKilatItemClick(getSnapshots().getSnapshot(posision), posision);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onKilatItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
