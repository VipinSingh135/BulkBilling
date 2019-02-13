package com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.core;

import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.models.DoctorProfileModel;
import com.acuratechglobal.bulkbilling.api.response.GetProfileApiResponse;
import com.acuratechglobal.bulkbilling.api.response.LoginApiResponse;
import com.acuratechglobal.bulkbilling.api.response.SpecializationResponse;
import com.acuratechglobal.bulkbilling.models.DoctorAvailabilityModel;
import com.acuratechglobal.bulkbilling.models.UserData;
import com.acuratechglobal.bulkbilling.screens.DoctorScreens.createProfile.CreateProfileActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.mainActivity.MainActivity;
import com.acuratechglobal.bulkbilling.utils.ImageUtils;
import com.acuratechglobal.bulkbilling.utils.NetworkUtils;
import com.acuratechglobal.bulkbilling.utils.SharedPrefsUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;

import static com.acuratechglobal.bulkbilling.utils.ImageUtils.REQUEST_GALLERY;

public class CreateProfileModel {
    private static final int REQUEST_PLACE_PICKER = 6;
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final CreateProfileActivity activity;
    private final Api apis;

    private String level1Id= null;
    private String level2Id= null;

    private List<String> daysList=new ArrayList<>();
    private List<String> selectedDaysList=new ArrayList<>();

    private File profileFile;
    private UserData data;
    private SharedPrefsUtil prefs;
    public CreateProfileModel(CreateProfileActivity activity, Api api, SharedPrefsUtil prefs) {
        this.activity = activity;
        this.apis = api;
        daysList.clear();
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
        daysList.add("Saturday");

        this.prefs= prefs;
        if (prefs!=null){
            data= prefs.getObject(SharedPrefsUtil.PREFERENCE_USER_DATA,UserData.class);
        }
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    Observable<GetProfileApiResponse> performGetProfile(){
        return apis.apiGetProfile(data.getDocUID());
    }
    Observable<LoginApiResponse> performCreateProfile(DoctorProfileModel request) {
        return apis.apiCreateProfile(request);
    }

    Observable<SpecializationResponse> callLevel1Api() {
        return apis.apiSpecialization1();
    }
    Observable<SpecializationResponse> callLevel2Api(String levelId1) {
        return apis.apiSpecialization2(levelId1);
    }
    Observable<SpecializationResponse> callLevel3Api(String levelId2) {
        return apis.apiSpecialization3(levelId2);
    }

    Observable<SpecializationResponse> callQualificationApi() {
        return apis.apiQualificationList();
    }

    String getLevel1Id() {
        return level1Id;
    }

    void setLevel1Id(String level1Id) {
        if (this.level1Id!=null && !this.level1Id.equals(level1Id)) {
            level2Id=null;
        }
        this.level1Id = level1Id;
    }

    String getLevel2Id() {
        return level2Id;
    }

    void setLevel2Id(String level2Id) {
        this.level2Id = level2Id;
    }

    Observable<Boolean> networkAvailable() {
        return NetworkUtils.networkAvailable(activity);
    }

    Observable<Boolean> isLevel1Selected() {
        return Observable.just(isLevel1Added());
    }

    Observable<Boolean> isLevel2Selected() {
        return Observable.just(isLevel2Added());
    }

    private boolean isLevel1Added() {
        return level1Id != null && level1Id.length() > 0;
    }
    private boolean isLevel2Added() {
        return level2Id != null && level2Id.length() > 0;
    }

    void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item==0)
                {
                    openCamera();
                    dialog.dismiss();
                }
                else if (item==1)
                {
                    openGallery();
                    dialog.dismiss();
                }
                else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openCamera() {
        Logger.getAnonymousLogger().info("Beginning of Take Photo");
        Intent callCameraApplicationIntent = new Intent();
        callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        // We give some instruction to the intent to save the image
        File photoFile = null;
        try {
            // If the createImageFile will be successful, the photo file will have the address of the file
            photoFile = ImageUtils.setUpPhotoFile();
            // Here we call the function that will try to catch the exception made by the throw function
        } catch (IOException e) {
            Logger.getAnonymousLogger().info("Exception error in generating the file");
            e.printStackTrace();
        }
        // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
        assert photoFile != null;
        Uri outputUri = FileProvider.getUriForFile(
                activity,
                activity.getPackageName(),
                photoFile);

        callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        // The following is a new line with a trying attempt
        callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Logger.getAnonymousLogger().info("Calling the camera App by intent");

        // The following strings calls the camera app and wait for his file in return.

        profileFile = photoFile;

        activity.startActivityForResult(callCameraApplicationIntent, ImageUtils.REQUEST_CAMERA);

    }

    private void openGallery() {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(i, REQUEST_GALLERY);
    }

    File getProfileImage(){
        return profileFile;
    }

    void cropCameraImage(){
        UCrop.of(Uri.fromFile(profileFile), Uri.fromFile(profileFile))
                .withAspectRatio(1, 1)
                .withMaxResultSize(800, 800)
                .start(activity);
    }

    void cropGalleryImage(Uri data){
        String[] projection = {MediaStore.MediaColumns.DATA};
        CursorLoader cursorLoader = new CursorLoader(activity, data, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);

        try {
            profileFile = ImageUtils.setUpPhotoFile();
            UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(profileFile))
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(800, 800)
                    .start(activity);
        } catch (IOException e) {
            Logger.getAnonymousLogger().info("Exception error in generating the file");
            e.printStackTrace();
        }

    }

    List<String> getDaysList() {
        return daysList;
    }

    List<String> getSelectedDaysList() {
        return selectedDaysList;
    }

    void setDays(DoctorAvailabilityModel doctorAvailability) {
        selectedDaysList.clear();
        if (doctorAvailability.getSunday()){
            selectedDaysList.add("Sunday");
        }
        if (doctorAvailability.getMonday()){
            selectedDaysList.add("Monday");
        }
        if (doctorAvailability.getTuesday()){
            selectedDaysList.add("Tuesday");
        }
        if (doctorAvailability.getWednesday()){
            selectedDaysList.add("Wednesday");
        }
        if (doctorAvailability.getThursday()){
            selectedDaysList.add("Thursday");
        }
        if (doctorAvailability.getFriday()){
            selectedDaysList.add("Friday");
        }
        if (doctorAvailability.getSaturday()){
            selectedDaysList.add("Saturday");
        }

    }

    void gotoHomeScreen(UserData userData, String msg) {
        Toasty.success(activity, msg, Toast.LENGTH_SHORT, true).show();
        prefs.save(SharedPrefsUtil.PREFERENCE_USER_DATA,userData);
        Intent intent= new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    void showGooglePlaces() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            activity.startActivityForResult(builder.build(activity), REQUEST_PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

}
