// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.bitwig.framework.extension;

import de.mossgrabers.framework.controller.IControllerSetup;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;


/**
 * A generic controller extension implementation, which delegates to a setup instance.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class GenericControllerExtension extends ControllerExtension
{
    private IControllerSetup setup;


    /**
     * Constructor.
     *
     * @param setup A setup
     * @param definition A definition
     * @param host The DAW host
     */
    public GenericControllerExtension (final IControllerSetup setup, final ControllerExtensionDefinition definition, final ControllerHost host)
    {
        super (definition, host);
        this.setup = setup;
    }


    /** {@inheritDoc} */
    @Override
    public void init ()
    {
        this.setup.init ();
        this.getHost ().scheduleTask (this.setup::startup, 1000);
    }


    /** {@inheritDoc} */
    @Override
    public void exit ()
    {
        this.setup.exit ();
    }


    /** {@inheritDoc} */
    @Override
    public void flush ()
    {
        this.setup.flush ();
    }
}
