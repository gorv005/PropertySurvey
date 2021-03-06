package com.softminesol.propertysurvey.survey.newPropertyEntry.view.presenter;

import com.softminesol.propertysurvey.survey.common.view.presenter.PropertyLocationContract;

import java.util.List;

import frameworks.customadapter.CustomAdapterModel;

/**
 * Created by sandeep on 13/5/18.
 */
public interface NewPropertyInforFragmentContract {
    public interface View extends PropertyLocationContract.View {
        void showMessage(String message);

    }

    public interface Presenter extends PropertyLocationContract.Presenter<View> {

    }
}
