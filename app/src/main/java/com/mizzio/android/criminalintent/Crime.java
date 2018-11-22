package com.mizzio.android.criminalintent;

import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;
    private String mSuspect;

    public Crime(){
        this(UUID.randomUUID());
//        Random random = new Random();
//        if (random.nextInt(2) == 0){
//            mRequiresPolice = false;
//        }
//        else{
//            mRequiresPolice = true;
//        }
    }
    public Crime (UUID id){
        mId = id;
        mDate = new Date();
    }

    public boolean isPoliceRequired() {
        return mRequiresPolice;
    }

    public void setPoliceRequired(boolean policeRequired) {
        this.mRequiresPolice = policeRequired;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        this.mSuspect = suspect;
    }
}
