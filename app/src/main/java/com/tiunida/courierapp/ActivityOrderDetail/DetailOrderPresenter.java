package com.tiunida.courierapp.ActivityOrderDetail;

import android.util.Log;

import com.tiunida.courierapp.ActivityOrderDetail.events.DetailOrderEvents;
import com.tiunida.courierapp.ActivityOrderDetail.ui.DetailOrderViewMvp;
import com.tiunida.courierapp.EventBus.EventBus;
import com.tiunida.courierapp.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;

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
            setPrice(weight, diskon, jenis);
            mDetailOrderViewMvp.setLaundryDiskonTxt(diskon);
            mDetailOrderViewMvp.setCatatanTxt(catatan);
            mDetailOrderViewMvp.hideProgress();
            mDetailOrderViewMvp.setAskCostumerBtnEnable();
        }
    }

    public String setTotalPrice(int price, String diskon) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        int priceInt;
        int disokonInt = Integer.valueOf(diskon);
        int hasil;
        int totalPrice;
        priceInt = price;
        hasil = priceInt * disokonInt / 100;
        totalPrice = priceInt - hasil;
        return formatter.format(totalPrice);
    }

    public String setOriPrice(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(price);
    }

    public void setPrice(String weight, String diskon, String jenis) {
        if (jenis.equals("Biasa")) {
            switch (weight) {
                case "1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(3500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(3500, diskon));
                    break;
                case "1.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(3850));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(3850, diskon));
                    break;
                case "1.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(4200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(4200, diskon));
                    break;
                case "1.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(4550));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(4550, diskon));
                    break;
                case "1.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(4900));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(4900, diskon));
                    break;
                case "1.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(5250));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(5250, diskon));
                    break;
                case "1.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(5600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(5600, diskon));
                    break;
                case "1.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(5950));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(5950, diskon));
                    break;
                case "1.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(6300));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(6300, diskon));
                    break;
                case "1.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(6650));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(6650, diskon));
                    break;
                //-2---------------
                case "2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(7000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(7000, diskon));
                    break;
                case "2.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(7350));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(7350, diskon));
                    break;
                case "2.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(7700));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(7700, diskon));
                    break;
                case "2.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8050));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8050, diskon));
                    break;
                case "2.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8400, diskon));
                    break;
                case "2.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8750));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8750, diskon));
                    break;
                case "2.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9100));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9100, diskon));
                    break;
                case "2.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9450));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9450, diskon));
                    break;
                case "2.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9800, diskon));
                    break;
                case "2.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10150));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10150, diskon));
                    break;
                //-3---------------
                case "3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10500, diskon));
                    break;
                case "3.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10850));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10850, diskon));
                    break;
                case "3.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(3500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(3500, diskon));
                    break;
                case "3.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(11200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(11200, diskon));
                    break;
                case "3.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(11550));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(11550, diskon));
                    break;
                case "3.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(11900));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(11900, diskon));
                    break;
                case "3.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12250));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12250, diskon));
                    break;
                case "3.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12600, diskon));
                    break;
                case "3.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12950));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12950, diskon));
                    break;
                case "3.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(13300));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(13300, diskon));
                    break;
                //-4---------------
                case "4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(13650));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(13650, diskon));
                    break;
                case "4.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(14000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(14000, diskon));
                    break;
                case "4.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(14350));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(14350, diskon));
                    break;
                case "4.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(14700));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(14700, diskon));
                    break;
                case "4.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15050));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15050, diskon));
                    break;
                case "4.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15400, diskon));
                    break;
                case "4.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15750));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15750, diskon));
                    break;
                case "4.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16100));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16100, diskon));
                    break;
                case "4.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16450));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16450, diskon));
                    break;
                case "4.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16800, diskon));
                    break;
                //-5---------------
                case "5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(17150));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(17150, diskon));
                    break;
                case "5.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(17500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(17500, diskon));
                    break;
                case "5.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(17850));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(17850, diskon));
                    break;
                case "5.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18200, diskon));
                    break;
                case "5.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18550));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18550, diskon));
                    break;
                case "5.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18900));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18900, diskon));
                    break;
                case "5.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19250));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19250, diskon));
                    break;
                case "5.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19600, diskon));
                    break;
                case "5.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19950));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19950, diskon));
                    break;
                case "5.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20300));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20300, diskon));
                    break;
                //-6---------------
                case "6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20650));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20650, diskon));
                    break;
                case "6.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21000, diskon));
                    break;
                case "6.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21350));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21350, diskon));
                    break;
                case "6.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21700));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21700, diskon));
                    break;
                case "6.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22050));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22050, diskon));
                    break;
                case "6.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22400, diskon));
                    break;
                case "6.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22750));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22750, diskon));
                    break;
                case "6.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23100));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23100, diskon));
                    break;
                case "6.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23450));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23450, diskon));
                    break;
                case "6.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23800, diskon));
                    break;
                //-7---------------
                case "7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24150));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24150, diskon));
                    break;
                case "7.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24500, diskon));
                    break;
                case "7.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24850));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24850, diskon));
                    break;
                case "7.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25200, diskon));
                    break;
                case "7.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25550));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25550, diskon));
                    break;
                case "7.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25900));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25900, diskon));
                    break;
                case "7.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(26250));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(26250, diskon));
                    break;
                case "7.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(26600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(26600, diskon));
                    break;
                case "7.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(26950));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(26950, diskon));
                    break;
                case "7.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(27300));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(27300, diskon));
                    break;
                //-8---------------
                case "8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(27650));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(27650, diskon));
                    break;
                case "8.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28000, diskon));
                    break;
                case "8.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28350));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28350, diskon));
                    break;
                case "8.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28700));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28700, diskon));
                    break;
                case "8.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29050));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29050, diskon));
                    break;
                case "8.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29400, diskon));
                    break;
                case "8.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29750));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29750, diskon));
                    break;
                case "8.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30100));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30100, diskon));
                    break;
                case "8.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30450));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30450, diskon));
                    break;
                case "8.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30800, diskon));
                    break;
                //-9---------------
                case "9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31150));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31150, diskon));
                    break;
                case "9.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31500));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31500, diskon));
                    break;
                case "9.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31850));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31850, diskon));
                    break;
                case "9.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32200, diskon));
                    break;
                case "9.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32550));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32550, diskon));
                    break;
                case "9.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32900));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32900, diskon));
                    break;
                case "9.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33250));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33250, diskon));
                    break;
                case "9.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33600, diskon));
                    break;
                case "9.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33950));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33950, diskon));
                    break;
                case "9.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(34300));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(34300, diskon));
                    break;
            }
        }

        if (jenis.equals("Express")) {
            switch (weight) {
                case "1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(6000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(6000, diskon));
                    break;
                case "1.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(6600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(6600, diskon));
                    break;
                case "1.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(7200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(7200, diskon));
                    break;
                case "1.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(7800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(7800, diskon));
                    break;
                case "1.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8400, diskon));
                    break;
                case "1.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9000, diskon));
                    break;
                case "1.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9600, diskon));
                    break;
                case "1.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10200, diskon));
                    break;
                case "1.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10800, diskon));
                    break;
                case "1.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(11400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(11400, diskon));
                    break;
                //-2---------------
                case "2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12000, diskon));
                    break;
                case "2.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12600, diskon));
                    break;
                case "2.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(13200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(13200, diskon));
                    break;
                case "2.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(13800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(13800, diskon));
                    break;
                case "2.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(14400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(14400, diskon));
                    break;
                case "2.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15000, diskon));
                    break;
                case "2.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15600, diskon));
                    break;
                case "2.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16200, diskon));
                    break;
                case "2.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16800, diskon));
                    break;
                case "2.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(17400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(17400, diskon));
                    break;
                //-3---------------
                case "3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18000, diskon));
                    break;
                case "3.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18600, diskon));
                    break;
                case "3.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19200, diskon));
                    break;
                case "3.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19800, diskon));
                    break;
                case "3.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20400, diskon));
                    break;
                case "3.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20000, diskon));
                    break;
                case "3.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20600, diskon));
                    break;
                case "3.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21200, diskon));
                    break;
                case "3.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21800, diskon));
                    break;
                case "3.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22400, diskon));
                    break;
                //-4---------------
                case "4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22000, diskon));
                    break;
                case "4.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22600, diskon));
                    break;
                case "4.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23200, diskon));
                    break;
                case "4.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23800, diskon));
                    break;
                case "4.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24400, diskon));
                    break;
                case "4.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24000, diskon));
                    break;
                case "4.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24600, diskon));
                    break;
                case "4.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25200, diskon));
                    break;
                case "4.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25800, diskon));
                    break;
                case "4.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(26400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(26400, diskon));
                    break;
                //-5---------------
                case "5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(27000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(27000, diskon));
                    break;
                case "5.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(27600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(27600, diskon));
                    break;
                case "5.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28200, diskon));
                    break;
                case "5.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28800, diskon));
                    break;
                case "5.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29400, diskon));
                    break;
                case "5.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29000, diskon));
                    break;
                case "5.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29600, diskon));
                    break;
                case "5.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30200, diskon));
                    break;
                case "5.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30800, diskon));
                    break;
                case "5.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31400, diskon));
                    break;
                //-6---------------
                case "6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31000, diskon));
                    break;
                case "6.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31600, diskon));
                    break;
                case "6.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32200, diskon));
                    break;
                case "6.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32800, diskon));
                    break;
                case "6.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33400, diskon));
                    break;
                case "6.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33000, diskon));
                    break;
                case "6.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33600, diskon));
                    break;
                case "6.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(34200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(34200, diskon));
                    break;
                case "6.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(34800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(34800, diskon));
                    break;
                case "6.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(35400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(35400, diskon));
                    break;
                //-7---------------
                case "7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(36000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(36000, diskon));
                    break;
                case "7.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(36600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(36600, diskon));
                    break;
                case "7.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(37200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(37200, diskon));
                    break;
                case "7.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(37800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(37800, diskon));
                    break;
                case "7.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(38400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(38400, diskon));
                    break;
                case "7.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(38000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(38000, diskon));
                    break;
                case "7.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(38600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(38600, diskon));
                    break;
                case "7.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(39200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(39200, diskon));
                    break;
                case "7.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(39800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(39800, diskon));
                    break;
                case "7.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(40400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(40400, diskon));
                    break;
                //-8---------------
                case "8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(41000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(41000, diskon));
                    break;
                case "8.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(41600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(41600, diskon));
                    break;
                case "8.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(42200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(42200, diskon));
                    break;
                case "8.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(42800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(42800, diskon));
                    break;
                case "8.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(43400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(43400, diskon));
                    break;
                case "8.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(44000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(44000, diskon));
                    break;
                case "8.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(44600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(44600, diskon));
                    break;
                case "8.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(45200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(45200, diskon));
                    break;
                case "8.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(45800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(45800, diskon));
                    break;
                case "8.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(46400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(46400, diskon));
                    break;
                //-9---------------
                case "9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(47000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(47000, diskon));
                    break;
                case "9.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(47600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(47600, diskon));
                    break;
                case "9.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(48200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(48200, diskon));
                    break;
                case "9.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(48800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(48800, diskon));
                    break;
                case "9.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(49400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(49400, diskon));
                    break;
                case "9.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(50000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(50000, diskon));
                    break;
                case "9.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(50600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(50600, diskon));
                    break;
                case "9.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(51200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(51200, diskon));
                    break;
                case "9.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(51800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(51800, diskon));
                    break;
                case "9.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(52400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(52400, diskon));
                    break;
            }
        }

        if (jenis.equals("Kilat")) {
            switch (weight) {
                case "1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8000, diskon));
                    break;
                case "1.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(8800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(8800, diskon));
                    break;
                case "1.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(9600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(9600, diskon));
                    break;
                case "1.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(10400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(10400, diskon));
                    break;
                case "1.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(11200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(11200, diskon));
                    break;
                case "1.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12000, diskon));
                    break;
                case "1.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(12800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(12800, diskon));
                    break;
                case "1.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(13600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(13600, diskon));
                    break;
                case "1.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(14400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(14400, diskon));
                    break;
                case "1.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(15200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(15200, diskon));
                    break;
                //-2---------------
                case "2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16000, diskon));
                    break;
                case "2.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(16800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(16800, diskon));
                    break;
                case "2.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(17600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(17600, diskon));
                    break;
                case "2.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(18400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(18400, diskon));
                    break;
                case "2.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(19200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(19200, diskon));
                    break;
                case "2.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20000, diskon));
                    break;
                case "2.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(20800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(20800, diskon));
                    break;
                case "2.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(21600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(21600, diskon));
                    break;
                case "2.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(22400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(22400, diskon));
                    break;
                case "2.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(23200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(23200, diskon));
                    break;
                //-3---------------
                case "3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24000, diskon));
                    break;
                case "3.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(24800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(24800, diskon));
                    break;
                case "3.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(25600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(25600, diskon));
                    break;
                case "3.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(26400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(26400, diskon));
                    break;
                case "3.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(27200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(27200, diskon));
                    break;
                case "3.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28000, diskon));
                    break;
                case "3.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(28800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(28800, diskon));
                    break;
                case "3.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(29600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(29600, diskon));
                    break;
                case "3.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(30400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(30400, diskon));
                    break;
                case "3.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(31200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(31200, diskon));
                    break;
                //-4---------------
                case "4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32000, diskon));
                    break;
                case "4.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(32800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(32800, diskon));
                    break;
                case "4.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33600, diskon));
                    break;
                case "4.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(33400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(33400, diskon));
                    break;
                case "4.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(34200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(34200, diskon));
                    break;
                case "4.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(35000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(35000, diskon));
                    break;
                case "4.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(35800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(35800, diskon));
                    break;
                case "4.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(36600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(36600, diskon));
                    break;
                case "4.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(37400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(37400, diskon));
                    break;
                case "4.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(38200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(38200, diskon));
                    break;
                //-5---------------
                case "5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(39000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(39000, diskon));
                    break;
                case "5.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(39800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(39800, diskon));
                    break;
                case "5.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(40600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(40600, diskon));
                    break;
                case "5.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(41400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(41400, diskon));
                    break;
                case "5.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(42200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(42200, diskon));
                    break;
                case "5.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(43000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(43000, diskon));
                    break;
                case "5.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(43800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(43800, diskon));
                    break;
                case "5.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(44600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(44600, diskon));
                    break;
                case "5.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(45400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(45400, diskon));
                    break;
                case "5.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(46200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(46200, diskon));
                    break;
                //-6---------------
                case "6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(47000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(47000, diskon));
                    break;
                case "6.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(47800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(47800, diskon));
                    break;
                case "6.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(48600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(48600, diskon));
                    break;
                case "6.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(49400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(49400, diskon));
                    break;
                case "6.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(50200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(50200, diskon));
                    break;
                case "6.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(51000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(51000, diskon));
                    break;
                case "6.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(51800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(51800, diskon));
                    break;
                case "6.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(52600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(52600, diskon));
                    break;
                case "6.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(53400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(53400, diskon));
                    break;
                case "6.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(54200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(54200, diskon));
                    break;
                //-7---------------
                case "7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(55000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(55000, diskon));
                    break;
                case "7.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(55800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(55800, diskon));
                    break;
                case "7.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(56600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(56600, diskon));
                    break;
                case "7.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(57400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(57400, diskon));
                    break;
                case "7.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(58200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(58200, diskon));
                    break;
                case "7.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(59000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(59000, diskon));
                    break;
                case "7.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(59800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(59800, diskon));
                    break;
                case "7.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(60600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(60600, diskon));
                    break;
                case "7.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(61400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(61400, diskon));
                    break;
                case "7.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(62200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(62200, diskon));
                    break;
                //-8---------------
                case "8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(63000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(63000, diskon));
                    break;
                case "8.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(63800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(63800, diskon));
                    break;
                case "8.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(64600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(64600, diskon));
                    break;
                case "8.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(65400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(65400, diskon));
                    break;
                case "8.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(66200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(66200, diskon));
                    break;
                case "8.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(67000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(67000, diskon));
                    break;
                case "8.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(67800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(67800, diskon));
                    break;
                case "8.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(68600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(68600, diskon));
                    break;
                case "8.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(69400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(69400, diskon));
                    break;
                case "8.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(70200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(70200, diskon));
                    break;
                //-9---------------
                case "9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(71000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(71000, diskon));
                    break;
                case "9.1":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(71800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(71800, diskon));
                    break;
                case "9.2":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(72600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(72600, diskon));
                    break;
                case "9.3":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(73400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(73400, diskon));
                    break;
                case "9.4":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(74200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(74200, diskon));
                    break;
                case "9.5":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(75000));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(75000, diskon));
                    break;
                case "9.6":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(75800));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(75800, diskon));
                    break;
                case "9.7":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(76600));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(76600, diskon));
                    break;
                case "9.8":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(77400));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(77400, diskon));
                    break;
                case "9.9":
                    mDetailOrderViewMvp.setOriginalPriceTxt(setOriPrice(78200));
                    mDetailOrderViewMvp.setTotalPriceTxt(setTotalPrice(78200, diskon));
                    break;
            }
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
