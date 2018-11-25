package cz.lukastrnka.vat.VatRates.data;

public class Rates {

	private Double super_reduced;
	private Double reduced;
	private Double reduced1;
	private Double reduced2;
	private Double standard;
	private Double parking;

	public Rates() {
		super();
	}

	public Rates(double super_reduced, double reduced1, double reduced2, double standard, double parking) {
		super();
		this.super_reduced = super_reduced;
		this.reduced1 = reduced1;
		this.reduced2 = reduced2;
		this.standard = standard;
		this.parking = parking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parking == null) ? 0 : parking.hashCode());
		result = prime * result + ((reduced == null) ? 0 : reduced.hashCode());
		result = prime * result + ((reduced1 == null) ? 0 : reduced1.hashCode());
		result = prime * result + ((reduced2 == null) ? 0 : reduced2.hashCode());
		result = prime * result + ((standard == null) ? 0 : standard.hashCode());
		result = prime * result + ((super_reduced == null) ? 0 : super_reduced.hashCode());
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
		Rates other = (Rates) obj;
		if (parking == null) {
			if (other.parking != null)
				return false;
		} else if (!parking.equals(other.parking))
			return false;
		if (reduced == null) {
			if (other.reduced != null)
				return false;
		} else if (!reduced.equals(other.reduced))
			return false;
		if (reduced1 == null) {
			if (other.reduced1 != null)
				return false;
		} else if (!reduced1.equals(other.reduced1))
			return false;
		if (reduced2 == null) {
			if (other.reduced2 != null)
				return false;
		} else if (!reduced2.equals(other.reduced2))
			return false;
		if (standard == null) {
			if (other.standard != null)
				return false;
		} else if (!standard.equals(other.standard))
			return false;
		if (super_reduced == null) {
			if (other.super_reduced != null)
				return false;
		} else if (!super_reduced.equals(other.super_reduced))
			return false;
		return true;
	}

	public Double getSuper_reduced() {
		return super_reduced;
	}

	public void setSuper_reduced(Double super_reduced) {
		this.super_reduced = super_reduced;
	}

	public Double getReduced() {
		return reduced;
	}

	public void setReduced(Double reduced) {
		this.reduced = reduced;
	}

	public Double getReduced1() {
		return reduced1;
	}

	public void setReduced1(Double reduced1) {
		this.reduced1 = reduced1;
	}

	public Double getReduced2() {
		return reduced2;
	}

	public void setReduced2(Double reduced2) {
		this.reduced2 = reduced2;
	}

	public Double getStandard() {
		return standard;
	}

	public void setStandard(Double standard) {
		this.standard = standard;
	}

	public Double getParking() {
		return parking;
	}

	public void setParking(Double parking) {
		this.parking = parking;
	}

}
