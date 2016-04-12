package com.fan.jfinal.validator;

import com.fan.jfinal.base.BaseController;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ShortCircuitValidator extends Validator{

	public ShortCircuitValidator () {
		setShortCircuit(true);
	}
	protected void validate(Controller c) {
		
	}

	protected void handleError(Controller c) {
		BaseController controller = (BaseController)c;
		controller.renderMsg(1, controller.getAttrForStr("message"));
	}
}
