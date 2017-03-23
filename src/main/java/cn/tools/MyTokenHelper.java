package cn.tools;

import java.util.Map;
import org.apache.struts2.util.TokenHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class MyTokenHelper extends TokenHelper{
	private static final Logger LOG = LoggerFactory.getLogger(MyTokenHelper.class);

	@SuppressWarnings("rawtypes")
	public static boolean validToken() {
		String tokenName = getTokenName();
		if (tokenName == null) {
			if(LOG.isDebugEnabled())
				LOG.debug("no token name found -> Invalid token ");
			return false;
		}
		String token = getToken(tokenName);
		if(token == null) {
			if(LOG.isDebugEnabled())
				LOG.debug("no token found for token name "+tokenName+" -> Invalid token ");
				return false;
		}
		Map session = ActionContext.getContext().getSession();
		String sessionToken = (String) session.get(tokenName);
		//System.out.println("token==="+token);
		//System.out.println("sessionToken==="+sessionToken);
		if (!token.equals(sessionToken)) {
			LOG.warn(LocalizedTextUtil.findText(TokenHelper.class, "struts.internal.invalid.token", ActionContext.getContext().getLocale(), "Form token {0} does not match the session token {1}.", new Object[]{
			token, sessionToken
			}));

			return false;
		}
		// remove the token so it won't be used again
		session.remove(tokenName);
		return true;
	}
}
