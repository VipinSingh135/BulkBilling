package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.core;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.MainTabActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainTabActivity.list.SideMenuAdapter;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.DocHomeFragment;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.PatHomeFragment;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import kotlin.Unit;


public class MainView {

    private View view;
    private MainTabActivity activity;
    private FrameLayout containerLayout;
    private ImageView imgProfile;
    private TextView tvName,tvEmail;
    private List<String> menuList=new ArrayList<>();
    private UserData data;
    private SharedPrefsUtil prefs;
    private BottomNavigationView bottomNavigationView;
    private final PublishSubject<Integer> tabSelected = PublishSubject.create();

    public MainView(MainTabActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main_tab, parent, true);
        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }

        imgProfile = view.findViewById(R.id.imgProfile);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        bottomNavigationView = view.findViewById(R.id.navigation);

        setMenuList();
        setNavigationHeader();

//        if (data.getUserType()==2) {
//            goToFragment(new DocHomeFragment(), false);
//        }else{
//            goToFragment(new PatHomeFragment(), false);
//        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                tabSelected.onNext(item.getItemId());
                return true;
            }
        });
    }

    public void setNavigationHeader(){
        data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        tvName.setText(data.getFirstName()+" "+data.getLastName());
        tvEmail.setText(data.getEmail());
        if (data.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(data.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        }
    }
    public View constructView() {
        return view;
    }

    private void setMenuList() {
        menuList.clear();
        if(data.getUserType()==3) {
            menuList.add("Home");
            menuList.add("My Appointments");
            menuList.add("My Favourites");
            menuList.add("My Profile");
            menuList.add("Ratings");
            menuList.add("Notification");
            menuList.add("Settings");
        }else{
            menuList.add("Home");
            menuList.add("My Appointments");
            menuList.add("My Profile");
            menuList.add("My Ratings");
            menuList.add("Notification");
            menuList.add("Settings");
        }
    }

    void goToFragment(Fragment fragment, boolean addToBackStack)  {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.containerLayout, fragment).commit();
    }

//    Observable<Integer> itemMenuClicks() {
//        return adapter.observeClicks();
//    }

    void closeDrawer(){
    }

    void openDrawer(){
    }

//    Observable<Unit> btnLogout(){
//        return RxView.clicks(btnLogout);
//    }

    Observable<Integer> tabSelected(){
        return tabSelected;
    }
}
