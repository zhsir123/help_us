package stack.birds.helpus.TabFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import stack.birds.helpus.Helper.ReportDBHelper;
import stack.birds.helpus.Item.Receive;
import stack.birds.helpus.Item.Report;
import stack.birds.helpus.R;


/**
 * Created by dsm2016 on 2017-07-31.
 */

public class ReportListFragment extends Fragment {
    View view;
    Realm mRealm;

    private List<Report> reportList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report_list, container, false);

        initRecyclerView();

        return view;
    }

    private void init() {
        mRealm = Realm.getInstance(getActivity());
    }
    private RealmResults<Receive> getReceiveList() {
        return mRealm.where(Receive.class).findAll();
    }
    
    private void initRecyclerView () {
        asdf

        // report_db 에 있는 report 데이터들을 불러옴
        reportDBHelper = new ReportDBHelper(getContext());
        reportList = reportDBHelper.select();

        // recyclerView 설정
        recyclerView = (RecyclerView) view.findViewById(R.id.report_recycler_view);

        // 해당 데이터를 recyclerView에 지정
        reportAdapter = new ReportAdapter(reportList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reportAdapter);
    }
}
