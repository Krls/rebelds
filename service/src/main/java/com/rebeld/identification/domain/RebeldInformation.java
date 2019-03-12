package com.rebeld.identification.domain;

import lombok.Data;

/**
 * The information of a rebeld.
 * 
 * @author krlsMM
 */
@Data
public class RebeldInformation {
	/**
	 * The rebeld name.
	 */
	private String name;
	/**
	 * The rebeld plante. 
	 */
	private String planet;
	
	/**
	 * Default contructor
	 */
	public RebeldInformation() {
		super();
	}

	/**
	 * Constructor using fields.
	 * @param name name of rebeld
	 * @param planet rebeld's planet
	 */
	public RebeldInformation(String name, String planet) {
		super();
		this.name = name;
		this.planet = planet;
	}

	@Override
	public String toString() {
		return "RebeldInformation [name=" + name + ", planet=" + planet + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RebeldInformation other = (RebeldInformation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planet == null) {
			if (other.planet != null)
				return false;
		} else if (!planet.equals(other.planet))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((planet == null) ? 0 : planet.hashCode());
		return result;
	}
}
