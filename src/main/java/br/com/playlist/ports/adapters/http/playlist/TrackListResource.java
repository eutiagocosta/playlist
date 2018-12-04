package br.com.playlist.ports.adapters.http.playlist;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrackListResource {

	@JsonProperty("tracks")
	private Track track;
	
	public TrackListResource(Track track) {
		this.track = track;
	}

	@SuppressWarnings("unused")
	private TrackListResource() {}
	
	public Track getTrack() {
		return track;
	}

	public void setTracks(Track track) {
		this.track = track;
	}

	public static class Track {
		
		private List<Item> items;
		
		public Track(List<Item> items) {
			this.items = items;
		}

		@SuppressWarnings("unused")
		private Track() {}
		
		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}

		public static class Item {
			
			private String name;

			public Item(String name) {
				this.name = name;
			}
			
			@SuppressWarnings("unused")
			private Item() {}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
			
		}
	}
}
