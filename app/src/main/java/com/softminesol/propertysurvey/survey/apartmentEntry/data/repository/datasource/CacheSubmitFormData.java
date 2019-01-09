package com.softminesol.propertysurvey.survey.apartmentEntry.data.repository.datasource;

import android.content.Context;

import com.softminesol.propertysurvey.roomDb.PropertySurveyDB;
import com.softminesol.propertysurvey.survey.common.model.apartment.SaveApartmentRequest;
import com.softminesol.propertysurvey.survey.common.model.property.GetPropertySaveResponse;
import com.softminesol.propertysurvey.survey.common.model.property.SavePropertyRequest;

import javax.inject.Inject;

import frameworks.di.qualifier.ApplicationContext;
import rx.Observable;

public class CacheSubmitFormData {
    private final PropertySurveyDB propertySurveyDB;

    @Inject
    public CacheSubmitFormData(@ApplicationContext Context context) {
        this.propertySurveyDB = PropertySurveyDB.getInstance(context);
    }


    public Observable<GetPropertySaveResponse> submitFormData(SaveApartmentRequest formData) {
        propertySurveyDB.getApartmentDao().insert(formData);
        return Observable.just(new GetPropertySaveResponse());
    }
}