package com.example.myquickappapplication.Models;

public class MessageData {
   public String senderId;
   public String conversionID;
   public String messageId;
   public String body;
   public String timeStamp;

   public String getSenderId(){
       return senderId;
   }
   public void setSenderId(String senderId){
       this.senderId = senderId;

   }

   public String getConversionID(){
       return conversionID;
   }
   public void setConversionID(String conversionID){
       this.conversionID = conversionID;
   }

   public String getMessageId(){
       return messageId;
   }
   public void setMessageId(String messageId){
       this.messageId= messageId;
   }

   public String getTimeStamp(){
       return timeStamp;
   }
   public void setTimeStamp(String timeStamp){
       this.timeStamp=timeStamp;
   }

   public String getBody(){
       return getBody();
   }
   public void setBody(String body){
       this.body=body;
   }
}
