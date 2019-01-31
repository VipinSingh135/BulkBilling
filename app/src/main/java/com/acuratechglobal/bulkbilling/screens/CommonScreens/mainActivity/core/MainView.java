package com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.core;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acuratechglobal.bulkbilling.R;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.fragmentHome.DocHomeFragment;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.list.SideMenuAdapter;
import com.acuratechglobal.bulkbilling.screens.PatientScreens.fragmentHome.PatHomeFragment;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import kotlin.Unit;


public class MainView {

    private View view;
    private MainActivity activity;

    private DrawerLayout drawerLayout;
    private FrameLayout containerLayout;
    private ImageView imgProfile;
    private TextView tvName,tvEmail;
    private NavigationView navigationView;
    private RecyclerView recyclerItems;
    private SideMenuAdapter adapter;
    private List<String> menuList=new ArrayList<>();
    private Button btnLogout;
    UserData data;

    public MainView(MainActivity context, SharedPrefsUtil prefs) {
        this.activity = context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        imgProfile = view.findViewById(R.id.imgProfile);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        btnLogout = view.findViewById(R.id.btnLogout);
        recyclerItems = view.findViewById(R.id.recyclerItems);

        setMenuList();
        tvName.setText(data.getFirstName()+" "+data.getLastName());
        tvEmail.setText(data.getEmail());
        if (data.getsPhotoPath()!=null){
            Glide.with(activity).load(Uri.parse(data.getsPhotoPath())).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user)).into(imgProfile);
        }
        adapter= new SideMenuAdapter();
        recyclerItems.setLayoutManager(new LinearLayoutManager(activity));
        recyclerItems.setAdapter(adapter);
        if (data.getUserType()==2) {
            goToFragment(new DocHomeFragment(), false);
        }else{
            goToFragment(new PatHomeFragment(), false);
        }
        setMenuAdapter(0);
    }

    public View constructView() {
        return view;
    }

    private void setMenuList() {
        menuList.clear();
        menuList.add("Home");
        menuList.add("My Appointments");
        menuList.add("My Favourites");
        menuList.add("My Profile");
        menuList.add("Ratings");
        menuList.add("Notification");
        menuList.add("Settings");
    }

    void setMenuAdapter(int pos) {
        adapter.setAdapter(menuList,pos);
    }

    void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment).commit();
    }

    Observable<Integer> itemMenuClicks() {
        return adapter.observeClicks();
    }

    void closeDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    Observable<Unit> btnLogout(){
        return RxView.clicks(btnLogout);
    }
}
