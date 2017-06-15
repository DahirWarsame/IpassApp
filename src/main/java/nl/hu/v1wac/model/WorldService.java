package nl.hu.v1wac.model;

import nl.hu.v1wac.persistence.UitgaveDAO;
import nl.hu.v1wac.persistence.InkomstDAO;

import java.util.List;

public class WorldService {
	private UitgaveDAO UitgaveDAO = new UitgaveDAO();
	private InkomstDAO InkomstDAO = new InkomstDAO();

	public List<Uitgave> getUitgaveByUserID(int id) { return UitgaveDAO.findByUserID(id);}

	public boolean delete(Uitgave uitgave) { return UitgaveDAO.delete(uitgave); }

	public void addUitgave(Uitgave uitgave) { UitgaveDAO.save(uitgave);}

	public void updateUitgave(Uitgave uitgave) { UitgaveDAO.update(uitgave);}

	public List<Inkomst> getInkomstByUserID(int id) { return InkomstDAO.findByUserID(id);}

	public boolean delete(Inkomst inkomst) { return InkomstDAO.delete(inkomst); }

	public void addInkomst(Inkomst inkomst) { InkomstDAO.save(inkomst);}

	public void updateInkomst(Inkomst inkomst) { InkomstDAO.update(inkomst);}
}
