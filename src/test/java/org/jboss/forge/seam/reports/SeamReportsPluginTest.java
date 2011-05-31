package org.jboss.forge.seam.reports;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.forge.project.services.FacetFactory;
import org.jboss.forge.test.AbstractShellTest;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SeamReportsPluginTest extends AbstractShellTest{
    
    @Inject
    private FacetFactory factory;

    @Test
    public void testSetup() {
        assertNotNull(factory);
    }

}
