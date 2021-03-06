package com.softminesol.propertysurvey.survey.apartmentEntry.data.net;

import com.softminesol.propertysurvey.survey.common.model.apartment.SaveApartmentRequest;
import com.softminesol.propertysurvey.survey.common.model.property.GetPropertySaveResponse;

import frameworks.network.model.DataResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by sandeep on 13/5/18.
 */
public interface NewServeyAPI {

    @POST(NewServeyURL.saveApartmentAPI)
    Observable<Response<DataResponse<GetPropertySaveResponse>>> submitNewApartment(@Body SaveApartmentRequest formData);
}
