package org.jboss.forge.seam.reports;

import java.util.List;

import javax.inject.Inject;

import org.codehaus.plexus.util.StringUtils;
import org.jboss.forge.project.Project;
import org.jboss.forge.project.dependencies.Dependency;
import org.jboss.forge.project.dependencies.DependencyBuilder;
import org.jboss.forge.project.facets.DependencyFacet;
import org.jboss.forge.shell.ShellPrompt;
import org.jboss.forge.shell.plugins.Alias;
import org.jboss.forge.shell.plugins.Command;
import org.jboss.forge.shell.plugins.DefaultCommand;
import org.jboss.forge.shell.plugins.Option;
import org.jboss.forge.shell.plugins.PipeOut;
import org.jboss.forge.shell.plugins.Plugin;
import org.jboss.forge.shell.plugins.RequiresFacet;

/**
 * Seam Reports Forge Plugin
 * 
 * @author George Gastaldi (gastaldi)
 * 
 */
@Alias("seam-reports")
@RequiresFacet({ DependencyFacet.class })
public class SeamReportsPlugin implements Plugin
{

   @Inject
   Project project;
   @Inject
   ShellPrompt prompt;

   @DefaultCommand
   public void help(PipeOut out)
   {
      out.println("The following parameters are available:");
      out.println(" setup --provider [BIRT,JASPERREPORTS,PENTAHO]");
   }

   @Command(value = "setup")
   public void setup(@Option(name = "provider", completer = ProvidersTokenCompleter.class) String provider)
   {
      DependencyFacet dependencyFacet = project.getFacet(DependencyFacet.class);
      DependencyBuilder seamReportsDependency = DependencyBuilder.create().setGroupId("org.jboss.seam.reports")
                .setArtifactId("seam-reports-api");

      if (!dependencyFacet.hasDependency(seamReportsDependency))
      {
         if (!dependencyFacet.hasRepository(DependencyFacet.KnownRepository.JBOSS_NEXUS))
         {
            dependencyFacet.addRepository(DependencyFacet.KnownRepository.JBOSS_NEXUS);
         }

         List<Dependency> versions = dependencyFacet.resolveAvailableVersions(seamReportsDependency);

         Dependency choosenVersion = prompt.promptChoiceTyped("Which version of Seam Reports do you want to install?",
                    versions, versions.get(versions.size() - 1));
         dependencyFacet.setProperty("seam.reports.version", choosenVersion.getVersion());

         dependencyFacet.addDependency(seamReportsDependency.setVersion("${seam.reports.version}"));
      }
      if (StringUtils.isNotBlank(provider))
      {
         dependencyFacet.addDependency(DependencyBuilder.create().setGroupId("org.jboss.seam.reports")
                     .setArtifactId("seam-reports-" + provider.toLowerCase()).setVersion("${seam.reports.version}"));
      }
   }

}
