/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.drone.webdriver.factory;

import org.jboss.arquillian.drone.spi.Configurator;
import org.jboss.arquillian.drone.spi.Destructor;
import org.jboss.arquillian.drone.spi.Instantiator;
import org.jboss.arquillian.drone.webdriver.configuration.WebDriverConfiguration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory which combines {@link org.jboss.arquillian.drone.spi.Configurator},
 * {@link org.jboss.arquillian.drone.spi.Instantiator} and {@link org.jboss.arquillian.drone.spi.Destructor} for SafariDriver.
 *
 * @author <a href="jlocker@redhat.com>Jiri Locker</a>
 *
 */
public class SafariDriverFactory extends AbstractWebDriverFactory<SafariDriver> implements
        Configurator<SafariDriver, WebDriverConfiguration>, Instantiator<SafariDriver, WebDriverConfiguration>,
        Destructor<SafariDriver> {

    private static final String BROWSER_CAPABILITIES = new BrowserCapabilitiesList.Safari().getReadableName();

    @Override
    public int getPrecedence() {
        return 0;
    }

    @Override
    public void destroyInstance(SafariDriver instance) {
        instance.quit();
    }

    @Override
    public SafariDriver createInstance(WebDriverConfiguration configuration) {

        DesiredCapabilities capabilities = new DesiredCapabilities(configuration.getCapabilities());

        return SecurityActions.newInstance(configuration.getImplementationClass(), new Class<?>[] { Capabilities.class },
                new Object[] { capabilities }, SafariDriver.class);

    }

    @Override
    protected String getDriverReadableName() {
        return BROWSER_CAPABILITIES;
    }

}
