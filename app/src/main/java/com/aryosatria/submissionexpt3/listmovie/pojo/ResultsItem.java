package com.aryosatria.submissionexpt3.listmovie.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem implements Parcelable {

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("original_title")
	private String originalTitle;

	@SerializedName("video")
	private boolean video;

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("id")
	private int id;

	@SerializedName("adult")
	private boolean adult;

	@SerializedName("vote_count")
	private int voteCount;

	protected ResultsItem(Parcel in) {
		overview = in.readString();
		originalLanguage = in.readString();
		originalTitle = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		releaseDate = in.readString();
		popularity = in.readDouble();
		voteAverage = in.readDouble();
		id = in.readInt();
		adult = in.readByte() != 0;
		voteCount = in.readInt();
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public String getTitle(){
		return title;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return
			"ResultsItem{" +
			"overview = '" + overview + '\'' +
			",original_language = '" + originalLanguage + '\'' +
			",original_title = '" + originalTitle + '\'' +
			",video = '" + video + '\'' +
			",title = '" + title + '\'' +
			",genre_ids = '" + genreIds + '\'' +
			",poster_path = '" + posterPath + '\'' +
			",backdrop_path = '" + backdropPath + '\'' +
			",release_date = '" + releaseDate + '\'' +
			",popularity = '" + popularity + '\'' +
			",vote_average = '" + voteAverage + '\'' +
			",id = '" + id + '\'' +
			",adult = '" + adult + '\'' +
			",vote_count = '" + voteCount + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(overview);
		parcel.writeString(originalLanguage);
		parcel.writeString(originalTitle);
		parcel.writeByte((byte) (video ? 1 : 0));
		parcel.writeString(title);
		parcel.writeString(posterPath);
		parcel.writeString(backdropPath);
		parcel.writeString(releaseDate);
		parcel.writeDouble(popularity);
		parcel.writeDouble(voteAverage);
		parcel.writeInt(id);
		parcel.writeByte((byte) (adult ? 1 : 0));
		parcel.writeInt(voteCount);
	}
}