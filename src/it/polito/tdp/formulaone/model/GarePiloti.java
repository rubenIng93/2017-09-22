package it.polito.tdp.formulaone.model;

public class GarePiloti {
	
	private Integer idGara1;
	private Integer idGara2;
	private Integer pilotiAllarrivo;
	
	
	public GarePiloti(Integer idGara1, Integer idGara2, Integer pilotiAllarrivo) {
		super();
		this.idGara1 = idGara1;
		this.idGara2 = idGara2;
		this.pilotiAllarrivo = pilotiAllarrivo;
	}


	public Integer getIdGara1() {
		return idGara1;
	}


	public void setIdGara1(Integer idGara1) {
		this.idGara1 = idGara1;
	}


	public Integer getIdGara2() {
		return idGara2;
	}


	public void setIdGara2(Integer idGara2) {
		this.idGara2 = idGara2;
	}


	public Integer getPilotiAllarrivo() {
		return pilotiAllarrivo;
	}


	public void setPilotiAllarrivo(Integer pilotiAllarrivo) {
		this.pilotiAllarrivo = pilotiAllarrivo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGara1 == null) ? 0 : idGara1.hashCode());
		result = prime * result + ((idGara2 == null) ? 0 : idGara2.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GarePiloti other = (GarePiloti) obj;
		if (idGara1 == null) {
			if (other.idGara1 != null)
				return false;
		} else if (!idGara1.equals(other.idGara1))
			return false;
		if (idGara2 == null) {
			if (other.idGara2 != null)
				return false;
		} else if (!idGara2.equals(other.idGara2))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format("GarePiloti -> idGara1 = %s, idGara2 = %s, pilotiAllarrivo = %s", idGara1, idGara2,
				pilotiAllarrivo);
	}
	
	
	
	
	
	

}
