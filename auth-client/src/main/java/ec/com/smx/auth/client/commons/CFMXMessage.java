/**
 * 
 */
package ec.com.smx.auth.client.commons;

import ec.com.kruger.utilitario.loggin.resources.MessageResolver;

/**
 * @author gnolivos
 *
 */
public final class CFMXMessage {
	
	/**
	 * instance
	 */
	private static final MessageResolver MESSAGE_RESOLVER  = new MessageResolver("ec.com.smx.auth.client.resources.application");
	
	 /**
     * Private constructor.
     */
	private CFMXMessage() {
		super();
	}
	
	/**
	 * Company id
	 * 
	 * @return 1
	 */
	public static Integer getCompanyId() {
		return MESSAGE_RESOLVER.getInteger("ec.com.smx.auth.ws.company.code");
	}
	
	/**
	 * Event code
	 * 
	 * @return CFMXTMPW
	 */
	public static String getEventCode() {
		return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.event.code");
	}
	
	/**
	 * no-answer@favorita.com
	 *
	 * @return
	 */
	public static String getEmailRecoverPwd() {
		return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.email.recover.password");
	}
	
	/**
	 * obtiene el path de los archivos drools
	 * 
	 * @return
	 */
	public static String getDroolsPath() {
    	return MESSAGE_RESOLVER.getString("ec.com.smx.auth.path.drools");
    }
	
	/**
	 * infoServer/TypeServer.drl
	 * 
	 * @return
	 */
	public static String getPathInfoTypeServer() {
		return MESSAGE_RESOLVER.getString("ec.com.smx.auth.rules.validate.type.server");
	}
	
	/**
	 * inventoriesWs/local
	 * 
	 * @return
	 */
    public static String getLocalPath() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.locals");
    }
    
    /**
     * inventoriesWs/local/getTypeLocals
     * 
     * @return
     */
    public static String getPathTypeLocal() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.type.locals");
    }
    
    /**
     * datosCorporativo/local/findLocalActYPublic
     * 
     * @return
     */
    public static String getPathLocalsEstablishment() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.locals.establishment");
    }
    
    /**
	 * datosCorporativo/areaTrabajo/findDataLocalByWorkArea
	 * 
	 * @return
	 */
    public static String getLocalController() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.local.controller");
    }
    
    /**
	 * datosCorporativo/adminFavoritos/addFavoriteOptions
	 * 
	 * @return
	 */
    public static String getPathAddFavorite() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.add.favorite");
    }
    
    /**
	 * datosCorporativo/adminFavoritos/deleteFavoriteOptions
	 * 
	 * @return
	 */
    public static String getPathRemoveFavorite() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.remove.favorite");
    }
    
    /**
     * datosCorporativo/funcionario/findFunctionary
     * 
     * @return
     */
    public static String getFuncionary() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.funcionary");
    }
    
    /**
     * datosCorporativo/funcionario/findFunctionaryByUser
     * 
     * @return
     */
    public static String getFuncionaryByUser() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.funcionary.by.user");
    }
      
    /**
     * datosCorporativo/login/findLoginUserByRoleTypeId
     * 
     * @return
     */
    public static String getLogin() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.login");
    }
    
    /** getProfile
 	 * 
 	 * @return
 	 */
    public static String getProfile() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.profile");
    }
    
    /** getSystemId
 	 * 
 	 * @return
 	 */
    public static String getSystemId() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.system.id");
    }
    
    /** getTypeProfile
     * retorna el id del perfil que utiliza el sistema
 	 * 
 	 * @return
 	 */
    public static String getTypeProfile() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.profile");
    }
    
    /**
     * datosCorporativo/security/obtenerPreguntasSecretasUsuario
     * 
     * @return
     */
    public static String getSecretQuestions() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.secret.questions");
    }
    
    /**
     * datosCorporativo/security/updateUserPwd
     * 
     * @return
     */
    public static String getUpdateUserPwd() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.user.password");
    }
    
    /**
     * mensajeriaWs/service/findEventoDtoById
     * 
     * @return
     */
    public static String getEventById() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.find.event.by.id");
    }
    
    /**
     * mensajeriaWs/service/sendEmail
     * 
     * @return
     */
    public static String getPathSendEmail() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.send.email");
    }
    
    /**
     * /datosCorporativo/menu/obtenerTokenPorUsuario.json
     * 
     * @return
     */
    public static String getCorpToken() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.get.token");
    }
    
    /**
     * datosCorporativo/security/changePassword
     *
     * @return
     */
    public static String getPathChangePassword() {
        return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.change.password");
    }
    
    /**
     * datosCorporativo/funcionario/findFuncionarioPorCodigoUsuario
     * 
     * @return
     */
    public static String getPathLocalDefault() {
    	return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.local.default");
    }
    
    /**
     * datosCorporativo/funcionario/findFuncionarioPorCodigoUsuario
     * 
     * @return
     */
    public static String getUpdateDevice() {
    	return MESSAGE_RESOLVER.getString("ec.com.smx.auth.ws.path.web.service.corporativo.device.update");
    }
}
