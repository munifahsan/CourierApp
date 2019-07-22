package com.tiunida.courierapp.FragmentBiasa.Adapter;

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
import com.tiunida.courierapp.FragmentBiasa.Model.BiasaModel;
import com.tiunida.courierapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BiasaAdapter extends FirestoreRecyclerAdapter<BiasaModel, BiasaAdapter.Holder> {

    private OnItemClickListener listener;

    public BiasaAdapter(@NonNull FirestoreRecyclerOptions<BiasaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder orderHolder, int i, @NonNull BiasaModel model) {

        orderHolder.mNameTxt.setText(model.getA_name());
        orderHolder.mDormitoryTxt.setText(model.getA_dormitory());
        orderHolder.mRoomTxt.setText(model.getA_room());
        orderHolder.mDoneTimeTxt.setText(model.getA_waktu_selesai());

        //orderHolder.mjenis.setText(model.getA_jenis());
        if (model.getA_price2() != null) {
            Log.d("tidak ", "null");
            int priceInt = Integer.valueOf(model.getA_price2());
            int diskonInt = Integer.valueOf(model.getA_diskon());
            int hasil = priceInt * diskonInt / 100;
            int totalPrice = priceInt - hasil;
            orderHolder.mPrice.setText(String.valueOf(totalPrice));
        }

        //orderHolder.mPrice.setText(model.getA_price2());

        String string1 = "1";
        if (model.getH_accepted2().equals(string1)) {
            orderHolder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mAcceptedLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            orderHolder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mAcceptedLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (model.getH_on_proses2().equals(string1)) {
            orderHolder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mProsesLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            orderHolder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mProsesLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (model.getH_done2().equals(string1)) {
            orderHolder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mDoneLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            orderHolder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mDoneLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (model.getH_paid2().equals(string1)) {
            orderHolder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mPaidLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
        } else {
            orderHolder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mPaidLine.setBackgroundResource(R.drawable.rectangle_view_border);
        }

        if (model.getH_delivered2().equals(string1)) {
            orderHolder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_background);
        } else {
            orderHolder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_border);
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
                        listener.onBiasaItemClick(getSnapshots().getSnapshot(posision), posision);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
