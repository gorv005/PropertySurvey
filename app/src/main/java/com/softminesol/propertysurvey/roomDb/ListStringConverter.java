package com.softminesol.propertysurvey.roomDb;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.reflect.TypeToken;
import com.softminesol.propertysurvey.survey.common.model.apartment.Owner;

import java.util.List;

import frameworks.utils.GsonFactory;

public class ListStringConverter {
    @TypeConverter
    public static String toString(List<String> strings) {
        return GsonFactory.getGson().toJson(strings);
    }

    @TypeConverter
    public static List<String> toOwnerList(String value) {
        return GsonFactory.getGson().fromJson(value,new TypeToken<List<String>>(){}.getType());
    }
}
