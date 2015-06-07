package cz.izar.game.event;

public class Event {
	private final String type;
	private String data;
	private int code;
	private boolean defaultPrevented;
	
	public Event(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}
	public String getData() {
		return data;
	}
	public String getType() {
		return type;
	}

	public void setCode(int code) {
		this.code = code;
	}
	public void setData(String data) {
		this.data = data;
	}


	public void preventDefault() {
		this.defaultPrevented = true;
	}
	public boolean isDefaultPrevented() {
		return this.defaultPrevented;
	}

	
	@Override
	public String toString() {
		return "Event ('"+getType()+"', "+getCode()+", '"+getData()+"')";
	}
	
	
	
	
	// PhasalEvent
	
	public enum Phase {
		PRE_PHASE,
		POST_PHASE;
		@Override
		public String toString(){
			return this == PRE_PHASE
				? "pre"
				: "post";
		}
	}
	private Phase phase = Phase.PRE_PHASE;
	
	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
}
