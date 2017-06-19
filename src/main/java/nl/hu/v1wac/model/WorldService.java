package nl.hu.v1wac.model;

import nl.hu.v1wac.persistence.UitgaveDAO;
import nl.hu.v1wac.persistence.InkomstDAO;

import java.util.List;

public class WorldService {
	private UitgaveDAO UitgaveDAO = new UitgaveDAO();
	private InkomstDAO InkomstDAO = new InkomstDAO();

	//Uitgaves
	public List<Uitgave> getUitgaveByUserID(int id) { return UitgaveDAO.findByUserID(id);}

	public Uitgave getUitgaveById(int code) { return UitgaveDAO.findByID(code); }

	public boolean delete(Uitgave uitgave) { return UitgaveDAO.delete(uitgave); }

	public void addUitgave(Uitgave uitgave) { UitgaveDAO.save(uitgave);}

	public void updateUitgave(Uitgave uitgave) { UitgaveDAO.update(uitgave);}

	public int getUitgaveSum(int id) { return UitgaveDAO.totaalUitgave(id); }
	//Inkomsen
	public List<Inkomst> getInkomstByUserID(int id) { return InkomstDAO.findByUserID(id);}

	public boolean delete(Inkomst inkomst) { return InkomstDAO.delete(inkomst); }

	public void addInkomst(Inkomst inkomst) { InkomstDAO.save(inkomst); }

	public void updateInkomst(Inkomst inkomst) { InkomstDAO.update(inkomst); }

    public Inkomst getInkomstById(int id) { return InkomstDAO.findByID(id); }

	public int getInkomstSum(int id) { return InkomstDAO.totaalInkomst(id); }
}
