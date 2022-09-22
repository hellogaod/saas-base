import loginController from './sys-login-controller.controller';
import sysValidateController from './sys-validate-controller.controller';
import enterpriseController from './sys-enterprise-controller.controller';
import moduleController from './sys-module-controller.controller';
import otherController from './sys-other-controller.controller';
import moduleDetailController from './sys-module-detail-controller.controller';
export var managerService = {
    sysValidateController: sysValidateController,
    loginController: loginController,
    enterpriseController: enterpriseController,
    moduleController: moduleController,
    otherController: otherController,
    moduleDetailController: moduleDetailController
};
