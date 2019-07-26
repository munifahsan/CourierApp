package com.tiunida.courierapp.ActivityOrderDetail;

import android.util.Log;

import com.tiunida.courierapp.ActivityOrderDetail.events.DetailOrderEvents;
import com.tiunida.courierapp.ActivityOrderDetail.ui.DetailOrderViewMvp;
import com.tiunida.courierapp.EventBus.EventBus;
import com.tiunida.courierapp.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import static com.tiunida.courierapp.ActivityOrderDetail.events.DetailOrderEvents.onGetDataSuccess;

public class DetailOrderPresenter implements DetailOrderPresenterMvp {
    private DetailOrderInteractorMvp mDetailOrderInteractorMvp;
    private DetailOrderViewMvp mDetailOrderViewMvp;

    private EventBus mEventBus;

    public DetailOrderPresenter(DetailOrderViewMvp detailOrderViewMvp) {
        mDetailOrderInteractorMvp = new DetailOrderInteractor();
        mDetailOrderViewMvp = detailOrderViewMvp;
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Subscribe
    public void onEventMainThread(DetailOrderEvents event) {
        switch (event.getEventType()) {
            case DetailOrderEvents.onInputSuccess:
                onInputSuccess();
                break;
            case DetailOrderEvents.onInputError:
                onInputError(event.getErrorMessage());
                break;
            case DetailOrderEvents.onGetDataSuccess:
                onGetDataSucces(event.getName(), event.getDormitory(), event.getRoom(), event.getPhone(), event.getStatus(), event.getDataTimeNow(), event.getDataTimeDone(), event.getDataJenis(), event.getDataWeight(), event.getDataPrice(), event.getDataDiskon(), event.getDataDesc(), event.getDataAccept(), event.getDataOnProses(), event.getDataDone(), event.getDataPaid(), event.getDataPaidConfirm(), event.getDataDelivered(), event.getDataDeliveredConfirm());
                Log.d("name event main", "" + event.getName());
                break;
            case DetailOrderEvents.onGetDataError:

                break;
            case DetailOrderEvents.onUpdateDataSuccess:
                onUpdateSucces(event.getDataAccept(), event.getDataOnProses(), event.getDataDone(), event.getDataPaid(), event.getDataDelivered());
                break;
            case DetailOrderEvents.onUpdateDataError:
                onUpdateError(event.getErrorMessage());
                break;
        }
    }

    public void onGetDataSucces(String name, String dormitory, String room, String phone, String status, String timeNow, String timeDpne,
                                String jenis, String weight, String price, String diskon, String catatan,
                                String dataAccept, String dataOnProses,
                                String dataDone, String dataPaid,
                                String paidConfirm, String delivered, String deliverConfirm) {
        if (mDetailOrderViewMvp != null) {
            setIndicator(dataAccept, dataOnProses, dataDone, dataPaid, paidConfirm, delivered, deliverConfirm);
            setButton(dataAccept, dataOnProses, dataDone, dataPaid, paidConfirm, delivered, deliverConfirm);
            mDetailOrderViewMvp.setNameTxt(name);
            mDetailOrderViewMvp.setDormitoryTxt(dormitory);
            mDetailOrderViewMvp.setRoomTxt(room);
            mDetailOrderViewMvp.setPhoneNumber(phone);
            mDetailOrderViewMvp.setStatusTxt(status);
            mDetailOrderViewMvp.setTglPesanTxt(timeNow);
            mDetailOrderViewMvp.setTglSelesaiTxt(timeDpne);
            mDetailOrderViewMvp.setLaundryTypeTxt(jenis);
            mDetailOrderViewMvp.setLaudnryWeightTxt(weight);
            mDetailOrderViewMvp.setOriginalPriceTxt(price);
            mDetailOrderViewMvp.setLaundryDiskonTxt(diskon);
            mDetailOrderViewMvp.setCatatanTxt(catatan);
            mDetailOrderViewMvp.hideProgress();
            mDetailOrderViewMvp.setAskCostumerBtnEnable();
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mDetailOrderViewMvp = null;
        mEventBus.unregister(this);
    }

    public void getOrdersData(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.getOrdersData(order_id);
        }
    }

    public void validateUpdateWeight(String order_id, String weight) {
        mDetailOrderViewMvp.showProgress();
        if (!order_id.isEmpty()) {
            if (!weight.isEmpty()) {
                mDetailOrderInteractorMvp.updateWeight(order_id, weight);
            } else {
                mDetailOrderViewMvp.showMessage("Anda belum memasukan data apapun");
            }
        }
    }

    public void validateUpdateAccept(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.updateAccept(order_id);
        }
    }

    public void validateUpdateProses(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.updateProses(order_id);
        }
    }

    public void validateUpdateDone(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.updateDone(order_id);
        }
    }

    public void validateUpdatePaid(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.updatePaid(order_id);
        }
    }

    public void validateUpdateDeliver(String order_id) {
        if (!order_id.isEmpty()) {
            mDetailOrderViewMvp.showProgress();
            mDetailOrderInteractorMvp.updateDeliver(order_id);
        }
    }

    public void onUpdateSucces(String dataAccept, String dataOnProses,
                               String dataDone, String dataPaid,
                               String delivered) {
        if (mDetailOrderViewMvp != null) {
            mDetailOrderViewMvp.hideProgress();
            mDetailOrderViewMvp.showMessage("succes");
        }
    }

    public void onUpdateError(String error) {
        if (mDetailOrderViewMvp != null) {
            mDetailOrderViewMvp.showMessage(error);
        }
    }

    public void setIndicator(String dataAccept, String dataOnProses, String dataDone, String dataPaid, String paidConfirm, String delivered, String deliverConfirm) {
        String string1 = "1";
        if (dataAccept.equals(string1)) {
            mDetailOrderViewMvp.setAcceptIndicatorCheck();
        } else {
            mDetailOrderViewMvp.setAcceptIndicatorUnCheck();
        }

        if (dataOnProses.equals(string1)) {
            mDetailOrderViewMvp.setProsesIndicatorCheck();
        } else {
            mDetailOrderViewMvp.setProsesIndicatorUnCheck();
        }

        if (dataDone.equals(string1)) {
            mDetailOrderViewMvp.setDoneIndicatorCheck();
        } else {
            mDetailOrderViewMvp.setDoneIndicatorUnCheck();
        }

        if (dataPaid.equals(string1) && paidConfirm.equals(string1)) {
            mDetailOrderViewMvp.setPaidIndicatorCheck();
        } else {
            mDetailOrderViewMvp.setPaidIndicatorUnCheck();
        }

        if (delivered.equals(string1) && deliverConfirm.equals(string1)) {
            mDetailOrderViewMvp.setDeliveredIndicatorCheck();
        } else {
            mDetailOrderViewMvp.setDeliveredIndicatorUnCheck();
        }
    }

    public void setButton(String dataAccept, String dataOnProses, String dataDone, String dataPaid, String paidConfirm, String delivered, String deliverConfirm) {

        setAcceptButton(dataAccept);

        setProsesButton(dataAccept, dataOnProses);

        setDoneButton(dataDone, dataOnProses);

        setPaidButton(dataDone, dataPaid, paidConfirm);

        setDeliverButton(dataPaid, paidConfirm, delivered, deliverConfirm);

        setWeightButton(dataAccept, dataOnProses);
    }

    public void setAcceptButton(String dataAccept) {
        String exist = "1";
        String empty = "";
        if (dataAccept.equals(exist)) {
            mDetailOrderViewMvp.setAcceptOkBtnDisable();
        } else {
            mDetailOrderViewMvp.setAcceptOkBtnEnable();
        }
    }

    public void setProsesButton(String dataAccept, String dataOnProses) {
        String exist = "1";
        String empty = "";
        if (dataOnProses.equals(exist)) {
            mDetailOrderViewMvp.setProsesOkBtnDisable();
        } else if (!dataAccept.equals(exist)) {
            mDetailOrderViewMvp.setProsesOkBtnDisable();
        } else {
            mDetailOrderViewMvp.setProsesOkBtnEnable();
        }
    }

    public void setDoneButton(String dataDone, String dataOnProses) {
        String exist = "1";
        String empty = "";
        if (dataDone.equals(exist)) {
            mDetailOrderViewMvp.setDoneOkBtnDisable();
        } else if (!dataOnProses.equals(exist)) {
            mDetailOrderViewMvp.setDoneOkBtnDisable();
        } else {
            mDetailOrderViewMvp.setDoneOkBtnEnable();
        }
    }

    public void setPaidButton(String dataDone, String dataPaid, String dataPaidConfrim) {
        String exist = "1";
        String empty = "";
        if (dataPaid.equals(exist) && dataPaidConfrim.equals(empty)) {
            mDetailOrderViewMvp.setPaidOkBtnEnable();
            mDetailOrderViewMvp.setConfirmPaidBtnTxt("KONFIRMASI TELAH DIBAYAR");
        } else if (dataPaid.equals(exist) && dataPaidConfrim.equals(exist)) {
            mDetailOrderViewMvp.setPaidOkBtnDisable();
            mDetailOrderViewMvp.setConfirmPaidBtnTxt("SELESAI DIBAYAR");
        } else {
            mDetailOrderViewMvp.setPaidOkBtnDisable();
            mDetailOrderViewMvp.setConfirmPaidBtnTxt("MENUNGGU KONFIRMASI PELANGGAN");
        }
    }

    public void setDeliverButton(String dataPaid, String dataPaidConfrim, String delivered, String deliverConfirm) {
        String exist = "1";
        String empty = "";
        if (dataPaidConfrim.equals(exist) && delivered.equals(empty) && deliverConfirm.equals(empty)) {
            mDetailOrderViewMvp.setDeliverOkBtnEnable();
            mDetailOrderViewMvp.setConfirmDeliverBtnTxt("KONFIRMASI TELAH DIANTAR");
        } else if (delivered.equals(exist) && deliverConfirm.equals(exist)){
            mDetailOrderViewMvp.setDeliverOkBtnDisable();
            mDetailOrderViewMvp.setConfirmDeliverBtnTxt("SELESAI DIANTAR");
        } else if (delivered.equals(exist) && deliverConfirm.equals(empty)){
            mDetailOrderViewMvp.setDeliverOkBtnDisable();
            mDetailOrderViewMvp.setConfirmDeliverBtnTxt("MENUNGGU KONFIRMASI PELANGGAN");
        } else if (delivered.equals(empty) && deliverConfirm.equals(empty)){
            mDetailOrderViewMvp.setDeliverOkBtnDisable();
            mDetailOrderViewMvp.setConfirmDeliverBtnTxt("KONFIRMASI TELAH DIANTAR");
        }
    }

    public void setWeightButton(String dataAccept, String dataOnProses) {
        String exist = "1";
        String empty = "";

        if (dataAccept.equals(exist)) {
            mDetailOrderViewMvp.setSaveWeightBtnEnable();
            mDetailOrderViewMvp.setSaveWeightEdtEnable();
            if (dataOnProses.equals(exist)) {
                mDetailOrderViewMvp.setSaveWeightBtnDisable();
                mDetailOrderViewMvp.setSaveWeightEdtDisable();
            }
        } else {
            mDetailOrderViewMvp.setSaveWeightBtnDisable();
            mDetailOrderViewMvp.setSaveWeightEdtDisable();
        }
    }

    public void onInputSuccess() {
        if (mDetailOrderViewMvp != null) {
            mDetailOrderViewMvp.hideProgress();
        }
    }

    public void onInputError(String error) {
        if (mDetailOrderViewMvp != null) {
            mDetailOrderViewMvp.showMessage(error);
        }
    }
}
