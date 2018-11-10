package com.mizzio.android.criminalintent;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Button mCallPolice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
        //mCrimeRecyclerView.getAdapter().notifyItemMoved(0,5);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolder(View itemView){
            //(LayoutInflater inflater, ViewGroup parent)
            //super(inflater.inflate(R.layout.list_item_crime,parent,false));
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
        }

        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            if (this.getItemViewType() == 1){
                mCallPolice = itemView.findViewById(R.id.police_button);
                mCallPolice.setOnClickListener(new View.OnClickListener() {
                    @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),R.string.call_police,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        }


        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(),mCrime.getTitle()+"clicked!", Toast.LENGTH_SHORT).show();
        }

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
        public int getItemViewType(int positiion){
            boolean isPoliceNeeded = mCrimes.get(positiion).isPoliceRequired();
            if (isPoliceNeeded){
                return 1;
            }
            else{
                return  0;
            }
        }


    }
}
