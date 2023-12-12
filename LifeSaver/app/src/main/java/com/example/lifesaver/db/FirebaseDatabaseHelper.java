package com.example.lifesaver.db;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.lifesaver.bo.OnlineSupportBo;
import com.example.lifesaver.bo.UrgentSupportBo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceResources;
    private List<UrgentSupportBo> urgentResources =new ArrayList<>();
    private List<OnlineSupportBo> onlineResources =new ArrayList<>();

    Context context;


    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public interface UrgentResourcesReadCallback {
        void onDataRead(List<UrgentSupportBo> supportList,List<String> keys);
    }
    public interface OnlineResourcesReadCallback {
        void onDataRead(List<OnlineSupportBo> supportList, List<String> keys);
    }

    public void readUrgentSupport(final UrgentResourcesReadCallback callback){
        mReferenceResources = mDatabase.getReference().child("urgent_support");
        mReferenceResources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urgentResources.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    UrgentSupportBo resource = keyNode.getValue(UrgentSupportBo.class);
                    urgentResources.add(resource);
                    System.out.println(resource);
                }
                callback.onDataRead(urgentResources,keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readOnlineSupport(final OnlineResourcesReadCallback callback){
        mReferenceResources = mDatabase.getReference().child("online_support");
        mReferenceResources.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlineResources.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    OnlineSupportBo resource = keyNode.getValue(OnlineSupportBo.class);
                    onlineResources.add(resource);
                    System.out.println(resource);
                }
                callback.onDataRead(onlineResources,keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void updateUrgentBookedStatus(String rcsId, int value) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("urgent_support").child(rcsId);
        reference.child("booked").setValue(value); // Update the "booked" attribute to 1
       // Update the "booked" attribute to 1
    }
    public void updateOnlineBookedStatus(String rcsId, int value) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("online_support").child(rcsId);
        reference.child("booked").setValue(value); // Update the "booked" attribute to 1
        // Update the "booked" attribute to 1
    }
}
