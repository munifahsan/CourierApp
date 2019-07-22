package com.tiunida.courierapp.ActivityOrderDetail;

public interface DetailOrderInteractorMvp {
    void getOrdersData(String order_id);
    void updateWeight(String order_id, String weight);
    void updateAccept(String order_id);
    void updateProses(String order_id);
    void updateDone(String order_id);
    void updatePaid(String order_id);
    void updateDeliver(String order_id);
}
