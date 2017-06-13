package nl.hu.v1wac.model;

import nl.hu.v1wac.persistence.UitgaveDAO;

import java.util.List;

public class WorldService {
	private UitgaveDAO UitgaveDAO = new UitgaveDAO();

	public List<Uitgave> getAllCountries() {
		return UitgaveDAO.findAll();
	}

	public List<Uitgave> get10LargestPopulations() { return UitgaveDAO.find10LargestPopulations(); }

	public List<Uitgave> get10LargestSurfaces() { return UitgaveDAO.find10LargestSurfaces(); }

	public List<Uitgave> getUitgaveByUserID(int code) { return UitgaveDAO.findByUserID(code);}

	public boolean delete(Uitgave country) { return UitgaveDAO.delete(country); }

	public void addCountry(Uitgave country) { UitgaveDAO.save(country);}

	public void updateCountry(Uitgave country) { UitgaveDAO.update(country);}
}
