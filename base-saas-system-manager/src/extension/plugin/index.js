// import cityPlugin from './city.plugin'
import dictPlugin from './dict.plugin';
import filterPlugin from './filter.plugin';
import helperPlugin from './helper.plugin';
import validatorPlugin from './validator.plugin';
import reminderPlugin from './reminder.plugin';
// import coordinatePlugin from './coordinate.plugin'
export default (function (_a) {
    var store = _a.store;
    return ({
        // cityPlugin,
        dictPlugin: dictPlugin,
        filterPlugin: filterPlugin,
        helperPlugin: helperPlugin,
        validatorPlugin: validatorPlugin,
        reminderPlugin: reminderPlugin,
    });
});
