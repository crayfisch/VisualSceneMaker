package de.dfki.vsm.util.evt;

import de.dfki.vsm.util.log.LOGDefaultLogger;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Gregor Mehlmann TODO: IMPLEMENT THRED POOL THAT EXECUTES THE EVENT
 * UPDATES!!!!
 */
public class EventDispatcher {

    // The Singelton Instance
    private static EventDispatcher sInstance = null;

    // The Logger Instance
    private final LOGDefaultLogger mLogger = LOGDefaultLogger.getInstance();

    // The Listener List
    private final List<EventListener> mListenerList = new CopyOnWriteArrayList<EventListener>();

    // The Timer Thread
    private static Timer mTimer;

    // Construct The Instance
    private EventDispatcher() {
    }

    // Get The Singelton Instance
    public final static synchronized EventDispatcher getInstance() {
        if (sInstance == null) {
            sInstance = new EventDispatcher();
        }

        //added pg 24.3.17 canceling Timer Thread requires a new instance
        mTimer = new Timer("EventCasterTimer");
        
        return sInstance;
    }

    // Cancel The Timer Thread
    public final synchronized void cancel() {
        mTimer.cancel();
    }

    // Add An Event Listener
    public final synchronized void register(final EventListener listener) {
        //mLogger.message("Registering '" + listener + "'");
        mListenerList.add(listener);
    }

    public final synchronized void remove(final EventListener listener) {
        //mLogger.message("Remove '" + listener + "'");
        mListenerList.remove(listener);
    }

    public final synchronized void convey(final EventObject event) {
        schedule(event, 1);
    }

    public final /*synchronized*/ void dispatch(final EventObject event) {

        for (final EventListener listener : mListenerList) {
            //mLogger.message("Conveying '" + event + "' To '" + listener + "'");
            listener.update(event);
        }

    }

    public final synchronized void schedule(final EventObject event, final long timeout) {

        // Create The Timer Task
        final TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                dispatch(event);
            }
        };
        
        try{
        // Schedule The Event
        mTimer.schedule(timer, timeout);
        } catch(final IllegalStateException exc) {
            mLogger.warning(exc.toString());
        }
    }
}
