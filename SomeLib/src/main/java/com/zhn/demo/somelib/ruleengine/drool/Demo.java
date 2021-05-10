package com.zhn.demo.somelib.ruleengine.drool;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

public class Demo {

    public static void main(String[] args) {
        KieServices services = KieServices.Factory.get();
        KieSession session = services.getKieClasspathContainer().newKieSession();
        session.insert(null);
        session.fireAllRules();
        session.dispose();
    }

}
