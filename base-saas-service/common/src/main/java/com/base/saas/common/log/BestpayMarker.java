package com.base.saas.common.log;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import org.slf4j.Marker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BestpayMarker implements Marker {
    private static final long serialVersionUID = -1387951413362902419L;
    public static final String BESTPAY_MARKER_NAME = "bestpay_marker";
    public static String TRACE_LOG_ID = "TRACE_LOG_ID";
    public static String OTHER_MSG = "OTHER_MSG";
    Map<MarkerKey, String> bestpayMarkers;

    BestpayMarker(BestpayMarker.MarkerKey markerKey, String value) {
        this(new BestpayMarker.MarkerKey[]{markerKey}, new String[]{value});
    }

    BestpayMarker(BestpayMarker.MarkerKey[] markerKeys, String[] values) {
        this.bestpayMarkers = new HashMap(markerKeys.length);
        this.setMarkerKeys(markerKeys, values);
    }

    public void setMarkerKeys(BestpayMarker.MarkerKey[] markerKeys, String[] values) {
        String[] newValues = values;
        if (values.length < markerKeys.length) {
            newValues = new String[markerKeys.length];
            System.arraycopy(values, 0, newValues, 0, values.length);
            Arrays.fill(newValues, values.length, markerKeys.length, "");
        }

        for(int i = 0; i < markerKeys.length; ++i) {
            this.bestpayMarkers.put(markerKeys[i], newValues[i]);
        }

    }

    public Map<MarkerKey, String> getBestpayMarkers() {
        return this.bestpayMarkers;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(80);
        Iterator ite = this.bestpayMarkers.keySet().iterator();

        while(ite.hasNext()) {
            BestpayMarker.MarkerKey key = (BestpayMarker.MarkerKey)ite.next();
            sb.append("\"").append(key).append("\":\"").append((String)this.bestpayMarkers.get(key)).append("\"");
            if (ite.hasNext()) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public String getName() {
        return "bestpay_marker";
    }

    /** @deprecated */
    @Deprecated
    public void add(Marker reference) {
    }

    /** @deprecated */
    @Deprecated
    public boolean remove(Marker reference) {
        return false;
    }

    /** @deprecated */
    @Deprecated
    public boolean hasChildren() {
        return false;
    }

    /** @deprecated */
    @Deprecated
    public boolean hasReferences() {
        return false;
    }

    /** @deprecated */
    @Deprecated
    public Iterator iterator() {
        return null;
    }

    /** @deprecated */
    @Deprecated
    public boolean contains(Marker other) {
        return false;
    }

    /** @deprecated */
    @Deprecated
    public boolean contains(String name) {
        return false;
    }

    public static enum MarkerKey {
        PRODUCT_NO("PRODUCT_NO"),
        AMT("AMT"),
        MARKER("MARKER"),
        EXNAME("EXNAME"),
        EXMESSAGE("EXMESSAGE"),
        EXSTACK("EXSTACK"),
        OTOD("OTOD"),
        BSTACC("BSTACC"),
        MOBACC("MOBACC"),
        BSTCDACC("BSTCDACC"),
        ODTP("ODTP"),
        TDTP("TDTP"),
        PARTNERID("PARTNERID"),
        ACCORT("ACCORT");

        String key;

        private MarkerKey(String key) {
            this.key = key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }
}
