package com.acuratechglobal.bulkbilling.utils.rx;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestRxSchedulers implements RxSchedulers {

    @Override
    public Scheduler androidUI() {
        return  Schedulers.trampoline();
    }

    @SuppressWarnings("PMD.ShortMethodName")
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler background() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler network() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }
}