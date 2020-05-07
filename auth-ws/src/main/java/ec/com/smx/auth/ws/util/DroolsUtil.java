/**
 * 
 */
package ec.com.smx.auth.ws.util;

import java.io.File;

import org.drools.core.SessionConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.io.ResourceFactory;

import ec.com.smx.auth.client.commons.CFMXMessage;

/**
 * @author gnolivos
 *
 */
public final class DroolsUtil {
	
	/**
	 * constructor
	 */
	private DroolsUtil() {}
	
	/**
	 * Ejecuta la reglas de drools
	 * 
	 * @param caseObject
	 * @param ruleName
	 */
	public static void applyRules(Object caseObject, String ruleName) {
		if (!ruleName.isEmpty()) {
			KieServices kieServices = KieServices.Factory.get();
			KieFileSystem kfs = kieServices.newKieFileSystem();
			kfs.write(ResourceFactory.newFileResource(new File(CFMXMessage.getDroolsPath().concat(ruleName))));
			KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
			kieBuilder.buildAll();
			KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
			KieSessionConfiguration conf = SessionConfiguration.getDefaultInstance();
			KieSession ksession = kieContainer.newKieSession(conf);
			ksession.insert(caseObject);
			ksession.fireAllRules();
			ksession.dispose();
		}
	}

}
