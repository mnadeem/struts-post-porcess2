package com.test.struts.chain.commands;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.chain.contexts.ServletActionContext;

public class PostProcess implements Command {

	public boolean execute(Context context) throws Exception {

		postProcess( ((ServletActionContext) context).getRequest());

		return false;
	}

	private void postProcess(final HttpServletRequest request) {
		ActionMessages errors = (ActionMessages) request.getAttribute(Globals.ERROR_KEY);
		if ((errors != null) && !errors.isEmpty()) {
			logErrors(errors);
		}
	}

	private void logErrors(final ActionMessages errors) {
		@SuppressWarnings("unchecked")
		Iterator<ActionMessage> iterator = errors.get();
		while (iterator.hasNext()) {
			ActionMessage message = iterator.next();
			System.out.println("********* " + message);
		}
	}

}
