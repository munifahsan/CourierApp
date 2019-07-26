package com.tiunida.courierapp.ActivityOrderDetail.ui;

public interface DetailOrderViewMvp {
    void setNoLaundry(String txt);

    void setNameTxt(String txt);

    void setDormitoryTxt(String txt);

    void setRoomTxt(String txt);

    void setPhoneNumber(String txt);

    void setStatusTxt(String txt);

    void setTglPesanTxt(String txt);

    void setTglSelesaiTxt(String txt);

    void setLaundryTypeTxt(String txt);

    void setLaudnryWeightTxt(String txt);

    void setOriginalPriceTxt(String txt);

    void setLaundryDiskonTxt(String txt);

    void setTotalPriceTxt(String txt);

    void setCatatanTxt(String txt);

    void setAcceptIndicatorCheck();

    void setAcceptIndicatorUnCheck();

    void setProsesIndicatorCheck();

    void setProsesIndicatorUnCheck();

    void setDoneIndicatorCheck();

    void setDoneIndicatorUnCheck();

    void setPaidIndicatorCheck();

    void setPaidIndicatorUnCheck();

    void setDeliveredIndicatorCheck();

    void setDeliveredIndicatorUnCheck();

    void showProgress();

    void hideProgress();

    void showMessage(String message);

    void setAcceptOkBtnDisable();

    void setAcceptOkBtnEnable();

    void setProsesOkBtnDisable();

    void setProsesOkBtnEnable();

    void setDoneOkBtnDisable();

    void setDoneOkBtnEnable();

    void setPaidOkBtnDisable();

    void setPaidOkBtnEnable();

    void setDeliverOkBtnDisable();

    void setConfirmPaidBtnTxt(String txt);

    void setConfirmDeliverBtnTxt(String txt);

    void setDeliverOkBtnEnable();

    void setSaveWeightBtnDisable();

    void setSaveWeightBtnEnable();

    void setAskCostumerBtnEnable();

    void setAskCostumerBtnDisable();

    void setSaveWeightEdtDisable();

    void setSaveWeightEdtEnable();
}
