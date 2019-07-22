package com.tiunida.courierapp.FragmentBiasa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.tiunida.courierapp.ActivityOrderDetail.ui.DetailOrderActivity;
import com.tiunida.courierapp.FragmentBiasa.Adapter.BiasaAdapter;
import com.tiunida.courierapp.FragmentBiasa.Model.BiasaModel;
import com.tiunida.courierapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BiasaFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference ordersRef = firebaseFirestore.collection("Orders");

    private LinearLayoutManager mLayoutManager;
    private BiasaAdapter adapter;
    private FirebaseAuth firebaseAuth;
    //private String user_id;
    private View myFragment;

    private int a = 0;

    Query query;

    @BindView(R.id.menu_item_accept)
    FloatingActionButton mMenuitemAccept;
    @BindView(R.id.menu_item_proses)
    FloatingActionButton mMenuItemProses;
    @BindView(R.id.menu_item_done)
    FloatingActionButton mMenuItemDone;
    @BindView(R.id.menu_item_paid)
    FloatingActionButton mMenuItemPaid;
    @BindView(R.id.menu_item_deliver)
    FloatingActionButton mMenuItemDeliver;
    @BindView(R.id.menu_item_all)
    FloatingActionButton mMenuitemAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_biasa, container, false);

        ButterKnife.bind(this, myFragment);

        firebaseAuth = FirebaseAuth.getInstance();
        //user_id = firebaseAuth.getCurrentUser().getUid();

        query = ordersRef.whereEqualTo("h_accepted2", "")
                .whereEqualTo("a_jenis", "Biasa")
                .whereEqualTo("h_on_proses2", "")
                .whereEqualTo("h_done2", "")
                .whereEqualTo("h_paid2", "")
                .whereEqualTo("h_delivered2", "")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });


        return myFragment;
    }

    @OnClick(R.id.menu_item_accept)
    public void onMenuItemAcceptOnClick() {
        getAcceptData();
    }

    @OnClick(R.id.menu_item_proses)
    public void onMenuItemProsesOnClick() {
        getOnProsesData();
    }

    @OnClick(R.id.menu_item_done)
    public void onMenuItemDoneOnClick() {
        getDoneData();
    }

    @OnClick(R.id.menu_item_paid)
    public void onMenuItemPaidOnClick() {
        getPaidData();
    }

    @OnClick(R.id.menu_item_deliver)
    public void onMenuItemDeliverOnClick() {
        getDeliverData();
    }

    @OnClick(R.id.menu_item_all)
    public void onMenuItemAllOnClick() {
        getAllData();
    }

    private void getAcceptData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("h_accepted2", "")
                .whereEqualTo("a_jenis", "Biasa")
                .whereEqualTo("h_on_proses2", "")
                .whereEqualTo("h_done2", "")
                .whereEqualTo("h_paid2", "")
                .whereEqualTo("h_delivered2", "")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);

        if (query != null) {
            FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                    .setQuery(query, BiasaModel.class)
                    .build();

            adapter = new BiasaAdapter(options);

            RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
            recyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(adapter);

            adapter.startListening();

            adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
                @Override
                public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                    BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                    String id = documentSnapshot.getId();
                    Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                    Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                    orderDetailIntent.putExtra("id", id);
                    startActivity(orderDetailIntent);

                }
            });
        } else {
            Toast.makeText(getActivity(), "kosong", Toast.LENGTH_LONG).show();
        }


    }

    private void getOnProsesData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("h_accepted2", "1")
                .whereEqualTo("h_on_proses2", "")
                .whereEqualTo("h_done2", "")
                .whereEqualTo("h_paid2", "")
                .whereEqualTo("h_delivered2", "")
                .whereEqualTo("a_jenis", "Biasa")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });
    }

    private void getDoneData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("h_accepted2", "1")
                .whereEqualTo("h_on_proses2", "1")
                .whereEqualTo("h_done2", "")
                .whereEqualTo("h_paid2", "")
                .whereEqualTo("h_delivered2", "")
                .whereEqualTo("a_jenis", "Biasa")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });
    }

    private void getPaidData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("h_accepted2", "1")
                .whereEqualTo("h_on_proses2", "1")
                .whereEqualTo("h_done2", "1")
                .whereEqualTo("h_paid2", "")
                .whereEqualTo("h_delivered2", "")
                .whereEqualTo("a_jenis", "Biasa")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });
    }

    private void getDeliverData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("h_accepted2", "1")
                .whereEqualTo("h_on_proses2", "1")
                .whereEqualTo("h_done2", "1")
                .whereEqualTo("h_paid2", "1")
                .whereEqualTo("h_delivered2", "1")
                .whereEqualTo("a_jenis", "Biasa")
                .orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });
    }

    private void getAllData() {
        adapter.stopListening();
        Log.d("test", "sip");
        query = ordersRef.whereEqualTo("a_jenis", "Biasa").orderBy("a_uniq_id", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<BiasaModel> options = new FirestoreRecyclerOptions.Builder<BiasaModel>()
                .setQuery(query, BiasaModel.class)
                .build();

        adapter = new BiasaAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        adapter.setOnItemClickListener(new BiasaAdapter.OnItemClickListener() {
            @Override
            public void onBiasaItemClick(DocumentSnapshot documentSnapshot, int position) {
                BiasaModel model = documentSnapshot.toObject(BiasaModel.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), DetailOrderActivity.class);
                orderDetailIntent.putExtra("id", id);
                startActivity(orderDetailIntent);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
