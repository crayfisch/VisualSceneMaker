package de.dfki.vsm.xtension.ssi.logger;

import de.dfki.vsm.model.project.PluginConfig;
import de.dfki.vsm.model.scenescript.ActionFeature;
import de.dfki.vsm.model.scenescript.ActionParam;
import de.dfki.vsm.runtime.activity.AbstractActivity;
import de.dfki.vsm.runtime.activity.SpeechActivity;
import de.dfki.vsm.runtime.activity.executor.ActivityExecutor;
import de.dfki.vsm.runtime.project.RunTimeProject;
import de.dfki.vsm.xtension.ssi.SSIEventSender;
import java.util.LinkedList;

/**
 * @author Patrick Gebhard
 * @author Gregor Mehlmann
 */
public final class SSILoggerExecutor extends ActivityExecutor {

    // The SSI receiver data
    private final String mLHost;
    private final String mLPort;
    private final String mRHost;
    private final String mRPort;
    // The feedback variable
    private final String mVar;
    // The SSI event handler
    private SSIEventSender mSender;

    // Construct executor
    public SSILoggerExecutor(
            final PluginConfig config,
            final RunTimeProject project) {
        super(config, project);
        mVar = mConfig.getProperty("var");
        mLHost = mConfig.getProperty("slhost");
        mLPort = mConfig.getProperty("slport");
        mRHost = mConfig.getProperty("srhost");
        mRPort = mConfig.getProperty("srport");
    }

    // Get marker syntax
    @Override
    public synchronized String marker(final long id) {
        return "$(" + id + ")";
    }

    @Override
    public void launch() {
        // Initialize the event sender
        mSender = new SSIEventSender(
                mLHost, Integer.parseInt(mLPort),
                mRHost, Integer.parseInt(mRPort));
        // Start the SSI event sender
        mSender.start();

    }

    @Override
    public void unload() {
        mSender.abort();
    }

    // Execute activity
    @Override
    public void execute(final AbstractActivity activity) {

        if (activity instanceof SpeechActivity) {
            final SpeechActivity speechActivity = (SpeechActivity) activity;
            final String text = speechActivity.getTextOnly("$(").trim();
            final LinkedList<String> timemarks = speechActivity.getTimeMarks("$(");
            // If text is empty - assume activity has empty text but has marker activities registered
            if (text.isEmpty()) {
                for (final String mark : timemarks) {
                    mLogger.warning("Directly executing activity at timemark " + mark);
                    mProject.getRunTimePlayer().getActivityScheduler().handle(mark);
                }
            }
        } else {
            activity.setType(AbstractActivity.Type.parallel);
            final LinkedList<ActionFeature> features = activity.getFeatures();
            final String systime = Long.toString(System.currentTimeMillis());
            // Get log message features
            final String name = activity.getName();
            final String sender = get("sender", features);
            final String event = get("event", features);
            final String state = get("state", features);
            final String time = get("time", features);
            final String content = get("content", features);
            final String duration = get("duration", features);
            // Create final event message
            final SSILoggerMessage message
                    = new SSILoggerMessage(name,
                            (sender == null ? "default" : sender),
                            (event == null ? "default" : event),
                            (state == null ? "completed" : state),
                            (time == null ? "0" : systime),
                            (duration == null ? "1" : duration),
                            (content == null ? " " : content));
            // Send the event message
            mSender.sendString(message.toString());
        }
    }


    /*
    private void send(String message) {

        mLogger.message("Sending log message: " + message);

        DatagramSocket c;
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = (message).getBytes("UTF8");

//            //Try the 255.255.255.255 first
//            try {
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), mPort);
//                c.send(sendPacket);
//               // mLogger.message(">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
//            } catch (Exception e) {
//            }
            // Broadcast the message over all the network interfaces
            String hosts = "";

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package
                    boolean packetSend = false;
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, mPort);
                        c.send(sendPacket);
                        hosts = hosts + broadcast.getHostAddress() + ", ";
                        mLogger.message("Message sent to " + broadcast.getHostAddress() + " on interface " + networkInterface.getDisplayName());
                        packetSend = true;
                    } catch (Exception e) {
                        packetSend = false;
                    }

                    if (packetSend) {
                        mProject.setVariable(mVar, new StringValue("Message successfully send"));
                    }
                }
            }

            // Close the socket
            c.close();
        } catch (IOException ex) {
            mLogger.message(ex.toString());
        }
    }*/
    // Get value of action feature
    private String get(final String name, final LinkedList<ActionFeature> features) {
        for (final ActionFeature feature : features) {
            if(feature instanceof ActionParam) {
                final String val = feature.getVal();
                
            } else if (feature.getKey().equalsIgnoreCase(name)) {
                return feature.getVal();
            }
        }
        return null;
    }
}