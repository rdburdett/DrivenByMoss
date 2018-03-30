// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.apcmini;

import de.mossgrabers.framework.configuration.AbstractConfiguration;
import de.mossgrabers.framework.configuration.IEnumSetting;
import de.mossgrabers.framework.configuration.ISettingsUI;
import de.mossgrabers.framework.controller.IValueChanger;


/**
 * The configuration settings for APC.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class APCminiConfiguration extends AbstractConfiguration
{
    /** Default function of the faders. */
    public static final Integer    FADER_CTRL         = Integer.valueOf (30);
    /** Default function of the track buttons. */
    public static final Integer    SOFT_KEYS          = Integer.valueOf (31);

    private static final String [] FADER_CTRL_OPTIONS =
    {
        "Volume",
        "Pan",
        "Send 1",
        "Send 2",
        "Send 3",
        "Send 4",
        "Send 5",
        "Send 6",
        "Send 7",
        "Send 8",
        "Device"
    };

    /** The names of the track button functions. */
    public static final String []  SOFT_KEYS_OPTIONS  =
    {
        "Clip Stop",
        "Solo",
        "Rec Arm",
        "Mute",
        "Select"
    };

    private String                 faderCtrl          = FADER_CTRL_OPTIONS[0];
    private String                 softKeys           = SOFT_KEYS_OPTIONS[0];

    private IEnumSetting           faderCtrlSetting;
    private IEnumSetting           softKeysSetting;


    /**
     * Constructor.
     *
     * @param valueChanger The value changer
     */
    public APCminiConfiguration (final IValueChanger valueChanger)
    {
        super (valueChanger);
    }


    /** {@inheritDoc} */
    @Override
    public void init (final ISettingsUI settingsUI)
    {
        ///////////////////////////
        // Play and Sequence

        this.activateQuantizeAmountSetting (settingsUI);

        ///////////////////////////
        // Scale

        this.activateScaleSetting (settingsUI);
        this.activateScaleBaseSetting (settingsUI);
        this.activateScaleInScaleSetting (settingsUI);
        this.activateScaleLayoutSetting (settingsUI);

        ///////////////////////////
        // Workflow

        this.activateBehaviourOnStopSetting (settingsUI);
        this.activateSelectClipOnLaunchSetting (settingsUI);
        this.activateNewClipLengthSetting (settingsUI);

        ///////////////////////////
        // Button Control

        this.faderCtrlSetting = settingsUI.getEnumSetting ("Fader Ctrl", "Button Control", FADER_CTRL_OPTIONS, FADER_CTRL_OPTIONS[0]);
        this.faderCtrlSetting.addValueObserver (value -> {
            this.faderCtrl = value;
            this.notifyObservers (FADER_CTRL);
        });

        this.softKeysSetting = settingsUI.getEnumSetting ("Soft Keys", "Button Control", SOFT_KEYS_OPTIONS, SOFT_KEYS_OPTIONS[0]);
        this.softKeysSetting.addValueObserver (value -> {
            this.softKeys = value;
            this.notifyObservers (SOFT_KEYS);
        });

    }


    /**
     * Set the fader control.
     *
     * @param faderCtrl The fader control
     */
    public void setFaderCtrl (final String faderCtrl)
    {
        this.faderCtrlSetting.set (faderCtrl);
    }


    /**
     * Set the track button function.
     *
     * @param softKeys The track button function
     */
    public void setSoftKeys (final String softKeys)
    {
        this.softKeysSetting.set (softKeys);
    }


    /**
     * Get the fader control.
     *
     * @return The fader control
     */
    public String getFaderCtrl ()
    {
        return this.faderCtrl;
    }


    /**
     * Get the track button function.
     *
     * @return The track button function
     */
    public String getSoftKeys ()
    {
        return this.softKeys;
    }
}
