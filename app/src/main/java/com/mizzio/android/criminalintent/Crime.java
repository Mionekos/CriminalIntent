package com.mizzio.android.criminalintent;

import android.view.ViewGroup;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
        Random random = new Random();
        if (random.nextInt(2) == 0){
            mRequiresPolice = false;
        }
        else{
            mRequiresPolice = true;
        }
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
}
