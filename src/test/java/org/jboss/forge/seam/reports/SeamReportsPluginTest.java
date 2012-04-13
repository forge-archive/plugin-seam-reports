package org.jboss.forge.seam.reports;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.forge.project.Project;
import org.jboss.forge.project.dependencies.DependencyBuilder;
import org.jboss.forge.project.facets.DependencyFacet;
import org.jboss.forge.test.AbstractShellTest;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;

/**
 * {@link SeamReportsPlugin} test case
 * 
 * @author George Gastaldi (gastaldi)
 */
public class SeamReportsPluginTest extends AbstractShellTest
{

   @Test
   public void testNoParameters() throws Exception
   {
      Project project = initializeJavaProject();
      // Execute SUT
      getShell().execute("seam-reports");
      DependencyFacet facet = project.getFacet(DependencyFacet.class);
      boolean actual = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-api"));
      assertFalse("Seam Reports dependency missing", actual);
   }

   @Test
   public void testSetupAPI() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("");
      // Execute SUT
      getShell().execute("seam-reports setup");
      DependencyFacet facet = project.getFacet(DependencyFacet.class);
      boolean actual = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-api"));
      assertTrue("Seam Reports dependency missing", actual);
   }

   @Test
   public void testSetupJasperReports() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("");
      // Execute SUT
      getShell().execute("seam-reports setup --provider JASPER");
      DependencyFacet facet = project.getFacet(DependencyFacet.class);
      boolean api = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-api"));
      assertTrue("Seam Reports API dependency missing", api);
      boolean actual = facet.hasDirectDependency(DependencyBuilder
               .create("org.jboss.seam.reports:seam-reports-jasper"));
      assertTrue("Seam Reports dependency missing", actual);
   }

   @Test
   public void testSetupPentahoReporting() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("");
      // Execute SUT
      getShell().execute("seam-reports setup --provider PENTAHO");
      DependencyFacet facet = project.getFacet(DependencyFacet.class);
      boolean api = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-api"));
      assertTrue("Seam Reports API dependency missing", api);
      boolean actual = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-pentaho"));
      assertTrue("Seam Reports dependency missing", actual);
   }

   @Test
   public void testSetupMVEL() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("");
      // Execute SUT
      getShell().execute("seam-reports setup --provider MVEL");
      DependencyFacet facet = project.getFacet(DependencyFacet.class);
      boolean api = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-api"));
      assertTrue("Seam Reports API dependency missing", api);
      boolean actual = facet.hasDirectDependency(DependencyBuilder.create("org.jboss.seam.reports:seam-reports-mvel"));
      assertTrue("Seam Reports dependency missing", actual);
   }

   @Deployment
   public static JavaArchive getDeployment()
   {
      return AbstractShellTest.getDeployment().addPackages(true, SeamReportsPlugin.class.getPackage());
   }

}
