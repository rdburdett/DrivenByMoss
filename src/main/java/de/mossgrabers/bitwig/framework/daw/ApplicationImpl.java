// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2019
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.bitwig.framework.daw;

import de.mossgrabers.framework.daw.IApplication;

import com.bitwig.extension.controller.api.Action;
import com.bitwig.extension.controller.api.ActionCategory;
import com.bitwig.extension.controller.api.Application;


/**
 * Proxy to the Bitwig Application.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class ApplicationImpl implements IApplication
{
    private Application application;


    /**
     * Constructor.
     *
     * @param application The application object
     */
    public ApplicationImpl (final Application application)
    {
        this.application = application;

        this.application.hasActiveEngine ().markInterested ();
        this.application.panelLayout ().markInterested ();
    }


    /** {@inheritDoc} */
    @Override
    public void enableObservers (final boolean enable)
    {
        this.application.hasActiveEngine ().setIsSubscribed (enable);
        this.application.panelLayout ().setIsSubscribed (enable);
    }


    /** {@inheritDoc} */
    @Override
    public boolean isEngineActive ()
    {
        return this.application.hasActiveEngine ().get ();
    }


    /** {@inheritDoc} */
    @Override
    public void setEngineActive (final boolean active)
    {
        if (active)
            this.application.activateEngine ();
        else
            this.application.deactivateEngine ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleEngineActive ()
    {
        if (this.application.hasActiveEngine ().get ())
            this.application.deactivateEngine ();
        else
            this.application.activateEngine ();
    }


    /** {@inheritDoc} */
    @Override
    public void setPanelLayout (final String panelLayout)
    {
        this.application.setPanelLayout (panelLayout);
    }


    /** {@inheritDoc} */
    @Override
    public String getPanelLayout ()
    {
        return this.application.panelLayout ().get ();
    }


    /** {@inheritDoc} */
    @Override
    public boolean isArrangeLayout ()
    {
        return PANEL_LAYOUT_ARRANGE.equals (this.getPanelLayout ());
    }


    /** {@inheritDoc} */
    @Override
    public boolean isMixerLayout ()
    {
        return PANEL_LAYOUT_MIX.equals (this.getPanelLayout ());
    }


    /** {@inheritDoc} */
    @Override
    public boolean isEditLayout ()
    {
        return PANEL_LAYOUT_EDIT.equals (this.getPanelLayout ());
    }


    /** {@inheritDoc} */
    @Override
    public boolean isPlayLayout ()
    {
        return PANEL_LAYOUT_PLAY.equals (this.getPanelLayout ());
    }


    /** {@inheritDoc} */
    @Override
    public void toggleNoteEditor ()
    {
        this.application.toggleNoteEditor ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleAutomationEditor ()
    {
        this.application.toggleAutomationEditor ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleDevices ()
    {
        this.application.toggleDevices ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleInspector ()
    {
        this.application.toggleInspector ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleMixer ()
    {
        this.application.toggleMixer ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleFullScreen ()
    {
        this.application.toggleFullScreen ();
    }


    /** {@inheritDoc} */
    @Override
    public void toggleBrowserVisibility ()
    {
        this.application.toggleBrowserVisibility ();
    }


    /** {@inheritDoc} */
    @Override
    public void duplicate ()
    {
        this.application.duplicate ();
    }


    /** {@inheritDoc} */
    @Override
    public void deleteSelection ()
    {
        this.application.remove ();
    }


    /** {@inheritDoc} */
    @Override
    public void redo ()
    {
        this.application.redo ();
    }


    /** {@inheritDoc} */
    @Override
    public void undo ()
    {
        this.application.undo ();
    }


    /** {@inheritDoc} */
    @Override
    public void addAudioTrack ()
    {
        this.application.createAudioTrack (-1);
    }


    /** {@inheritDoc} */
    @Override
    public void addEffectTrack ()
    {
        this.application.createEffectTrack (-1);
    }


    /** {@inheritDoc} */
    @Override
    public void addInstrumentTrack ()
    {
        this.application.createInstrumentTrack (-1);
    }


    /** {@inheritDoc} */
    @Override
    public void arrowKeyLeft ()
    {
        this.application.arrowKeyLeft ();
    }


    /** {@inheritDoc} */
    @Override
    public void arrowKeyUp ()
    {
        this.application.arrowKeyUp ();
    }


    /** {@inheritDoc} */
    @Override
    public void arrowKeyRight ()
    {
        this.application.arrowKeyRight ();
    }


    /** {@inheritDoc} */
    @Override
    public void arrowKeyDown ()
    {
        this.application.arrowKeyDown ();
    }


    /** {@inheritDoc} */
    @Override
    public void enter ()
    {
        this.application.enter ();
    }


    /** {@inheritDoc} */
    @Override
    public void escape ()
    {
        this.application.escape ();
    }


    /** {@inheritDoc} */
    @Override
    public void zoomOut ()
    {
        this.invokeAction ("Zoom Out");
    }


    /** {@inheritDoc} */
    @Override
    public void zoomIn ()
    {
        this.invokeAction ("Zoom In");
    }


    /** {@inheritDoc} */
    @Override
    public void incTrackHeight ()
    {
        // No track height increase method available
        this.invokeAction ("toggle_double_or_single_row_track_height");
    }


    /** {@inheritDoc} */
    @Override
    public void decTrackHeight ()
    {
        // No track height increase method available
        this.invokeAction ("toggle_double_or_single_row_track_height");
    }


    /** {@inheritDoc} */
    @Override
    public void sliceToSampler ()
    {
        this.invokeAction ("slice_to_multi_sampler_track");
    }


    /** {@inheritDoc} */
    @Override
    public void sliceToDrumMachine ()
    {
        this.invokeAction ("slice_to_drum_track");
    }


    /**
     * Invokes the action for the given action identifier.
     *
     * @param id the action identifier string, must not be `null`
     */
    public void invokeAction (final String id)
    {
        this.getAction (id).invoke ();
    }


    /**
     * Returns the action for the given action identifier.
     *
     * @param id the action identifier string, must not be `null`
     * @return The action
     */
    private Action getAction (final String id)
    {
        return this.application.getAction (id);
    }


    /**
     * Returns a list of action categories that is used by Bitwig Studio to group actions into
     * categories.
     *
     * @return All action categories
     */
    public ActionCategory [] getActionCategories ()
    {
        return this.application.getActionCategories ();
    }


    /**
     * Returns the action category associated with the given identifier.
     *
     * @param id the category identifier string, must not be `null`
     * @return The category
     */
    public ActionCategory getActionCategory (final String id)
    {
        return this.application.getActionCategory (id);
    }
}