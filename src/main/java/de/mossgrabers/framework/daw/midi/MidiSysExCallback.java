// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.daw.midi;

/**
 * Callback for receiving MIDI System exclusive messages.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public interface MidiSysExCallback
{
    /**
     * Handle received midi sysex data.
     *
     * @param data The sysex formatted in hex
     */
    void handleMidi (final String data);
}
