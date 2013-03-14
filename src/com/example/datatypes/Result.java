package com.example.datatypes;

import com.google.gson.annotations.SerializedName;

public class Result {
	@SerializedName("from_usr_id_str")
	public String fromUsrIdStr;
	
	@SerializedName("profile_image_url")
	public String profileImageUrl;
	
	@SerializedName("created_at")
	public String createdAt;
	
	@SerializedName("from_user")
	public String fromUser;
	
	@SerializedName("id_str")
	public String idStr;
	
	public Metadata metadata;
	
	@SerializedName("to_usr_id")
	public String toUsrId;
	
	public String text;
	
	public long id;
	
	@SerializedName("from_usr_id")
	public String fromUsrId;
	
	@SerializedName("iso_language_code")
	public String isoLanguageCode;
	
	@SerializedName("to_usr_id_str")
	public String toUsrIdStr;
	
	public String source;
}
