package com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.core;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.api.request.GetDoctorListRequest;
import com.acuratechglobal.bulkbilling.models.SpecializationModel;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.MultiSelectAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.OptionsAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.list.SelectedItemAdapter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.list.PatHomeAdapter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.viewDoctorProfile.DoctorProfileActivity;
import com.acuratechglobal.bulkbilling.utils.UiUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kotlin.Unit;

import static com.acuratechglobal.bulkbilling.utils.UiUtils.getInputText;

public class HomeView {

    private View view,viewShadow;
    private RelativeLayout relativeFilter;
    private LinearLayout linearFilter;
    private ImageButton btnMenu,btnFilter,btnCancel;
    private RecyclerView recyclerHome;
    private SearchView searchView;
    private final MainActivity activity;
    private final ProgressDialog progressDialog;
    private GetDoctorListRequest request;
    private PatHomeAdapter adapter;
    private List<DoctorProfileModel> dataList;

    private TextView tvSpclArea, tvSpclField, tvSpecialty;
    private ImageView imgStar1, imgStar2, imgStar3,imgStar4,imgStar5;
    private RadioButton rdBtnAbove, rdBtn03, rdBtn05, rdBtn10, rdBtn15;
    private RecyclerView recyclerSpeciality;
    private Button btnClear,btnApply;

    private OptionsAdapter optionAdapter, optionAdapter2;
    private Dialog dialog, dialogMultiSelect;
    private TextView tvTitle;
    private Button btnDone;
    private RecyclerView recyclerMultiselect;
    private MultiSelectAdapter multiSelectAdapter;
    private SelectedItemAdapter adapterLevel3;

    private List<SpecializationModel> selectedlistLevel3 = new ArrayList<>();
    private int experience = 0;
    private int level1Id=0,level2Id=0;
    private boolean isStar1=false,isStar2=false,isStar3=false,isStar4=false,isStar5=false;
    PublishSubject<String> searchDoc;

    public HomeView(MainActivity context, ProgressDialog dialog) {
        this.activity= context;
        this.progressDialog= dialog;

        FrameLayout parent = new FrameLayout(activity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(activity).inflate(R.layout.pat_fragment_home, parent, true);
        btnMenu= view.findViewById(R.id.btnMenu);
        btnFilter= view.findViewById(R.id.btnFilter);
        searchView= view.findViewById(R.id.searchView);
        btnCancel= view.findViewById(R.id.btnCancel);
        linearFilter= view.findViewById(R.id.linearFilter);
        relativeFilter= view.findViewById(R.id.relativeFilter);
        viewShadow= view.findViewById(R.id.viewShadow);
        recyclerHome= view.findViewById(R.id.recyclerHome);

        tvSpclArea = view.findViewById(R.id.tvSpclArea);
        tvSpclField = view.findViewById(R.id.tvSpclField);
        tvSpecialty = view.findViewById(R.id.tvSpecialty);
        rdBtnAbove = view.findViewById(R.id.rdBtnAbove);
        rdBtn03 = view.findViewById(R.id.rdBtn03);
        rdBtn05 = view.findViewById(R.id.rdBtn05);
        rdBtn10 = view.findViewById(R.id.rdBtn10);
        rdBtn15 = view.findViewById(R.id.rdBtn15);
        imgStar1 = view.findViewById(R.id.imgStar1);
        imgStar2 = view.findViewById(R.id.imgStar2);
        imgStar3 = view.findViewById(R.id.imgStar3);
        imgStar4 = view.findViewById(R.id.imgStar4);
        imgStar5 = view.findViewById(R.id.imgStar5);
        btnClear = view.findViewById(R.id.btnClear);
        btnApply = view.findViewById(R.id.btnApply);

        recyclerSpeciality = view.findViewById(R.id.recyclerSpeciality);

        adapter= new PatHomeAdapter();
        recyclerHome.setLayoutManager(new LinearLayoutManager(activity));
        recyclerHome.setAdapter(adapter);
//        ButterKnife.bind(view, activity);
        request= new GetDoctorListRequest();
        request.setPageNo(1);
        request.setRecordsPerPage(100);
        request.setSortBy("Id");
        request.setSortOrder("Desc");
        dataList=new ArrayList<>();

        optionAdapter = new OptionsAdapter();
        optionAdapter2 = new OptionsAdapter();

        dialogMultiSelect = createMultiSelectDialog();
        btnDone = dialogMultiSelect.findViewById(R.id.btnDone);
        tvTitle = dialogMultiSelect.findViewById(R.id.tvTitle);
        recyclerMultiselect = dialogMultiSelect.findViewById(R.id.recyclerView);

        multiSelectAdapter = new MultiSelectAdapter();

        adapterLevel3 = new SelectedItemAdapter();
        recyclerSpeciality.setAdapter(adapterLevel3);
        recyclerSpeciality.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));

        searchDoc= PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchDoc.onNext(query);
                UiUtils.hideKeyboard(activity);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchDoc.onNext("");
                return false;
            }
        });
    }


    public View getView() {
        return this.view;
    }

    void showLoadingDialog(String loadingText) {
        progressDialog.setMessage(loadingText);
        progressDialog.show();
    }

    void hideLoadingDialog() {
        progressDialog.dismiss();
    }

    Observable<Unit> menuClick() {
        return RxView.clicks(btnMenu);
    }

    Observable<Unit> filterClick() {
        return RxView.clicks(btnFilter);
    }

    Observable<Unit> shadowClick() {
        return RxView.clicks(viewShadow);
    }

    Observable<Unit> cancelClick() {
        return RxView.clicks(btnCancel);
    }

    Observable<Integer> itemClick() {
        return adapter.observeClicks();
    }

    Observable<Unit> spclAreaClick() {
        return RxView.clicks(tvSpclArea);
    }

    Observable<Unit> spclFieldClick() {
        return RxView.clicks(tvSpclField);
    }

    Observable<Unit> specialityClick() {
        return RxView.clicks(tvSpecialty);
    }

    public GetDoctorListRequest getRequest() {
        return request;
    }

    void setAdapterList(List<DoctorProfileModel> list){
        dataList.clear();
        dataList.addAll(list);
        adapter.notifyAdapter(list);
    }

    void gotoDocProfile(int pos){
        Intent intent= new Intent(activity, DoctorProfileActivity.class);
        String strData= new Gson().toJson(dataList.get(pos));
        intent.putExtra("data",strData);
        activity.startActivity(intent);
    }

    void showFilter() {
        linearFilter.setVisibility(View.VISIBLE);
        relativeFilter.setVisibility(View.VISIBLE);
        UiUtils.slideUp(linearFilter);
    }
    void hideFilter() {
//        UiUtils.slideDown(linearFilter);
        UiUtils.slideDown(linearFilter).setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearFilter.setVisibility(View.INVISIBLE);
                relativeFilter.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private Dialog createDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_options);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    //Specialization Level 1
    Observable<Integer> itemClicksLevel1() {
        return optionAdapter.observeClicks();
    }
    void showDialogLevel1(List<SpecializationModel> strList) {
        dialog = createDialog();
        TextView tvTitle = dialog.findViewById(R.id.tVDialogTitle);
        RecyclerView listView;
        optionAdapter.setAdapter(strList);
        listView = dialog.findViewById(R.id.listView);
        listView.setAdapter(optionAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        listView.setLayoutManager(mLayoutManager);
        tvTitle.setText("Select Specialization Area");
        dialog.show();
    }
    void setTvSpclArea(SpecializationModel model) {
        if (!model.getName().equals(getInputText(tvSpclArea))){
            tvSpclField.setText(null);
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
            selectedlistLevel3.clear();
            level2Id=0;
        }
        tvSpclArea.setText(model.getName());
        level1Id= model.getId();
        dialog.dismiss();
    }

    //Specialization Level 2
    Observable<Integer> itemClicksLevel2() {
        return optionAdapter2.observeClicks();
    }
    void showDialogLevel2(List<SpecializationModel> strList) {
        dialog = createDialog();
        TextView tvTitle = dialog.findViewById(R.id.tVDialogTitle);
        RecyclerView listView;
        optionAdapter2.setAdapter(strList);
        listView = dialog.findViewById(R.id.listView);
        listView.setAdapter(optionAdapter2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        listView.setLayoutManager(mLayoutManager);
        tvTitle.setText("Select Specialization Field");
        dialog.show();
    }
    void setTvSpclField(SpecializationModel model) {
        if (!model.getName().equals(getInputText(tvSpclField))){
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
            selectedlistLevel3.clear();
        }
        tvSpclField.setText(model.getName());
        level2Id= model.getId();
        dialog.dismiss();
    }

    //Specialization Level 1 & 2 Dialog
    private Dialog createMultiSelectDialog() {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_multiselect);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    //Specialization Level 3
    void showDialogLevel3(String title, List<SpecializationModel> list) {

        multiSelectAdapter.setAdapterItems(list, selectedlistLevel3);
        recyclerMultiselect.setAdapter(multiSelectAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerMultiselect.setLayoutManager(mLayoutManager);
        tvTitle.setText(title);
        dialogMultiSelect.show();
    }
    Observable<Integer> itemClicksLevel3() {
        return multiSelectAdapter.observeClicks();
    }
    void notifyItemChangedlevel3(List<SpecializationModel> list, Integer pos) {
        boolean isSelected=false;
        for (SpecializationModel obj:selectedlistLevel3) {
            if (obj.getId()==list.get(pos).getId()){
                isSelected=true;
                selectedlistLevel3.remove(obj);
                break;
            }
        }
        if (!isSelected) {
            selectedlistLevel3.add(list.get(pos));
        }

        multiSelectAdapter.notifyAdapter(selectedlistLevel3);
    }
    void setSpecialityAdapter() {
        dialogMultiSelect.dismiss();
        if (selectedlistLevel3 == null || selectedlistLevel3.size() == 0) {
            tvSpecialty.setVisibility(View.VISIBLE);
            recyclerSpeciality.setVisibility(View.GONE);
        } else {
            tvSpecialty.setVisibility(View.GONE);
            recyclerSpeciality.setVisibility(View.VISIBLE);
        }
        adapterLevel3.setAdapterList(selectedlistLevel3);
//        selectedlistLevel3.clear();
//        selectedlistLevel3.addAll(selectedlistLevel3);
    }
    Observable<Integer> itemClickslevel3Selected() {
        return adapterLevel3.observeClicks();
    }
    Observable<Unit> doneClick() {
        return RxView.clicks(btnDone);
    }

    //Radio Button Toggle
    void toggleRdBtns(int pos) {
        switch (pos) {
            case 1:
                rdBtn03.setChecked(true);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 1;
                break;
            case 2:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(true);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 2;
                break;
            case 3:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(true);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(false);
                experience = 3;
                break;
            case 4:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(true);
                rdBtnAbove.setChecked(false);
                experience = 4;
                break;
            case 5:
                rdBtn03.setChecked(false);
                rdBtn05.setChecked(false);
                rdBtn10.setChecked(false);
                rdBtn15.setChecked(false);
                rdBtnAbove.setChecked(true);
                experience = 5;
                break;
        }

    }
    Observable<Unit> rdBtnAboveClick() {
        return RxView.clicks(rdBtnAbove);
    }
    Observable<Unit> rdBtn03Click() {
        return RxView.clicks(rdBtn03);
    }
    Observable<Unit> rdBtn05Click() {
        return RxView.clicks(rdBtn05);
    }
    Observable<Unit> rdBtn10Click() {
        return RxView.clicks(rdBtn10);
    }
    Observable<Unit> rdBtn15Click() {
        return RxView.clicks(rdBtn15);
    }

    //Image Star Toggle
    Observable<Unit> star1Click() {
        return RxView.clicks(imgStar1);
    }
    void star1Toggle(){
        if (isStar1){
            isStar1=false;
            imgStar1.setImageResource(R.drawable.filter_star1_off);
        }else {
            isStar1=true;
            imgStar1.setImageResource(R.drawable.filter_star1_on);
        }
    }
    Observable<Unit> star2Click() {
        return RxView.clicks(imgStar2);
    }
    void star2Toggle(){
        if (isStar2){
            isStar2=false;
            imgStar2.setImageResource(R.drawable.filter_star2_off);
        }else {
            isStar2=true;
            imgStar2.setImageResource(R.drawable.filter_star2_on);
        }
    }
    Observable<Unit> star3Click() {
        return RxView.clicks(imgStar3);
    }
    void star3Toggle(){
        if (isStar3){
            isStar3=false;
            imgStar3.setImageResource(R.drawable.filter_star3_off);
        }else {
            isStar3=true;
            imgStar3.setImageResource(R.drawable.filter_star3_on);
        }
    }
    Observable<Unit> star4Click() {
        return RxView.clicks(imgStar4);
    }
    void star4Toggle(){
        if (isStar4){
            isStar4=false;
            imgStar4.setImageResource(R.drawable.filter_star4_off);
        }else {
            isStar4=true;
            imgStar4.setImageResource(R.drawable.filter_star4_on);
        }
    }
    Observable<Unit> star5Click() {
        return RxView.clicks(imgStar5);
    }
    void star5Toggle(){
        if (isStar5){
            isStar5=false;
            imgStar5.setImageResource(R.drawable.filter_star5_off);
        }else {
            isStar5=true;
            imgStar5.setImageResource(R.drawable.filter_star5_on);
        }
    }

    //Apply Filter
    Observable<Unit> applyFilter() {
        return RxView.clicks(btnApply);
    }
    Observable<Unit> clearFilter() {
        return RxView.clicks(btnClear);
    }
    void setFilterParams(){
        if (level1Id!=0) {
            request.setLevel1ID(level1Id);
        }else {
            request.setLevel1ID(null);
        }
        if (level2Id!=0) {
            request.setLevel2ID(level2Id);
        }else {
            request.setLevel2ID(null);
        }
        if (selectedlistLevel3!=null && selectedlistLevel3.size()!=0){
            List<Integer> list = new ArrayList<>();
            for (SpecializationModel model: selectedlistLevel3) {
                list.add(model.getId());
            }
            request.setLevel3ID(list);
        }else {
            request.setLevel3ID(null);
        }
        if (experience!=0){
            request.setExperience(experience);
        }else {
            request.setExperience(null);
        }

        if (isStar1 || isStar2 || isStar3 || isStar4 || isStar5){
            List<Integer> ratinglist = new ArrayList<>();
            if (isStar1){
                ratinglist.add(1);
            }
            if (isStar2){
                ratinglist.add(2);
            }
            if (isStar3){
                ratinglist.add(3);
            }
            if (isStar4){
                ratinglist.add(4);
            }
            if (isStar5){
                ratinglist.add(5);
            }
            request.setRate(ratinglist);
        }else {
            request.setRate(null);
        }
    }

    void clearFilterParams(){
        request.setRate(null);
        request.setExperience(null);
        request.setLevel3ID(null);
        request.setLevel1ID(null);
        request.setLevel2ID(null);

        tvSpclField.setText(null);
        tvSpclArea.setText(null);
        tvSpecialty.setVisibility(View.VISIBLE);
        recyclerSpeciality.setVisibility(View.GONE);
        selectedlistLevel3.clear();
        level2Id=0;
        level1Id=0;

        rdBtn03.setChecked(false);
        rdBtn05.setChecked(false);
        rdBtn10.setChecked(false);
        rdBtn15.setChecked(false);
        rdBtnAbove.setChecked(false);

        isStar1=false;
        isStar2=false;
        isStar3=false;
        isStar4=false;
        isStar5=false;

        imgStar1.setImageResource(R.drawable.filter_star1_off);
        imgStar2.setImageResource(R.drawable.filter_star2_off);
        imgStar3.setImageResource(R.drawable.filter_star3_off);
        imgStar4.setImageResource(R.drawable.filter_star4_off);
        imgStar5.setImageResource(R.drawable.filter_star5_off);

    }


    //search
    Observable<String> searchDoctors() {
        return searchDoc;
    }

    void setSearchParams(String search){
       request.setSearch(search);
    }


}
