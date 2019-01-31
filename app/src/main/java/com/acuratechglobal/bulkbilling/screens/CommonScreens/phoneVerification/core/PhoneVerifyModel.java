package com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.core;

import android.content.Intent;

import com.acuratechglobal.bulkbilling.api.Api;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.phoneVerification.PhoneVerifyActivity;
import com.acuratechglobal.bulkbilling.screens.CommonScreens.setPassword.SetPassActivity;

import io.reactivex.Observable;

public class PhoneVerifyModel {
//    private static final String DATE_PICKER_TAG = "datePickerTag";

    private final PhoneVerifyActivity activity;
    private final Api apis;
    private final String TAG= "OTP Verification :";
    private String code= null;

    public PhoneVerifyModel(PhoneVerifyActivity activity, Api api) {
        this.activity = activity;
        this.apis = api;
    }

    void finish() {
        this.activity.finish();
    }

    String getString(int stringResourceId) {
        return activity.getString(stringResourceId);
    }

    void gotoSetPassword(int userType){
        Intent intent= new Intent(activity, SetPassActivity.class);
        intent.putExtra("userType",userType);
        activity.startActivity(intent);
    }

//    Observable<PhoneVerificationDataModel>  sendCode(){
//
//        return Observable.create(new ObservableOnSubscribe<PhoneVerificationDataModel>() {
//            @Override
//            public void subscribe(ObservableEmitter<PhoneVerificationDataModel> emitter) throws Exception {
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(activity.getPhoneNumber(),
//                        60,
//                        TimeUnit.SECONDS,
//                        activity,
//                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                            @Override
//                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                                PhoneVerificationDataModel model= new PhoneVerificationDataModel();
//                                model.setStatus(1);
//                                model.setMessage("Phone number verified successfully");
//                                code= phoneAuthCredential.getSmsCode();
//                                emitter.onNext(model);
//                            }
//
//                            @Override
//                            public void onVerificationFailed(FirebaseException e) {
//                                PhoneVerificationDataModel model= new PhoneVerificationDataModel();
//                                model.setStatus(3);
//                                model.setMessage(e.getMessage());
//                                emitter.onNext(model);
//                            }
//
//                            @Override
//                            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
////                        super.onCodeSent(s, forceResendingToken);
//                                PhoneVerificationDataModel model= new PhoneVerificationDataModel();
//                                model.setStatus(2);
//                                model.setMessage(s);
//                                code= s;
//                                emitter.onNext(model);
//                                Log.d(TAG, "onCodeSent successfully: "+ s);
//                            }
//                        });
//            }
//        });
////        int isVerified=0;
//    }

    Observable<Boolean> isVerified(String code) {
        return Observable.just(isCodeVerified(code));
    }


    private Boolean isCodeVerified(String code){
        if (code.equals("1234")){
            return true;
        }else return false;
    }

}
