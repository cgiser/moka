package com.cgiser.moka.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cgiser.keel.http.utils.HttpSpringUtils;
import com.cgiser.moka.action.AbstractAction;
import com.cgiser.moka.manager.ChipManager;
import com.cgiser.moka.model.Role;
import com.cgiser.moka.result.ReturnType;

public class ExchangeChipAction extends AbstractAction {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReturnType<Integer> returnType = new ReturnType<Integer>();
		try{
			Role role = super.getCurrentRole(request);
			if(null==role){
				returnType.setMsg("您还没登录哦！");
				returnType.setStatus(0);
				super.printReturnType2Response(response, returnType);
				return null;
			}
			ChipManager chipManager = (ChipManager)HttpSpringUtils.getBean("chipManager");
			returnType.setValue(chipManager.exChange(role.getRoleId()));
			returnType.setStatus(1);
			super.printReturnType2Response(response, returnType);
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		try{
			returnType.setValue(null);
			returnType.setStatus(0);
			super.printReturnType2Response(response, returnType);
		}catch (Exception e) {
			logger.error(e.getMessage(),e);// TODO: handle exception
		}
		return null;
		
	}
}
