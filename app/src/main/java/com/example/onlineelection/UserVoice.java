package com.example.onlineelection;

public class UserVoice {
    private String Partyname;
   private String Partyid;
   public UserVoice(){

   }

    public UserVoice(String partyname, String partyid) {
        Partyname = partyname;
        Partyid = partyid;
    }

    public String getPartyname() {
        return Partyname;
    }

    public String getPartyid() {
        return Partyid;
    }
}
