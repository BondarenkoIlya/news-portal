package com.epam.ilya.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * {@inheritDoc}
 *
 * @author Ilya_Bondarenko
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    /**
     * {@inheritDoc}
     */
    public SecurityWebApplicationInitializer() {
        super(WebSecurityConfig.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        EnumSet<DispatcherType> securityDispatcherTypes = super.getSecurityDispatcherTypes();
        securityDispatcherTypes.remove(DispatcherType.ASYNC);
        securityDispatcherTypes.remove(DispatcherType.ERROR);
        securityDispatcherTypes.add(DispatcherType.FORWARD);
        return securityDispatcherTypes;
    }


}
