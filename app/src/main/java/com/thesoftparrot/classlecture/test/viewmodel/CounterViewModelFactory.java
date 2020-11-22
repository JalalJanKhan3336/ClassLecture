package com.thesoftparrot.classlecture.test.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CounterViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    public CounterViewModelFactory() {}

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CounterViewModel();
    }
}
