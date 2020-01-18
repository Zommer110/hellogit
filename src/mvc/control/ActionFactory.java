package mvc.control;

import mvc.action.Action;
import mvc.action.IndexAction;

public class ActionFactory {
	private static ActionFactory factory;
	private ActionFactory() {};
	public static synchronized ActionFactory getInstance() {
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("index")) {
			action = new IndexAction();
		}
		return action;
	}
}
