/**
 * Created by sergemalo on 2017-09-04.
 */
package com.skydelsolutions.gnssreceiverexpert;
import java.util.ArrayList;
import java.util.List;


public class GnssExpert
{
    List<String> getGnssReceivers(String gnssReceiverType)
    {
        List<String> receivers = new ArrayList<String>();
        if (gnssReceiverType.equals("Mass-Market"))
        {
            receivers.add("Ublox");
            receivers.add("Qualcom");
            receivers.add("Broadcom");
        }
        else if (gnssReceiverType.equals("Professional grade"))
        {
            receivers.add("Javad");
            receivers.add("Novatel");
            receivers.add("Septentrio");
        }
        else if (gnssReceiverType.equals("Spaceborne"))
        {
            receivers.add("General Dynamics");
        }
        return receivers;
    }
}
