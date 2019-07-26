package com.tiunida.courierapp.ActivityOrderDetail.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tiunida.courierapp.ActivityOrderDetail.DetailOrderPresenter;
import com.tiunida.courierapp.ActivityOrderDetail.DetailOrderPresenterMvp;
import com.tiunida.courierapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailOrderActivity extends AppCompatActivity implements DetailOrderViewMvp {

    private DetailOrderPresenterMvp mDetailOrderPresenterMvp;

    @BindView(R.id.detailOrderToolbar)
    Toolbar mDetailOrderToolbar;
    @BindView(R.id.order_detail_progress)
    ProgressBar mOrderDetailProgress;

    @BindView(R.id.no_order_txt)
    TextView mNoOrderTxt;
    @BindView(R.id.name_order_txt)
    TextView mNameOrderTxt;
    @BindView(R.id.dormitory_order_txt)
    TextView mDormitoryOrderTxt;
    @BindView(R.id.room_order_txt)
    TextView mRoomOrderTxt;
    @BindView(R.id.no_phone_order_txt)
    TextView mNoPhoneOrderTxt;
    @BindView(R.id.status_order_txt)
    TextView mStatusorderTxt;
    @BindView(R.id.laudnryType)
    TextView mLaundryType;
    @BindView(R.id.laudryWeight)
    TextView mLaundryWeight;
    @BindView(R.id.originalPrice)
    TextView mOriginalPrice;
    @BindView(R.id.laudryDiskon)
    TextView mLaundryDiskon;
    @BindView(R.id.laundryPrice)
    TextView mLaundryPrice;
    @BindView(R.id.catatan_txt)
    TextView mCatatanTxt;
    @BindView(R.id.inputWeightEdt)
    EditText mInputWeightEdt;
    @BindView(R.id.saveWeightBtn)
    Button mSaveWeightBtn;

    @BindView(R.id.acceptIndicator)
    View acceptIndicator;
    @BindView(R.id.acceptLine)
    View acceptLine;
    @BindView(R.id.prosesIndicator)
    View prosesIndicator;
    @BindView(R.id.prosesLine)
    View prosesLine;
    @BindView(R.id.doneIndicator)
    View doneIndicator;
    @BindView(R.id.doneLine)
    View doneLine;
    @BindView(R.id.paidIndicator)
    View paidIndicator;
    @BindView(R.id.paidLine)
    View paidLine;
    @BindView(R.id.deliveredIndicator)
    View deliveredIndicator;

    @BindView(R.id.acceptOkBtn)
    Button mAcceptOkBtn;
    @BindView(R.id.prosesOkBtn)
    Button mProsesOkBtn;
    @BindView(R.id.doneOkBtn)
    Button mDoneOkBtn;
    @BindView(R.id.paidOkBtn)
    Button mPaidOkBtn;
    @BindView(R.id.deliverOkBtn)
    Button mDeliverOkBtn;

    @BindView(R.id.askCostumerBtn)
    Button mAskCostumerBtn;

    @BindView(R.id.tgl_pesan_order_txt)
    TextView mTglglPesan;
    @BindView(R.id.tgl_selesai_order_txt)
    TextView mTglSelesai;

    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        mDetailOrderPresenterMvp = new DetailOrderPresenter(this);
        mDetailOrderPresenterMvp.onCreate();

        ButterKnife.bind(this);

        Intent orderDetailIntent = getIntent();
        order_id = orderDetailIntent.getStringExtra("id");

        Toast.makeText(DetailOrderActivity.this, "order id " + order_id, Toast.LENGTH_LONG).show();

        setSupportActionBar(mDetailOrderToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("ORDER BIASA");
        mDetailOrderToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black);
        mDetailOrderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getData();
    }

    private void openWhatsApp() {

        String number = mNoPhoneOrderTxt.getText().toString(); // E164 format without '+' sign
        //String str = "09XX-XXX-XXXX";
        number = "62" + number.substring(1);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", number + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
//        if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
//            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
//            return;
//        }
        startActivity(sendIntent);
    }

    public void getData() {
        mDetailOrderPresenterMvp.getOrdersData(order_id);
    }

    public void setNoLaundry(String txt) {
        mNoOrderTxt.setText(txt);
    }

    public void setNameTxt(String txt) {
        mNameOrderTxt.setText(txt);
    }

    public void setDormitoryTxt(String txt) {
        mDormitoryOrderTxt.setText(txt);
    }

    public void setRoomTxt(String txt) {
        mRoomOrderTxt.setText(txt);
    }

    public void setPhoneNumber(String txt) {
        mNoPhoneOrderTxt.setText(txt);
    }

    public void setStatusTxt(String txt) {
        mStatusorderTxt.setText(txt);
    }

    public void setTglPesanTxt(String txt) {
        mTglglPesan.setText(txt);
    }

    public void setTglSelesaiTxt(String txt) {
        mTglSelesai.setText(txt);
    }

    public void setLaundryTypeTxt(String txt) {
        mLaundryType.setText(txt);
    }

    public void setLaudnryWeightTxt(String txt) {
        mLaundryWeight.setText(txt);
    }

    public void setOriginalPriceTxt(String txt) {
        mOriginalPrice.setText(txt);
    }

    public void setLaundryDiskonTxt(String txt) {
        mLaundryDiskon.setText(txt);
    }

    public void setTotalPriceTxt(String txt) {
        mLaundryPrice.setText(txt);
    }

    public void setCatatanTxt(String txt) {
        mCatatanTxt.setText(txt);
    }

    public void setAcceptIndicatorCheck() {
        acceptIndicator.setBackgroundResource(R.drawable.circle_view_background);
        acceptLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
    }

    public void setAcceptIndicatorUnCheck() {
        acceptIndicator.setBackgroundResource(R.drawable.circle_view_border);
        acceptLine.setBackgroundResource(R.drawable.rectangle_view_border);
    }

    public void setProsesIndicatorCheck() {
        prosesIndicator.setBackgroundResource(R.drawable.circle_view_background);
        prosesLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
    }

    public void setProsesIndicatorUnCheck() {
        prosesIndicator.setBackgroundResource(R.drawable.circle_view_border);
        prosesLine.setBackgroundResource(R.drawable.rectangle_view_border);
    }

    public void setDoneIndicatorCheck() {
        doneIndicator.setBackgroundResource(R.drawable.circle_view_background);
        doneLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
    }

    public void setDoneIndicatorUnCheck() {
        doneIndicator.setBackgroundResource(R.drawable.circle_view_border);
        doneLine.setBackgroundResource(R.drawable.rectangle_view_border);
    }

    public void setPaidIndicatorCheck() {
        paidIndicator.setBackgroundResource(R.drawable.circle_view_background);
        paidLine.setBackgroundResource(R.drawable.rectangle_view_bacground);
    }

    public void setPaidIndicatorUnCheck() {
        paidIndicator.setBackgroundResource(R.drawable.circle_view_border);
        paidLine.setBackgroundResource(R.drawable.rectangle_view_border);
    }

    public void setDeliveredIndicatorCheck() {
        deliveredIndicator.setBackgroundResource(R.drawable.circle_view_background);
    }

    public void setDeliveredIndicatorUnCheck() {
        deliveredIndicator.setBackgroundResource(R.drawable.circle_view_border);
    }

    public void showProgress() {
        mOrderDetailProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mOrderDetailProgress.setVisibility(View.GONE);
    }

    public void showMessage(String message) {
        Toast.makeText(DetailOrderActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void setAcceptOkBtnDisable() {
        mAcceptOkBtn.setEnabled(false);
        mAcceptOkBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mAcceptOkBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setAcceptOkBtnEnable() {
        mAcceptOkBtn.setEnabled(true);
        mAcceptOkBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mAcceptOkBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setProsesOkBtnDisable() {
        mProsesOkBtn.setEnabled(false);
        mProsesOkBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mProsesOkBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setProsesOkBtnEnable() {
        mProsesOkBtn.setEnabled(true);
        mProsesOkBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mProsesOkBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setDoneOkBtnDisable() {
        mDoneOkBtn.setEnabled(false);
        mDoneOkBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mDoneOkBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setDoneOkBtnEnable() {
        mDoneOkBtn.setEnabled(true);
        mDoneOkBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mDoneOkBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setPaidOkBtnDisable() {
        mPaidOkBtn.setEnabled(false);
        mPaidOkBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mPaidOkBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setPaidOkBtnEnable() {
        mPaidOkBtn.setEnabled(true);
        mPaidOkBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mPaidOkBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setDeliverOkBtnDisable() {
        mDeliverOkBtn.setEnabled(false);
        mDeliverOkBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mDeliverOkBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setDeliverOkBtnEnable() {
        mDeliverOkBtn.setEnabled(true);
        mDeliverOkBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mDeliverOkBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setConfirmPaidBtnTxt(String txt){
        mPaidOkBtn.setText(txt);
    }

    public void setConfirmDeliverBtnTxt(String txt){
        mDeliverOkBtn.setText(txt);
    }

    public void setSaveWeightBtnDisable() {
        mSaveWeightBtn.setEnabled(false);
        mSaveWeightBtn.setBackgroundResource(R.drawable.btn_ok_background_disable);
        mSaveWeightBtn.setTextColor(getResources().getColor(R.color.abuabu3));
    }

    public void setSaveWeightBtnEnable() {
        mSaveWeightBtn.setEnabled(true);
        mSaveWeightBtn.setBackgroundResource(R.drawable.btn_ok_background_enable);
        mSaveWeightBtn.setTextColor(getResources().getColor(R.color.putih));
    }

    public void setSaveWeightEdtDisable() {
        mInputWeightEdt.setEnabled(false);
    }

    public void setSaveWeightEdtEnable() {
        mInputWeightEdt.setEnabled(true);
    }

    public void setAskCostumerBtnEnable() {
        mAskCostumerBtn.setEnabled(true);
    }

    public void setAskCostumerBtnDisable() {
        mAskCostumerBtn.setEnabled(false);
    }

    @OnClick(R.id.acceptOkBtn)
    public void onAcceptOkBtnOnClick() {
        showDialogOnAcceptOkBtnOnClick();
    }

    @OnClick(R.id.prosesOkBtn)
    public void onProsesOkBtnOnClick() {
        mDetailOrderPresenterMvp.validateUpdateProses(order_id);
        mDetailOrderPresenterMvp.getOrdersData(order_id);
    }

    @OnClick(R.id.doneOkBtn)
    public void onDoneOkBtnOnClick() {
        showDialogOnDoneOkBtnOnClick();
    }

    @OnClick(R.id.paidOkBtn)
    public void onPaidOkBtnOnClick() {
        showDialogOnPaidOkBtnOnClick();
    }

    @OnClick(R.id.deliverOkBtn)
    public void onDeliverOkBtnOnClick() {
        showDialogOnDeliverOkBtnOnClick();
    }

    @OnClick(R.id.saveWeightBtn)
    public void onSaveWeightBtnOnClick() {
        showDialogOnSaveWeightBtnOnClick();
    }

    @OnClick(R.id.askCostumerBtn)
    public void onAskCostumerBtnOnClick() {
        openWhatsApp();
    }

    public void showDialogOnAcceptOkBtnOnClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin udah diambil ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Saip udah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mDetailOrderPresenterMvp.validateUpdateAccept(order_id);
                        mDetailOrderPresenterMvp.getOrdersData(order_id);

                    }
                })
                .setNegativeButton("Eh iya belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    public void showDialogOnSaveWeightBtnOnClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin angkanya bener ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Siap yakin", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mDetailOrderPresenterMvp.validateUpdateWeight(order_id, mInputWeightEdt.getText().toString());
                        mDetailOrderPresenterMvp.getOrdersData(order_id);

                    }
                })
                .setNegativeButton("Hmmm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    public void showDialogOnDoneOkBtnOnClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin udah selesai semua ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Siap udah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mDetailOrderPresenterMvp.validateUpdateDone(order_id);
                        mDetailOrderPresenterMvp.getOrdersData(order_id);

                    }
                })
                .setNegativeButton("Eh iya belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    public void showDialogOnPaidOkBtnOnClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin udah dibayar lunas ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Siap udah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mDetailOrderPresenterMvp.validateUpdatePaid(order_id);
                        mDetailOrderPresenterMvp.getOrdersData(order_id);

                    }
                })
                .setNegativeButton("Eh iya belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }

    public void showDialogOnDeliverOkBtnOnClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title dialog
        alertDialogBuilder.setTitle("Yakin udah dianter sampe tujuan ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Siap udah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        mDetailOrderPresenterMvp.validateUpdateDeliver(order_id);
                        mDetailOrderPresenterMvp.getOrdersData(order_id);

                    }
                })
                .setNegativeButton("Eh iya belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }
}

