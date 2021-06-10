package com.example.counterpartydirectory.validation.validator;

import com.example.counterpartydirectory.validation.InnValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InnValidator implements ConstraintValidator<InnValid, String> {
    private static final int[] WEIGHT_COEFFICIENTS_JURIDICAL_PERSON = new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private static final int[] WEIGHT_COEFFICIENTS_PHYSICAL_PERSON_BY_11 = new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
    private static final int[] WEIGHT_COEFFICIENTS_PHYSICAL_PERSON_BY_12 = new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};

    @Override
    public boolean isValid(String inn, ConstraintValidatorContext context) {
        if (inn != null) {
            if (inn.length() == 10) {
                return checkInnJuridicalPerson(inn);
            } else if (inn.length() == 12) {
                return checkInnPhysicalPerson(inn);
            }
        }
        return false;
    }

    private boolean checkInnJuridicalPerson(String inn) {
        int controlSum = 0;
        for (int i = 0; i < inn.length(); i++) {
            controlSum += Character.getNumericValue(inn.charAt(i)) * WEIGHT_COEFFICIENTS_JURIDICAL_PERSON[i];
        }
        int controlNumber = calculateControlNumber(controlSum);
        return controlNumber == Character.getNumericValue(inn.charAt(9));
    }

    private boolean checkInnPhysicalPerson(String inn) {
        int controlSum1 = 0;
        for (int i = 0; i < inn.length() - 1; i++) {
            controlSum1 += Character.getNumericValue(inn.charAt(i)) * WEIGHT_COEFFICIENTS_PHYSICAL_PERSON_BY_11[i];
        }
        int controlNumber1 = calculateControlNumber(controlSum1);
        int controlSum2 = 0;
        for (int i = 0; i < inn.length(); i++) {
            controlSum2 += Character.getNumericValue(inn.charAt(i)) * WEIGHT_COEFFICIENTS_PHYSICAL_PERSON_BY_12[i];
        }
        int controlNumber2 = calculateControlNumber(controlSum2);
        return controlNumber1 == Character.getNumericValue(inn.charAt(10)) &&
                controlNumber2 == Character.getNumericValue(inn.charAt(11));
    }

    private int calculateControlNumber(int controlSum) {
        int controlNumber = controlSum % 11;
        return controlNumber > 9 ? controlNumber % 10 : controlNumber;
    }
}