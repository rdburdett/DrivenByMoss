package de.mossgrabers.mcu.command.trigger;

import de.mossgrabers.framework.daw.IModel;
import de.mossgrabers.framework.mode.ModeManager;
import de.mossgrabers.framework.utils.ButtonEvent;
import de.mossgrabers.mcu.MCUConfiguration;
import de.mossgrabers.mcu.controller.MCUControlSurface;
import de.mossgrabers.mcu.mode.Modes;


/**
 * A select track command which activates the volume mode temporarily.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class FaderTouchCommand extends SelectCommand
{
    /**
     * Constructor.
     *
     * @param index The channel index
     * @param model The model
     * @param surface The surface
     */
    public FaderTouchCommand (final int index, final IModel model, final MCUControlSurface surface)
    {
        super (index, model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void executeNormal (final ButtonEvent event)
    {
        final MCUConfiguration configuration = this.surface.getConfiguration ();
        if (this.index < 8)
        {
            if (configuration.useFadersAsKnobs ())
            {
                this.surface.getModeManager ().getActiveMode ().onValueKnobTouch (this.index, event == ButtonEvent.DOWN);
                return;
            }

            final ModeManager modeManager = this.surface.getModeManager ();
            if (event == ButtonEvent.DOWN)
                modeManager.setActiveMode (Modes.MODE_VOLUME);
            else if (event == ButtonEvent.UP)
                modeManager.restoreMode ();
        }

        if (configuration.isTouchChannel ())
            super.executeNormal (event);
    }
}
