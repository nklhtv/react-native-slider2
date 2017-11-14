import React, { Component, PropTypes } from 'react';
import { View, UIManager, requireNativeComponent } from 'react-native';

const RCTSliderViewConstants = UIManager.RCTSliderView.Constants;

class Slider extends Component {
    constructor(props) {
        super(props);

        this.callbacks = {
            [RCTSliderViewConstants.ON_SLIDING]: this._invokeEventCallback.bind(this, 'onSliding'),
            [RCTSliderViewConstants.ON_SLIDING_COMPLETE]: this._invokeEventCallback.bind(this, 'onSlidingComplete')
        };
    }

    shouldComponentUpdate(nextProps, nextState) {
        return nextProps.style !== this.props.style ||
            nextProps.value !== this.props.value ||
            nextProps.maxValue !== this.props.maxValue;
    }

    render() {
        return (
            <RCTSliderView
                style={this.props.style}
                value={this.props.value}
                maxValue={this.props.maxValue}
                {...this.callbacks}
            />
        );
    }

    _invokeEventCallback(eventName, event) {
        if (typeof this.props[eventName] === 'function') {
            this.props[eventName](event.nativeEvent);
        }
    }

}

Slider.propTypes = {
    ...View.propTypes,
    value: PropTypes.number.isRequired,
    maxValue: PropTypes.number.isRequired,
    [RCTSliderViewConstants.ON_SLIDING]: PropTypes.func,
    [RCTSliderViewConstants.ON_SLIDING_COMPLETE]: PropTypes.func
};

Slider.defaultProps = {
    value: 0,
    maxValue: 100
};

const RCTSliderViewInterface = {
    name: 'RCTSliderView',
    propTypes: {
        ...View.propTypes,
        value: PropTypes.number.isRequired,
        maxValue: PropTypes.number.isRequired,
        [RCTSliderViewConstants.ON_SLIDING]: PropTypes.func,
        [RCTSliderViewConstants.ON_SLIDING_COMPLETE]: PropTypes.func
    }
};

const RCTSliderView = requireNativeComponent('RCTSliderView', RCTSliderViewInterface, {
    nativeOnly: {
        value: true,
        maxValue: true
    }
});

export default Slider;
