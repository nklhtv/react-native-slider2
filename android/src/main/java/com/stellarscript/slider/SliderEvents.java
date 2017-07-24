package com.stellarscript.slider;

import android.support.annotation.NonNull;

final class SliderEvents {

    private static final String EVENT_NAME_PREFIX = SliderView.class.getSimpleName();

    static final String ON_SLIDING_EVENT = getFullEventName("onSliding");
    static final String ON_SLIDING_COMPLETE_EVENT = getFullEventName("onSlidingComplete");

    static final String ON_SLIDING_VALUE_PROP = "value";
    static final String ON_SLIDING_COMPLETE_VALUE_PROP = "value";

    private static String getFullEventName(@NonNull final String eventName) {
        return EVENT_NAME_PREFIX.concat(eventName);
    }

}
