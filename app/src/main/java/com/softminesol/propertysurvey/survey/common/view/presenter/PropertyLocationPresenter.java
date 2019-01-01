package com.softminesol.propertysurvey.survey.common.view.presenter;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.pchmn.materialchips.ChipView;
import com.softmine.imageupload.domain.ImageUploadUseCase;
import com.softmine.imageupload.view.ImageUploadActivity;
import com.softminesol.maps.MapsActivityCurrentPlace;
import com.softminesol.propertysurvey.CommonBaseUrl;
import com.softminesol.propertysurvey.survey.common.domain.SurveyAreaTypeUseCase;
import com.softminesol.propertysurvey.survey.common.domain.SurveyMeasurementListUseCase;
import com.softminesol.propertysurvey.survey.common.model.formData.FloorDetailsItem;
import com.softminesol.propertysurvey.survey.common.model.property.SavePropertyRequest;
import com.softminesol.propertysurvey.survey.common.view.activity.FloorInfoActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import frameworks.basemvp.AppBasePresenter;
import frameworks.network.usecases.RequestParams;
import frameworks.utils.AdapterFactory;

import static com.softmine.imageupload.view.ImageUploadActivity.FILE_PATHS;

/**
 * Created by sandeep on 6/5/18.
 */
public class PropertyLocationPresenter<T extends PropertyLocationContract.View> extends AppBasePresenter<T> implements PropertyLocationContract.Presenter<T> {
    protected final AdapterFactory adapterFactory;
    private final SurveyAreaTypeUseCase areaTypeUseCase;
    private final SurveyMeasurementListUseCase measurementListUseCase;
    protected List<FloorDetailsItem> floorDetailsItems = new ArrayList<>();

    @Inject
    public PropertyLocationPresenter(AdapterFactory adapterFactory, SurveyAreaTypeUseCase areaTypeUseCase, SurveyMeasurementListUseCase measurementListUseCase) {
        this.adapterFactory = adapterFactory;
        this.areaTypeUseCase = areaTypeUseCase;
        this.measurementListUseCase = measurementListUseCase;
    }

    @Override
    public void attachView(T view) {
        super.attachView(view);
        getView().setSewageConnectoion(adapterFactory.getYesNoAdapter());
        getView().setWaterConnection(adapterFactory.getYesNoAdapter());
        getView().setMsmo(adapterFactory.getYesNoAdapter());
        getView().setPropertyUsage(adapterFactory.getTypeOfPropertyUsage());
        getView().setTypeOfProperty(adapterFactory.getTypeOfPropertyAdapter());
        getView().setTypeOfNonesProperty(adapterFactory.getTypeOfNonResPropertyAdapter());
        getView().setRainWaterHarvesting(adapterFactory.getYesNoAdapter());
        getView().setLiftFacility(adapterFactory.getYesNoAdapter());
        getView().setPowerBackup(adapterFactory.getYesNoAdapter());
        getView().setParkingFacility(adapterFactory.getYesNoAdapter());
        getView().setFireFighting(adapterFactory.getYesNoAdapter());
        getView().setSourceOfWater(adapterFactory.getSourceOfWaterProperty());
        getView().setBuidlingStatus(adapterFactory.getBuildingStatus());
        getView().setRoadWidth(adapterFactory.getRoadWidth());

    }

    @Override
    public void onSubmitClick() {

    }

    @Override
    public void onAddressClick() {
        getView().startActivityForResult(MapsActivityCurrentPlace.getInstance(getView().getContext(),GoogleMap.MAP_TYPE_SATELLITE),2);
    }

    public boolean validateForm() {
        return true;
    }


    public SavePropertyRequest getPropertyData() {

        SavePropertyRequest savePropertyRequest=new SavePropertyRequest();
        savePropertyRequest.setMapId(getView().getMapId());
        savePropertyRequest.setParcelId(getView().getParcelId());
        savePropertyRequest.setPropertyType(getView().getPropertyType());
        savePropertyRequest.setPropertyUsage(getView().getPropertyUsage());
        savePropertyRequest.setBuildingName(getView().getBuildingName());
        savePropertyRequest.setStreet(getView().getStreetName());
        savePropertyRequest.setColony(getView().getColonyName());
        savePropertyRequest.setPincode(getView().getPinCode());
        savePropertyRequest.setWardNo(getView().getWardNo());
        savePropertyRequest.setZoneId(getView().getZoneId());
        savePropertyRequest.setRainHarvestingSystem(getView().getRainWaterHarvesting());
        savePropertyRequest.setBuildingStatus(getView().getBuildingStatus());
        savePropertyRequest.setPlotArea(getView().getPropetyArea());
        savePropertyRequest.setLiftFacility(getView().getLiftFacility());
        savePropertyRequest.setParkingFacility(getView().getParkingFacility());
        savePropertyRequest.setAgeOfProperty(getView().getAgeOfBuilding());
        savePropertyRequest.setTotalFloor(getView().getfloorCount());
        savePropertyRequest.setFireFighting(getView().getFireFighting());
        savePropertyRequest.setRoadWidth(getView().getRoadWidth());
        if(location != null) {
            savePropertyRequest.setLattitude(location.getLatitude()+"");
            savePropertyRequest.setLongitude(location.getLongitude()+"");
        }
        savePropertyRequest.setImagesList(fileUrls);
        return savePropertyRequest;
    }

    Location location;
    //TODO change requestcode to static constant
    @Override
    public void onAddFloorCLicked() {
        getView().startActivityForResult(new Intent(getView().getContext(), FloorInfoActivity.class), 1);
    }

    @Override
    public void onUploadImageClick() {
        String url = CommonBaseUrl.BASE_URL+"property/uploadPropertImage";
        String param_name = "propertyImage";

        getView().startActivityForResult(ImageUploadActivity.getIntent(getView().getContext(),url,param_name),ImageUploadActivity.REQUEST_GET_FILE_SERVER_URI);
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 ) {
            if(resultCode == 1) {
                if (clickedFloorDetailsItem != null) {
                    floorDetailsItems.remove(clickedFloorDetailsItem);
                }
                FloorDetailsItem formDetailsItem = (FloorDetailsItem) data.getSerializableExtra("floorDetails");
                floorDetailsItems.add(formDetailsItem);
            }
            // IF user just cancel then null clickeditem clickedFloorDetailsItem added new floor it always be null
            getView().clearChips();
            for(FloorDetailsItem floorDetailsItem: floorDetailsItems) {
                addChip(floorDetailsItem);
            }
            clickedFloorDetailsItem = null;
        }else if(requestCode == 2) {
            if(resultCode == 2) {
                  location = (Location) data.getParcelableExtra("Location");
                  if(location != null) {
                      getView().setLatLng(location.getLatitude()+","+location.getLongitude());
                  }
            }
        }else if (requestCode == ImageUploadActivity.REQUEST_GET_FILE_SERVER_URI) {
            if(resultCode == Activity.RESULT_OK) {
               fileUrls = data.getStringArrayListExtra(FILE_PATHS);

            }
        }
        return true;
    }


    ArrayList<String> fileUrls ;

    FloorDetailsItem clickedFloorDetailsItem;
    protected void addChip(final FloorDetailsItem formDetailsItem) {
        final ChipView chipView = new ChipView(getView().getContext());
        chipView.setDeletable(true);
        chipView.setLabel(formDetailsItem.getFloorType());
        getView().addChipView(chipView);
        getView().setFloorCount(floorDetailsItems.size() + "");
        chipView.setOnDeleteClicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floorDetailsItems.remove(formDetailsItem);
                getView().removeChip(chipView);
            }
        });
        chipView.setOnChipClicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedFloorDetailsItem = formDetailsItem;
                getView().startActivityForResult(FloorInfoActivity.getFloorInfoIntet(getView().getContext(),formDetailsItem),1);

            }
        });
    }
}
