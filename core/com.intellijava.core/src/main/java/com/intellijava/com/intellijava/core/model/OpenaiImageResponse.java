package com.intellijava.com.intellijava.core.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OpenaiImageResponse extends BaseRemoteModel {


	private long created;


	private List<Data> data;

	public static class Data {
		private String url;

		public String getUrl() {
			return url;
		}
	}

	public long getCreated() {
		return created;
	}

	public List<Data> getData() {
		return data;
	}

}
