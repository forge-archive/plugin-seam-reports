package org.jboss.forge.seam.reports;

import java.util.Arrays;
import java.util.List;

import org.jboss.forge.shell.completer.SimpleTokenCompleter;

public class ProvidersTokenCompleter extends SimpleTokenCompleter {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List getCompletionTokens() {
        return Arrays.asList("JASPERREPORTS", "BIRT", "PENTAHO");
    }

}
