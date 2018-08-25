/*
 
 */
package com.system.session;
  
import com.system.dao.RoleEntityDAO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sistemas
 */
@Service
@Transactional
public class AccessService {
        
    @Autowired
    private RoleEntityDAO rolePageAccessDAO;
 
    public Boolean checkAccess(Principal principal, String pageId) {
        return principal != null && pageAccess(principal, pageId);
    }

    private boolean pageAccess(Principal principal, String pageId) {
//        if (principal.getAccessAll()) {
//            return true;
//        } else {
 //           return principal.getPages().contains(pageId);
//        }
        return true;
    }
    
    public Boolean checkAuth(Principal principal, String pageId, String operation) {
//        Integer idPageAccess = pageAccessDAO.getIdByPageName(pageId);        
 //       Integer idRole = principal.getRoleId();
       
//        if (principal.getAccessAll()) {//implementar permisos  determinadas funcionalidades
//            return true;
//        } else {            
  //          return rolePageAccessDAO.getPermission(idRole, idPageAccess, operation);
//        }

    return true;
    }
}
