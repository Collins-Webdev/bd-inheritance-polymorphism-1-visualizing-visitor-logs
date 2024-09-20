package com.amazon.ata.inheritance;

import com.amazon.ata.inheritance.model.*;

public class VisitFactory {
    public Visit createVisitFromLogEvent(final String logEvent) {
        final String[] columns = logEvent.split("\\|");
        final String visitorId = columns[1];
        final Visit visit;

        if (visitorId.startsWith("ff")) {
            visit = new TestVisit();
        } else if (visitorId.matches(".*a[0-9a-f]$")) {
            visit = new AmazonVisit();
        } else if (visitorId.matches(".*f[0-9a-f]$")) {
            if (visitorId.startsWith("f0")) {
                visit = new ZapposVisit();
            } else {
                visit = new AmazonVisit();
            }
        } else if (visitorId.equals("000000000000")) {
            visit = new Visit();
        } else {
            visit = new InvalidVisit();
        }

        return visit;
    }
}