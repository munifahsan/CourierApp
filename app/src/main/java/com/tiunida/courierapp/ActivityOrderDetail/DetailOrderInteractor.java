package com.tiunida.courierapp.ActivityOrderDetail;

import com.tiunida.courierapp.ActivityOrderDetail.Model.DetailOrderRepository;
import com.tiunida.courierapp.ActivityOrderDetail.Model.DetailOrderRepositoryMvp;

public class DetailOrderInteractor implements DetailOrderInteractorMvp {
    private DetailOrderRepositoryMvp mDetailOrderRepositoryMvp;

    public DetailOrderInteractor() {
        mDetailOrderRepositoryMvp = new DetailOrderRepository();
    }

    public void getOrdersData(String order_id) {
        mDetailOrderRepositoryMvp.getOrdersData(order_id);
    }

    public void updateWeight(String order_id, String weight) {
        mDetailOrderRepositoryMvp.updateWeight(order_id, weight);
    }

    public void updateAccept(String order_id) {
        mDetailOrderRepositoryMvp.updateAccept(order_id);
    }

    public void updateProses(String order_id) {
        mDetailOrderRepositoryMvp.updateProses(order_id);
    }

    public void updateDone(String order_id) {
        mDetailOrderRepositoryMvp.updateDone(order_id);
    }

    public void updatePaid(String order_id) {
        mDetailOrderRepositoryMvp.updatePaid(order_id);
    }

    public void updateDeliver(String order_id) {
        mDetailOrderRepositoryMvp.updateDeliver(order_id);
    }
}
