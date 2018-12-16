package com.softminesol.propertysurvey.survey.common.view.presenter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.pchmn.materialchips.ChipView;
import com.softminesol.propertysurvey.GlobalConfig;
import com.softminesol.propertysurvey.imageupload.domain.intractor.ImageUploadUseCase;
import com.softminesol.propertysurvey.imageupload.model.ImageUploadResponse;
import com.softminesol.propertysurvey.survey.common.domain.SurveyConstructionTypeUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyFloorListUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyGetCategoryUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyGetPropertyTypeUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyGetSubCategoryUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyMeasurementListUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyOwnerShipUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyRebateUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyUsageListUseCase;
import com.softminesol.propertysurvey.survey.common.model.ConstructionType;
import com.softminesol.propertysurvey.survey.common.model.FloorsList;
import com.softminesol.propertysurvey.survey.common.model.MeasurementUnitList;
import com.softminesol.propertysurvey.survey.common.model.OwnerShipList;
import com.softminesol.propertysurvey.survey.common.model.PropertyCategory;
import com.softminesol.propertysurvey.survey.common.model.PropertyCategoryList;
import com.softminesol.propertysurvey.survey.common.model.PropertySubCategoryList;
import com.softminesol.propertysurvey.survey.common.model.PropertyType;
import com.softminesol.propertysurvey.survey.common.model.PropertyTypes;
import com.softminesol.propertysurvey.survey.common.model.RebateList;
import com.softminesol.propertysurvey.survey.common.model.UsageList;
import com.softminesol.propertysurvey.survey.common.model.formData.FloorDetailsItem;
import com.softminesol.propertysurvey.survey.common.model.formData.OwnerDetailsItem;
import com.softminesol.propertysurvey.survey.common.view.activity.FloorInfoActivity;
import com.softminesol.propertysurvey.survey.common.view.activity.OwnerInfoActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import frameworks.basemvp.AppBasePresenter;
import frameworks.customadapter.CustomAdapterModel;
import frameworks.imageloader.view.ActivityPicChooser;
import frameworks.network.usecases.RequestParams;
import frameworks.utils.AdapterFactory;
import rx.Subscriber;

import static com.softminesol.propertysurvey.imageupload.domain.intractor.ImageUploadUseCase.IMAGE_PATH;
import static frameworks.imageloader.view.ActivityPicChooser.IMAGE_URI_REQUEST;
import static frameworks.imageloader.view.ActivityPicChooser.IMAGE_URI_RESULT;

/**
 * Created by sandeep on 6/5/18.
 */
public class ApartmentInfoPresenter extends AppBasePresenter<ApartmentInfoContract.View> implements ApartmentInfoContract.Presenter {

    private final AdapterFactory adapterFactory;


    @Inject
    public ApartmentInfoPresenter(AdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;

    }

    @Override
    public void attachView(ApartmentInfoContract.View view) {
        super.attachView(view);
        getView().setLicenceStatus(adapterFactory.getYesNoAdapter());
    }


    @Override
    public void onNextClick() {

    }

    @Override
    public void onAddOwnerClick() {
        getView().startActivityForResult(new Intent(getView().getContext(), OwnerInfoActivity.class), 1);

    }












}
