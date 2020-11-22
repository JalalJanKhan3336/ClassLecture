package com.thesoftparrot.classlecture.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Long> counterLiveData;

    public CounterViewModel() {
        counterLiveData = new MutableLiveData<>();
    }

    public LiveData<Long> getCounterLiveData() {
        return counterLiveData;
    }

    public void updateCounter(Long counter){
        counterLiveData.postValue(counter);
    }
}
