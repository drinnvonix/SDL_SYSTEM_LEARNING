package com.example.sdl_system_learning.validator;

import com.google.i18n.phonenumbers.*;

public class PhoneValidator{

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean isValid(String isoCode, String number){

        try{
            Phonenumber.PhoneNumber parsed =
                    phoneUtil.parse(number, isoCode);

            return phoneUtil.isValidNumberForRegion(parsed, isoCode);

        } 
        catch (NumberParseException e){

            return false;
        }
    }
}