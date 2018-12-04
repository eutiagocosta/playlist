package br.com.playlist.ports.adapters.http.climate;

public class OpenWeatherResource {

	private MainResource main;

	public OpenWeatherResource(MainResource main) {
		setMain(main);
	}
	
	@SuppressWarnings("unused")
	private OpenWeatherResource() {}

	public MainResource getMain() {
		return main;
	}

	private void setMain(MainResource main) {
		this.main = main;
	}
	
	public static class MainResource {
		
		private Integer temp;

		public MainResource(Integer temp) {
			setTemp(temp);
		}
		
		@SuppressWarnings("unused")
		private MainResource() {}

		public Integer getTemp() {
			return temp;
		}

		private void setTemp(Integer temp) {
			this.temp = temp;
		}

		@Override
		public String toString() {
			return "MainResource [temp=" + temp + "]";
		}
		
	}

	@Override
	public String toString() {
		return "OpenWeatherResource [main=" + main + "]";
	}
	
}
