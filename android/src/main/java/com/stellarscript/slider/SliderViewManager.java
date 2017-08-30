package com.stellarscript.slider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

final class SliderViewManager extends SimpleViewManager<SliderView> {

    private static final String REACT_CLASS = "RCT" + SliderView.class.getSimpleName();
    private static final String REACT_REGISTRATION_NAME = "registrationName";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedViewConstants() {
        final Map<String, Object> constants = MapBuilder.newHashMap();

        constants.put("ON_SLIDING", SliderEvents.ON_SLIDING_EVENT);
        constants.put("ON_SLIDING_COMPLETE", SliderEvents.ON_SLIDING_COMPLETE_EVENT);

        return constants;
    }

    @Override
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        final Map<String, Object> events = MapBuilder.newHashMap();

        events.put(SliderEvents.ON_SLIDING_EVENT, MapBuilder.of(REACT_REGISTRATION_NAME, SliderEvents.ON_SLIDING_EVENT));
        events.put(SliderEvents.ON_SLIDING_COMPLETE_EVENT, MapBuilder.of(REACT_REGISTRATION_NAME, SliderEvents.ON_SLIDING_COMPLETE_EVENT));

        return events;
    }

    @Override
    protected SliderView createViewInstance(@NonNull final ThemedReactContext themedReactContext) {
        return new SliderView(themedReactContext);
    }

    @ReactProp(name = SliderProps.VALUE_PROP)
    public void setValue(@NonNull final SliderView sliderView, final double value) {
        final int newValue = (int) Math.min(value, Integer.MAX_VALUE);
        sliderView.setValue(newValue);
    }

    @ReactProp(name = SliderProps.MAX_VALUE_PROP)
    public void setMaxValue(@NonNull final SliderView sliderView, final double maxValue) {
        final int newMaxValue = (int) Math.min(maxValue, Integer.MAX_VALUE);
        sliderView.setMaxValue(newMaxValue);
    }

}
