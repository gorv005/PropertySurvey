package com.softminesol.propertysurvey.survey.newPropertyEntry.di;

import com.softminesol.propertysurvey.survey.apartmentEntry.view.fragment.NewApartmentFragment;
import com.softminesol.propertysurvey.survey.draftedEntity.view.DraftedListActivity;
import com.softminesol.propertysurvey.survey.newPropertyEntry.view.fragment.NewPropertyInfoFragment;
import com.softminesol.survey_framework.survey.common.di.SurveyFormScope;
import com.softminesol.survey_framework.survey.common.di.SurveyModule;

import dagger.Component;
import frameworks.di.component.BaseAppComponent;

/**
 * Created by sandeep on 13/5/18.
 */
@SurveyFormScope
@Component(modules = {NewSurveyModule.class, SurveyModule.class}, dependencies = BaseAppComponent.class)
public interface NewSurveyComponent {
    void inject(NewPropertyInfoFragment newPropertyInfoFragment);
    void inject(DraftedListActivity draftedListActivity);
    void inject(NewApartmentFragment newApartmentFragment);
}