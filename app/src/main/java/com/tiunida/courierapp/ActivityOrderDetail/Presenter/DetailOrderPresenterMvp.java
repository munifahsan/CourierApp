package com.tiunida.courierapp.ActivityOrderDetail.Presenter;

public interface DetailOrderPresenterMvp {
    void onCreate();
    void onDestroy();
    void getOrdersData(String order_id);
    void validateUpdateWeight(String order_id, String weight);
    void validateUpdateAccept(String order_id);
    void validateUpdateProses(String order_id);
    void validateUpdateDone(String order_id);
    void validateUpdatePaid(String order_id);
    void validateUpdateDeliver(String order_id);
}
