package org.jboss.forge.seam.reports;

import java.util.Arrays;
import java.util.List;

import org.jboss.forge.shell.completer.SimpleTokenCompleter;

/**
 * Completer for supported providers
 * 
 * @author George Gastaldi (gastaldi)
 */
public class ProvidersTokenCompleter extends SimpleTokenCompleter
{

   @Override
   public List<String> getCompletionTokens()
   {
      return Arrays.asList("JASPERREPORTS", "PENTAHO");
   }

}
