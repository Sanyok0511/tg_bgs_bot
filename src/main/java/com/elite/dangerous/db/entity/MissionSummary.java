package com.elite.dangerous.db.entity;

import java.util.Date;

public interface MissionSummary {
    String getSystemName();
    int getCompleted();
    int getAccepted();
    int getFailed();
    Date getDate();
}
