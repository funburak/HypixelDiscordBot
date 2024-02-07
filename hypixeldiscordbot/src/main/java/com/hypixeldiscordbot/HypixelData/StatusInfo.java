package com.hypixeldiscordbot.HypixelData;

public class StatusInfo {
    private boolean success;
    private String uuid;
    private Session session;

    @Override
    public String toString() {
        return session.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private class Session{
        private boolean online;
        private String gameType;
        private String mode;
        private String map;

        @Override
        public String toString() {
            return "Online=" + online + "\nGame type=" + gameType + "\nMode=" + mode + "\nMap=" + map;
        }        
    }
}
