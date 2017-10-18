package com.example.jussi.puzzle;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;

/**
 * Created by jussi on 10/12/17.
 */

public class MyDuration extends Duration {
    @Override
    public int getSign() {
        return 0;
    }

    @Override
    public Number getField(DatatypeConstants.Field field) {
        return null;
    }

    @Override
    public boolean isSet(DatatypeConstants.Field field) {
        return false;
    }

    @Override
    public Duration add(Duration rhs) {
        return null;
    }

    @Override
    public void addTo(Calendar calendar) {

    }

    @Override
    public Duration multiply(BigDecimal factor) {
        return null;
    }

    @Override
    public Duration negate() {
        return null;
    }

    @Override
    public Duration normalizeWith(Calendar startTimeInstant) {
        return null;
    }

    @Override
    public int compare(Duration duration) {
        return 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
