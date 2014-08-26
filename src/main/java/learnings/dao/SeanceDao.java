package learnings.dao;

import java.util.Date;
import java.util.List;

import learnings.model.Seance;

public interface SeanceDao {
	public List<Seance> listerSeances();

	public List<Seance> listerTPNotesParDateRendu(Date date);
}
