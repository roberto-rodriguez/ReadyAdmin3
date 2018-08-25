/*

 */
package com.system.session;

import com.system.dao.UserAppDAO;
import com.system.dao.UserDAO;
import com.system.dto.UserAppDTO;
import com.system.dto.UserDTO;
import com.system.dto.request.Hash;
import com.system.dto.response.WebResponseData;
import com.system.util.DateUtil;
import com.system.util.ObjectHasher;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.RequestContextHolder;

/**
 *
 * @author Alejo
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private Principal principal;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserAppDAO userAppDAO;

    public WebResponseData login(HttpSession session, Hash data, boolean loadApps) throws Exception {
        UserDTO user = userDAO.login(data);

        if (user == null) {
            return null;
        }

        if (!user.getActive()) {
            throw new RuntimeException("Your user is disabled, consult the Administrator");
        }

        Hash result = ObjectHasher.toHash(user);

        if (loadApps) {
            List<UserAppDTO> apps = userAppDAO.getAppsByUser(user.getId());

            result.put("apps", apps);
        } else {//If not, then is re-login
            Date lastLogin = user.getLastLogin();

            System.out.println("lastLogin = " + lastLogin);

            if (!DateUtil.wasTokenWithinAMinute(lastLogin)) {
                return WebResponseData.toLoginFail();
            }
        }
         
        principal.init(user);

        if (result != null) {
            System.out.println("principal.getToken() = " + result.getString("token"));
            session.setAttribute(Principal.TOKEN, principal.getToken());
            return new WebResponseData(result);
        }
        return WebResponseData.toLoginFail();
    }

}
