package com.mizzio.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Button mCallPolice;
    private static final  int REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null){
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

//        mAdapter = new CrimeAdapter(crimes);
//        mCrimeRecyclerView.setAdapter(mAdapter);
        //mCrimeRecyclerView.getAdapter().notifyItemMoved(0,5);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;

        public CrimeHolder(View itemView){
            //(LayoutInflater inflater, ViewGroup parent)
            //super(inflater.inflate(R.layout.list_item_crime,parent,false));
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            //mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            DateFormat dateFormat = new SimpleDateFormat();
            mDateTextView.setText(DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH).format(mCrime.getDate()));


            if (this.getItemViewType() == 1){
                mCallPolice = itemView.findViewById(R.id.police_button);
                mCallPolice.setOnClickListener(new View.OnClickListener() {
                    @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),R.string.call_police,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            //mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);

        }


        @Override
        public void onClick(View view){

            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivityForResult(intent, REQUEST_CODE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE){}
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            int layout = 0;

            switch (viewType){
                case 0:
                    layout = R.layout.list_item_crime;
                    break;
                case 1:
                    layout = R.layout.list_item_crime_police;
                    break;
            }

            View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
            return new CrimeHolder(view);
            //LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();

        }

        @Override
        public int getItemViewType(int position){
            boolean isPoliceNeeded = mCrimes.get(position).isPoliceRequired();
            if (isPoliceNeeded){
                return 1;
            }
            else{
                return  0;
            }
        }


    }
}
